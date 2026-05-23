package com.eduardodev.banking_system_api.resources;

import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class ContaResource {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Conta>> getAllAccounts() {
        List<Conta> contas = accountService.findAllAccounts();
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getAccountById(@PathVariable Long id) {
        Conta conta = accountService.findAccountById(id);
        if (conta != null) {
            return ResponseEntity.ok().body(conta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Conta> createAccount(@RequestBody Conta conta) {
        Conta createdConta = accountService.createAccount(conta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(createdConta.getIdConta()).toUri();
        return ResponseEntity.created(uri).body(createdConta);
    }
    @PutMapping(value = "/deposit/{id}")
    public ResponseEntity<Conta> updateDeposito(@PathVariable Long id, @RequestBody BigDecimal valor) {
        Conta updatedConta = accountService.updateDeposit(id, valor);
        if (updatedConta != null) {
            return ResponseEntity.ok().body(updatedConta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/saque/{id}")
    public ResponseEntity<Conta> updateSaque(@PathVariable Long id, @RequestBody BigDecimal valor) {
        Conta updatedConta = accountService.updateSaque(id, valor);
        if (updatedConta != null) {
            return ResponseEntity.ok().body(updatedConta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
