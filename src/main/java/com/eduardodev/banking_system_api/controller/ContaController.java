package com.eduardodev.banking_system_api.controller;

import com.eduardodev.banking_system_api.dtos.request.ContaDtoCorrenteRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoEmpresarialRequest;
import com.eduardodev.banking_system_api.dtos.request.ContaDtoPoupancaRequest;
import com.eduardodev.banking_system_api.dtos.response.ContaCorrenteDtoResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoEmpresarialResponse;
import com.eduardodev.banking_system_api.dtos.response.ContaDtoPoupancaResponse;
import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.entities.ContaCorrente;
import com.eduardodev.banking_system_api.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Contas", description = "Visualizar todas as operações referente a Conta")
public class ContaController {

    @Autowired
    AccountService accountService;

    @GetMapping
    @Operation(summary = "Listar todas as contas")
    @ApiResponse(responseCode = "200", description = "Contas listadas com sucesso")
    public ResponseEntity<List<Conta>> getAllAccounts() {
        List<Conta> contas = accountService.findAllAccounts();
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma conta por Id")
    @ApiResponse(responseCode = "200", description = "Conta retornada pelo [ID]W com sucesso")
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    public ResponseEntity<Conta> getAccountById(@PathVariable Long id) {
        Conta conta = accountService.findAccountById(id);
        if (conta != null) {
            return ResponseEntity.ok().body(conta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insert/cc")
    @Operation(summary = "Inserir uma conta corrente")
    @ApiResponse(responseCode = "201", description = "Conta Corrente criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Falha na criação")
    public ResponseEntity<ContaCorrenteDtoResponse> createAccount(@RequestBody ContaDtoCorrenteRequest conta) {
        ContaCorrenteDtoResponse createdConta = accountService.createAccountCorrente(conta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(createdConta.getId()).toUri();
        return ResponseEntity.created(uri).body(createdConta);
    }
    @PostMapping("/insert/ce")
    @Operation(summary = "Inserir uma conta empresarial")
    @ApiResponse(responseCode = "201", description = "Conta Empresarial criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Falha na criação")
    public ResponseEntity<ContaDtoEmpresarialResponse> createAccount(@RequestBody ContaDtoEmpresarialRequest conta) {
        ContaDtoEmpresarialResponse createdConta = accountService.createAccountEmpresarial(conta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(createdConta.getId()).toUri();
        return ResponseEntity.created(uri).body(createdConta);
    }
    @PostMapping("/insert/cp")
    @Operation(summary = "Inserir uma conta poupança")
    @ApiResponse(responseCode = "201", description = "Conta Poupança criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Falha na criação")
    public ResponseEntity<ContaDtoPoupancaResponse> createAccount(@RequestBody ContaDtoPoupancaRequest conta) {
        ContaDtoPoupancaResponse createdConta = accountService.createAccountPoupanca(conta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(createdConta.getId()).toUri();
        return ResponseEntity.created(uri).body(createdConta);
    }

    @PutMapping(value = "/deposit/{id}")
    @Operation(summary = "Realizar um deposito em uma conta de acordo com o [ID]")
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    public ResponseEntity<Conta> updateDeposito(@PathVariable Long id, @RequestBody BigDecimal valor) {
        Conta updatedConta = accountService.Deposit(id, valor);
        if (updatedConta != null) {
            return ResponseEntity.ok().body(updatedConta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/saque/{id}")
    @Operation(summary = "Realizar um saque em uma conta de acordo com o [ID]")
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    public ResponseEntity<Conta> updateSaque(@PathVariable Long id, @RequestBody BigDecimal valor) {
        Conta updatedConta = accountService.Saque(id, valor);
        if (updatedConta != null) {
            return ResponseEntity.ok().body(updatedConta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/delete/{id}")
    @Operation(summary = "Deletar uma conta pelo [ID]")
    @ApiResponse(responseCode = "204", description = "Conta deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/transf/{idOrigem}/{idDestino}")
    @Operation(summary = "Realizar uma transferência com base no [ID] de uma conta [ORIGEM] para o [ID] da conta [DESTINO]")
    @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Conta [DESTINO] ou [ORIGEM] não encontradas")
    public ResponseEntity<Conta> updateTransferencia(@PathVariable Long idOrigem, @PathVariable Long idDestino, @RequestBody BigDecimal valor) {
        Conta updatedConta = accountService.Transferencia(idOrigem, idDestino, valor);
        if (updatedConta != null) {
            return ResponseEntity.ok().body(updatedConta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
