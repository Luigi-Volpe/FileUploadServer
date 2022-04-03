package com.dwmarketing.fileupload.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User luigi = new User(
                    "Luigi"
            );
            userRepository.saveAll(List.of(luigi));
        };
    }
}
