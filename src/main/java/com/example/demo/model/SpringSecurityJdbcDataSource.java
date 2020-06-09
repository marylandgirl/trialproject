package com.example.demo.model;



import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class SpringSecurityJdbcDataSource {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcDataSource.class, args);
    }



    @Bean
    public CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository) throws Exception {
        return(String[] args) -> {



            // insert first user hard code it


            User user = new User("bart", "bart@domain.com", "bart",
                    "Bart", "Simpson", true);

            Role userRole = new Role("bart", "ROLE_USER");
//            for (Interest interests: interestRepository.findAll()){
//                user.getInterests().add(interests);
//            }

            userRepository.save(user);
            roleRepository.save(userRole);

            // second user hard code it
            User admin1 = new User("admin", "ted@domain.com", "admin",
                    "Teddy", "Bear", true);
            Role adminRole1 = new Role("admin", "ROLE_ADMIN");

            userRepository.save(admin1);
            roleRepository.save(adminRole1);


            // insert third user
            User admin = new User("super", "super@domain.com", "super",
                    "Super", "Man", true);
            Role adminRole = new Role("super", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);

            Role adminRole2 = new Role("super", "ROLE_USER");
            roleRepository.save(adminRole2);



        };
    }

}
