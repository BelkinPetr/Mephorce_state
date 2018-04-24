package Controller;
import DAOImplement.StudentDao;
import DAOImplement.StudentRatingDao;
import HibernateEntities.*;
import ServiceEntites.AddInfEnt;
import ServiceEntites.TextField;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.UserSkill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import static DAOImplement.FileDao.createNewFile;
import static DAOImplement.FileDao.getFileList;
import static DAOImplement.SkillDao.*;
import static DAOImplement.StudentDao.*;

/**
 * Created by kinetik on 06.03.17.
 */
@Controller
@SessionAttributes("Student")
public class StudentMovController {
    //Создание логгера для логгирование происходящих событий
    private static final Logger logger = Logger.getLogger(StudentMovController.class);

    @ModelAttribute("Student")
    public StudentsEntity populateStudent(@ModelAttribute("Student") StudentsEntity student) {
        return student;
    }

    @RequestMapping(value = "/PCabinStudentControl")
    public ModelAndView adminInNextViewChecker(@ModelAttribute("Student") StudentsEntity student) {
        ModelAndView studentFirstIn = new ModelAndView("inViews/firstInViews/studentFirstIn");
        studentFirstIn.addObject("Student", student);
        try {
            if (validateStudent(student)) {
                logger.warn("Unauthorized access with /PCabinStudentControl");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            if (student.getStFirstIn() == null || student.getStFirstIn() == 0) {
                student.setStFirstIn(1);
                updateStudent(student);
                studentFirstIn.addObject("addInf", new AddInfEnt());
                return studentFirstIn;
            }
            return new ModelAndView("redirect:/PCabinStudent");
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PStudentChangeInfo")
    public ModelAndView adminChangeInfoData(@ModelAttribute("Student") StudentsEntity student) {
        try {
            if (validateStudent(student)) {
                logger.warn("Unauthorized access with /PStudentChangeInfo");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView studentChData = new ModelAndView("inViews/firstInViews/studentFirstIn");
            studentChData.addObject("addInf", new AddInfEnt());
            return studentChData;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/addInformStudent")
    public ModelAndView updateClientInformation(@ModelAttribute("Student") StudentsEntity student,
                                                @ModelAttribute("addInf") AddInfEnt addInf) {
        try {
            if (validateStudent(student)) {
                logger.warn("Unauthorized access with /addInformStudent");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            student.setStBirthdate(addInf.getBirthdate().trim());
            student.setStEmail(addInf.getE_mail().trim());
            student.setStHometown(addInf.getHometown().trim());
            student.setStPhone(addInf.getPhone_number().trim());
            updateStudent(student);
            logger.info("Update admin info " + student.getStFamily() + " id:" + student.getStudentsId());
            ModelAndView studentSkills = new ModelAndView("inViews/firstInViews/studentSkills");

            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(getStudentListSkillsById(student.getStudentsId()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            studentSkills.addObject("Skills", json);
            return studentSkills;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }


    //Метод осущетсвляет переход на профиль студента
    @RequestMapping(value = "/PCabinStudent")
    public ModelAndView safeStudentFuck (@ModelAttribute("Student") StudentsEntity student) {
        if (validateStudent(student)) {
            logger.warn("Unauthorized access with /PProfileStudent");
            return new ModelAndView("otherViews/accessDeniedView");
        }
        ModelAndView mv = new ModelAndView("inViews/persCabViews/studentPersCab");
        mv.addObject("Student", student);
        mv.addObject("Skills", StudentRatingDao.getStudentRatingById(student.getStudentsId()));
        mv.addObject("projectList", getStudentsProject(student));
        return mv;
    }
    //    //Метод осуществляет переход на страницу и возможность просмотра проектов студентов, в которых он участвует
     //сразу после авторизации
  /*   @RequestMapping(value="/PCabinStudentControl")
     public ModelAndView studentsProject(@ModelAttribute("Student") StudentsEntity student) {
        ModelAndView studProj = new ModelAndView("inViews/persCabViews/studentProjects");
        studProj.addObject("projects", getStudentsProject(student));
        return studProj;
  }*/
    //Переход на страницу проектов студентов через ссылку в основном меню
    @RequestMapping(value="/PStudentsProjects")
    public ModelAndView studentsProjects(@ModelAttribute("Student") StudentsEntity student){
        ModelAndView studPro = new ModelAndView("inViews/persCabViews/studentProjects");
        studPro.addObject("projects", getStudentsProject(student));
        return studPro;
    }
    //метод перехода на главное окно проекта, первоначально появляются задачи студента по выбранному проекту
    @RequestMapping(value = "/PMainWindowProject")
    public ModelAndView windowProject (@ModelAttribute("Student") StudentsEntity student,
                                       @RequestParam String prId) {
        ModelAndView windProj = new ModelAndView("inViews/persCabViews/mainWindowProject");
        windProj.addObject("prId", prId);
        windProj.addObject("Project", getProjectById(Integer.parseInt(prId)));
        return windProj;
    }
    //метод перехода на задачи исполнителя по выбранному проекту
    @RequestMapping(value = "/PStudentTasks")
    public ModelAndView studentTasks (@ModelAttribute("Student") StudentsEntity student, @RequestParam String prId) {
        int projId = Integer.parseInt(prId);
        ModelAndView stTask = new ModelAndView("inViews/studentViews/studentTasks");
        stTask.addObject("prId", prId);
        stTask.addObject("Tasks", getStTaskByProject(student.getStudentsId(), projId));
        stTask.addObject("SemanticTasks", getStSemTaskByProject(student.getStudentsId(), projId));
        stTask.addObject("Project", getProjectById(Integer.parseInt(prId)));
        return stTask;
    }
    //метод перехода на главное окно решения задачи
    @RequestMapping(value = "/PMainWindowTask")
    public ModelAndView winOfTask (@ModelAttribute("Student") StudentsEntity student,
                                   @RequestParam String taskId) {
        ModelAndView winOfTask = new ModelAndView("inViews/studentViews/mainWindowTask");
        int taskIdInt = Integer.parseInt(taskId);
        TasksEntity task = getTaskById(taskIdInt);
        String statusTask;
        switch(task.getStatusModer()){
            case 0:
                statusTask = "Не отправлен на проверку";
                break;
            case 1:
                statusTask = "Отправлено. Ожидает проверки";
                break;
            case 2:
                statusTask = "Прошло проверку";
                break;
            default:
                statusTask = "Статус неопределен";
                break;
        }
        winOfTask.addObject("TaskModer", statusTask);
        winOfTask.addObject("Task", task);
        winOfTask.addObject("TextField", new TextField());
        return winOfTask;
    }

    @RequestMapping(value = "/PMainWindowSemTask")
    public ModelAndView winOfSemTask (@ModelAttribute("Student") StudentsEntity student,
                                      @RequestParam String taskId) {
        ModelAndView winOfSemTask = new ModelAndView("inViews/studentViews/mainWindowSemTask");
        int taskIdInt = Integer.parseInt(taskId);
        SemantictasksEntity task = getSemTaskById(taskIdInt);
        String statusTask;
        switch(task.getStatusModer()){
            case 0:
                statusTask = "Не отправлен на проверку";
                break;
            case 1:
                statusTask = "Отправлено. Ожидает проверки";
                break;
            case 2:
                statusTask = "Прошло проверку";
                break;
            default:
                statusTask = "Статус неопределен";
                break;
        }
        winOfSemTask.addObject("SemTaskModer", statusTask);
        winOfSemTask.addObject("SemTask", task);
        winOfSemTask.addObject("TextField", new TextField());
        return winOfSemTask;
    }

    //метод сохранения обновления решения задачи
    @RequestMapping(value = "/saveTask")
    public ModelAndView saveTask(@ModelAttribute("TaskField") TextField textField,
                                 @RequestParam String taskId) {
        TasksEntity task = getTaskById(Integer.parseInt(taskId));
        task.setResults(textField.getResult());
        updateTask(task);
        return new ModelAndView("redirect:/PMainWindowTask?taskId="+taskId);
    }

    @RequestMapping(value = "/saveSemTask")
    public ModelAndView saveSemTask(@ModelAttribute("TaskField") TextField textField,
                                    @RequestParam String taskId) {
        SemantictasksEntity semtask = getSemTaskById(Integer.parseInt(taskId));
        semtask.setResults(textField.getResult());
        updateSemTask(semtask);
        return new ModelAndView("redirect:/PMainWindowSemTask?taskId="+taskId);
    }



    @RequestMapping(value = "/sendToModer")
    public ModelAndView sendTaskToModer(@RequestParam String taskId){
        TasksEntity task = getTaskById(Integer.parseInt(taskId));
        task.setStatusModer(1);
        updateTask(task);
        return new ModelAndView("redirect:/PMainWindowTask?taskId="+taskId);
    }

    @RequestMapping(value = "/sendToModer1")
    public ModelAndView sendSemTaskToModer(@RequestParam String taskId){
        SemantictasksEntity semtask = getSemTaskById(Integer.parseInt(taskId));
        semtask.setStatusModer(1);
        updateSemTask(semtask);
        return new ModelAndView("redirect:/PMainWindowSemTask?taskId="+taskId);
    }



    //метод перехода на проектные задачи
    @RequestMapping(value = "/PProjectTasks")
    public ModelAndView prTasks (@ModelAttribute("Student") StudentsEntity student,
                                 @RequestParam String prId) {
        ModelAndView prTasks = new ModelAndView("inViews/studentViews/projectTasks");
        prTasks.addObject("prId", prId);
        prTasks.addObject("Tasks", getTaskByProject(Integer.parseInt(prId)));
        prTasks.addObject("SemanticTasks", getSemTaskByProject(Integer.parseInt(prId)));
        return prTasks;
    }
    //метод для перехода на Семантику проекта - конспект
    @RequestMapping(value = "/PConspect")
    public ModelAndView conspect (@ModelAttribute("Project") StudentsEntity student,
                                  @RequestParam  String prId) {
        ModelAndView conspect = new ModelAndView("inViews/studentViews/conspect");
        conspect.addObject("Student", student);
        conspect.addObject("prId", Integer.parseInt(prId));
        return conspect;
    }
    //метод перехода на Cемантику проекта - словарь
    @RequestMapping(value = "/PDictionary")
    public ModelAndView slovar (@ModelAttribute("Project") StudentsEntity student,
                                @RequestParam String prId) {
        ModelAndView slovar = new ModelAndView("inViews/studentViews/dictionary");
        slovar.addObject("Project", student);
        slovar.addObject("prId", Integer.parseInt(prId));
        return slovar;
    }

    @RequestMapping(value = "/PInstrumenty")
    public ModelAndView instrum (@ModelAttribute("Student") StudentsEntity student,
                                 @RequestParam String prId) {
        ModelAndView instrum = new ModelAndView("inViews/studentViews/instrumenty");
        instrum.addObject("Student", student);
        instrum.addObject("prId", prId);
        return instrum;
    }

    @RequestMapping(value = "/PKriterii")
    public ModelAndView krit (@ModelAttribute("Student") StudentsEntity student,
                              @RequestParam String prId) {
        ModelAndView krit = new ModelAndView("inViews/studentViews/kriterii");
        krit.addObject("Student", student);
        krit.addObject("prId", prId);
        return krit;
    }

    @RequestMapping(value = "/PNorms")
    public ModelAndView norm (@ModelAttribute("Student") StudentsEntity student,
                              @RequestParam String prId) {
        ModelAndView norm = new ModelAndView("inViews/studentViews/norms");
        norm.addObject("Student", student);
        norm.addObject("prId", prId);
        return norm;
    }

    @RequestMapping(value = "/PStarttext")
    public ModelAndView stText (@ModelAttribute("Student") StudentsEntity student,
                                @RequestParam String prId) {
        ModelAndView stText = new ModelAndView("inViews/studentViews/starttext");
        stText.addObject("Student", student);
        stText.addObject("prId", prId);
        return stText;
    }



    @RequestMapping(value = "/setStudentFirstInSkills", method = RequestMethod.POST, consumes = "application/json")
    public ModelAndView setSkillsFirstIn(@RequestBody String skills, @ModelAttribute("Student") StudentsEntity student) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<LinkedHashMap<String, String>> arrOfparts = mapper.readValue(skills, ArrayList.class);
            HashSet<Integer> skillsForDb = new HashSet<>();
            for (LinkedHashMap<String, String> value: arrOfparts){
                skillsForDb.add(Integer.parseInt(value.get("id")));
            }
            HashSet<Integer> exSkillsSet = new HashSet<>();
            for(UserSkill skill: getStudentListSkillsById(student.getStudentsId())){
                exSkillsSet.add(skill.getSkilId());
            }
            exSkillsSet.removeAll(skillsForDb);
            deleteNewStudentSkillsSet(exSkillsSet, student.getStudentsId());
            saveNewStudentSkillsSet(skillsForDb, student.getStudentsId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/PCabinStudentControl");
    }

    private boolean validateStudent(StudentsEntity student) {
        return student == null || student.getStudentsId() == null;
    }

    @RequestMapping(value = "/PFileStudent")
    public ModelAndView PFileStudent (@ModelAttribute("Student") StudentsEntity student,
                                      @RequestParam String prId) {
        try {
            ProjectsEntity project = StudentDao.getProjectById(Integer.parseInt(prId));
            ModelAndView studentFiles = new ModelAndView("FileViews/PageFileStudent");
            studentFiles.addObject("fileList", getFileList(project));
            studentFiles.addObject("Student", student);
            studentFiles.addObject("prId", Integer.parseInt(prId));
            return studentFiles;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }
    private static final org.slf4j.Logger logger1 = LoggerFactory.getLogger(AdminMovController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileStudent (@RequestParam("file") MultipartFile file, @ModelAttribute("Student") StudentsEntity student,
                                     @RequestParam("prID") String prId) {
        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();
                String rootPath = "/server/file_storage/files";
                //String rootPath = "\\mephorce-dev\\";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles"+ prId);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);


                if (uploadedFile.exists()) {

                    return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u0443\\u0436\\u0435\\u0020\\u0441\\u0443\\u0449\\u0435\\u0441\\u0442\\u0432\\u0443\\u0435\\u0442\\u0021'); window.location.href = '/PFileStudent?projId=" + prId + "'; </script>";
                } else {

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();

                    logger1.info("uploaded: " + uploadedFile.getAbsolutePath());


                    FilesEntity fileen = new FilesEntity();
                    fileen.setFile_name(name);
                    fileen.setFile_path((dir.getAbsolutePath() + "\\" + name));
                    try {
                        if (student != null) {
                            fileen.setStId(student.getStudentsId());
                            fileen.setClId(-1);
                            fileen.setModId(-1);


                            fileen.setPrId((Integer.parseInt(prId)));
                        }
                    } catch (Exception e) {
                    }

                    createNewFile(fileen);
                    return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u0443\\u0441\\u043F\\u0435\\u0448\\u043D\\u043E\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D'); window.location.href = '/PFileStudent?prId=" + prId + "'; </script>";

                }
            } catch (Exception e) {
                return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PFileStudent?prId=" + prId + "'; </script>";
            }
        } else {
            return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PFileStudent?prId=" + prId + "'; </script>";
        }

    }
    @RequestMapping(value = "/downStudent")

    public void downloadFile (@RequestParam String fileName,
                              HttpServletRequest request,
                              HttpServletResponse response)
    {
        //If user is not authorized - he should be thrown out from here itself

        //Authorized user will download the file

        Path file = Paths.get(fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/loadFiles");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}