package com.igortullio.hering.adapter.dto.output;

import com.igortullio.hering.core.domain.Precondition;
import com.igortullio.hering.core.domain.enumeration.Format;
import com.igortullio.hering.core.domain.enumeration.IntegrationType;
import com.igortullio.hering.core.domain.enumeration.UserField;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class IntegrationDtoOutput extends AbstractDtoOutput {

    private Format format;
    private IntegrationType integrationType;
    private String destination;
    private String subject;
    private Set<UserField> acceptedFields;
    private Precondition precondition;

}
