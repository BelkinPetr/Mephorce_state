package Controller;

import DAO.RegulationsDAO;
import DAOImplement.ClientsDao;
import DAOImplement.StudentDao;
import HibernateEntities.*;
import dto.RegulationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.stream.Collectors;

import static DAOImplement.AdminDao.getAdminProject;


@Controller
@SessionAttributes({"Admin", "Client", "Student"})
@RequestMapping("/regulations")
public class RegulationsController {

    @Autowired
    private Converter<RegulationDTO, RegulationEntity> regulationConverter;

    @Autowired
    private RegulationsDAO regulationsDAO;

    @GetMapping("/add-admin")
    public ModelAndView addRegulationPageAdmin(@ModelAttribute("Admin")ModeratorsEntity admin){
        ModelAndView modelAndView = new ModelAndView("Community/AddRegulation");
        RegulationDTO dto = new RegulationDTO();
        dto.setAuthor(admin.getModFirstName() + " " + admin.getModSecName() + " " + admin.getModFamily());
        modelAndView.addObject("regulation", new RegulationDTO());
        modelAndView.addObject("projects", getAdminProject(admin)
                .stream()
                .map(ProjectsEntity::getTitle)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        modelAndView.addObject("action", "add-admin");
        return modelAndView;
    }

    @GetMapping("/add-client")
    public ModelAndView addRegulationPageClient(@ModelAttribute("Client") ClientsEntity client){
        ModelAndView modelAndView = new ModelAndView("Community/AddRegulation");
        RegulationDTO dto = new RegulationDTO();
        dto.setAuthor(client.getClFirstName() + " " + client.getClSecName() + " " + client.getClFamily());
        modelAndView.addObject("regulation", new RegulationDTO());
        modelAndView.addObject("projects", ClientsDao.getClientProject(client)
                .stream()
                .map(ProjectsEntity::getTitle)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        modelAndView.addObject("action", "add-client");
        return modelAndView;
    }

    @GetMapping("/add-student")
    public ModelAndView addRegulationPageStudent(@ModelAttribute("Student") StudentsEntity student){
        ModelAndView modelAndView = new ModelAndView("Community/AddRegulation");
        RegulationDTO dto = new RegulationDTO();
        dto.setAuthor(student.getStFirstName() + " " + student.getStSecName() + " " + student.getStFamily());
        modelAndView.addObject("regulation", new RegulationDTO());
        modelAndView.addObject("projects", StudentDao.getStudentsProject(student)
                .stream()
                .map(ProjectsEntity::getTitle)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        modelAndView.addObject("action", "add-student");
        return modelAndView;
    }

    @PostMapping("/add-admin")
    public ModelAndView addRegulationAdmin(@ModelAttribute("regulation") RegulationDTO dto){
        RegulationEntity regulation = regulationConverter.convert(dto);
        regulationsDAO.createRegulation(regulation);
        return new ModelAndView("redirect:/regulations/?role=admin");
    }

    @PostMapping("/add-client")
    public ModelAndView addRegulationClient(@ModelAttribute("regulation") RegulationDTO dto){
        RegulationEntity regulation = regulationConverter.convert(dto);
        regulationsDAO.createRegulation(regulation);
        return new ModelAndView("redirect:/regulations/?role=client");
    }

    @PostMapping("/add-student")
    public ModelAndView addRegulationStudent(@ModelAttribute("regulation") RegulationDTO dto){
        RegulationEntity regulation = regulationConverter.convert(dto);
        regulationsDAO.createRegulation(regulation);
        return new ModelAndView("redirect:/regulations/?role=student");
    }


    @RequestMapping(value = "/")
    public ModelAndView PCommunityRegulations(@RequestParam("role") String role) {
        try {
            ModelAndView adminNews;
            switch (role){
                case "admin": adminNews = new ModelAndView("Community/RegulationsAdmin");
                    break;
                case "client": adminNews = new ModelAndView("Community/RegulationsClient");
                    break;
                case "student": adminNews = new ModelAndView("Community/RegulationsStudent");
                    break;
                default:
                    adminNews = new ModelAndView("Community/RegulationsStudent");
            }
            adminNews.addObject("regulations", regulationsDAO.getRegulations());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }



    @RequestMapping("/approve/{id}")
    public ModelAndView approve(@PathVariable Long id, @RequestParam("role") String role){
        regulationsDAO.setStatusById(id, RegulationEntity.Status.APPROVED);
        return new ModelAndView("redirect:/regulations/?role=" + role);
    }

    @RequestMapping("/decline/{id}")
    public ModelAndView decline(@PathVariable Long id, @RequestParam("role") String role){
        regulationsDAO.setStatusById(id, RegulationEntity.Status.DECLINED);
        return new ModelAndView("redirect:/regulations/?role=" + role);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id,  @RequestParam("role") String role){
        regulationsDAO.removeRegulation(id);
        return new ModelAndView("redirect:/regulations/?role=" + role);
    }
}
