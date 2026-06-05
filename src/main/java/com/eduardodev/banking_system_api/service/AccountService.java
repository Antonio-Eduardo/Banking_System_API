package com.eduardodev.banking_system_api.service;

import com.eduardodev.banking_system_api.dtos.request.ContaDtoCorrenteRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoEmpresarialRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoPoupancaRequest;
import com.eduardodev.banking_system_api.dtos.request.superdtoconta.ContaDTO;
import com.eduardodev.banking_system_api.dtos.response.ContaCorrenteDtoResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoEmpresarialResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoPoupancaResponse;
import com.eduardodev.banking_system_api.dtos.response.OperacaoDTOresponse;
import com.eduardodev.banking_system_api.entities.*;
import com.eduardodev.banking_system_api.exceptions.ResourceNotFoundException;
import com.eduardodev.banking_system_api.repository.AccountRepository;
import com.eduardodev.banking_system_api.service.converter.ContaConverter;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContaConverter convert;

    @Transactional
    public ContaCorrenteDtoResponse createAccountCorrente(ContaDtoCorrenteRequest contaDTOrequest) {
        ContaCorrente conta = convert.toEntityCC(contaDTOrequest);
         ContaCorrente savedConta = accountRepository.save(conta);
        return convert.toResponseCC(savedConta);

    }
    @Transactional
    public ContaDtoPoupancaResponse createAccountPoupanca(ContaDtoPoupancaRequest contaDTOrequest) {
        ContaPoupanca conta = convert.toEntityCP(contaDTOrequest);
        ContaPoupanca savedConta =accountRepository.save(conta);
        return convert.toResponseCP(savedConta);
    }
    @Transactional
    public ContaDtoEmpresarialResponse createAccountEmpresarial(ContaDtoEmpresarialRequest contaDTOrequest) {
        ContaEmpresarial conta = convert.toEntityCE(contaDTOrequest);
        ContaEmpresarial savedConta = accountRepository.save(conta);
        return convert.toResponseCE(savedConta);
    }

    @Transactional
    public void deleteAccount(Long id) {
        Conta existingConta = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Conta não encontrada com id: " + id));
        accountRepository.delete(existingConta);
    }

    public Conta findAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Conta não encontrada com id: " + id));
    }

    public List<Conta> findAllAccounts() {
        return new ArrayList<>(accountRepository.findAll());
    }

    @Transactional
    public OperacaoDTOresponse Deposit(Long id, BigDecimal amount) {
        Conta existingConta = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Conta não encontrada com id: " + id));
            Transacao transacao = existingConta.deposito(amount);
            Conta savedAccount = accountRepository.save(existingConta);
            return convert.toOperationResponse(savedAccount,transacao);
    }
    @Transactional
    public OperacaoDTOresponse Saque(Long id, BigDecimal amount) {
        Conta existingConta = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Conta não encontrada com id: " + id));
        Transacao transacao = existingConta.sacar(amount);
        Conta conta = accountRepository.save(existingConta);
            return convert.toOperationResponse(conta,transacao);

        }
    @Transactional
    public OperacaoDTOresponse Transferencia(Long idOrigem, Long idDestino, BigDecimal amount) {
        Conta contaOrigem = accountRepository.findById(idOrigem).orElseThrow(
                () -> new ResourceNotFoundException("Conta não encontrada com id: " + idOrigem));
        Conta contaDestino = accountRepository.findById(idDestino).orElseThrow(
                () -> new ResourceNotFoundException("Conta não encontrada com id: " + idDestino));
            Transacao transacao = contaOrigem.sacar(amount);
            contaDestino.deposito(amount);
            Conta savedContaOrigem = accountRepository.save(contaOrigem);
            accountRepository.save(contaDestino);
            return convert.toOperationResponse(savedContaOrigem,transacao);
    }

}
