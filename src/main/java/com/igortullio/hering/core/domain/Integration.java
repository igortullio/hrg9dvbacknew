package com.igortullio.hering.core.domain;

import com.igortullio.hering.core.domain.enumeration.Format;
import com.igortullio.hering.core.domain.enumeration.IntegrationType;
import com.igortullio.hering.core.domain.enumeration.UserField;

import java.util.Set;

public class Integration extends AbstractDomain {

    private Format format;
    private IntegrationType integrationType;
    private String destination;
    private String subject;
    private Set<UserField> acceptedFields;
    private Precondition precondition;

    public Set<UserField> getAcceptedFields() {
        return acceptedFields;
    }

    public void setAcceptedFields(Set<UserField> acceptedFields) {
        this.acceptedFields = acceptedFields;
    }

    public IntegrationType getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(IntegrationType integrationType) {
        this.integrationType = integrationType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Precondition getPrecondition() {
        return precondition;
    }

    public void setPrecondition(Precondition precondition) {
        this.precondition = precondition;
    }

}
