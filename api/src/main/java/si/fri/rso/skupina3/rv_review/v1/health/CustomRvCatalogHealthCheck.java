package si.fri.rso.skupina3.rv_review.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Liveness
@ApplicationScoped
public class CustomRvCatalogHealthCheck implements HealthCheck {

    private static final Logger LOG = Logger.getLogger(CustomRvCatalogHealthCheck.class.getSimpleName());
    String url =  "http://rv-catalog:8082/v1/rvs";

    @Override
    public HealthCheckResponse call() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.up(CustomRvCatalogHealthCheck.class.getSimpleName());
            }
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
        }
        return HealthCheckResponse.down(CustomRvCatalogHealthCheck.class.getSimpleName());
    }
}