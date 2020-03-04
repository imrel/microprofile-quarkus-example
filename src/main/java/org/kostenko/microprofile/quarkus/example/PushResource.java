package org.kostenko.microprofile.quarkus.example;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.kostenko.microprofile.quarkus.example.restclient.push.PushMessage;
import org.kostenko.microprofile.quarkus.example.restclient.push.PushService;

/**
 *
 * @author kostenko
 */
@Path("/push")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tags(value = @Tag(name = "push", description = "All the push methods"))
public class PushResource {

    @Inject
    @RestClient
    PushService pushService;
    
    @POST
    @APIResponses(value = {
        @APIResponse(responseCode = "400", description = "Push sending  error"),
        @APIResponse(responseCode = "200", description = "Push successfuly sent.")})
    @Operation(summary = "Send firebase push notificaton")
    public void sendPush(PushMessage message) {
        pushService.send(message);
    }

}
