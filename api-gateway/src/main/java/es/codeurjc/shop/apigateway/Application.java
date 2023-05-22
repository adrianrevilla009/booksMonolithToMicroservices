package es.codeurjc.shop.apigateway;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {

    private static String MODEL_MONOLITH = "monolith";
    private static String MODEL_MICROSERVICE = "microservice";

    @Value("${app.model}")
    private String model; // monolith

    @Value("${service.monolith}")
    private String monolithUrl; // http://localhost:8080/

    @Value("${service.microservice}")
    private String microserviceUrl; // http://localhost:8081/
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        System.out.println("microservice " + this.microserviceUrl);
        System.out.println("monolith " + this.monolithUrl);
        System.out.println("model " + this.model);
        if (this.model.equals(MODEL_MICROSERVICE)) {
            return builder.routes()
                    .route(r -> r.path("/users/**")
                            .uri(this.microserviceUrl))
                    .build();
        }
        return builder.routes()
                .route(r -> r.path("/users/**")
                        .uri(this.monolithUrl))
                .build();
    }

    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
