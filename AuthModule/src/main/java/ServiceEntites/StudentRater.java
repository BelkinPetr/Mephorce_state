package ServiceEntites;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by ilysko on 30.06.17.
 */
public class StudentRater {
    private Integer rating;
    private String comment;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
