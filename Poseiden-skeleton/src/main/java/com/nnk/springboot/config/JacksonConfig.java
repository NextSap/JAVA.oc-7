package com.nnk.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class JacksonConfig implements ApplicationRunner {

    private final Logger logger = LogManager.getLogger(this);
    private final UserRepository userRepository;

    @Value("${feedDatabase}")
    private boolean feedDatabase;

    @Value("classpath:data.json")
    private Resource data;

    @Autowired
    public JacksonConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!feedDatabase) return;

        logger.info("Feeding database");

        Data dataObject = new ObjectMapper().readValue(data.getInputStream(), Data.class);
        feedDatabase(dataObject);

        logger.info("Database feeded");
    }

    private void feedDatabase(Data data) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (User user : data.users) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    // Password1@
    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private User[] users;
    }
}
