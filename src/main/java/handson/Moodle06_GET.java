package handson;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.projects.Project;
import io.sphere.sdk.projects.queries.ProjectGet;
import io.sphere.sdk.shippingmethods.ShippingMethod;
import io.sphere.sdk.shippingmethods.queries.ShippingMethodByKeyGet;
import io.sphere.sdk.taxcategories.TaxCategory;
import io.sphere.sdk.taxcategories.queries.TaxCategoryByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;

/**
 * Configure sphere client and get project information.
 */
public class Moodle06_GET {
    private static final Logger LOG = LoggerFactory.getLogger(Moodle06_GET.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final Project project = client.execute(ProjectGet.of()).toCompletableFuture().get();
            LOG.info("Project info {}", project);

            final TaxCategory taxCategory = client.execute(TaxCategoryByKeyGet.of("vat")).toCompletableFuture().get();
            LOG.info("TaxCategory info {}", taxCategory);

            final ShippingMethod shippingMethod = client.execute(ShippingMethodByKeyGet.of("DHL")).toCompletableFuture().get();
            LOG.info("Shipping method {}", shippingMethod);
        }
    }
}
