package services;

/**
 * Created by kinetik on 01.05.17.
 */
public class UserSkill {
    private String skillCat;
    private Integer rating;
    private String skilName;
    private Integer skilId;

    public UserSkill(Integer skilId, String skilName, Integer rating, String skillCat) {
        this.rating = rating;
        this.skillCat = skillCat;
        this.skilId = skilId;
        this.skilName = skilName;
    }

    public String getSkilName() {
        return skilName;
    }

    public void setSkilName(String skilName) {
        this.skilName = skilName;
    }

    public Integer getSkilId() {
        return skilId;
    }

    public void setSkilId(Integer skilId) {
        this.skilId = skilId;
    }

    public String getSkillCat() {
        return skillCat;
    }

    public void setSkillCat(String skillCat) {
        this.skillCat = skillCat;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
