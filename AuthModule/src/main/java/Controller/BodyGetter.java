package Controller;

import HibernateEntities.GroupsEntity;
import HibernateEntities.SkillCatEntity;
import HibernateEntities.SkillListEntity;
import HibernateEntities.StudentsEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static DAOImplement.GroupDao.getGroupList;
import static DAOImplement.SkillDao.getCatIdByName;
import static DAOImplement.SkillDao.getCategorySkill;
import static DAOImplement.SkillDao.getSkillByCat;
import static DAOImplement.StudentDao.loadStudent;

/**
 * Created by kinetik on 01.05.17.
 */
@Controller
public class BodyGetter {

    private static final Logger logger = Logger.getLogger(AdminMovController.class);
    private final List<SkillCatEntity> catList = getCategorySkill();

    @RequestMapping(value = "/loadStudents", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentsEntity> loadStudents(@RequestParam(value = "groupId", required = true) Integer groupId) throws IllegalStateException {
        try {
            List<StudentsEntity> stList = loadStudent(groupId);
            return stList;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            List<StudentsEntity> stList = new ArrayList<>();
            StudentsEntity failStudent = new StudentsEntity();
            failStudent.setStFamily("Ошибка");
            failStudent.setStFirstName("Ошибка");
            failStudent.setStSecName("Ошибка");
            stList.add(failStudent);
            return stList;
        }
    }

    @RequestMapping(value = "/loadGroups", method = RequestMethod.GET)
    @ResponseBody
    public List<GroupsEntity> loadGroups(){
        try {
            List<GroupsEntity> groupList = getGroupList();
            return groupList;
        } catch (Exception ex){
            logger.error(ex.getStackTrace());
            List<GroupsEntity> grList = new ArrayList<>();
            GroupsEntity failGroup = new GroupsEntity();
            failGroup.setGroupId(-1);
            failGroup.setGroupName("Ошибка");
            grList.add(failGroup);
            return grList;
        }
    }

    @RequestMapping(value = "/getSkillCats", method = RequestMethod.GET)
    public @ResponseBody List<SkillCatEntity> getCats(@RequestParam String catName) {
        List<SkillCatEntity> result = new ArrayList<>();
        for (SkillCatEntity cat : catList) {
            if (cat.getCatName().contains(catName)) {
                result.add(cat);
            }
        }
        return result;
    }

    @RequestMapping(value = "/getSkills", method = RequestMethod.GET)
    @ResponseBody
    public List<SkillListEntity> loadSkills(@RequestParam(value = "catName", required = true) String catName)
            throws IllegalStateException {
        try {
            int catId = getCatIdByName(catName);
            List<SkillListEntity> result = getSkillByCat(catId);
            return result;
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            List<SkillListEntity> skList = new ArrayList<>();
            SkillListEntity failSkill = new SkillListEntity();
            failSkill.setSkilName("Выберите навык");
            skList.add(failSkill);
            return skList;
        }
    }
}