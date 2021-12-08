package com.igortullio.hering.adapter.database.repository;

import com.igortullio.hering.adapter.database.entity.IntegrationEntity;
import com.igortullio.hering.adapter.mapper.IntegrationMapper;
import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.exception.HeringException;
import com.igortullio.hering.core.exception.in_use.UserInUseException;
import com.igortullio.hering.core.exception.not_found.AbstractNotFoundException;
import com.igortullio.hering.core.exception.not_found.IntegrationNotFoundException;
import com.igortullio.hering.core.exception.not_found.UserNotFoundException;
import com.igortullio.hering.core.port.RepositoryPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class IntegrationRepositoryPortImpl implements RepositoryPort<Integration> {

    private final IntegrationJpaRepository integrationJpaRepository;
    private final IntegrationMapper integrationMapper;

    public IntegrationRepositoryPortImpl(IntegrationJpaRepository integrationJpaRepository, IntegrationMapper integrationMapper) {
        this.integrationJpaRepository = integrationJpaRepository;
        this.integrationMapper = integrationMapper;
    }

    @Override
    public Integration find(Long id) {
        IntegrationEntity integrationEntity = integrationJpaRepository.findById(id)
                .orElseThrow(() -> new IntegrationNotFoundException(id));

        return integrationMapper.integrationEntityToIntegration(integrationEntity);
    }

    @Override
    public Integration save(Integration integration) {
        try {
            IntegrationEntity integrationEntity = integrationMapper.integrationToIntegrationEntity(integration);
            integrationEntity = integrationJpaRepository.save(integrationEntity);

            return integrationMapper.integrationEntityToIntegration(integrationEntity);
        } catch (AbstractNotFoundException exception) {
            throw new HeringException(exception.getMessage(), exception);
        }
    }

    @Override
    public Integration update(Long id, Integration integration) {
        Integration integrationInDB = find(id);
        integrationInDB.setAcceptedFields(integration.getAcceptedFields());
        integrationInDB.setFormat(integration.getFormat());
        integrationInDB.setPrecondition(integration.getPrecondition());

        IntegrationEntity integrationEntity = integrationMapper.integrationToIntegrationEntity(integrationInDB);
        integrationEntity = integrationJpaRepository.save(integrationEntity);

        return integrationMapper.integrationEntityToIntegration(integrationEntity);
    }

    @Override
    public void delete(Long id) {
        try {
            integrationJpaRepository.deleteById(id);
            integrationJpaRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new UserNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new UserInUseException(id);
        }
    }

}
