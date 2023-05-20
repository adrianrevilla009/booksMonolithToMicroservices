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

    @Value("${service.monolith}")
    private String monolithUrl;

    @Value("${service.microservice}")
    private String microserviceUrl;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
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

}
