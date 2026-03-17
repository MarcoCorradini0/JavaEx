package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.agroal.api.AgroalDataSource;
import java.sql.SQLException;

@Path("/hello")
public class GreetingResource {

    @Inject
    AgroalDataSource ds;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/db")
    @Produces(MediaType.TEXT_PLAIN)
    public String dbCheck() throws SQLException {
        try (
            var conn = ds.getConnection();
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT 1")
        ) {
            if (rs.next()) {
                return "DB OK: " + rs.getInt(1);
            }
        }
        return "DB connection failed";
    }
}