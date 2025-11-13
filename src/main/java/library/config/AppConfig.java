package library.config;


import jakarta.ws.rs.ApplicationPath;
import library.controller.BookController;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        // register(library.controller.BookController.class);
        packages("library.controller");
        System.out.println("🚀 Jersey REST API initialized at /api");

    }
}
