# Sistema Bancário Multicliente (Java)

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
[![Static Badge](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/Sistema_Bancario/blob/master/LICENSE)

> Sistema de operações bancárias com 3 tipos de conta, simulando o fluxo real de uma instituição financeira.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Evolução do Projeto](#evolução-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Regras de Negócio](#regras-de-negócio)
- [Conceitos Aplicados](#conceitos-aplicados)
- [Exemplo de Saída](#exemplo-de-saída)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

O **Sistema Bancário Multicliente** é uma aplicação Java com foco em Programação Orientada a Objetos e persistência de dados. O sistema permite a criação e gerenciamento de contas bancárias, realizando operações como depósitos, saques, transferências e consulta de histórico de transações.

> *Nota de desenvolvimento:* O projeto foi construído com atenção à separação de responsabilidades entre camadas (DAO, Service, Entities), aplicando herança e polimorfismo para modelar os diferentes tipos de conta. A persistência evoluiu progressivamente de arquivos de texto até JPA/Hibernate com controle transacional manual, consolidando o entendimento de ORM e gerenciamento de transações.

---

## Evolução do Projeto

O projeto passou por três fases de evolução:

1. **Arquivos de texto (.txt)** — persistência simples para validação da lógica de negócio
2. **JDBC + MySQL** — persistência relacional com controle manual de conexões
3. **JPA / Hibernate** — mapeamento objeto-relacional com gerenciamento automático de transações via `EntityManager`

---

## Funcionalidades

- [x] **Criação de Contas:** Conta Corrente, Conta Empresarial e Conta Poupança
- [x] **Depósito:** Crédito de valores com atualização de saldo
- [x] **Saque:** Débito com validação de saldo e limites
- [x] **Transferência:** Movimentação entre contas com consistência transacional
- [x] **Histórico de Transações:** Registro bidirecional ordenado cronologicamente de forma decrescente
- [x] **Tratamento de Erros:** Saldo insuficiente, limite excedido e exceções de banco de dados

---

## Tecnologias Utilizadas

- **Java** (JDK 17+)
- **JPA / Hibernate**
- **JDBC**
- **MySQL**
- **Maven**
- **Git / GitHub**

---

## Estrutura do Projeto

```
src/
├── main/
│   ├── entities/       → Classes das contas e transações
│   ├── dao/            → Interfaces DAO
│   ├── dao/impl/       → Implementação JPA/Hibernate/JPQL
│   ├── dao/db/         → Conexão com banco de dados
│   ├── exceptions/     → Tratamento de erros
│   └── Main.java       → Execução no console
└── services/           → Regras de negócio
```

---

## Regras de Negócio

- **Conta Corrente** possui taxa fixa por operação
- **Conta Empresarial** possui limite de saque
- **Conta Poupança** possui comportamento diferenciado de rendimento
- **Transferências** utilizam transações SQL para garantir consistência dos dados

---

## Conceitos Aplicados

- Herança e Polimorfismo
- Encapsulamento e Abstração
- Interfaces e DAO Pattern
- Service Layer
- Injeção de Dependência
- Separação de Responsabilidades
- Controle Transacional com JPA (`begin`, `commit`, `rollback`)
- JPQL

---

## Exemplo de Saída

```text
Conta [Corrente]
Titular: Eduardo
Número: 1273
Saldo: 300.00

Transações:
DEPOSITO      | R$ 200.00 | Saldo: 200.00
DEPOSITO      | R$ 200.00 | Saldo: 400.00
SAQUE         | R$ 50.00  | Saldo: 350.00
TRANSFERENCIA | R$ 100.00 | Saldo: 250.00
```

---

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Antonio-Eduardo/Sistema_Bancario.git
   ```

2. **Acesse a pasta do projeto:**
   ```bash
   cd Sistema_Bancario
   ```

3. **Configure o banco de dados MySQL** e ajuste as credenciais na classe `DB.java`

4. **Execute a aplicação:**
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```

---

## Melhorias Futuras

- [ ] API REST com Spring Boot
- [ ] Testes automatizados
- [ ] Uso de `BigDecimal` para operações financeiras
- [ ] Sistema de autenticação de usuários
- [ ] Interface gráfica
