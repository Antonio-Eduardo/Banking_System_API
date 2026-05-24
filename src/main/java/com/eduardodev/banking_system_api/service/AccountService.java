package com.eduardodev.banking_system_api.service;

import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Conta createAccount(Conta conta) {
        return accountRepository.save(conta);
    }
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
    public Conta findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
    public List<Conta> findAllAccounts() {
        return new ArrayList<>(accountRepository.findAll());
    }

    @Transactional
    public Conta Deposit(Long id, BigDecimal amount) {
        Conta existingConta = accountRepository.findById(id).orElse(null);
        if (existingConta != null) {
            existingConta.deposito(amount);
            return accountRepository.save(existingConta);
        } else {
            return null;
        }
    }
    @Transactional
    public Conta Saque(Long id, BigDecimal amount) {
        Conta existingConta = accountRepository.findById(id).orElse(null);
        if (existingConta != null) {
            existingConta.sacar(amount);
            return accountRepository.save(existingConta);
        } else {
            return null;

        }
    }
    @Transactional
    public Conta Transferencia(Long idOrigem, Long idDestino, BigDecimal amount) {
        Conta contaOrigem = accountRepository.findById(idOrigem).orElse(null);
        Conta contaDestino = accountRepository.findById(idDestino).orElse(null);
        if (contaOrigem != null && contaDestino != null) {
            contaOrigem.sacar(amount);
            contaDestino.deposito(amount);
            accountRepository.save(contaOrigem);
            return accountRepository.save(contaDestino);
        } else {
            return null;
        }
    }
}
