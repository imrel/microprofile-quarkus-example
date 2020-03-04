package org.kostenko.microprofile.quarkus.example.restclient.push;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
public interface PushService {

    @POST
    @Path("/")
    @Produces("application/json")
    @ClientHeaderParam(name = "Authorization", value = "{generateAuthHeader}")        
    void send(PushMessage msg);
    
      default String generateAuthHeader() {
        return "key=AAAA7V_QiEQ:APA91bFXYNGyccOxYrLUlPwg92yQC4ecBW_xQmiY-Syx8PUG0RlwcBlLRtP3xbI8g9ombS13VjMJGX-7HLfkMw1Bbexh239SjY3JXOGmqJzj4rQZyqlwrt95GimVdyEKiAL2Mo2JXfvL";
    }

}
