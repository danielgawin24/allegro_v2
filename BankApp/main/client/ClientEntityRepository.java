package com.example.bankApp.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByUsername(String username);
}
