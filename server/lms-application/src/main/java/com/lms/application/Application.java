package com.lms.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages =
        {
                "com.lms.security",
                "com.lms.atom",
                "com.lms.filesystem",
                "com.lms.configuration",
                "com.lms.client",
                "com.lms.client.api",
                "com.lms.gateway",
                "com.lms.integration",
                "com.lms.application"
        })
@EnableJpaRepositories(basePackages =
        {
                "com.lms.security",
                "com.lms.configuration",
                "com.lms.client",
                "com.lms.client.api",
                "com.lms.atom"
        })
@EntityScan(basePackages =
        {
                "com.lms.security",
                "com.lms.configuration",
                "com.lms.client",
                "com.lms.client.api",
                "com.lms.atom"
        })
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    //for war extension
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    //for jar extension
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
