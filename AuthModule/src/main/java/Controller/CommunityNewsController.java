package Controller;

import HibernateEntities.NewsEntity;
import ServiceEntites.NewsCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import static DAOImplement.CommunityNewsDao.addNews;
import static DAOImplement.CommunityNewsDao.getNewsList;
import static DAOImplement.CommunityNewsDao.updateNews;

/**
 * @author Nastya Golubeva
 */
@Controller
@SessionAttributes("News")
public class CommunityNewsController {

    @PostMapping(value = "/add-news")
    public ModelAndView createNews(@ModelAttribute("News") NewsEntity news) {
        try {
            if (news == null) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            addNews(news);
            return new ModelAndView("redirect:/PageCommunityNewsAdmin");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @PutMapping(value = "/updateNews")
    public ModelAndView changeNews(@ModelAttribute("News") NewsEntity news) {
        ModelAndView newsModel = new ModelAndView("Community/UpdateNews");
        newsModel.addObject("News", news);
        try {
            if (news == null) {
                return new ModelAndView("otherViews/accessDeniedView");
            }
            updateNews(news);
            return new ModelAndView("redirect:/PageCommunityNewsAdmin");
        } catch (Exception ex) {
            return new ModelAndView("otherViews/errorView");
        }
    }

    @GetMapping(value = "/loadNews")
    @ResponseBody
    public List<NewsEntity> loadNews() {
        try {
            return getNewsList();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

}
