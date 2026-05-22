package com.eduardodev.banking_system_api.resources;

import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contas")
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
        return ResponseEntity.ok().body(createdConta);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Conta> updateAccount(@PathVariable Long id, @RequestBody Conta conta) {
        Conta existingConta = accountService.findAccountById(id);
        if (existingConta != null) {
            conta.setIdConta(id);
            Conta updatedConta = accountService.updateAccount(conta);
            return ResponseEntity.ok().body(updatedConta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
