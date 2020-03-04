package org.kostenko.microprofile.quarkus.example.push;

import org.kostenko.microprofile.quarkus.example.LoggingFilter;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author kostenko
 */
@Path("/")
@RegisterRestClient(configKey = "push-api")
@RegisterProvider(LoggingFilter.class)
public interface PushClientService {

    @POST
    @Path("/fcm/send")
    @Produces("application/json")
    @ClientHeaderParam(name = "Authorization", value = "{generateAuthHeader}")
    void send(PushMessage msg);

    default String generateAuthHeader() {
        return "key=" + ConfigProvider.getConfig().getValue("firebase.server_key", String.class);
    }
}
