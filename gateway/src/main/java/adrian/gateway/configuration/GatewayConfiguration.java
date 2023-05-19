package adrian.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    private static String MODEL_MONOLITH = "monolith";
    private static String MODEL_MICROSERVICE = "microservice";

    @Value("${app.model}")
    private String model;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        if (this.model.equals(MODEL_MICROSERVICE)) {
            return builder.routes()
                    .route(r -> r.path("/users/**")
                            .uri("http://localhost:8081/"))
                    .build();
        }
        return builder.routes()
                .route(r -> r.path("/users/**")
                        .uri("http://localhost:8080/"))
                .build();
    }

}
