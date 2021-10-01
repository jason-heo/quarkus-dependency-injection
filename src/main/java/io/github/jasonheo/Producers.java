package io.github.jasonheo;

import io.quarkus.arc.properties.IfBuildProperty;
import io.quarkus.arc.properties.UnlessBuildProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

public class Producers {
    @Produces
    @ApplicationScoped
    @IfBuildProperty(name = "env", stringValue = "local")
    public DbService getLocalDbService() {
        DbService dbService = new DbService("jdbc:mysql://localhost:3306/sakila");

        return dbService;
    }

    @Produces
    @ApplicationScoped
    @UnlessBuildProperty(name = "env", stringValue = "local")
    public DbService getRealDbService() {
        DbService dbService = new DbService("jdbc:mysql://real-db.com:3306/sakila");

        return dbService;
    }
}
