package com.eduardodev.banking_system_api.repository;

import com.eduardodev.banking_system_api.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transacao, Long> {
}
