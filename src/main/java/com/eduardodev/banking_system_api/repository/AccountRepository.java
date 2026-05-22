package com.eduardodev.banking_system_api.repository;

import com.eduardodev.banking_system_api.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Conta, Long> {
}
