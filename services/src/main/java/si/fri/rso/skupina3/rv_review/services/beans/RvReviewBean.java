package si.fri.rso.skupina3.rv_review.services.beans;

import si.fri.rso.skupina3.lib.RvReview;
import si.fri.rso.skupina3.rv_review.models.converters.RvReviewConverter;
import si.fri.rso.skupina3.rv_review.models.entities.RvReviewEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class RvReviewBean {

    private Logger log = Logger.getLogger(RvReviewBean.class.getName());

    @Inject
    private EntityManager em;

    public List<RvReview> getRvReview() {

        TypedQuery<RvReviewEntity> query = em.createNamedQuery(
                "RvReviewEntity.getAll", RvReviewEntity.class);

        List<RvReviewEntity> resultList = query.getResultList();

        return resultList.stream().map(RvReviewConverter::toDto).collect(Collectors.toList());

    }
}
