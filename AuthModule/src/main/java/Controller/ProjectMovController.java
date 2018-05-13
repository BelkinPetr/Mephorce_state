package Controller;

import DAOImplement.ProjectDao;
import HibernateEntities.ClientsEntity;
import HibernateEntities.MdPrEntity;
import HibernateEntities.ModeratorsEntity;
import HibernateEntities.ProjectsEntity;
import ServiceEntites.AddProject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static DAOImplement.AdminDao.getListofProjects;
import static DAOImplement.AdminDao.getProjectById;
import static DAOImplement.AdminDao.updateProject;
import static DAOImplement.ProjectDao.*;

/**
 * Created by postgres on 03.04.2017.
 */
@Controller
@SessionAttributes("Client")
public class ProjectMovController {

    ModelAndView mvCrProject = new ModelAndView("CreateProjectViews/CrProject");
    ModelAndView mvProject = new ModelAndView("CreateProjectViews/Projects");
    ModelAndView mvInvite = new ModelAndView("CreateProjectViews/Invite");

    @RequestMapping(value = "/goToProjectAppender")
    public ModelAndView regProjectLink() {
        ModelAndView mvCrProject = new ModelAndView("CreateProjectViews/CrProject");
        mvCrProject.addObject("Adder", new AddProject());
        return mvCrProject;
    }

