package com.igortullio.hering.adapter.mapper;

import com.igortullio.hering.adapter.database.entity.IntegrationEntity;
import com.igortullio.hering.adapter.dto.input.IntegrationDtoInput;
import com.igortullio.hering.adapter.dto.output.IntegrationDtoOutput;
import com.igortullio.hering.core.domain.Integration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IntegrationMapper {

    Integration integrationEntityToIntegration(IntegrationEntity integrationEntity);

    IntegrationEntity integrationToIntegrationEntity(Integration integration);

    IntegrationDtoOutput integrationToIntegrationDtoOutput(Integration integration);

    Integration integrationDtoInputToIntegration(IntegrationDtoInput type);

}
