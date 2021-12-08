package com.igortullio.hering.core.domain;

import java.time.OffsetDateTime;

public abstract class AbstractDomain {

    private Long id;
    private OffsetDateTime dateCreate;
    private OffsetDateTime dateUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(OffsetDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public OffsetDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(OffsetDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

}
