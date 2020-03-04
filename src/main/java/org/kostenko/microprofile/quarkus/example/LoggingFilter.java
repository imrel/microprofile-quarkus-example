package org.kostenko.microprofile.quarkus.example;

import io.vertx.core.http.HttpServerRequest;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kostenko
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class.getName());

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {

        //String text = null;
        //try (Scanner scanner = new Scanner(context.getEntityStream(), StandardCharsets.UTF_8.name())) {
        //    text = scanner.useDelimiter("\\A").next();
        //}
        LOG.info("\n\n\n");
        LOG.info("--- " + context.getUriInfo().getAbsolutePath().toString());
        LOG.info("--- " + context.getHeaders());
        //LOG.info("--- " + text);
    }

}
