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
        ContaCorrente conta = toEntityCC(contaDTOrequest);
         ContaCorrente savedConta = accountRepository.save(conta);
        return toResponseCC(savedConta);

    }
    @Transactional
    public ContaDtoPoupancaResponse createAccountPoupanca(ContaDtoPoupancaRequest contaDTOrequest) {
        ContaPoupanca conta = toEntityCP(contaDTOrequest);
        ContaPoupanca savedConta =accountRepository.save(conta);
        return toResponseCP(savedConta);
    }
    @Transactional
    public ContaDtoEmpresarialResponse createAccountEmpresarial(ContaDtoEmpresarialRequest contaDTOrequest) {
        ContaEmpresarial conta = toEntityCE(contaDTOrequest);
        ContaEmpresarial savedConta = accountRepository.save(conta);
        return toResponseCE(savedConta);
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
    public ContaCorrenteDtoResponse toResponseCC(ContaCorrente contaRequest){
       ContaCorrenteDtoResponse contaCorrenteDtoResponse = new ContaCorrenteDtoResponse();
       contaCorrenteDtoResponse.setId(contaRequest.getIdConta());
       contaCorrenteDtoResponse.setTitular(contaRequest.getTitular());
       contaCorrenteDtoResponse.setBalance(contaRequest.getBalance());
       contaCorrenteDtoResponse.setNumeroConta(contaRequest.getNumeroConta());
       contaCorrenteDtoResponse.setAtiva(contaRequest.isAtiva());
       contaCorrenteDtoResponse.setDataAbertura(contaRequest.getDataAbertura());
       contaCorrenteDtoResponse.setAgencia(contaRequest.getAgencia());
       contaCorrenteDtoResponse.setLimiteChequeEspecial(contaRequest.getLimiteChequeEspecial());
       return contaCorrenteDtoResponse;
    }
    public ContaDtoEmpresarialResponse toResponseCE(ContaEmpresarial contaRequest){
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
    public ContaDtoPoupancaResponse toResponseCP(ContaPoupanca contaRequest){
        ContaDtoPoupancaResponse contaPoupancaDtoResponse = new ContaDtoPoupancaResponse();
        contaPoupancaDtoResponse.setId(contaRequest.getIdConta());
        contaPoupancaDtoResponse.setTitular(contaRequest.getTitular());
        contaPoupancaDtoResponse.setBalance(contaRequest.getBalance());
        contaPoupancaDtoResponse.setNumeroConta(contaRequest.getNumeroConta());
        contaPoupancaDtoResponse.setAtiva(contaRequest.isAtiva());
        contaPoupancaDtoResponse.setDataAbertura(contaRequest.getDataAbertura());
        contaPoupancaDtoResponse.setAgencia(contaRequest.getAgencia());
        contaPoupancaDtoResponse.setDataAniversario(contaRequest.getDataAniversario());
        return contaPoupancaDtoResponse;
    }
    public ContaEmpresarial toEntityCE(ContaDtoEmpresarialRequest conta) {
        ContaEmpresarial contaEmpresarial = new ContaEmpresarial();
        contaEmpresarial.setTitular(conta.getTitular());
        contaEmpresarial.setBalance(conta.getBalance());
        contaEmpresarial.setNumeroConta(conta.getNumeroConta());
        contaEmpresarial.setAtiva(conta.isAtiva());
        contaEmpresarial.setDataAbertura(conta.getDataAbertura());
        contaEmpresarial.setAgencia(conta.getAgencia());
        contaEmpresarial.setRazaoSocial(conta.getRazaoSocial());
        contaEmpresarial.setCnpj(conta.getCnpj());
        contaEmpresarial.setEmprestimo(conta.getEmprestimo());
        return contaEmpresarial;
    }
    public ContaPoupanca toEntityCP(ContaDtoPoupancaRequest conta) {
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setTitular(conta.getTitular());
        contaPoupanca.setBalance(conta.getBalance());
        contaPoupanca.setNumeroConta(conta.getNumeroConta());
        contaPoupanca.setAtiva(conta.isAtiva());
        contaPoupanca.setDataAbertura(conta.getDataAbertura());
        contaPoupanca.setAgencia(conta.getAgencia());
        contaPoupanca.setDataAniversario(conta.getDataAniversario());
        return contaPoupanca;
    }
    public ContaCorrente toEntityCC(ContaDtoCorrenteRequest conta) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setTitular(conta.getTitular());
        contaCorrente.setBalance(conta.getBalance());
        contaCorrente.setNumeroConta(conta.getNumeroConta());
        contaCorrente.setAtiva(conta.isAtiva());
        contaCorrente.setDataAbertura(conta.getDataAbertura());
        contaCorrente.setAgencia(conta.getAgencia());
        contaCorrente.setLimiteChequeEspecial(conta.getLimiteChequeEspecial());
        return contaCorrente;
    }
}
