package com.igortullio.hering.adapter.config;

import com.igortullio.hering.adapter.client.HttpClient;
import com.igortullio.hering.adapter.database.repository.IntegrationRepositoryFindAllPortImpl;
import com.igortullio.hering.adapter.database.repository.IntegrationRepositoryPortImpl;
import com.igortullio.hering.adapter.database.repository.UserRepositoryPortImpl;
import com.igortullio.hering.adapter.email.EmailSendImpl;
import com.igortullio.hering.core.service.IntegrationService;
import com.igortullio.hering.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(UserRepositoryPortImpl userRepositoryPort,
                                   IntegrationRepositoryFindAllPortImpl integrationFindAllRepository,
                                   EmailSendImpl emailSendPort,
                                   HttpClient clientSendPort) {
        return new UserService(userRepositoryPort, integrationFindAllRepository, emailSendPort, clientSendPort);
    }

    @Bean
    public IntegrationService integrationService(IntegrationRepositoryPortImpl integrationRepositoryPort) {
        return new IntegrationService(integrationRepositoryPort);
    }

}
