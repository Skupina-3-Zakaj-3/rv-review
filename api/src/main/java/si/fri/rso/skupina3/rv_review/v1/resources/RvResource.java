package si.fri.rso.skupina3.rv_review.v1.resources;

import si.fri.rso.skupina3.lib.RvReview;
import si.fri.rso.skupina3.rv_review.services.beans.RvReviewBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RvResource {

    private Logger log = Logger.getLogger(RvResource.class.getName());

    @Inject
    private RvReviewBean rvReviewBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getRvReview() {

        log.info("getRvReview() - GET");
        List<RvReview> reviews = rvReviewBean.getRvReview();

        return Response.status(Response.Status.OK).entity(reviews).build();
    }
}
