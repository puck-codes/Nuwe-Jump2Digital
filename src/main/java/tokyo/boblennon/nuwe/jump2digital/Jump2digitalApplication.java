package tokyo.boblennon.nuwe.jump2digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Nuwel JUMP2DIGITAL", version = "1.0", description = "Documentation APIs v1.0"))

public class Jump2digitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jump2digitalApplication.class, args);
	}


}
