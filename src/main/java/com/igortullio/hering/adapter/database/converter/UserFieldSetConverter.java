package com.igortullio.hering.adapter.database.converter;

import com.igortullio.hering.core.domain.enumeration.UserField;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class UserFieldSetConverter implements AttributeConverter<Set<UserField>, String> {

    private static final String SPLIT = ";";

    @Override
    public String convertToDatabaseColumn(Set<UserField> attribute) {
        return Objects.nonNull(attribute)
                ? attribute.stream().map(Enum::name).collect(Collectors.joining(SPLIT))
                : null;
    }

    @Override
    public Set<UserField> convertToEntityAttribute(String dbData) {
        return Objects.nonNull(dbData)
                ? Arrays.stream(dbData.split(SPLIT)).map(UserField::valueOf).collect(Collectors.toSet())
                : Collections.emptySet();
    }

}
