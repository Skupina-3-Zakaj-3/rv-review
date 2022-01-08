package si.fri.rso.skupina3.rv_review.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.skupina3.lib.RvReview;
import si.fri.rso.skupina3.rv_review.services.beans.RvReviewBean;

import javax.enterprise.context.ApplicationScoped;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class RvReviewResource {

    private Logger log = Logger.getLogger(RvReviewResource.class.getName());

    @Inject
    private RvReviewBean rvReviewBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getRvReview() {

        log.info("getRvReview() - GET");
        List<RvReview> reviews = rvReviewBean.getRvReviewFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(reviews).build();
    }

    @GET
    @Path("/{rvReviewId}")
    public Response getRvReview(@PathParam("rvReviewId") Integer rvReviewId) {

        RvReview rvReview = rvReviewBean.getRvReview(rvReviewId);

        if (rvReview == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(rvReview).build();
    }


    @POST
    public Response createRvReview(RvReview rvReview) {

        if ((rvReview.getComment() == null || rvReview.getScore() == null || rvReview.getUser_id() == null ||
                rvReview.getRv_id() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            if (rvReview.getReview_date() == null)
                rvReview.setReview_date(new Date());

            rvReview = rvReviewBean.createRvReview(rvReview);
        }

        return Response.status(Response.Status.CREATED).entity(rvReview).build();

    }

    @DELETE
    @Path("{rvReviewId}")
    public Response deleteImageMetadata(@PathParam("rvReviewId") Integer rvReviewId){

        boolean deleted = rvReviewBean.deleteRvReview(rvReviewId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @PUT
    @Path("{rvReviewId}")
    public Response putRvReview(@PathParam("rvReviewId") Integer rvReviewId,
                                RvReview rvReview){

        rvReview = rvReviewBean.putRvReview(rvReviewId, rvReview);

        if (rvReview == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();

    }

}
