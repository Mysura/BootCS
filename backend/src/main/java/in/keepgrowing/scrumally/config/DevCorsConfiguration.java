package in.keepgrowing.scrumally.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile({"dev","test","prod"})
public class DevCorsConfiguration implements WebMvcConfigurer {

	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Value("${dev.profile.url}")
	private String devUrl;
	
	@Value("${test.profile.url}")
	private String testUrl;
	
	@Value("${prod.profile.url}")
	private String prodUrl;
	
	  @Override public void addCorsMappings(CorsRegistry registry) { 
		  String activeDomain = null;
	    	System.out.println("Activate profile is :"+activeProfile);
	    	switch(activeProfile) {
	    	  case "prod":
	    		  activeDomain = prodUrl;
	    	    break;
	    	  case "test":
	    		  activeDomain = testUrl;
	    	    break;
	    	  default:
	    		  activeDomain = devUrl;
	    	}
	  //System.out.println(activeProfile+""+devUrl+""+testUrl+""+prodUrl);
	  
		registry.addMapping("/api/**").allowedOrigins(activeDomain)
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
	}
    
}
