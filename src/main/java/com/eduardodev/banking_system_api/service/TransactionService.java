package com.eduardodev.banking_system_api.service;

import com.eduardodev.banking_system_api.entities.Transacao;
import com.eduardodev.banking_system_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transacao createTransaction(Transacao transacao) {
        return transactionRepository.save(transacao);
}
        public void deleteTransaction(Long id) {
            transactionRepository.deleteById(id);
        }
        public Transacao findTransactionById(Long id) {
            return transactionRepository.findById(id).orElse(null);
        }
        public Transacao updateTransaction(Transacao transacao) {
            return transactionRepository.save(transacao);
        }
        public Set<Transacao> findAllTransactions() {
            return Set.copyOf(transactionRepository.findAll());
        }
}
