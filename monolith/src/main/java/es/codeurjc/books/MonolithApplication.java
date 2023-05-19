package es.codeurjc.books;

import es.codeurjc.books.utils.ArchitectureModel;
import es.codeurjc.books.utils.MicroserviceModel;
import es.codeurjc.books.utils.MonolithModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class MonolithApplication {
	private static final Logger LOGGER = Logger.getLogger( MonolithApplication.class.getName() );

	public static void main(String[] args) {
		SpringApplication.run(MonolithApplication.class, args);
	}

	@Bean(name = "architectureModel")
	@ConditionalOnProperty(prefix = "app", name = "model", havingValue = "monolith")
	public ArchitectureModel architectureModelMonolith() {
		LOGGER.info("Monolith model initialized");
		return new MonolithModel();
	}

	@Bean(name = "architectureModel")
	@ConditionalOnProperty(prefix = "app", name = "model", havingValue = "microservice")
	public ArchitectureModel architectureModelMicroservice() {
		LOGGER.info("Microservice model initialized");
		return new MicroserviceModel();
	}

}
