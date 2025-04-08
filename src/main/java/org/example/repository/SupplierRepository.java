package org.example.repository;

import org.example.entitiy.SuplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SuplierEntity,Long> {
    Optional<SuplierEntity> findByEmail(String email);
}