    @RequestMapping(value = "/CreateProject")
    public ModelAndView regNewProject(@ModelAttribute("Adder") AddProject addProject,
                                      @ModelAttribute("Client") ClientsEntity client) {
        mvCrProject = new ModelAndView("CreateProjectViews/CrProject");
        boolean errors = false;
        try {
            if (addProject.getCltitle().trim().equals("")) {
                mvCrProject.addObject("Tittle", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (addProject.getCldescription().trim().equals("")) {
                mvCrProject.addObject("Description", "Это поле обязательно для заполнения");
                errors = true;
            }

         /*   if (addProject.getClstatus().trim().equals("")) {
                mvCrProject.addObject("Status", "Это поле обязательно для заполнения");
                errors = true;
            }*/
            if (addProject.getCldateOfReady().equals("")) {
                mvCrProject.addObject("DateOfReady", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (addProject.getClsum().equals("")) {
                mvCrProject.addObject("Sum", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (addProject.getCldateOfCreation().equals("")) {
                mvCrProject.addObject("DateOfCreation", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (addProject.getClworktypes().trim().equals("")) {
                mvCrProject.addObject("WorkTypes", "Это поле обязательно для заполнения");
                errors = true;
            }
            if (errors) {
                return mvCrProject;
            } else {
                ProjectsEntity projectsEntity = new ProjectsEntity();
                projectsEntity.setTitle(addProject.getCltitle().trim());
                projectsEntity.setDescription(addProject.getCldescription().trim());
                projectsEntity.setClId(client.getClientId());
               // projectsEntity.setStatus(addProject.getClstatus().trim());
                projectsEntity.setDateOfCreation(addProject.getCldateOfCreation());
                projectsEntity.setDateOfReady(addProject.getCldateOfReady());
                projectsEntity.setSum(addProject.getClsum());
                projectsEntity.setWorktypes(addProject.getClworktypes());
                createNewProject(projectsEntity);
                return new ModelAndView("redirect:/PCabinClient");
            }
        } catch (Exception ex) {
            ModelAndView errorView = new ModelAndView("otherViews/errorView");
            errorView.addObject("message", ex.toString());
            return errorView;
        }
    }

    @RequestMapping(value = "/goToProject")
    public ModelAndView clientChangeProjectData(@ModelAttribute("Client") ClientsEntity client,
                                                @RequestParam String prId) {
        try {
            ModelAndView clientchprdata = new ModelAndView("CreateProjectViews/Projects");
            ProjectsEntity project = getProjectById(Integer.parseInt(prId));
            AddProject addInfo = new AddProject(project);
            clientchprdata.addObject("addInfo", new AddProject());
            clientchprdata.addObject("Project", project);
            return clientchprdata;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }


    @RequestMapping(value = "/loadModer", method = RequestMethod.GET)
    @ResponseBody
    public ModeratorsEntity loadModer(@RequestParam(value = "moderName", required = true) String moderName, @RequestParam(value="moderId",required = true) Integer modId) throws IllegalStateException {
        try {

            ModeratorsEntity moder = getAdminByName(moderName);
            ModeratorsEntity moderId =getAdminById(modId);

            return moder;
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/addInformProjectNew")
    public ModelAndView updateClientInformation(@ModelAttribute("addInfo") AddProject addInf,
                                                @RequestParam String prId) {
        try {
            ProjectsEntity projectsEntity = getProjectById(Integer.parseInt(prId));
            projectsEntity.setTitle(addInf.getCltitle().trim());
            projectsEntity.setDescription(addInf.getCldescription().trim());
            projectsEntity.setDateOfCreation(addInf.getCldateOfCreation());
            projectsEntity.setStatus(addInf.getClstatus().trim());
            projectsEntity.setSum(addInf.getClsum());
            projectsEntity.setDateOfReady(addInf.getCldateOfReady());
            projectsEntity.setWorktypes(addInf.getClworktypes());
            updateProject1(projectsEntity);
            return new ModelAndView("redirect:/PCabinClient");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping(value = "/PClientInvitation")
    public ModelAndView goToInvitation(@ModelAttribute ClientsEntity client, @RequestParam String prId) {
        int projId = Integer.parseInt(prId);
        ProjectsEntity project = getProjectById(projId);
     //   List<ModeratorsEntity> blackList = AdminDao.getList(projId, -1);
        List<ModeratorsEntity> denyList = ProjectDao.getList(projId, 0);
        List<ModeratorsEntity> acceptList = ProjectDao.getList(projId, 1);
      //  List<ModeratorsEntity> noAnswerList = AdminDao.getList(projId, 2)
        List<ModeratorsEntity> potentialCandidates = getSkilledModerators(projId);
        /*if(blackList!=null && potentialCandidates!=null) {
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
        }*/
        ModelAndView invitationPart = new ModelAndView("CreateProjectViews/Invite");
        invitationPart.addObject("project", projId);
        invitationPart.addObject("potentialList", potentialCandidates);
       //invitationPart.addObject("blackList", blackList);
        invitationPart.addObject("acceptList", acceptList);
        invitationPart.addObject("denyList", denyList);
        //invitationPart.addObject("noAnswerList",noAnswerList);*/
        return invitationPart;
    }
    @RequestMapping(value = "/inviteAdminClient")
    public ModelAndView inviteModerator(@RequestParam String modId, @RequestParam String prId) {

        saveInvite(Integer.parseInt(prId), Integer.parseInt(modId), 2);
        ProjectsEntity project=getProjectById(Integer.valueOf(prId));
        project.setStatus("на рассмотрении");
        updateProject1(project);
        return new ModelAndView("redirect:/PClientInvitation?prId=" + prId);
    }
    @RequestMapping(value = "/getClientAdmin")
    public ModelAndView studentCard(@RequestParam String modId, @RequestParam String prId){
        ModeratorsEntity moderator = getAdminById(Integer.parseInt(modId));
        ModelAndView stCard = new ModelAndView("CreateProjectViews/moderatorCard");
        stCard.addObject("moderator", moderator);
        stCard.addObject("projectId", Integer.parseInt(prId));
        return stCard;
    }
   @RequestMapping(value = "/addModeratorToProject")
    public ModelAndView clientToProjectController(@RequestParam Integer prId) {
       try {
           List<ModeratorsEntity> acceptprojectslist=getListofProjectsandModerators(prId,1);
           List<ModeratorsEntity> rejectprojectslist=getListofProjectsandModerators(prId,0);
           ModelAndView adminInvitation = new ModelAndView("CreateProjectViews/Acception");
           adminInvitation.addObject("accept",acceptprojectslist );
           adminInvitation.addObject("projectList",rejectprojectslist);
           adminInvitation.addObject("project",getProjectById(prId));


           // adminInvitation.addObject("Invitation",);

           return adminInvitation;
       } catch (Exception ex){
           return  new ModelAndView("otherViews/errorView");

       }
   }

   @RequestMapping(value = "/SubmitModerator")
           public ModelAndView acceptModerator(@RequestParam int prId, @RequestParam Integer modId){
   ProjectDao.saveInvite(prId,modId, 3);
   addModeratorToProject(prId, modId);
       ProjectsEntity project=getProjectById(prId);
       project.setMdId(modId);
       project.setStatus("укомплектован");
       updateProject1(project);
   return new ModelAndView("redirect:/addModeratorToProject?prId="+prId);
   }
    @RequestMapping(value = "/denyModerator")
    public ModelAndView denyStudentToProject(@RequestParam String modId, @RequestParam String prId){
        saveInvite(Integer.parseInt(prId), Integer.parseInt(modId), 0);
        return new ModelAndView("redirect:/addModeratorToProject?prId="+prId);
    }
    @RequestMapping(value = "/addModeratorToBlackList")
    public ModelAndView studentToBlackList(@RequestParam String modId, @RequestParam String prId){
        saveInvite(Integer.parseInt(prId), Integer.parseInt(modId), -1);
        return new ModelAndView("redirect:/addModeratorToProject?prId="+prId);
    }

    //   ProjectDao.saveInvite(Integer.parseInt(prId), Integer.parseInt(ClId), 3);
       // addModeratorToProject(Integer.parseInt(prId), Integer.parseInt(CLId));
        //return new ModelAndView("redirect:/PClientInvitation?proj="+prId);
    //}
}