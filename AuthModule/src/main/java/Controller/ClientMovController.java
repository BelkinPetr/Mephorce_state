package Controller;

import HibernateEntities.ClientsEntity;
import HibernateEntities.ModeratorsEntity;
import HibernateEntities.PhotosEntity;
import HibernateEntities.ProjectsEntity;
import ServiceEntites.AddInfEnt;
import ServiceEntites.SelectProject;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.enterprise.inject.Model;
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
import java.util.List;

import static DAOImplement.ClientsDao.getClientProject;
import static DAOImplement.ClientsDao.updateClient;
import static DAOImplement.FileDao.createNewPhoto;
import static DAOImplement.FileDao.getPhotoList;
import static DAOImplement.FileDao.getPhotoListforClient;
import static DAOImplement.ProjectDao.getAdminById;
import static DAOImplement.ProjectDao.getProjectById;

/**
 * Created by kinetik on 05.03.17.
 */
@Controller
@SessionAttributes("Client")
public class ClientMovController {
    private static final Logger logger = Logger.getLogger(ClientMovController.class);

    @ModelAttribute("Client")
    public ClientsEntity populateClient(@ModelAttribute("LoginClient") ClientsEntity client) {
        return client == null ? null : client;
    }

    @RequestMapping(value = "/PCabinClientControl")
    public ModelAndView clientInNextViewChecker(@ModelAttribute("LoginClient") ClientsEntity client) {
        ModelAndView clientFirstIn = new ModelAndView("inViews/firstInViews/clientFirstIn");
        try {
            if (clientValidator(client)) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            if (client.getClFirstIn() == null || client.getClFirstIn() == 0) {
                client.setClFirstIn(1);
                updateClient(client);
                clientFirstIn.addObject("addInf", new AddInfEnt());
                return clientFirstIn;
            }
            return new ModelAndView("redirect:/PCabinClient");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PCabinClient")
    public ModelAndView clientPersonalCabinetGenerator(@ModelAttribute("Client") ClientsEntity client) {
        try {
            if (clientValidator(client)) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView clientPersCabinet = new ModelAndView("inViews/persCabViews/clientPersCab");
         // ProjectsEntity project = (ProjectsEntity) getClientProject(client);
          //Integer modId=project.getMdId();
            clientPersCabinet.addObject("projectList", getClientProject(client));
            return clientPersCabinet;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PClientChangeInfo")
    public ModelAndView adminChangeInfoData(@ModelAttribute("Client") ClientsEntity client) {
        try {
            if (clientValidator(client)) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView clientChData = new ModelAndView("inViews/firstInViews/clientFirstIn");
            clientChData.addObject("addInf", new AddInfEnt());
            return clientChData;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/addInformClient")
    public ModelAndView updateClientInformation(@ModelAttribute("Client") ClientsEntity client,
                                                @ModelAttribute("addInf") AddInfEnt addInf) {
        try {
            if (clientValidator(client)) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            client.setClBirthdate(addInf.getBirthdate().trim());
            client.setClCompany(addInf.getCompany().trim());
            client.setClEmail(addInf.getE_mail().trim());
            client.setClHometown(addInf.getHometown().trim());
            client.setClPos(addInf.getPosition().trim());
            client.setClPhone(addInf.getPhone_number().trim());
            updateClient(client);
            return new ModelAndView("redirect:/PPersCabinClient");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }
    @RequestMapping(value = "/PPersCabinClient")
    public ModelAndView clientPersonalCabinet(@ModelAttribute("Client") ClientsEntity client) {

        try {
            if (clientValidator(client)) {
                logger.warn("Unauthorized access with /PPersCabinClient");
                return new ModelAndView("otherViews/accessDeniedView");
            }
            ModelAndView clientPersCabinet1 = new ModelAndView("inViews/persCabViews/ClientProfile");
            clientPersCabinet1.addObject("projectList",getClientProject(client));
            clientPersCabinet1.addObject("photoList", getPhotoListforClient(client));
            return clientPersCabinet1;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }
    private static final org.slf4j.Logger logger2 = LoggerFactory.getLogger(AdminMovController.class);
    @RequestMapping(value = "/goToProjectInfo")
    public ModelAndView projectInfo(@ModelAttribute("Client") ClientsEntity client,@RequestParam String prId) {
        try {
            ModelAndView projectInfo = new ModelAndView("CreateProjectViews/ProjectInfo");
            ProjectsEntity project = getProjectById(Integer.parseInt(prId));
            ModeratorsEntity moderator = getAdminById(project.getMdId());
            projectInfo.addObject("Project",project);
            projectInfo.addObject("Moder",moderator);
            return projectInfo;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            return new ModelAndView("otherViews/errorView");
        }
    }


    @RequestMapping(value = "/uploadPhotoforClient", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPhoto(@RequestParam("photo") MultipartFile photo, @ModelAttribute("Client") ClientsEntity client,
                              @RequestParam("clID") String clId) {
        String name = null;

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                name = photo.getOriginalFilename();
                //name = "prof_pic.jpg";
                //String rootPath = "\\Users\\Irishka\\IdeaProjects\\mephorce\\AuthModule\\target\\AuthModule\\resources\\admins";
                String rootPath = "/server/file_storage/files";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator+ client.getClientId()+ File.separator);

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
                photos.setModId(-1);
                photos.setClId(client.getClientId());
                photos.setStId(-1);
                createNewPhoto(photos);

                return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u0443\\u0441\\u043F\\u0435\\u0448\\u043D\\u043E\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D'); window.location.href = '/PPersCabinClient'; </script>";

            } catch (Exception e) {
                return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PPersCabinClient'; </script>";
            }
        } else {
            return "<script language='javascript'> alert('\\u0424\\u0430\\u0439\\u043B\\u0020\\u043D\\u0435\\u0020\\u0437\\u0430\\u0433\\u0440\\u0443\\u0436\\u0435\\u043D\\u0021'); window.location.href = '/PPersCabinClient'; </script>";
        }

    }

    @RequestMapping(value = "/downPhotoforClient")
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

    public boolean clientValidator(ClientsEntity client) {
        return client.getClientId() == null || client == null;
    }
}