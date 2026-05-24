package com.eduardodev.banking_system_api.config;

import com.eduardodev.banking_system_api.entities.*;
import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.repository.AccountRepository;
import com.eduardodev.banking_system_api.repository.TransactionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (accountRepository.count() > 0) {
            System.out.println("DataInitializer: dados já existem, pulando seed.");
            return;
        }

        ContaCorrente cc1 = new ContaCorrente();
        cc1.setTitular("Alice Silva");
        cc1.setBalance(new BigDecimal("1000.00"));

        ContaPoupanca cp1 = new ContaPoupanca();
        cp1.setTitular("Bob Santos");
        cp1.setBalance(new BigDecimal("2500.00"));

        ContaEmpresarial ce1 = new ContaEmpresarial();
        ce1.setTitular("Acme Ltda");
        ce1.setBalance(new BigDecimal("50000.00"));
        ce1.setEmprestimoPers(new BigDecimal("10000.00"));


        accountRepository.saveAll(Arrays.asList(cc1, cp1, ce1));

        List<Transacao> manualTxs = new ArrayList<>();

        Transacao t1 = new Transacao();
        t1.setTipoOperacao(TipoOperacao.OPERACAO_DEPOSITO);
        t1.setValor(new BigDecimal("1000.00"));
        t1.setSaldoApos(cc1.getBalance());
        t1.setData(Instant.now());
        cc1.addTransacao(t1);
        manualTxs.add(t1);

        Transacao t2 = new Transacao();
        t2.setTipoOperacao(TipoOperacao.OPERACAO_DEPOSITO);
        t2.setValor(new BigDecimal("2500.00"));
        t2.setSaldoApos(cp1.getBalance());
        t2.setData(Instant.now());
        cp1.addTransacao(t2);
        manualTxs.add(t2);

        Transacao t3 = new Transacao();
        t3.setTipoOperacao(TipoOperacao.OPERACAO_DEPOSITO);
        t3.setValor(new BigDecimal("10000.00"));
        t3.setSaldoApos(ce1.getBalance());
        t3.setData(Instant.now());
        ce1.addTransacao(t3);
        manualTxs.add(t3);

        accountRepository.saveAll(Arrays.asList(cc1, cp1, ce1));
        transactionRepository.saveAll(manualTxs);

        try {
            cc1.sacar(new BigDecimal("150.00"));
            accountRepository.save(cc1);
        } catch (Exception e) {
            System.out.println("Saque demo falhou: " + e.getMessage());
        }
        try {
            cc1.transferencia(new BigDecimal("150.00"), cp1);
            accountRepository.saveAll(Arrays.asList(cc1, cp1));
        } catch (Exception e) {
            System.out.println("Transferência demo falhou: " + e.getMessage());
        }
        try {
            ce1.sacar(new BigDecimal("5000.00"));
            accountRepository.save(ce1);
        } catch (Exception e) {
            System.out.println("Saque empresarial demo falhou: " + e.getMessage());
        }
        System.out.println("Seed finalizado:");
        accountRepository.findAll().forEach(a -> {
            System.out.println("Conta: " + a.getTitular() + " - Saldo: " + a.getBalance());
            if (a.getHistoricoTransacoes() != null) {
                a.getHistoricoTransacoes().forEach(tx -> {
                    String dataStr = tx.getData() == null ? "N/A" : tx.getData().toString();
                    System.out.println("  TX: " + tx.getTipoOperacao() + " | valor=" + tx.getValor() + " | saldoApos=" + tx.getSaldoApos() + " | data=" + dataStr);
                });
            }
        });
    }
}