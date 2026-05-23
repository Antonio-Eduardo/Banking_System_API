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

O projeto evoluiu de um sistema de console até uma API REST completa com Spring Boot, utilizando PostgreSQL como banco de dados e Docker para gerenciamento do ambiente.

---

## Evolução do Projeto

1. **Arquivos de texto (.txt)** — persistência simples para validar a lógica de negócio
2. **JDBC + MySQL** — persistência relacional com controle manual de conexões
3. **JPA / Hibernate** — mapeamento objeto-relacional com controle transacional via `EntityManager`
4. **Spring Boot + REST API** — exposição dos dados via endpoints HTTP, com Spring Data JPA e PostgreSQL rodando em Docker

---

## Funcionalidades

- [x] Criação de contas: Corrente, Empresarial e Poupança
- [x] Depósito com atualização de saldo
- [x] Saque com validação de saldo e limites
- [x] Transferência entre contas com consistência transacional
- [x] Histórico de transações ordenado cronologicamente
- [x] Tratamento de erros: saldo insuficiente, limite excedido, exceções de banco

---

## Tecnologias Utilizadas

- **Java 25 / Spring Boot 4**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (rodando via Docker)
- **Docker / Docker Desktop**
- **Lombok**
- **Maven**
- **Testcontainers** (testes de integração)
- **Git / GitHub**

---

## Estrutura do Projeto

```
src/
└── main/
    └── java/com/eduardodev/banking_system_api/
        ├── config/         → Seed de dados (TestConfig)
        ├── entities/       → Conta, ContaCorrente, ContaPoupanca, ContaEmpresarial, Transacao
        ├── enums/          → TipoOperacao, ErrorCode
        ├── exceptions/     → NegocioException, SaldoInsuficienteException, LimiteExcedidoException, DBException
        ├── interfaces/     → OperacaoBanco, Tax
        ├── interfaces/impl → OperacaoBancoImpl (controle transacional legado)
        ├── repository/     → AccountRepository, TransactionRepository
        ├── resources/      → ContaResource, TransactionResource (Controllers REST)
        └── service/        → AccountService, TransactionService
```

---

## Endpoints

| Método | Endpoint             | Descrição                        |
|--------|----------------------|----------------------------------|
| GET    | `/contas`            | Lista todas as contas            |
| GET    | `/contas/{id}`       | Busca uma conta por ID           |
| GET    | `/transactions`      | Lista todas as transações        |
| GET    | `/transactions/{id}` | Busca uma transação por ID       |

> A aplicação roda por padrão na porta `8081`.

---

## Regras de Negócio

- **Conta Corrente** — taxa fixa de R$25,00 por saque; taxa de 2% sobre depósitos e transferências
- **Conta Empresarial** — taxa fixa de R$50,00 por saque; taxa de 7% sobre depósitos e transferências; limite de saque de R$20.000
- **Conta Poupança** — taxa de 0,5% por operação; depósitos limitados a R$10.000; rendimento de 0,8% sobre o saldo
- **Transferências** — utilizam controle transacional para garantir consistência entre as contas

---

## Conceitos Aplicados

- Herança e Polimorfismo
- Encapsulamento e Abstração
- Interfaces e DAO Pattern
- Service Layer
- Injeção de Dependência (Spring)
- Separação de Responsabilidades
- Controle Transacional com JPA
- Spring Data JPA / JPQL
- REST com Spring MVC

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

3. **Configure as credenciais** em `src/main/resources/application.properties` se necessário.

4. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a API:**
   ```
   http://localhost:8081/contas
   http://localhost:8081/transactions
   ```

---

## Melhorias Futuras

- [ ] Endpoints de operações (depósito, saque, transferência) via POST
- [ ] Testes automatizados com Testcontainers
- [ ] Uso de `BigDecimal` para operações financeiras
- [ ] Sistema de autenticação (Spring Security)
- [ ] Documentação com Swagger/OpenAPI
- [ ] Interface gráfica
