package si.fri.rso.skupina3.rv_review.services.beans;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso.skupina3.lib.RvReview;
import si.fri.rso.skupina3.rv_review.models.converters.RvReviewConverter;
import si.fri.rso.skupina3.rv_review.models.entities.RvReviewEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class RvReviewBean {

    private Logger log = Logger.getLogger(RvReviewBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private RvReviewBean rvReviewBeanProxy;

    private Client httpClient;
    private String baseUrl;

    @Inject
    @DiscoverService("rv-catalog-service")
    private Optional<String> rvCatalogService;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrl = "http://rv-catalog:8082";
        baseUrl = rvCatalogService.get();
        log.info("DISCOVERY URL: " + rvCatalogService.get());
    }

    public List<RvReview> getRvReview() {

        TypedQuery<RvReviewEntity> query = em.createNamedQuery(
                "RvReviewEntity.getAll", RvReviewEntity.class);

        List<RvReviewEntity> resultList = query.getResultList();

        return resultList.stream().map(RvReviewConverter::toDto).collect(Collectors.toList());

    }

    public RvReview getRvReview(Integer rvReviewId) {

        RvReviewEntity rvReviewEntity = em.find(RvReviewEntity.class, rvReviewId);

        if (rvReviewEntity == null) {
            throw new NotFoundException();
        }

        RvReview rvReview = RvReviewConverter.toDto(rvReviewEntity);

        return rvReview;
    }

    public RvReview createRvReview(RvReview rvReview) {

        RvReviewEntity rvReviewEntity = RvReviewConverter.toEntity(rvReview);

        try {
            beginTx();
            em.persist(rvReviewEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (rvReviewEntity.getRv_review_id() == null) {
            throw new RuntimeException("Entity was not persisted");
        }
        else {
            try {
                log.info("URL: " + baseUrl + "/v1/rvs/" + rvReview.getRv_id() + "/rating");
                Response response = httpClient
                                    .target(baseUrl + "/v1/rvs/" + rvReview.getRv_id() + "/rating")
                                    .request().put(Entity.entity(Math.round(rvReview.getScore()), MediaType.TEXT_PLAIN));

                log.info("RV score updated with status " + response.getStatus());
            }
            catch (WebApplicationException | ProcessingException e) {
                log.severe(e.getMessage());
                throw new InternalServerErrorException(e);
            }
        }

        return RvReviewConverter.toDto(rvReviewEntity);
    }

    public RvReview putRvReview(Integer rvReviewId, RvReview rvReview) {

        RvReviewEntity c = em.find(RvReviewEntity.class, rvReviewId);

        if (c == null) {
            return null;
        }

        RvReviewEntity updatedRvReviewEntity = RvReviewConverter.toEntity(rvReview);

        try {
            beginTx();
            updatedRvReviewEntity.setRv_review_id(c.getRv_review_id());
            updatedRvReviewEntity = em.merge(updatedRvReviewEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return RvReviewConverter.toDto(updatedRvReviewEntity);
    }

    public boolean deleteRvReview(Integer rvReviewId) {

        RvReviewEntity rvReview = em.find(RvReviewEntity.class, rvReviewId);

        if (rvReview != null) {
            try {
                beginTx();
                em.remove(rvReview);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
