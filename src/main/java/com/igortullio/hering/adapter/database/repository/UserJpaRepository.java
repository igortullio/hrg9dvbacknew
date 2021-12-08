package com.igortullio.hering.adapter.database.repository;

import com.igortullio.hering.adapter.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
