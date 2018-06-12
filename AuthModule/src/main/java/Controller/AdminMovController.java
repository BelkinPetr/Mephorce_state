package Controller;

import DAOImplement.*;
import HibernateEntities.*;
import ServiceEntites.AddInfEnt;
import ServiceEntites.ProjectChanger;
import ServiceEntites.Rating;
import ServiceEntites.StudentRater;
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
import java.util.List;
import java.util.stream.Collectors;

import static DAOImplement.AdminDao.*;
import static DAOImplement.ClientsDao.getClientById;
import static DAOImplement.CommunityNewsDao.addNews;
import static DAOImplement.CommunityNewsDao.getNewsList;
import static DAOImplement.FileDao.*;
import static DAOImplement.ProjectDao.getProjectById1;
import static DAOImplement.ProjectDao.updateProject1;
import static DAOImplement.SkillDao.*;
import static DAOImplement.StudentDao.*;

/**
 * Created by kinetik on 05.03.17.
 */
@Controller
@SessionAttributes("Admin")
public class AdminMovController {

    private static final Logger logger = Logger.getLogger(AdminMovController.class);

    @ModelAttribute("Admin")
    public ModeratorsEntity populateAdmin(@ModelAttribute("Admin") ModeratorsEntity admin) {
        return admin;
    }

