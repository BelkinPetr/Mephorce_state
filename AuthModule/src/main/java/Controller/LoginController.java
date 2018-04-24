package Controller;


import HibernateEntities.*;
import ServiceEntites.LoginUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import static DAOImplement.AdminDao.getAdminList;
import static DAOImplement.AdminDao.getAdminMap;
import static DAOImplement.ClientsDao.getClientList;
import static DAOImplement.ClientsDao.getClientMap;
import static DAOImplement.StudentDao.getStudentsMap;
import static DAOImplement.UsersDao.getUserNameList;
import static DAOImplement.UsersDao.getUserObjectList;


/**
 * Created by kinetik on 25.02.17.
 */
@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @ModelAttribute("Client")
    public ClientsEntity populateClient(@ModelAttribute("Client") ClientsEntity client) {
        if (client.getClientId() != null) {
            logger.info("Client logout " + client.getClFamily() + " id:" + client.getClientId());
        }
        return null;
    }
    @ModelAttribute("Student")
    public StudentsEntity populateStudent(@ModelAttribute("Student") StudentsEntity student) {
        if (student.getStudentsId() != null) {
            logger.info("Student logout " + student.getStFamily() + " id:" + student.getStudentsId());
        }
        return null;
    }
    @ModelAttribute("Admin")
    public ModeratorsEntity populateAdmin(@ModelAttribute("Admin") ModeratorsEntity admin) {
        if (admin.getModId()!= null) {
            logger.info("Admin logout " + admin.getModFamily() + " id:" + admin.getModId());
        }
        return null;
    }


    ModelAndView mvAdmin = new ModelAndView("loginViews/adminBar");
    ModelAndView mvClient = new ModelAndView("loginViews/ownerBar");
    ModelAndView mvStudent = new ModelAndView("loginViews/studentBar");
    ModelAndView mvUsers = new ModelAndView("loginViews/index");

    @RequestMapping(value = {"/", "welcome"}, method = RequestMethod.GET)
    public ModelAndView main() {
        try {
            mvUsers.addObject("userList", getUserNameList());
            mvUsers.addObject("userJSP", new LoginUser());
            logger.info("New session is ready");
            return mvUsers;
        } catch (Exception ex){
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }
    //Метод осуществляет проверку первого уровня аутентификации. Комментарий про мапу аналогичен след методу - надо подумать+посовещаться
    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") LoginUser user) {
        if (validateUser(user)) {
            logger.warn("Unauthorized access with /check-user");
            return new ModelAndView("otherViews/accessDeniedView");
        }
        logger.info("Access demand: " + user.getName());
        HashMap<String, UsersEntity> userDict = getUserObjectList();
        try {
            if(userDict.get(user.getName()).getUserPassword().equals(user.getPassword())){
                if(Objects.equals(user.getName(), "модератор")){
                    ArrayList<String> adminList = getAdminList();
                    mvAdmin.addObject("adminLogin", new LoginUser());
                    mvAdmin.addObject("adminList", adminList);
                    mvUsers.addObject("message","");
                    logger.info("Login user to admin");
                    return mvAdmin;
                } else if(Objects.equals(user.getName(), "заказчик")){
                    ArrayList<String> clientList = getClientList();
                    mvClient.addObject("clientLogin", new LoginUser());
                    mvClient.addObject("clientList", clientList);
                    mvUsers.addObject("message","");
                    logger.info("Login user to client");
                    return mvClient;
                } else {
                    mvUsers.addObject("message","");
                    mvStudent.addObject("studentLogin", new LoginUser());
                    logger.info("Login user to student");
                    return mvStudent;
                }
            } else {
                mvUsers.addObject("message","Неверный пароль");
                logger.info("Bad password access for " + user.getName());
            }
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }

        return mvUsers;
    }
    @RequestMapping(value = "/checkClient")
    public ModelAndView checkClient(@ModelAttribute("clientLogin") LoginUser client, RedirectAttributes redir){
        if (validateUser(client)) {
            logger.warn("Unauthorized access with /checkClient");
            return new ModelAndView("otherViews/accessDeniedView");
        }
        try {
            HashMap<String, ClientsEntity> clientAll = getClientMap();
            if (clientAll.containsKey(client.getName())) {
                if ((clientAll.get(client.getName()).getClPassword()).equals(client.getPassword().trim())) {
                    mvClient.addObject("message", "");
                } else {
                    mvClient.addObject("message", "Неверный пароль");
                    logger.info("Access denied for client " + client.getName());
                    return mvClient;
                }
            } else {
                logger.warn("Bad login for client");
                mvClient.addObject("message", "Неверный логин");
                return mvClient;
            }
            ClientsEntity clientForTrans = clientAll.get(client.getName());
            redir.addFlashAttribute("LoginClient", clientForTrans);
            logger.info("Successful login for client " + client.getName());
            return new ModelAndView("redirect:/PCabinClientControl");
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }
    @RequestMapping(value = "/checkAdmin")
    public ModelAndView checkAdmin(@ModelAttribute("adminLogin") LoginUser admin, RedirectAttributes redir){
        if (validateUser(admin)) {
            logger.warn("Unauthorized access with /checkAdmin");
            return new ModelAndView("otherViews/accessDeniedView");
        }
        try {
            HashMap<String, ModeratorsEntity> adminMap = getAdminMap();
            if (adminMap.containsKey(admin.getName())) {
                if ((adminMap.get(admin.getName()).getModPassword()).equals(admin.getPassword().trim())) {
                    mvAdmin.addObject("message", "");
                } else {
                    mvAdmin.addObject("message", "Bad password");
                    logger.info("Access denied for admin " + admin.getName());
                    return mvAdmin;
                }
            } else {
                logger.warn("Bad login for admin");
                mvAdmin.addObject("message", "Bad login");
                return mvAdmin;
            }
            ModeratorsEntity adminForTrans = adminMap.get(admin.getName());
            redir.addFlashAttribute("Admin", adminForTrans);
            logger.info("Successful login for admin " + admin.getName());
            return new ModelAndView("redirect:/PCabinAdminControl");
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/checkStudent")
    public ModelAndView checkStudent(@ModelAttribute("studentLogin")  LoginUser student, RedirectAttributes redir){
        if (validateUser(student)) {
            logger.warn("Unauthorized access with /checkStudent");
            return new ModelAndView("otherViews/accessDeniedView");
        }
        try {
            HashMap<Integer, StudentsEntity> studentAll = getStudentsMap();
            if (studentAll.containsKey(Integer.parseInt(student.getName()))) {
                if (studentAll.get(Integer.parseInt(student.getName())).getStPkNumber().equals(student.getPassword().trim())) {
                    mvStudent.addObject("message", "");
                } else {
                    logger.info("Access denied for client " + student.getName());
                    mvStudent.addObject("message", "Неверный пароль");
                    return mvStudent;
                }
            } else {
                logger.warn("Bad login for student");
                mvStudent.addObject("message", "Неверный логин");
                return mvStudent;
            }
            StudentsEntity studentForRedir = studentAll.get(Integer.parseInt(student.getName()));
            redir.addFlashAttribute("Student", studentForRedir);
            logger.info("Successful login for student " + student.getName());
            return new ModelAndView("redirect:/PCabinStudentControl");
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }

    }
    @RequestMapping(value="/aboutUs")
    public ModelAndView aboutUs(){
        return new ModelAndView("otherViews/aboutPage");
    }
    @RequestMapping(value="/contacts")
    public ModelAndView contacts(){
        return new ModelAndView("otherViews/contactPage");
    }

    private boolean validateUser(LoginUser user) {
        return user == null || user.getName() == null;
    }
}