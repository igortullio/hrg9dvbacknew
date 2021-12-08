package com.igortullio.hering.adapter.database.repository;

import com.igortullio.hering.adapter.mapper.IntegrationMapper;
import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.port.RepositoryFindAllPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IntegrationRepositoryFindAllPortImpl implements RepositoryFindAllPort<Integration> {

    private final IntegrationJpaRepository integrationJpaRepository;
    private final IntegrationMapper integrationMapper;

    public IntegrationRepositoryFindAllPortImpl(IntegrationJpaRepository integrationJpaRepository, IntegrationMapper integrationMapper) {
        this.integrationJpaRepository = integrationJpaRepository;
        this.integrationMapper = integrationMapper;
    }


    @Override
    public List<Integration> findAll() {
        return integrationJpaRepository.findAll()
                .stream()
                .map(integrationMapper::integrationEntityToIntegration)
                .collect(Collectors.toList());
    }

}
