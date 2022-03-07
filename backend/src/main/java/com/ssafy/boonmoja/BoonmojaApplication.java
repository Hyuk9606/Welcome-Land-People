package com.ssafy.boonmoja;

import com.ssafy.boonmoja.config.properties.AppProperties;
import com.ssafy.boonmoja.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class BoonmojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoonmojaApplication.class, args);
	}

}