    @RequestMapping(value = "/PCabinAdminControl")
    public ModelAndView adminInNextViewChecker(@ModelAttribute("Admin") ModeratorsEntity admin) {
        ModelAndView adminFirstIn = new ModelAndView("inViews/firstInViews/adminFirstIn");
        adminFirstIn.addObject("Admin", admin);
        try {
            if (validateAdmin(admin)) {
                logger.warn("Unauthorized access with /PCabinAdminControl");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            if (admin.getModFirstIn() == null || admin.getModFirstIn() == 0) {
                admin.setModFirstIn(1);
                updateAdmin(admin);
                adminFirstIn.addObject("addInf", new AddInfEnt());
                return adminFirstIn;
            }
            return new ModelAndView("redirect:/PCabinAdmin");
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PCabinAdmin")
    public ModelAndView adminPersonalCabinetGenerator(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            if (validateAdmin(admin)) {
                logger.warn("Unauthorized access with /PCabinAdmin");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView adminPersCabinet = new ModelAndView("inViews/persCabViews/adminPersCab");
            adminPersCabinet.addObject("projectList", getAdminProject(admin));
            adminPersCabinet.addObject("Skills", getAdminSkillsById(admin.getModId()));
            adminPersCabinet.addObject("photoList", getPhotoList(admin));
            return adminPersCabinet;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    private static final org.slf4j.Logger logger2 = LoggerFactory.getLogger(AdminMovController.class);

    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPhoto(@RequestParam("photo") MultipartFile photo, @ModelAttribute("Admin") ModeratorsEntity admin,
                              @RequestParam("modID") String modId) {
        String name = null;

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                 name = photo.getOriginalFilename();
                //name = "prof_pic.jpg";
                //String rootPath = "\\Users\\Irishka\\IdeaProjects\\mephorce\\AuthModule\\target\\AuthModule\\resources\\admins";
                String rootPath = "/server/file_storage/files";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator+ admin.getModId()+ File.separator);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedPhoto = new File (dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedPhoto));
                stream.write(bytes);
                stream.flush();
                stream.close();

                logger2.info("uploaded: " + uploadedPhoto.getAbsolutePath());

                PhotosEntity photos = new PhotosEntity();
                 photos.setPhoto_path((dir.getAbsolutePath() + "\\" + name));
                 photos.setPhoto_name("photo");
                 photos.setClId(-1);
                 photos.setModId(admin.getModId());
                 photos.setStId(-1);
                createNewPhoto(photos);

                return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u0443\\u0441\\u043F\\u0435\\u0448\\u043D\\u043E\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D'); window.location.href = '/PCabinAdmin'; </script>";

            } catch (Exception e) {
                return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PCabinAdmin'; </script>";
            }
        } else {
            return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PCabinAdmin'; </script>";
        }

    }

    @RequestMapping(value = "/downPhoto")
    public void downloadPhoto (@RequestParam String fileName,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        Path file = Paths.get(fileName);
        if (Files.exists(file))
        {
            response.setContentType("image/jpeg");
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

    @RequestMapping(value = "/PAdminChangeInfo")
    public ModelAndView adminChangeInfoData(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            if (validateAdmin(admin)) {
                logger.warn("Unauthorized access with /PAdminChangeInfo");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView adminChData = new ModelAndView("inViews/firstInViews/adminFirstIn");
            adminChData.addObject("addInf", new AddInfEnt());
            return adminChData;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/addInformAdmin")
    public ModelAndView updateClientInformation(@ModelAttribute("Admin") ModeratorsEntity admin,
                                                @ModelAttribute("addInf") AddInfEnt addInf) {
        try {
            if (validateAdmin(admin)) {
                logger.warn("Unauthorized access with /addInformAdmin");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            admin.setModBirthdate(addInf.getBirthdate().trim());
            admin.setModEmail(addInf.getE_mail().trim());
            admin.setModHometown(addInf.getHometown().trim());
            admin.setModPhone(addInf.getPhone_number().trim());
            updateAdmin(admin);
            logger.info("Update admin info " + admin.getModFamily() + " id:" + admin.getModId());
            ModelAndView skills = new ModelAndView("inViews/firstInViews/AdminSkills");

            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(getAdminSkillList(admin.getModId()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            skills.addObject("Skills", json);
            return skills;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/setAdminFirstInSkills", method = RequestMethod.POST, consumes = "application/json")
    public ModelAndView setSkillsFirstIn(@RequestBody String skills, @ModelAttribute("Admin") ModeratorsEntity admin) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<LinkedHashMap<String, String>> arrOfparts = mapper.readValue(skills, ArrayList.class);
            HashSet<Integer> skillsForDb = new HashSet<>();
            for (LinkedHashMap<String, String> value: arrOfparts){
                skillsForDb.add(Integer.parseInt(value.get("id")));
            }
            List<UserSkill> exSkills =  getAdminSkillList(admin.getModId());
            HashSet<Integer> exSkillSet = new HashSet<>();
            for(UserSkill skill: exSkills){
                exSkillSet.add(skill.getSkilId());
            }
            exSkillSet.removeAll(skillsForDb);
            deleteAdminSkills(exSkillSet, admin.getModId());
            saveNewAdminSkillsSet(skillsForDb, admin.getModId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/PCabinAdminControl");
    }

    @RequestMapping(value = "/rateStudent")
    public ModelAndView rateStudent(@RequestParam String stId,
                                    @RequestParam String topic,
                                    @RequestParam String proj,
                                    @RequestParam String modId) {
        ModelAndView modelAndView = new ModelAndView("moderatorViews/rateStudentPage");
        int skilCatId = getCatIdByName(topic.substring(0, topic.length() - 2));
        modelAndView.addObject("studentsRating", new Rating(Integer.parseInt(stId), skilCatId));
        modelAndView.addObject("projectId", proj);
        modelAndView.addObject("StudentRater", new StudentRater());
        modelAndView.addObject("studentWasntRated",
                SkillRateLogDao.isStudentWasntRatedOnProjectByModerator(
                        Integer.parseInt(stId), Integer.parseInt(proj), Integer.parseInt(modId)
                ));
        modelAndView.addObject("modId", Integer.parseInt(modId));
        return modelAndView;
    }

    @RequestMapping(value = "/rateStudentAction")
    public ModelAndView rateStudentAction(@ModelAttribute("StudentRater") StudentRater studentRater,
                                          @RequestParam String stId,
                                          @RequestParam String catId,
                                          @RequestParam String projectId,
                                          @RequestParam String modId) {

        StudentsRating studentsRating = new StudentsRating();
        studentsRating.setStudentId(Integer.parseInt(stId));
        studentsRating.setSkillCatId(Integer.parseInt(catId));

        float A = TaskRatingDao.getStudentAcceptedTaskAmountForLast30Days(Integer.parseInt(stId)) /
                TaskRatingDao.getAverageTaskAcceptedAmountByStudents();
        List<TasksEntity> taskByCatIdList = getStTasksByStudentId(Integer.parseInt(stId));
        float sum = 0;
        for (TasksEntity task : taskByCatIdList) {
            TaskRating taskRating = TaskRatingDao.getStudentTask(task.getTaskId());
            boolean P = taskRating.isAbandonment();
            float U = ((float) taskRating.getSendToModeratorAmount()) /
                    ((float) (taskRating.getSendToModeratorAmount() + taskRating.getReturnedByModeratorAmount()));
            boolean I = taskRating.getSendToModeratorAmount() >= 1;
            int S = getTaskById(task.getTaskId()).getDifficultyLevel();
            int O = taskRating.getTaskWorkRate();

            sum += S * (10 * (Koefs.koefU * U + Koefs.koefP * (P ? 1 : 0) +
                    Koefs.koefI * (I ? 1 : 0)) + 4 * Koefs.koefO * O);
        }
        float formalRatingToSave = (float) Math.sqrt(A) * sum;

        studentsRating.setFormalRating(formalRatingToSave);

        StudentRateLogEntity studentRateLogEntity = new StudentRateLogEntity();
        studentRateLogEntity.setStudentId(Integer.parseInt(stId));
        studentRateLogEntity.setModeratorId(Integer.parseInt(modId));
        studentRateLogEntity.setSkillCatId(Integer.parseInt(catId));
        studentRateLogEntity.setProjectId(Integer.parseInt(projectId));
        studentRateLogEntity.setComment(studentRater.getComment());
        SkillRateLogDao.addStudentRateNote(studentRateLogEntity);

        StudentRatingDao.rateStudent(studentsRating, studentRater.getRating());
        return new ModelAndView("redirect:/PCabinAdmin");
    }

    @RequestMapping(value = "/PViewCabinStudent")
    public ModelAndView safeStudentFuck (@RequestParam String studentId) {
        StudentsEntity student = StudentDao.getStudentById(Integer.parseInt(studentId));
        ModelAndView mv = new ModelAndView("inViews/studentViews/studentViewForAdmin");
        mv.addObject("Student", student);
        mv.addObject("Skills", StudentRatingDao.getStudentRatingById(student.getStudentsId()));
        mv.addObject("projectList", getStudentsProject(student));
        return mv;
    }

    @RequestMapping(value = "/PPersCabinAdmin")
    public ModelAndView adminPersonalCabinet(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            if (validateAdmin(admin)) {
                logger.warn("Unauthorized access with /PPersCabinAdmin");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView adminPersCabinet1 = new ModelAndView("moderatorViews/startPageModerator");
            adminPersCabinet1.addObject("projectList", getAdminProject(admin));
            return adminPersCabinet1;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

//    @RequestMapping(value = "/PmainWindowProjectAdmin")
//    public ModelAndView mainWindowProjectAdmin(@ModelAttribute("Admin") ModeratorsEntity admin) {
//        try {
//            if (validateAdmin(admin)) {
//                logger.warn("Unauthorized access with /PmainWindowProjectAdmin");
//                return new ModelAndView("otherViews/accessDeniedView");
//            }
//            ModelAndView adminPersCabinet2 = new ModelAndView("moderatorViews/mainWindowProject");
//            adminPersCabinet2.addObject("projectList", getAdminProject(admin));
//            return adminPersCabinet2;
//        } catch (Exception ex) {
//            logger.error(ex.getStackTrace());
//            return new ModelAndView("otherViews/errorView");
//        }
//    }

    @RequestMapping(value = "/PmainWindowProjectAdmin")
    public ModelAndView mainWindowProjectAdmin(@ModelAttribute("Admin") ModeratorsEntity admin, @RequestParam String proj) {
        try {
            String moneyDivision;
            String topics;
            int projId = Integer.parseInt(proj);
            List<ProjectsEntity> projects = getAdminProject(admin).stream()
                    .filter(projectsEntity -> projectsEntity.getPrId()==projId)
                    .collect(Collectors.toList());
            ProjectsEntity project = projects.get(0);
            ModelAndView adminProjects = new ModelAndView("moderatorViews/mainWindowProject");
            List<StudentsEntity> slavesList = getSlavesFaces(projId);
            if (project.getPersonNumber()==null) {
                project.setPersonNumber(20);
            }
            if(project.getMoneyDivision()==null) {
                project.setMoneyDivision(13);
            }
            adminProjects.addObject("complection",
                    project.getPersonNumber()>slavesList.size() ? "Не укомлектован" : "Укомплектован");
            adminProjects.addObject("slavesNumber", slavesList.size());
            switch(project.getMoneyDivision()){
                case 0:
                    moneyDivision = "Поровну";
                    break;
                case 1:
                    moneyDivision = "По коэффициенту трудового участия";
                    break;
                case 2:
                    moneyDivision = "По первым десяти местам";
                    break;
                case 3:
                    moneyDivision = "По результатам работы";
                    break;
                default:
                    moneyDivision = "Не установлено";
                    break;
            }
            List<SkillCatEntity> projectSkills = getProjectSkills(projId);
            if(projectSkills==null || projectSkills.size()==0){
                topics = "Не установлены";
            }else {
                topics = "";
                for(SkillCatEntity cat: projectSkills) {
                    topics +=cat.getCatName()+", ";
                }
            }
            adminProjects.addObject("topic", topics);
            adminProjects.addObject("project", project);
            adminProjects.addObject("projectList", getAdminProject(admin));
            adminProjects.addObject("Admin", admin);
            adminProjects.addObject("Client", getClientById(project.getClId()));
            adminProjects.addObject("Slaves", slavesList);
            adminProjects.addObject("moneyDivision", moneyDivision);
            adminProjects.addObject("ProjectChanger", new ProjectChanger());
            return adminProjects;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/changeProjectInfoByAdmin1")
    public ModelAndView changeProjectInfo1(@ModelAttribute ProjectChanger projectChanger, @RequestParam String prId){
        int projectId = Integer.parseInt(prId);
        ProjectsEntity project = AdminDao.getProjectById(projectId);
        project.setPersonNumber(projectChanger.getNumberOfPeople());
        updateProject(project);
        ModelAndView res = new ModelAndView("redirect:/PmainWindowProjectAdmin?proj="+prId);
        return res;
    }

    @RequestMapping(value = "/changeProjectInfoByAdmin2")
    public ModelAndView changeProjectInfo2(@ModelAttribute ProjectChanger projectChanger, @RequestParam String prId){
        int projectId = Integer.parseInt(prId);
        ProjectsEntity project = AdminDao.getProjectById(projectId);
        project.setMoneyDivision(projectChanger.getMoneyDiv());
        updateProject(project);
        ModelAndView res = new ModelAndView("redirect:/PmainWindowProjectAdmin?proj="+prId);
        return res;
    }

    private boolean validateAdmin(ModeratorsEntity admin) {
        return admin == null || admin.getModId() == null;
    }

    @RequestMapping(value = "/PAdminInvitation")
    public ModelAndView goToInvitation(@ModelAttribute("Admin") ModeratorsEntity admin, @RequestParam String proj){
        try {
            int projId = Integer.parseInt(proj);
            List<ProjectsEntity> projects = getAdminProject(admin).stream()
                    .filter(projectsEntity -> projectsEntity.getPrId()==projId)
                    .collect(Collectors.toList());
            ProjectsEntity project = projects.get(0);
            List<StudentsEntity> blackList = getList(projId, -1);
            List<StudentsEntity> acceptList = getList(projId, 1);
            List<StudentsEntity> denyList = getList(projId, 0);
            List<StudentsEntity> noAnswerList = getList(projId, 2);
            List<StudentsEntity> potentialCandidates = getSkilledStudents(projId);
            List<StudentsEntity> slavesList = getSlavesFaces(projId);
            if(blackList!=null && potentialCandidates!=null) {
                potentialCandidates.removeAll(blackList);
            }
            if(acceptList!=null && potentialCandidates!=null) {
                potentialCandidates.removeAll(acceptList);
            }
            if(denyList!=null && potentialCandidates!=null) {
                potentialCandidates.removeAll(denyList);
            }
            if(noAnswerList!=null && potentialCandidates!=null) {
                potentialCandidates.removeAll(noAnswerList);
            }
            ModelAndView invitationPart = new ModelAndView("moderatorViews/invitationStudentFromModerator");
            invitationPart.addObject("project", projId);
            invitationPart.addObject("project1", project);
            invitationPart.addObject("potentialList", potentialCandidates);
            invitationPart.addObject("blackList", blackList);
            invitationPart.addObject("acceptList", acceptList);
            invitationPart.addObject("denyList", denyList);
            invitationPart.addObject("noAnswerList",noAnswerList);
            invitationPart.addObject("Slaves", slavesList);
            invitationPart.addObject("slavesNumber", slavesList.size());
            return invitationPart;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/inviteStudentAdmin")
    public ModelAndView inviteStudent(@RequestParam String stId, @RequestParam String prId){
        saveInvite(Integer.parseInt(prId), Integer.parseInt(stId), 2);
        return new ModelAndView("redirect:/PAdminInvitation?proj="+prId);
    }

    @RequestMapping(value = "/addStudentToBlackList")
    public ModelAndView studentToBlackList(@RequestParam String stId, @RequestParam String prId){
        saveInvite(Integer.parseInt(prId), Integer.parseInt(stId), -1);
        return new ModelAndView("redirect:/PAdminInvitation?proj="+prId);
    }

    @RequestMapping(value = "/addStudentToProject")
    public ModelAndView studentToProjectController(@RequestParam String stId, @RequestParam String prId){
        saveInvite(Integer.parseInt(prId), Integer.parseInt(stId), 3);
        addStudentToProject(Integer.parseInt(prId), Integer.parseInt(stId));
        return new ModelAndView("redirect:/PAdminInvitation?proj="+prId);
    }

    @RequestMapping(value = "/getStudentAdmin")
    public ModelAndView studentCard(@RequestParam String stId, @RequestParam String prId){
        StudentsEntity student = getStudentById(Integer.parseInt(stId));
        ModelAndView stCard = new ModelAndView("moderatorViews/studentCard");
        stCard.addObject("Student", student);
        stCard.addObject("projectId", Integer.parseInt(prId));
        return stCard;
    }

    @RequestMapping(value = "/denyStudent")
    public ModelAndView denyStudentToProject(@RequestParam String stId, @RequestParam String prId){
        saveInvite(Integer.parseInt(prId), Integer.parseInt(stId), 0);
        return new ModelAndView("redirect:/PAdminInvitation?proj="+prId);
    }

    @RequestMapping(value = "/PCommunityNewsAdmin")
    public ModelAndView PCommunityAdminM(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            ModelAndView adminNews = new ModelAndView("Community/PageCommunityNewsAdmin");
            adminNews.addObject("newsList", getNewsList());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PCommunityAddNews")
    public ModelAndView PCommunityAddNews(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            ModelAndView adminNews = new ModelAndView("Community/AddNews");
            adminNews.addObject("projectsList", getAdminProject(admin).stream()
                    .map(ProjectsEntity::getTitle).collect(Collectors.toList()));
            adminNews.addObject("News", new NewsEntity());
            adminNews.addObject("creator", admin.getModFamily());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/AddNewsAdmin")
    public ModelAndView createNews(@ModelAttribute("News") NewsEntity news) {
        try {
            if (news == null) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            addNews(news);
            ModelAndView model = new ModelAndView("Community/PageCommunityNewsAdmin");
            model.addObject("newsList", getNewsList());
            return model;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PCommunityRegulations")
    public ModelAndView PCommunityRegulations(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            ModelAndView adminNews = new ModelAndView("Community/Regulations");
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PClickOnTheNew")
    public ModelAndView PClickOnTheNew(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            ModelAndView adminNews = new ModelAndView("Community/ClickOnTheNew");
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }
    @RequestMapping(value = "/GoToInvite")
    public ModelAndView ClientInvite(@ModelAttribute("Admin") ModeratorsEntity admin) {
        try {
            List<ProjectsEntity> projectslist=getListofProjects(admin.getModId(),2);
            ModelAndView adminInvitation = new ModelAndView("CreateProjectViews/InvitationtoAdmin");
            adminInvitation.addObject("Admin", admin);
            adminInvitation.addObject("projectList",projectslist);


           // adminInvitation.addObject("Invitation",);

            return adminInvitation;
        } catch (Exception ex){
            return  new ModelAndView("otherViews/errorView");

        }
    }
    @RequestMapping(value="/AcceptInvitation")
    public ModelAndView AcceptInvite(@ModelAttribute("Admin") ModeratorsEntity admin,@RequestParam int prId){
    saveInvitation(prId,admin.getModId(), 1);
        ProjectsEntity project=getProjectById1(prId);
        project.setStatus("рассмотрен");
        updateProject1(project);
        return new ModelAndView("redirect:/GoToInvite?modId="+admin.getModId());
    }
    @RequestMapping(value="/RejectInvitation")
    public ModelAndView RejectInvite(@ModelAttribute("Admin") ModeratorsEntity admin,@RequestParam int prId){
        saveInvitation(prId,admin.getModId(), 0);
        return new ModelAndView("redirect:/GoToInvite?modId="+admin.getModId());
    }


    @RequestMapping(value = "/PFileAdmin")
    public ModelAndView PFileAdmin(@ModelAttribute("Admin") ModeratorsEntity admin,
                                   @RequestParam String projId) {
        try {
            ProjectsEntity project = AdminDao.getProjectById(Integer.parseInt(projId));
            ModelAndView adminFiles = new ModelAndView("FileViews/PageFileAdmin");
            adminFiles.addObject("fileList", getFileList(project));
            adminFiles.addObject("Admin", admin);
            adminFiles.addObject("projId", Integer.parseInt(projId));
            return adminFiles;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }
    private static final org.slf4j.Logger logger1 = LoggerFactory.getLogger(AdminMovController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute("Admin") ModeratorsEntity admin,
                             @RequestParam("prID") String projId) {
        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();
                String rootPath = "/server/file_storage/files";
              //  String rootPath = "\\Users\\Irishka\\IdeaProjects\\mephorce\\AuthModule\\src\\main\\webapp\\resources\\";
                //String rootPath = "\\mephorce-dev\\";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles"+ projId);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);


                if (uploadedFile.exists()) {

                    return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u0443\\u0436\\u0435\\u0020\\u0441\\u0443\\u0449\\u0435\\u0441\\u0442\\u0432\\u0443\\u0435\\u0442\\u0021'); window.location.href = '/PFileAdmin?projId=" + projId + "'; </script>";
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
                        if (admin != null) {
                            fileen.setModId(admin.getModId());
                            fileen.setClId(-1);
                            fileen.setStId(-1);


                            fileen.setPrId((Integer.parseInt(projId)));
                        }
                    } catch (Exception e) {
                    }

                    createNewFile(fileen);
                    return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u0443\\u0441\\u043F\\u0435\\u0448\\u043D\\u043E\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D'); window.location.href = '/PFileAdmin?projId=" + projId + "'; </script>";

                }
            } catch (Exception e) {
                return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PFileAdmin?projId=" + projId + "'; </script>";
            }
        } else {
            return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PFileAdmin?projId=" + projId + "'; </script>";
        }

    }
    @RequestMapping(value = "/down")

    public void download(@RequestParam String fileName,
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

    private class Koefs {
        final static float koefU = 0.5F;
        final static float koefO = 0.25F;
        final static float koefP = 0.13F;
        final static float koefI = 0.12F;
    }
}
