package com.eduardodev.banking_system_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public String home() {
        return """
           
                          BEM-VINDO AO SISTEMA BANCÁRIO API
         
           
            Esta API oferece funcionalidades completas para gerenciar contas bancárias e
            transações. Abaixo estão todos os endpoints disponíveis:
           ------------------------------------------------------------------------------
            GERENCIAMENTO DE CONTAS - /accounts
           ------------------------------------------------------------------------------ 
            GET /accounts
              → Retorna a lista de todas as contas bancárias cadastradas
              → Resposta: Array de contas com informações completas
            
            GET /accounts/{id}
              → Busca uma conta específica pelo ID
              → Parâmetro: id (Long) - Identificador único da conta
              → Resposta: Dados detalhados da conta
            
            POST /accounts
              → Cria uma nova conta bancária
              → Body: Objeto com informações da conta (tipo, titular, etc)
              → Resposta: Conta criada com status 201 (Created)
            
            PUT /accounts/deposit/{id}
              → Realiza um depósito em uma conta
              → Parâmetro: id (Long) - ID da conta
              → Body: Valor do depósito (BigDecimal)
              → Resposta: Conta atualizada com novo saldo
            
            PUT /accounts/saque/{id}
              → Realiza um saque de uma conta
              → Parâmetro: id (Long) - ID da conta
              → Body: Valor do saque (BigDecimal)
              → Resposta: Conta atualizada com novo saldo
              → Validações: Verifica saldo e limite disponível
            
            PUT /accounts/transf/{idOrigem}/{idDestino}
              → Realiza uma transferência entre contas
              → Parâmetros: idOrigem (Long), idDestino (Long)
              → Body: Valor a transferir (BigDecimal)
              → Resposta: Conta de origem atualizada
              → Validações: Verifica saldo em ambas as contas
            
            PUT /accounts/delete/{id}
              → Deleta (desativa) uma conta bancária
              → Parâmetro: id (Long) - ID da conta
              → Resposta: Status 204 (No Content)
           ------------------------------------------------------------------------------
            HISTÓRICO DE TRANSAÇÕES - /transactions
           ------------------------------------------------------------------------------ 
            GET /transactions
              → Retorna o histórico de todas as transações do sistema
              → Resposta: Array com todas as transações registradas
            
            GET /transactions/{id}
              → Busca uma transação específica pelo ID
              → Parâmetro: id (Long) - Identificador da transação
              → Resposta: Detalhes completos da transação
           ------------------------------------------------------------------------------           
            TIPOS DE CONTA SUPORTADOS     
           ------------------------------------------------------------------------------ 
            • CORRENTE - Conta Corrente (uso geral com possibilidade de limite)
            • POUPANCA - Conta Poupança (com rendimento)
            • EMPRESARIAL - Conta Empresarial (com taxas comerciais)
            
           ------------------------------------------------------------------------------
            EXEMPLO DE USO
           ------------------------------------------------------------------------------       
            1. Criar uma conta: POST /accounts
            2. Consultar conta: GET /accounts/{id}
            3. Fazer depósito: PUT /accounts/deposit/{id}
            4. Fazer saque: PUT /accounts/saque/{id}
            5. Transferir entre contas: PUT /accounts/transf/{idOrigem}/{idDestino}
            6. Consultar transações: GET /transactions
            ------------------------------------------------------------------------------
            """;
    }
}
