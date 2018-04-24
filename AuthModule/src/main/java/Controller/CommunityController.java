package Controller;

import DAO.CommentDAO;
import DAOImplement.ClientsDao;
import DAOImplement.CommunityNewsDao;
import DAOImplement.StudentDao;
import HibernateEntities.*;
import dto.CommentDTO;
import dto.NewsDTO;
import exceptions.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static DAOImplement.AdminDao.getAdminProject;
import static DAOImplement.CommunityNewsDao.addNews;
import static DAOImplement.CommunityNewsDao.getNewsList;

@Controller
@RequestMapping("/community")
@SessionAttributes({"Admin", " Client", "Student"})
public class CommunityController {

    @Autowired
    private Converter<NewsDTO, NewsEntity> newsConverter;

    @Autowired
    private Converter<CommentDTO, CommentEntity> commentConverter;

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping("/")
    public ModelAndView communityPage(@RequestParam("role") String role){
        try {
            ModelAndView adminNews;
            switch (role){
                case "admin" : adminNews = new ModelAndView("Community/PageCommunityNewsAdmin");
                    break;
                case "client" : adminNews = new ModelAndView("Community/PageCommunityNewsClient");
                    break;
                case "student" : adminNews = new ModelAndView("Community/PageCommunityNewsStudent");
                    break;
                default: adminNews = new ModelAndView("Community/PageCommunityNewsAdmin");
            }
            adminNews.addObject("newsList", getNewsList());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping("/news/add-admin")
    public ModelAndView PCommunityAddNewsAdmin(@ModelAttribute("Admin") ModeratorsEntity admin, ModelAndView adminNews) {
        try {
            adminNews.setViewName("Community/AddNewsAdmin");
            adminNews.addObject("projects", getAdminProject(admin)
                    .stream()
                    .map(ProjectsEntity::getTitle)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
            adminNews.addObject("News", new NewsDTO());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping("/news/add-client")
    public ModelAndView PCommunityAddNewsClient(@ModelAttribute("Client") ClientsEntity client, ModelAndView adminNews) {
        try {
            adminNews.setViewName("Community/AddNewsClient");
            adminNews.addObject("projects", ClientsDao.getClientProject(client)
                    .stream()
                    .map(ProjectsEntity::getTitle)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
            adminNews.addObject("News", new NewsDTO());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @RequestMapping("/news/add-student")
    public ModelAndView PCommunityAddNewsClient(@ModelAttribute("Student") StudentsEntity student, ModelAndView adminNews) {
        try {
            adminNews.setViewName("Community/AddNewsStudent");
            adminNews.addObject("projects", StudentDao.getStudentsProject(student)
                    .stream()
                    .map(ProjectsEntity::getTitle)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
            adminNews.addObject("News", new NewsDTO());
            return adminNews;
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @PostMapping(value = "/news/add-admin")
    public ModelAndView createNewsAdmin(@ModelAttribute("Admin") ModeratorsEntity admin,
                                   @ModelAttribute("News") NewsDTO news) {
        try {
            if (news == null) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            news.setCreator(admin.getModFirstName() + " " + admin.getModSecName() + " " + admin.getModFamily());
            addNews(newsConverter.convert(news));
            return new ModelAndView("redirect:/community/?role=admin");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @PostMapping(value = "/news/add-client")
    public ModelAndView createNewsClient(@ModelAttribute("Client") ClientsEntity client,
                                   @ModelAttribute("News") NewsDTO news) {
        try {
            if (news == null) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            news.setCreator(client.getClFirstName() + " " + client.getClSecName() + " " + client.getClFamily());
            addNews(newsConverter.convert(news));
            return new ModelAndView("redirect:/community/?role=client");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @PostMapping(value = "/news/add-student")
    public ModelAndView createNews(@ModelAttribute("Student") StudentsEntity student,
                                   @ModelAttribute("News") NewsDTO news) {
        try {
            if (news == null) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            news.setCreator(student.getStFirstName() + " " + student.getStSecName() + " " + student.getStFamily());
            addNews(newsConverter.convert(news));
            return new ModelAndView("redirect:/community/?role=students");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }


    @RequestMapping(value = "/news/{newsID}")
    public ModelAndView PClickOnTheNew(@PathVariable Integer newsID, @RequestParam("role") String role) {
        ModelAndView adminNews;
        switch (role){
            case "admin": adminNews = new ModelAndView("Community/ClickOnTheNewAdmin");
                break;
            case "client": adminNews = new ModelAndView("Community/ClickOnTheNewClient");
                break;
            case "student": adminNews = new ModelAndView("Community/ClickOnTheNewStudent");
                break;
                default: adminNews = new ModelAndView("Community/ClickOnTheNewAdmin");
        }
        Optional<NewsEntity> newsEntityOptional = CommunityNewsDao.getNewsById(newsID);
        adminNews.addObject("news",
                newsEntityOptional.orElseThrow(PageNotFoundException::new));
        return adminNews;
    }

    @PostMapping(value = "/news/{newsID}/comments_add-admin")
    public ModelAndView createCommentAdmin(@ModelAttribute("new_comment") CommentDTO dto,
                                      @PathVariable("newsID") Integer newsID,
                              @ModelAttribute("Admin") ModeratorsEntity admin){
        dto.setNewsID(newsID);
        dto.setAuthor(admin.getModFirstName() + " " + admin.getModSecName() + " " + admin.getModFamily());
        CommentEntity entity = commentConverter.convert(dto);
        commentDAO.createComment(entity);
        return new ModelAndView("redirect:/community/news/{newsID}?role=admin");
    }

    @PostMapping(value = "/news/{newsID}/comments_add-client")
    public ModelAndView createCommentClient(@ModelAttribute("new_comment") CommentDTO dto,
                                      @PathVariable("newsID") Integer newsID,
                                      @ModelAttribute("Client") ClientsEntity client){
        dto.setNewsID(newsID);
        dto.setAuthor(client.getClFirstName() + " " + client.getClSecName() + " " + client.getClFamily());
        CommentEntity entity = commentConverter.convert(dto);
        commentDAO.createComment(entity);
        return new ModelAndView("redirect:/community/news/{newsID}?role=client");
    }

    @PostMapping(value = "/news/{newsID}/comments_add-student")
    public ModelAndView createCommentClient(@ModelAttribute("new_comment") CommentDTO dto,
                                            @PathVariable("newsID") Integer newsID,
                                            @ModelAttribute("Student") StudentsEntity student){
        dto.setNewsID(newsID);
        dto.setAuthor(student.getStFirstName() + " " + student.getStSecName() + " " + student.getStFamily());
        CommentEntity entity = commentConverter.convert(dto);
        commentDAO.createComment(entity);
        return new ModelAndView("redirect:/community/news/{newsID}?role=student");
    }

}
