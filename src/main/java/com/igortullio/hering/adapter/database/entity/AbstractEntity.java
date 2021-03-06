package com.igortullio.hering.adapter.database.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private OffsetDateTime dateCreate;

    private OffsetDateTime dateUpdate;

    @PrePersist
    private void prePersist() {
        this.dateCreate = OffsetDateTime.now();
        this.dateUpdate = OffsetDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.dateUpdate = OffsetDateTime.now();
    }

}
