package com.eduardodev.banking_system_api;

import com.eduardodev.banking_system_api.entities.*;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.repository.AccountRepository;
import com.eduardodev.banking_system_api.repository.TransactionRepository;
import com.eduardodev.banking_system_api.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BankingSystemApiApplicationTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@BeforeEach
	void limpar() {
		transactionRepository.deleteAll();
		accountRepository.deleteAll();
	}

	@Test
	void deveriaCriarContaCorrente() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("1000.00"));

		Conta salva = accountService.createAccount(cc);

		assertNotNull(salva.getIdConta());
		assertEquals("Eduardo", salva.getTitular());
		assertEquals(new BigDecimal("1000.00"), salva.getBalance());
	}

	@Test
	void deveriaRealizarDepositoContaCorrente() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("1000.00"));
		accountService.createAccount(cc);

		Conta atualizada = accountService.updateDeposit(cc.getIdConta(), new BigDecimal("500.00"));

		assertEquals(new BigDecimal("1490.00"), atualizada.getBalance());
	}

	@Test
	void deveriaRealizarSaqueContaCorrente() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("1000.00"));
		accountService.createAccount(cc);

		Conta atualizada = accountService.updateSaque(cc.getIdConta(), new BigDecimal("100.00"));

		assertEquals(new BigDecimal("875.00"), atualizada.getBalance());
	}

	@Test
	void deveriaLancarSaldoInsuficienteNoSaqueCorrente() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("10.00"));
		accountService.createAccount(cc);

		assertThrows(SaldoInsuficienteException.class, () ->
				accountService.updateSaque(cc.getIdConta(), new BigDecimal("500.00"))
		);
	}

	@Test
	void deveriaLancarLimiteExcedidoNoDepositoCorrente() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("1000.00"));
		accountService.createAccount(cc);

		assertThrows(LimiteExcedidoException.class, () ->
				accountService.updateDeposit(cc.getIdConta(), new BigDecimal("3000.00"))
		);
	}

	@Test
	void deveriaCriarContaPoupanca() {
		ContaPoupanca cp = new ContaPoupanca();
		cp.setTitular("Alice");
		cp.setBalance(new BigDecimal("2000.00"));
		Conta salva = accountService.createAccount(cp);
		assertNotNull(salva.getIdConta());
		assertEquals(new BigDecimal("2000.00"), salva.getBalance());
	}

	@Test
	void deveriaCalcularRendimentoPoupanca() {
		ContaPoupanca cp = new ContaPoupanca();
		cp.setTitular("Alice");
		cp.setBalance(new BigDecimal("1000.00"));
		BigDecimal rendimento = cp.getRendimento();

		assertEquals(new BigDecimal("8.0000000000000000"), rendimento.stripTrailingZeros());
	}

	@Test
	void deveriaLancarLimiteExcedidoNoDepositoPoupanca() {
		ContaPoupanca cp = new ContaPoupanca();
		cp.setTitular("Alice");
		cp.setBalance(new BigDecimal("500.00"));
		accountService.createAccount(cp);

		assertThrows(LimiteExcedidoException.class, () ->
				accountService.updateDeposit(cp.getIdConta(), new BigDecimal("11000.00"))
		);
	}

	@Test
	void deveriaCriarContaEmpresarial() {
		ContaEmpresarial ce = new ContaEmpresarial();
		ce.setTitular("Acme Ltda");
		ce.setBalance(new BigDecimal("50000.00"));

		Conta salva = accountService.createAccount(ce);

		assertNotNull(salva.getIdConta());
		assertEquals("Acme Ltda", salva.getTitular());
	}
	@Test
	void deveriaLancarLimiteExcedidoNoSaqueEmpresarial() {
		ContaEmpresarial ce = new ContaEmpresarial();
		ce.setTitular("Acme Ltda");
		ce.setBalance(new BigDecimal("50000.00"));
		accountService.createAccount(ce);

		// limite de saque empresarial é R$ 20.000
		assertThrows(LimiteExcedidoException.class, () ->
				accountService.updateSaque(ce.getIdConta(), new BigDecimal("20000.00"))
		);
	}
	@Test
	void deveriaLancarLimiteExcedidoNoDepositoEmpresarial() {
		ContaEmpresarial ce = new ContaEmpresarial();
		ce.setTitular("Acme Ltda");
		ce.setBalance(new BigDecimal("50000.00"));
		accountService.createAccount(ce);

		assertThrows(LimiteExcedidoException.class, () ->
				accountService.updateDeposit(ce.getIdConta(), new BigDecimal("6000.00"))
		);
	}
	@Test
	void deveriaRegistrarTransacaoAoDepositar() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("1000.00"));
		accountService.createAccount(cc);

		accountService.updateDeposit(cc.getIdConta(), new BigDecimal("500.00"));

		Conta atualizada = accountService.findAccountById(cc.getIdConta());
		assertFalse(atualizada.getHistoricoTransacoes().isEmpty());
	}

	@Test
	void deveriaRegistrarTransacaoAoSacar() {
		ContaCorrente cc = new ContaCorrente();
		cc.setTitular("Eduardo");
		cc.setBalance(new BigDecimal("1000.00"));
		accountService.createAccount(cc);

		accountService.updateSaque(cc.getIdConta(), new BigDecimal("100.00"));

		Conta atualizada = accountService.findAccountById(cc.getIdConta());
		assertFalse(atualizada.getHistoricoTransacoes().isEmpty());
	}
}