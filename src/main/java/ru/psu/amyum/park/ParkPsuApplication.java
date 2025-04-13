package ru.psu.amyum.park;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ParkPsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkPsuApplication.class, args);
//		String secretKey = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
//		System.out.println("Generated Key: " + secretKey);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:1409") // Укажите разрешённый origin
						.allowedMethods("GET", "POST", "PUT", "DELETE");
//						.allowedHeaders("*")
//						.allowCredentials(true);
			}
		};
	}

}
