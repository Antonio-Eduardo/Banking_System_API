package com.eduardodev.banking_system_api.service.converter;

import com.eduardodev.banking_system_api.dtos.request.ContaDtoCorrenteRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoEmpresarialRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoPoupancaRequest;
import com.eduardodev.banking_system_api.dtos.response.ContaCorrenteDtoResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoEmpresarialResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoPoupancaResponse;
import com.eduardodev.banking_system_api.dtos.response.OperacaoDTOresponse;
import com.eduardodev.banking_system_api.entities.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ContaConverter {
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
    public OperacaoDTOresponse toOperationResponse (Conta conta, Transacao transacao){
        OperacaoDTOresponse operacaoDTOresponse = new OperacaoDTOresponse();
        operacaoDTOresponse.setId(conta.getIdConta());
        operacaoDTOresponse.setTitular(conta.getTitular());
        operacaoDTOresponse.setSaldoApos(conta.getBalance());
        operacaoDTOresponse.setDataHora(Instant.now());
        operacaoDTOresponse.setNumeroConta(conta.getNumeroConta());
        operacaoDTOresponse.setAgencia(conta.getAgencia());
        operacaoDTOresponse.setValorOperacao(transacao.getValor());
        operacaoDTOresponse.setTipoOperacao(transacao.getTipoOperacao().getValue());
        return operacaoDTOresponse;
    }
}
