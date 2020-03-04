package org.kostenko.microprofile.quarkus.example;

import java.util.Optional;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;

/**
 *
 * @author kostenko
 */
@RequestScoped
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tags(value = @Tag(name = "user", description = "All the user methods"))
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class UserResource {

    @Inject
    @Claim("user_name")
    Optional<JsonString> userName;

    @POST
    @PermitAll
    @Path("/token/{userName}")
    @APIResponses(value = {
        @APIResponse(responseCode = "400", description = "JWT generation error"),
        @APIResponse(responseCode = "200", description = "JWT successfuly created.", content = @Content(schema = @Schema(implementation = User.class)))})
    @Operation(summary = "Create JWT token by provided user name")
    public User getToken(@PathParam("userName") String userName) {
        User user = new User();
        user.setJwt(TokenUtils.generateJWT(userName));
        return user;
    }

    @GET
    @RolesAllowed("user")
    @Path("/current")
    @SecurityRequirement(name = "jwt", scopes = {})
    @APIResponses(value = {
        @APIResponse(responseCode = "401", description = "Unauthorized Error"),
        @APIResponse(responseCode = "200", description = "Return user data", content = @Content(schema = @Schema(implementation = User.class)))})
    @Operation(summary = "Return user data by provided JWT token")
    public User getUser() {
        User user = new User();
        user.setName(userName.get().getString());
        return user;
    }
}
