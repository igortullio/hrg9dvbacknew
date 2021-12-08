package com.igortullio.hering.adapter.database.repository;

import com.igortullio.hering.adapter.database.entity.IntegrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegrationJpaRepository extends JpaRepository<IntegrationEntity, Long> {
}
