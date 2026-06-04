package com.eduardodev.banking_system_api.service;

import com.eduardodev.banking_system_api.dtos.request.ContaDtoCorrenteRequest;
import com.eduardodev.banking_system_api.dtos.response.ContaCorrenteDtoResponse;
import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.entities.ContaCorrente;
import com.eduardodev.banking_system_api.entities.ContaPoupanca;
import com.eduardodev.banking_system_api.repository.AccountRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public ContaCorrenteDtoResponse createAccountCorrente(ContaDtoCorrenteRequest contaDTOrequest) {
        ContaCorrente conta = new ContaCorrente();
        conta.setTitular(contaDTOrequest.getTitular());
        conta.setBalance(contaDTOrequest.getBalance());
        conta.setNumeroConta(contaDTOrequest.getNumeroConta());
        conta.setAtiva(conta.isAtiva());
        conta.setDataAbertura(contaDTOrequest.getDataAbertura());
        conta.setAgencia(conta.getAgencia());
        conta.setLimiteChequeEspecial(contaDTOrequest.getLimiteChequeEspecial());


         ContaCorrente savedConta = accountRepository.save(conta);

        return getContaCorrenteDtoResponse(savedConta, conta);
    }

    private static @NonNull ContaCorrenteDtoResponse getContaCorrenteDtoResponse(ContaCorrente savedConta, ContaCorrente conta) {
        ContaCorrenteDtoResponse contaDTOresponse = new ContaCorrenteDtoResponse();
        contaDTOresponse.setId(savedConta.getIdConta());
        contaDTOresponse.setTitular(savedConta.getTitular());
        contaDTOresponse.setBalance(savedConta.getBalance());
        contaDTOresponse.setDataAbertura(conta.getDataAbertura());
        contaDTOresponse.setAtiva(conta.isAtiva());
        contaDTOresponse.setLimiteChequeEspecial(conta.getLimiteChequeEspecial());
        contaDTOresponse.setAgencia(conta.getAgencia());
        return contaDTOresponse;
    }

    @Transactional
    public ContaCorrenteDtoResponse createAccountPoupanca(ContaDtoCorrenteRequest contaDTOrequest) {
        Conta conta = new ContaPoupanca();
        conta.setTitular(contaDTOrequest.getTitular());
        conta.setBalance(contaDTOrequest.getBalance());
        conta.setAtiva(contaDTOrequest.isAtiva());
        conta.setNumeroConta(contaDTOrequest.getNumeroConta());
        conta.setDataAbertura(LocalDate.now());

        Conta savedConta = accountRepository.save(conta);
        ContaCorrenteDtoResponse contaDTOresponse = new ContaCorrenteDtoResponse();
        contaDTOresponse.setId(savedConta.getIdConta());
        contaDTOresponse.setTitular(savedConta.getTitular());
        contaDTOresponse.setBalance(savedConta.getBalance());

        return contaDTOresponse;
    }

    @Transactional
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
