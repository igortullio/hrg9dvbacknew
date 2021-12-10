package com.igortullio.hering.adapter.database.converter;

import com.igortullio.hering.core.domain.Precondition;
import com.igortullio.hering.core.domain.enumeration.UserField;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class PreconditionConverter implements AttributeConverter<Precondition, String> {

    private static final String CONCAT = ":";

    @Override
    public String convertToDatabaseColumn(Precondition attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getUserField().name() + CONCAT
                + attribute.getConditionString() + CONCAT
                + attribute.getConditionInteger() + CONCAT
                + attribute.getBiPredicatePosition();
    }

    @Override
    public Precondition convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }

        String[] split = dbData.split(CONCAT);
        return new Precondition(
                UserField.valueOf(split[0]),
                split[1],
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3])
        );
    }

}
