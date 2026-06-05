# Banking System API

![Status do Projeto](https://img.shields.io/badge/status-production-brightgreen)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
[![Deploy](https://img.shields.io/badge/Railway-online-blueviolet)](https://bankingsystemapi-production.up.railway.app)
[![Licença MIT](https://img.shields.io/badge/licenca-MIT-green)](LICENSE)

> API REST de operações bancárias com 3 tipos de conta, regras de negócio distintas por tipo, 13 testes de integração com Testcontainers e persistência em PostgreSQL. Deploy ativo no Railway.

**[→ API em produção](https://bankingsystemapi-production.up.railway.app)**

---

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Evolução do Projeto](#evolução-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Endpoints](#endpoints)
- [Regras de Negócio](#regras-de-negócio)
- [Conceitos Aplicados](#conceitos-aplicados)
- [Como Executar](#como-executar)
- [Testes](#testes)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

O **Banking System API** é uma API REST para operações bancárias com três tipos de conta — Corrente, Poupança e Empresarial — cada um com regras de negócio distintas. Todas as operações financeiras usam `BigDecimal` com arredondamento `HALF_EVEN` para precisão financeira. O controle transacional é gerenciado pelo Spring via `@Transactional`, com rollback automático em caso de exceção.

A hierarquia de contas é modelada com herança `SINGLE_TABLE` e polimorfismo — cada tipo implementa suas próprias regras de taxa, limite e operação através de métodos abstratos definidos na superclasse `Conta`.

---

## Evolução do Projeto

| Fase | Persistência | Destaques |
|------|-------------|-----------|
| 1 | Arquivos `.txt` | Validação da lógica de negócio |
| 2 | JDBC + MySQL | Persistência relacional, controle manual de conexões |
| 3 | JPA / Hibernate | Mapeamento O/R, `EntityManager`, JPQL |
| 4 | **Spring Boot + REST API** | Endpoints HTTP, Spring Data JPA, PostgreSQL, `BigDecimal`, Testcontainers |

---

## Funcionalidades

- [x] Criação de contas via endpoints REST separados por tipo (Corrente, Poupança, Empresarial)
- [x] Depósito com aplicação de taxa por tipo de conta
- [x] Saque com validação de saldo e limites por tipo de conta
- [x] Transferência entre contas com débito na origem e crédito no destino
- [x] Histórico de transações bidirecional por conta (`@OneToMany`)
- [x] Rendimento automático na conta poupança (`getRendimento()`)
- [x] Hierarquia de exceções com `ErrorCode` enum e tratamento global via `@RestControllerAdvice`
- [x] Documentação interativa com Swagger/OpenAPI
- [x] Controle de schema com Flyway (migrations versionadas)
- [x] Seed de dados automático no perfil `dev` via `CommandLineRunner`
- [x] 13 testes de integração com Testcontainers (PostgreSQL real em container)

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|-----------|--------|-----|
| Java | 25 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework principal |
| Spring Data JPA | — | Repositórios e persistência |
| Spring Validation | — | Validação de dados de entrada |
| Hibernate | — | ORM, estratégia `SINGLE_TABLE` |
| PostgreSQL | 16 | Banco de dados |
| Flyway | — | Controle de migrations de banco |
| Docker | — | Container do banco de dados (dev) |
| Testcontainers | 1.19.8 | PostgreSQL real nos testes de integração |
| SpringDoc OpenAPI | 2.8.8 | Documentação interativa (Swagger UI) |
| Lombok | — | Redução de boilerplate |
| Maven | — | Gerenciamento de dependências |

---

## Endpoints

> Base URL em produção: `https://bankingsystemapi-production.up.railway.app`  
> Documentação interativa: `https://bankingsystemapi-production.up.railway.app/swagger-ui/index.html`

### Contas — `/accounts`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/accounts` | Lista todas as contas |
| GET | `/accounts/{id}` | Busca uma conta por ID |
| POST | `/accounts/insert/cc` | Cria uma conta corrente |
| POST | `/accounts/insert/ce` | Cria uma conta empresarial |
| POST | `/accounts/insert/cp` | Cria uma conta poupança |
| PUT | `/accounts/deposit/{id}` | Realiza um depósito |
| PUT | `/accounts/saque/{id}` | Realiza um saque |
| PUT | `/accounts/transf/{idOrigem}/{idDestino}` | Transferência entre contas |
| PUT | `/accounts/delete/{id}` | Remove uma conta |

### Transações — `/transactions`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/transactions` | Lista todas as transações |
| GET | `/transactions/{id}` | Busca uma transação por ID |

### Exemplos de requisição

```http
POST /accounts/insert/cc
Content-Type: application/json

{
  "titular": "João Silva",
  "balance": 1000.00,
  "ativa": true,
  "dataAbertura": "2024-01-15",
  "agencia": "0001",
  "numeroConta": "12345-6",
  "limiteChequeEspecial": 500.00
}
```

```http
PUT /accounts/deposit/1
Content-Type: application/json

500.00
```

---

## Regras de Negócio

### Conta Corrente
- Taxa de **2%** sobre depósitos e transferências
- Taxa fixa de **R$ 25,00** por saque
- Limite máximo por depósito: **R$ 10.000,00**
- Limite máximo por saque: **R$ 20.000,00**

### Conta Poupança
- Taxa de **2%** em todas as operações
- Limite máximo por depósito: **R$ 10.000,00**
- Rendimento de **0,8%** sobre o saldo atual via `getRendimento()`

### Conta Empresarial
- Taxa de **2%** em todas as operações
- Limite máximo por depósito: **R$ 5.000,00**
- Limite máximo por saque: **R$ 20.000,00**
- Campo adicional `emprestimo`

### Geral
- `BigDecimal` com escala 2 e arredondamento `HALF_EVEN` em todas as operações
- Cada operação registra uma `Transacao` com tipo (convertido para `INTEGER` via `AttributeConverter`), valor, saldo pós-operação e timestamp
- Transferências registram transação em **ambas** as contas
- Rollback automático via `@Transactional` em `RuntimeException`
- Exceções tratadas globalmente com `@RestControllerAdvice` retornando `StandartError` com timestamp, status e mensagem

---

## Conceitos Aplicados

- Herança com `@Inheritance(strategy = SINGLE_TABLE)` e polimorfismo
- Abstração (`Conta` abstrata com métodos abstratos `sacar`, `deposito`, `transferencia`)
- Interface `Tax` para contrato de cálculo de taxa por tipo de conta
- `AttributeConverter` para persistir `TipoOperacao` como `INTEGER` no banco
- Injeção de dependência via Spring IoC
- Separação de responsabilidades: Controller → Service → Converter → Repository → Entity
- Histórico bidirecional de transações com `@OneToMany` / `@ManyToOne`
- DTOs com herança (`ContaDTO` base, subclasses por tipo) para evitar campos nulos
- Tratamento centralizado de exceções com `@RestControllerAdvice`
- Seed de dados com `CommandLineRunner` no perfil `dev`
- Testes de integração com `@SpringBootTest`, `@ActiveProfiles("test")`, Testcontainers

---

## Como Executar

### Pré-requisitos

- Java 25+
- Docker Desktop
- Maven

```bash
git clone https://github.com/Antonio-Eduardo/Banking_System_API.git
cd Banking_System_API
```

```bash
docker run --name banking-postgres \
  -e POSTGRES_PASSWORD=minhasenha \
  -e POSTGRES_DB=banking_system \
  -p 5432:5432 \
  -d postgres:16-alpine
```

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

A API estará disponível em `http://localhost:8081` e o Swagger em `http://localhost:8081/swagger-ui/index.html`.

---

## Testes

```bash
mvn test
```

| Cenário testado | Status |
|----------------|--------|
| Criar conta corrente, poupança e empresarial | ✅ |
| Depósito com taxa na conta corrente | ✅ |
| Saque com taxa fixa na conta corrente | ✅ |
| `SaldoInsuficienteException` no saque | ✅ |
| `LimiteExcedidoException` no depósito — corrente, poupança e empresarial | ✅ |
| `LimiteExcedidoException` no saque empresarial | ✅ |
| Rendimento da poupança | ✅ |
| Registro de `Transacao` ao depositar | ✅ |
| Registro de `Transacao` ao sacar | ✅ |

---

## Melhorias Futuras

- [ ] Autenticação e autorização com Spring Security + JWT
- [ ] Paginação nos endpoints de listagem
- [ ] Endpoint para rendimento da poupança
