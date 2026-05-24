# Sistema Bancário API (Java + Spring Boot)

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
[![Licença MIT](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/Sistema_Bancario/blob/master/LICENSE)

> API REST de operações bancárias com 3 tipos de conta, construída com Spring Boot e persistência em PostgreSQL via Docker.

---

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Evolução do Projeto](#evolução-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints](#endpoints)
- [Regras de Negócio](#regras-de-negócio)
- [Conceitos Aplicados](#conceitos-aplicados)
- [Como Executar](#como-executar)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

O **Sistema Bancário API** é uma aplicação Java com foco em Programação Orientada a Objetos, persistência de dados e arquitetura REST. O sistema gerencia contas bancárias e registra operações como depósitos, saques, transferências e histórico de transações.

O projeto evoluiu de um sistema de console até uma API REST completa com Spring Boot, utilizando PostgreSQL como banco de dados e Docker para gerenciamento do ambiente. Todas as operações financeiras utilizam `BigDecimal` para garantir precisão nos cálculos.

---

## Evolução do Projeto

1. **Arquivos de texto (.txt)** — persistência simples para validar a lógica de negócio
2. **JDBC + MySQL** — persistência relacional com controle manual de conexões
3. **JPA / Hibernate** — mapeamento objeto-relacional com controle transacional via `EntityManager`
4. **Spring Boot + REST API** — endpoints HTTP com Spring Data JPA, PostgreSQL via Docker e `BigDecimal` para precisão financeira

---

## Funcionalidades

- [x] Criação de contas via endpoint REST
- [x] Depósito com atualização de saldo
- [x] Saque com validação de saldo e limites
- [x] Transferência entre contas com consistência transacional
- [x] Histórico de transações por conta
- [x] Listagem de todas as transações
- [x] Tratamento de erros: saldo insuficiente, limite excedido, exceções de banco

---

## Tecnologias Utilizadas

- **Java 25 / Spring Boot 4**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (via Docker)
- **Docker / Docker Desktop**
- **Lombok**
- **BigDecimal** (precisão financeira)
- **Testcontainers** (testes de integração)
- **Maven**
- **Git / GitHub**

---

## Estrutura do Projeto

```
src/
└── main/
    └── java/com/eduardodev/banking_system_api/
        ├── config/           → Seed de dados para perfil de teste (TestConfig)
        ├── entities/         → Conta (abstrata), ContaCorrente, ContaPoupanca, ContaEmpresarial, Transacao
        ├── enums/            → TipoOperacao, TipoConta, ErrorCode
        ├── exceptions/       → NegocioException, SaldoInsuficienteException, LimiteExcedidoException, DBException, ValidacaoException
        ├── interfaces/       → OperacaoBanco, Tax
        ├── interfaces/impl/  → OperacaoBancoImpl
        ├── repository/       → AccountRepository, TransactionRepository
        ├── resources/        → ContaResource, TransactionResource (controllers REST)
        └── service/          → AccountService, TransactionService
```

---

## Endpoints

### Contas — `/accounts`

| Método | Endpoint                   | Descrição                        |
|--------|----------------------------|----------------------------------|
| GET    | `/accounts`                | Lista todas as contas            |
| GET    | `/accounts/{id}`           | Busca uma conta por ID           |
| POST   | `/accounts`                | Cria uma nova conta              |
| PUT    | `/accounts/deposit/{id}`   | Realiza um depósito na conta     |
| PUT    | `/accounts/saque/{id}`     | Realiza um saque na conta        |

### Transações — `/transactions`

| Método | Endpoint              | Descrição                        |
|--------|-----------------------|----------------------------------|
| GET    | `/transactions`       | Lista todas as transações        |
| GET    | `/transactions/{id}`  | Busca uma transação por ID       |

> A aplicação roda por padrão na porta `8081`.

---

## Regras de Negócio

### Conta Corrente
- Taxa de **2%** sobre depósitos e transferências
- Taxa fixa de **R$ 25,00** por saque
- Limite máximo de depósito: **R$ 2.500,00**
- Limite máximo de saque: **R$ 20.000,00**

### Conta Empresarial
- Taxa de **2%** sobre depósitos, saques e transferências
- Limite máximo de depósito: **R$ 5.000,00**
- Limite máximo de saque: **R$ 20.000,00**

### Conta Poupança
- Taxa de **2%** por operação
- Limite máximo de depósito: **R$ 10.000,00**
- Rendimento de **0,8%** sobre o saldo (`getRendimento()`)

### Geral
- Todas as operações financeiras utilizam `BigDecimal` com escala 2 e arredondamento `HALF_EVEN`
- Transferências atualizam saldo de origem e destino e registram transação em ambas as contas

---

## Conceitos Aplicados

- Herança e Polimorfismo
- Encapsulamento e Abstração
- Interfaces (`Tax`, `OperacaoBanco`) e DAO Pattern
- Service Layer
- Injeção de Dependência (Spring)
- Separação de Responsabilidades
- Spring Data JPA
- REST com Spring MVC
- `BigDecimal` para operações financeiras precisas

---

## Como Executar

### Pré-requisitos

- Java 25+
- Docker Desktop
- Maven

### Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Antonio-Eduardo/Sistema_Bancario.git
   cd Sistema_Bancario
   ```

2. **Suba o banco de dados com Docker:**
   ```bash
   docker run --name meu-postgres-terminal \
     -e POSTGRES_PASSWORD=minhasenha \
     -e POSTGRES_DB=banking_system \
     -p 5432:5432 \
     -d postgres:16-alpine
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a API:**
   ```
   GET  http://localhost:8081/accounts
   GET  http://localhost:8081/transactions
   ```

---

## Melhorias Futuras

- [ ] Endpoint de transferência entre contas via REST
- [ ] Tratamento global de exceções com `@ControllerAdvice`
- [x] Testes de integração com Testcontainers (13 testes, PostgreSQL via Testcontainers)
- [ ] Documentação com Swagger / OpenAPI
- [ ] Autenticação com Spring Security
- [ ] Paginação nos endpoints de listagem
- [ ] Interface gráfica
