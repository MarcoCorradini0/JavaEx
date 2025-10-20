package org.acme;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/index")
public class IndexResource {

    @Inject
    RedisDataSource redis;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index(long visits);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        ValueCommands<String, Long> commands = redis.value(Long.class);
        long visits = commands.incr("counter");
        return Templates.index(visits);
    }
}
