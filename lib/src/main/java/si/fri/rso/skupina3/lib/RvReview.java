package si.fri.rso.skupina3.lib;

import java.util.Date;

public class RvReview {

    private Integer rv_review_id;
    private String comment;
    private Float score;
    private Integer user_id;
    private Integer rv_id;
    private Date review_date;

    public Integer getRv_review_id() {
        return rv_review_id;
    }

    public void setRv_review_id(Integer rv_review_id) {
        this.rv_review_id = rv_review_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRv_id() {
        return rv_id;
    }

    public void setRv_id(Integer rv_id) {
        this.rv_id = rv_id;
    }

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }
}
