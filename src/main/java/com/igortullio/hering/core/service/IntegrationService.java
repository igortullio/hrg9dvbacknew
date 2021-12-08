package com.igortullio.hering.core.service;

import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.port.RepositoryFindAllPort;
import com.igortullio.hering.core.port.RepositoryPort;

import java.util.List;

public final class IntegrationService implements InterfaceService<Integration> {

    private final RepositoryPort<Integration> integrationRepository;

    public IntegrationService(RepositoryPort<Integration> integrationRepository) {
        this.integrationRepository = integrationRepository;
    }

    @Override
    public Integration find(Long id) {
        return integrationRepository.find(id);
    }

    @Override
    public Integration save(Integration integration) {
        return integrationRepository.save(integration);
    }

    @Override
    public Integration update(Long id, Integration integration) {
        return integrationRepository.update(id, integration);
    }

    @Override
    public void delete(Long id) {
        integrationRepository.delete(id);
    }

}
