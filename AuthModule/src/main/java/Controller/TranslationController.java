package Controller;

import HibernateEntities.StudentsEntity;
import HibernateEntities.translation.ModeratorCommentEntity;
import HibernateEntities.translation.TranslationTextEntity;
import ServiceEntities.ModeratorCommentService;
import ServiceEntities.TranslationTextService;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller

@SessionAttributes(value = {"text", "Student"})
@RequestMapping(value = "/translation")
public class TranslationController {

    private static final Logger logger = Logger.getLogger(TranslationController.class);

    @Autowired
    private TranslationTextService translationTextService;

    @Autowired
    private ModeratorCommentService moderatorCommentService;

    @PostConstruct
    private void init(){ logger.info("It works!"); }

    @ModelAttribute("Student")
    public StudentsEntity populateStudent(@ModelAttribute("Student") StudentsEntity student) {
        return student;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTranslationView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("TranslationViews/translationView");
        mav.addObject("unassignedTexts", translationTextService.getUnassignedTexts());
        return mav;
    }

    @RequestMapping(value = "/{textId}", method = RequestMethod.GET)
    public ModelAndView openTranslationText(@ModelAttribute("Student") StudentsEntity student, @PathVariable String textId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("Student", student);
        if (Strings.isNullOrEmpty(textId)) {
            mav.setViewName("otherViews/errorView");
            return mav;
        }

        if (student == null || student.getStudentsId() == null) {
            mav.setViewName("otherViews/accessDeniedView");
            return mav;
        }

        Long translationTextId = Long.parseLong(textId);

        TranslationTextEntity translationText = translationTextService.findTextById(translationTextId);

        if (translationText == null || translationText.getStudent() == null) {
            mav.setViewName("otherViews/errorView");
            return mav;
        }

        if (!Objects.equals(translationText.getStudent().getStudentsId(), student.getStudentsId())) {
            mav.setViewName("otherViews/errorView");
            return mav;
        }

        mav.setViewName("TranslationViews/translationText");
        mav.addObject("text", translationText);

        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView updateTranslationText(@ModelAttribute("text") TranslationTextEntity text, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        String message = "";
        if (request.getParameter("save") != null) {
            text.setStatus(TranslationTextEntity.Status.PARTIALY_COMPLETED);
            message = "Ваш перевод сохранен. Вы можете продолжить работу над ним позже";
        }

        if (request.getParameter("submit") != null) {
            text.setStatus(TranslationTextEntity.Status.NEEDS_REVIEW);
            message = "Ваш перевод отправлен на модерацию.";
        }

        translationTextService.updateText(text);

        mav.setViewName("TranslationViews/infoView");
        mav.addObject("text", text);
        mav.addObject("message", message);

        return mav;
    }

    @RequestMapping(value = "/{textId}/comment", method = RequestMethod.POST)
    public ModeratorCommentEntity addComment(@PathVariable String textId, @RequestBody String requestBody) {
        if (Strings.isNullOrEmpty(requestBody)) {
            return null;
        }
        return null;
    }

}
