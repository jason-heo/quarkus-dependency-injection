package io.github.jasonheo;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class MyResource {

    @Inject
    Logger logger;

    @Inject
    DbService dbService;

    @Inject
    EmailService emailService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/id/{id}")
    public String hello(@PathParam String id) {
        logger.info("hello(" + id + ") called");

        emailService.sendEmail(dbService.getEmailAddr(id));

        return "Hello " + dbService.getName(id);
    }
}