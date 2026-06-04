package com.eduardodev.banking_system_api.service;

import com.eduardodev.banking_system_api.dtos.request.ContaDtoCorrenteRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoEmpresarialRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoPoupancaRequest;
import com.eduardodev.banking_system_api.dtos.request.superdtoconta.ContaDTO;
import com.eduardodev.banking_system_api.dtos.response.ContaCorrenteDtoResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoEmpresarialResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoPoupancaResponse;
import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.entities.ContaCorrente;
import com.eduardodev.banking_system_api.entities.ContaEmpresarial;
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

        return convertContaCorrente(savedConta);

    }
    @Transactional
    public ContaDtoPoupancaResponse createAccountPoupanca(ContaDtoPoupancaRequest contaDTOrequest) {
        ContaPoupanca conta = new ContaPoupanca();
        conta.setTitular(contaDTOrequest.getTitular());
        conta.setBalance(contaDTOrequest.getBalance());
        conta.setAtiva(contaDTOrequest.isAtiva());
        conta.setNumeroConta(contaDTOrequest.getNumeroConta());
        conta.setDataAbertura(LocalDate.now());

        ContaPoupanca savedConta =accountRepository.save(conta);

        return convertContaPoupanca(savedConta);
    }
    @Transactional
    public ContaDtoEmpresarialResponse createAccountEmpresarial(ContaDtoEmpresarialRequest contaDTOrequest) {
        ContaEmpresarial conta = new ContaEmpresarial();
        conta.setTitular(contaDTOrequest.getTitular());
        conta.setBalance(contaDTOrequest.getBalance());
        conta.setNumeroConta(contaDTOrequest.getNumeroConta());
        conta.setAtiva(contaDTOrequest.isAtiva());
        conta.setDataAbertura(LocalDate.now());
        conta.setAgencia(contaDTOrequest.getAgencia());
        conta.setRazaoSocial(contaDTOrequest.getRazaoSocial());
        conta.setCnpj(contaDTOrequest.getCnpj());
        conta.setEmprestimo(contaDTOrequest.getEmprestimo());

        ContaEmpresarial savedConta = accountRepository.save(conta);

        return convertContaEmpresarial(savedConta);
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
    public ContaCorrenteDtoResponse convertContaCorrente(ContaCorrente contaRequest){
       ContaCorrenteDtoResponse contaCorrenteDtoResponse = new ContaCorrenteDtoResponse();
       contaCorrenteDtoResponse.setTitular(contaRequest.getTitular());
       contaCorrenteDtoResponse.setBalance(contaRequest.getBalance());
       contaCorrenteDtoResponse.setNumeroConta(contaRequest.getNumeroConta());
       contaCorrenteDtoResponse.setAtiva(contaRequest.isAtiva());
       contaCorrenteDtoResponse.setDataAbertura(contaRequest.getDataAbertura());
       contaCorrenteDtoResponse.setAgencia(contaRequest.getAgencia());
       contaCorrenteDtoResponse.setLimiteChequeEspecial(contaRequest.getLimiteChequeEspecial());
       return contaCorrenteDtoResponse;
    }
    public ContaDtoEmpresarialResponse convertContaEmpresarial(ContaEmpresarial contaRequest){
        ContaDtoEmpresarialResponse contaEmpresarialDtoResponse = new ContaDtoEmpresarialResponse();
        contaEmpresarialDtoResponse.setId(contaRequest.getIdConta());
        contaEmpresarialDtoResponse.setTitular(contaRequest.getTitular());
        contaEmpresarialDtoResponse.setBalance(contaRequest.getBalance());
        contaEmpresarialDtoResponse.setNumeroConta(contaRequest.getNumeroConta());
        contaEmpresarialDtoResponse.setAtiva(contaRequest.isAtiva());
        contaEmpresarialDtoResponse.setDataAbertura(contaRequest.getDataAbertura());
        contaEmpresarialDtoResponse.setAgencia(contaRequest.getAgencia());
        contaEmpresarialDtoResponse.setRazaoSocial(contaRequest.getRazaoSocial());
        contaEmpresarialDtoResponse.setCnpj(contaRequest.getCnpj());
        contaEmpresarialDtoResponse.setEmprestimo(contaRequest.getEmprestimo());
        return contaEmpresarialDtoResponse;
    }
    public ContaDtoPoupancaResponse convertContaPoupanca(ContaPoupanca contaRequest){
        ContaDtoPoupancaResponse contaPoupancaDtoResponse = new ContaDtoPoupancaResponse();
        contaPoupancaDtoResponse.setTitular(contaRequest.getTitular());
        contaPoupancaDtoResponse.setBalance(contaRequest.getBalance());
        contaPoupancaDtoResponse.setNumeroConta(contaRequest.getNumeroConta());
        contaPoupancaDtoResponse.setAtiva(contaRequest.isAtiva());
        contaPoupancaDtoResponse.setDataAbertura(contaRequest.getDataAbertura());
        contaPoupancaDtoResponse.setAgencia(contaRequest.getAgencia());
        contaPoupancaDtoResponse.setDataAniversario(contaRequest.getDataAniversario());
        return contaPoupancaDtoResponse;
    }
}
