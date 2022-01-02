package si.fri.rso.skupina3.rv_review.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService(value="rv-review-service", environment="dev", version = "1.0.0")
public class RvReviewApplication extends Application {
}
