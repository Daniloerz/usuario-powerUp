package com.example.usuariopowerUp.infrastructure.configuration;

import com.example.usuariopowerUp.domain.api.IObjectServicePort;
import com.example.usuariopowerUp.domain.spi.IObjectPersistencePort;
import com.example.usuariopowerUp.domain.usecase.ObjectUseCase;
import com.example.usuariopowerUp.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }
}