package handson.impl;

import io.sphere.sdk.client.SphereAccessTokenSupplier;
import io.sphere.sdk.client.SphereAsyncHttpClientFactory;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.http.HttpClient;

import java.io.IOException;
import java.util.Properties;

public class ClientService {
    /**
     * Creates a blocking sphere client
     *
     * @return Sphere client
     */
    public static SphereClient createSphereClient() throws IOException {
        final SphereClientConfig clientConfig = loadCTPClientConfig();
        final HttpClient httpClient = new SphereAsyncHttpClientFactory().getClient();
        final SphereAccessTokenSupplier accessTokenSupplier = SphereAccessTokenSupplier.ofAutoRefresh(clientConfig, httpClient, true);
        return SphereClient.of(clientConfig, httpClient, accessTokenSupplier);
    }

    /**
     * Sets a sphere client configuration
     *
     * @return sphere client configuration
     */
    private static SphereClientConfig loadCTPClientConfig() throws IOException {
        Properties properties = new Properties();
        properties.load(ClientService.class.getResourceAsStream("/dev.reznichenka.properties"));
        return SphereClientConfig.ofProperties(properties, "ctp.");
    }
}