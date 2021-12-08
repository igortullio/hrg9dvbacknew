package com.igortullio.hering.adapter.controller;

import com.igortullio.hering.adapter.dto.input.IntegrationDtoInput;
import com.igortullio.hering.adapter.dto.output.IntegrationDtoOutput;
import com.igortullio.hering.adapter.mapper.IntegrationMapper;
import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.service.IntegrationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integrations")
public class IntegrationController implements InterfaceController<IntegrationDtoInput, IntegrationDtoOutput> {

    private final IntegrationService integrationService;
    private final IntegrationMapper integrationMapper;

    public IntegrationController(IntegrationService integrationService, IntegrationMapper integrationMapper) {
        this.integrationService = integrationService;
        this.integrationMapper = integrationMapper;
    }

    @Override
    public IntegrationDtoOutput get(Long id) {
        Integration integration = integrationService.find(id);
        return integrationMapper.integrationToIntegrationDtoOutput(integration);
    }

    @Override
    public IntegrationDtoOutput post(IntegrationDtoInput integrationDtoInput) {
        Integration integration = integrationMapper.integrationDtoInputToIntegration(integrationDtoInput);
        return integrationMapper.integrationToIntegrationDtoOutput(integrationService.save(integration));
    }

    @Override
    public IntegrationDtoOutput put(Long id, IntegrationDtoInput integrationDtoInput) {
        Integration integration = integrationMapper.integrationDtoInputToIntegration(integrationDtoInput);
        return integrationMapper.integrationToIntegrationDtoOutput(integrationService.update(id, integration));
    }

    @Override
    public void delete(Long id) {
        integrationService.delete(id);
    }

}
