package com.eduardodev.banking_system_api.controller;

import com.eduardodev.banking_system_api.entities.Transacao;
import com.eduardodev.banking_system_api.repository.TransactionRepository;
import com.eduardodev.banking_system_api.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transações", description = "Operações relacionadas a transação")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Retornar todas as transações")
    @ApiResponse(responseCode = "200", description = "Transações retornadas com sucessos")
    public ResponseEntity<List<Transacao>> getAllTransactions() {
        List<Transacao> transactions = transactionService.findAllTransactions().stream().toList();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Retornar um transação pelo seu [ID]")
    @ApiResponse(responseCode = "200", description = "Transação retornada com sucesso")
    @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    public ResponseEntity<Transacao> getTransactionById(Long id){
        Transacao transacao = transactionService.findTransactionById(id);
        return ResponseEntity.ok().body(transacao);
    }
}
