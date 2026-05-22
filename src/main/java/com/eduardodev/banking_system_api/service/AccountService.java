package com.eduardodev.banking_system_api.service;

import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Conta updateAccount(Conta conta) {
        return accountRepository.save(conta);
    }
    public List<Conta> findAllAccounts() {
        return new ArrayList<>(accountRepository.findAll());
    }

}
