package si.fri.rso.skupina3.rv_review.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rv_reviews")
@NamedQueries(value =
        {
                @NamedQuery(name = "RvReviewEntity.getAll",
                        query = "SELECT review FROM RvReviewEntity review")
        })
public class RvReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rv_review_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "score")
    private Float score;

    @Column(name = "rv_id")
    private Integer rv_id;

    @Column(name = "review_date")
    private Date review_date;


    public Integer getRv_review_id() {
        return rv_review_id;
    }

    public void setRv_review_id(Integer rv_review_id) {
        this.rv_review_id = rv_review_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
