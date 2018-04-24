package Controller;

import HibernateEntities.*;
import ServiceEntites.RegStudent;
import ServiceEntites.RegUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static DAOImplement.AdminDao.checkIsAdmin;
import static DAOImplement.AdminDao.createNewAdmin;
import static DAOImplement.ClientsDao.checkIsClient;
import static DAOImplement.ClientsDao.createNewClient;
import static DAOImplement.GroupDao.getGroupList;
import static DAOImplement.StudentDao.checkIsStudent;
import static DAOImplement.StudentDao.createNewStudent;

/**
 * Created by kinetik on 27.02.17.
 */
@Controller
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    ModelAndView mvUsers = new ModelAndView("registrationViews/registrationSuccess");
    ModelAndView mvRegAdmin = new ModelAndView("registrationViews/regAdminPage");
    ModelAndView mvRegClient = new ModelAndView("registrationViews/regClientPage");
    ModelAndView mvRegStudent = new ModelAndView("registrationViews/regStudentPage");

    @RequestMapping(value = "/registrate")
    public ModelAndView registrationUser() {
        return new ModelAndView("registrationViews/registration");
    }

    @RequestMapping(value = "/regStudent")
    public ModelAndView regStudentLink() {
        try {
            List<GroupsEntity> groupList = getGroupList();
            mvRegStudent.addObject("groupList", groupList);
            mvRegStudent.addObject("student", new RegStudent());
            return mvRegStudent;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/regClient")
    public ModelAndView regClientLink() {
        mvRegClient.addObject("user", new RegUser());
        return mvRegClient;
    }

    @RequestMapping(value = "/regAdmin")
    public ModelAndView regAdminLink() {
        mvRegAdmin.addObject("user", new RegUser());
        return mvRegAdmin;
    }

    @RequestMapping(value = "/regStudentAction")
    public ModelAndView regStudentAction(@ModelAttribute("student") RegStudent student, RedirectAttributes redir) {
        mvRegStudent = new ModelAndView("registrationViews/regStudentPage");
        boolean errors = false;
        try {
            if (student.getThirdName().trim().equals("")) {
                mvRegStudent.addObject("famMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (student.getFirstName().trim().equals("")) {
                mvRegStudent.addObject("nameMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (student.getSecondName().trim().equals("")) {
                mvRegStudent.addObject("secNameMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (student.getPassword().trim().equals("")) {
                mvRegStudent.addObject("passMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (student.getGroupId().trim().equals("")) {
                mvRegStudent.addObject("groupMessage", "Выберите группу");
                errors = true;
            }
            if (!student.getPassword().trim().equals(student.getConfPassword().trim())) {
                mvRegStudent.addObject("confPassMessage", "Пароли должны совпадать");
                errors = true;
            }
            if (errors) {
                return mvRegStudent;
            } else {
                StudentsEntity studentForBase = new StudentsEntity();
                studentForBase.setStFirstName(student.getFirstName().trim());
                studentForBase.setStSecName(student.getSecondName().trim());
                studentForBase.setStFamily(student.getThirdName().trim());
                studentForBase.setStGroup(Integer.parseInt(student.getGroupId()));
                studentForBase.setStPkNumber(student.getPassword());
                if (checkIsStudent(studentForBase)) {
                    mvRegStudent.addObject("errMessage", "Пользователь с такими данными уже зарегистрирован в системе");
                    return mvRegStudent;
                } else {
                    logger.info("Student registration " + student.getThirdName() + " group:" + student.getGroupId());
                    redir.addFlashAttribute("Student", createNewStudent(studentForBase));
                    ModelAndView nextStep = new ModelAndView("redirect:/PCabinStudentControl");
                    return nextStep;
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            ModelAndView errorView = new ModelAndView("otherViews/errorView");
            errorView.addObject("message", ex.toString());
            return errorView;
        }
    }

    @RequestMapping(value = "/regClientAction")
    public ModelAndView regClientAction(@ModelAttribute("user") RegUser user, RedirectAttributes redir) {
        mvRegClient = new ModelAndView("registrationViews/regClientPage");
        boolean errors = false;
        try {
            if (user.getThirdName().trim().equals("")) {
                mvRegClient.addObject("famMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (user.getFirstName().trim().equals("")) {
                mvRegClient.addObject("nameMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (user.getSecName().trim().equals("")) {
                mvRegClient.addObject("secNameMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (user.getPassword().trim().equals("")) {
                mvRegClient.addObject("passMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (!user.getConfPassword().trim().equals(user.getPassword().trim())) {
                mvRegClient.addObject("confPassMessage", "Пароли должны совпадать");
                errors = true;
            }
            if (errors) {
                return mvRegClient;
            } else {
                ClientsEntity client = new ClientsEntity();
                client.setClFamily(user.getThirdName().trim());
                client.setClFirstName(user.getFirstName().trim());
                client.setClSecName(user.getSecName().trim());
                client.setClPassword(user.getPassword().trim());
                if (checkIsClient(client)) {
                    mvRegClient.addObject("errMessage", "Пользовтаель с такими данными зарегистрирован в системе");
                    return mvRegClient;
                } else {
                    logger.info("Client registration " + client.getClFamily());
                    redir.addFlashAttribute("LoginClient", createNewClient(client));
                    ModelAndView nextStep = new ModelAndView("redirect:/PCabinClientControl");
                    return nextStep;
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            ModelAndView errorView = new ModelAndView("otherViews/errorView");
            errorView.addObject("message", ex.toString());
            return errorView;
        }
    }

    @RequestMapping(value = "/regAdminAction")
    public ModelAndView regAdminAction(@ModelAttribute("user") RegUser user, RedirectAttributes redir) {
        mvRegAdmin = new ModelAndView("registrationViews/regAdminPage");
        boolean errors = false;
        try {
            if (user.getThirdName().trim().equals("")) {
                mvRegAdmin.addObject("famMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (user.getFirstName().trim().equals("")) {
                mvRegAdmin.addObject("nameMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (user.getSecName().trim().equals("")) {
                mvRegAdmin.addObject("secNameMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (user.getPassword().trim().equals("")) {
                mvRegAdmin.addObject("passMessage", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (!user.getConfPassword().trim().equals(user.getPassword().trim())) {
                mvRegAdmin.addObject("confPassMessage", "Пароли должны совпадать");
                errors = true;
            }
            if (errors) {
                return mvRegAdmin;
            } else {
                ModeratorsEntity admin = new ModeratorsEntity();
                admin.setModFamily(user.getThirdName().trim());
                admin.setModFirstName(user.getFirstName().trim());
                admin.setModSecName(user.getSecName().trim());
                admin.setModPassword(user.getPassword().trim());
                if (checkIsAdmin(admin)) {
                    mvRegAdmin.addObject("errMessage", "Пользователь с такими данными зарегистрирован в системе");
                    return mvRegAdmin;
                } else {
                    logger.info("Admin registration " + admin.getModFamily());
                    redir.addFlashAttribute("Admin", createNewAdmin(admin));
                    ModelAndView nextStep = new ModelAndView("redirect:/PCabinAdminControl");
                    return nextStep;
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            ModelAndView errorView = new ModelAndView("otherViews/errorView");
            errorView.addObject("message", ex.toString());
            return errorView;
        }
    }
}