package com.youcode.visionarycrofting;

import com.youcode.visionarycrofting.domain.Role;
import com.youcode.visionarycrofting.domain.User;
import com.youcode.visionarycrofting.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
//@EnableSwagger2
public class VisionarycroftingApplication {
	public static void main(String[] args) {
		SpringApplication.run(VisionarycroftingApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder (  );
	}

	@Bean
	CommandLineRunner run( UserService userService) {
		return args -> {
			userService.saveRole ( new Role ( null, "ROLE_USER" ) );
			userService.saveRole ( new Role ( null, "ROLE_PROVIDER" ) );
			userService.saveRole ( new Role ( null, "ROLE_ADMIN" ) );
			userService.saveRole ( new Role ( null, "ROLE_SUPER_ADMIN" ) );

			userService.saveUSer ( new User ( null, "Tayeb SOUINI", "Tayeb", "1234", new ArrayList <> (  ) ) );
			userService.saveUSer ( new User ( null, "Mohammed maitite", "Mohammed", "1234", new ArrayList <> (  ) ) );
			userService.saveUSer ( new User ( null, "Jamal Raouj", "Jamal", "1234", new ArrayList <> (  ) ) );
			userService.saveUSer ( new User ( null, "Nouhail el alami", "Nouhaila", "1234", new ArrayList <> (  ) ) );

			userService.addRoleToUSer ( "Tayeb", "ROLE_USER" );
			userService.addRoleToUSer ( "Tayeb", "ROLE_ADMIN" );
			userService.addRoleToUSer ( "Tayeb", "ROLE_SUPER_ADMIN" );


			userService.addRoleToUSer ( "Jamal", "ROLE_USER" );
			userService.addRoleToUSer ( "Jamal", "ROLE_ADMIN" );
			userService.addRoleToUSer ( "Jamal", "ROLE_PROVIDER" );
		};
	}
}
