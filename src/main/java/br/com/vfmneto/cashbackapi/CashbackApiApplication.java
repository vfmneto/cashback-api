package br.com.vfmneto.cashbackapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@EnableCircuitBreaker
@SpringBootApplication
public class CashbackApiApplication {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

	private static final Logger log = LoggerFactory.getLogger(CashbackApiApplication.class);
	private static final String SPRING_PROFILE_DEVELOPMENT = "dev";

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(CashbackApiApplication.class);
		addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		log.info("\n----------------------------------------------------------\n\t" +
				 "Application '{}' is running! Access URLs:\n\t" +
				 "Local: \t\t{}://localhost:{}\n\t" +
				 "External: \t{}://{}:{}\n\t" +
				 "Swagger: \t{}://localhost:{}/swagger-ui.html#/\n\t" +
				 "Profile(s): \t{}" +
				 "\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				protocol,
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				protocol,
				env.getProperty("server.port"),
				env.getActiveProfiles());
	}

	public static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>();

		defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_DEVELOPMENT);
		app.setDefaultProperties(defProperties);
	}

}

