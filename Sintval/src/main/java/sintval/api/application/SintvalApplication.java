package sintval.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "sintval.api.application","sintval.api.controller","sintval.api.entity","sintval.api.exception","sintval.api.repository","sintval.api.error"} )
public class SintvalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SintvalApplication.class, args);
	}

}
