package com.igortullio.hering.adapter.database.entity;

import com.igortullio.hering.adapter.database.converter.PreconditionConverter;
import com.igortullio.hering.adapter.database.converter.UserFieldSetConverter;
import com.igortullio.hering.core.domain.Precondition;
import com.igortullio.hering.core.domain.enumeration.Format;
import com.igortullio.hering.core.domain.enumeration.IntegrationType;
import com.igortullio.hering.core.domain.enumeration.UserField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "TB_INTEGRATION")
public class IntegrationEntity extends AbstractEntity {

    private static final long serialVersionUID = -5830006927012660737L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Format format;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IntegrationType integrationType;

    private String destination;

    private String subject;

    @Convert(converter = UserFieldSetConverter.class)
    private Set<UserField> acceptedFields;

    @Convert(converter = PreconditionConverter.class)
    private Precondition precondition;

}
