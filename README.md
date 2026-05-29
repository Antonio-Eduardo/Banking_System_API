# Sistema Bancário API

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
[![Licença MIT](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/Sistema_Bancario/blob/master/LICENSE)

> API REST de operações bancárias com 3 tipos de conta, regras de negócio distintas por tipo, testes de integração com Testcontainers e persistência em PostgreSQL via Docker.

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
- [Testes](#testes)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

O **Sistema Bancário API** é uma aplicação Java com foco em Programação Orientada a Objetos, persistência de dados e arquitetura REST. O sistema gerencia três tipos de conta bancária com regras de negócio distintas — corrente, poupança e empresarial — e registra todas as operações financeiras como depósitos, saques e transferências.

A hierarquia de contas é modelada com herança (`SINGLE_TABLE`) e polimorfismo: cada tipo implementa suas próprias regras de taxa, limite e operação. Todas as operações financeiras utilizam `BigDecimal` com arredondamento `HALF_EVEN`, garantindo precisão financeira. O controle transacional é gerenciado pelo Spring via `@Transactional`, assegurando rollback automático em caso de exceção.

---

## Evolução do Projeto

| Fase | Persistência | Destaques |
|------|-------------|-----------|
| 1 | Arquivos `.txt` | Validação da lógica de negócio |
| 2 | JDBC + MySQL | Persistência relacional, controle manual de conexões |
| 3 | JPA / Hibernate | Mapeamento O/R, `EntityManager`, JPQL |
| 4 | **Spring Boot + REST API** | Endpoints HTTP, Spring Data JPA, PostgreSQL via Docker, `BigDecimal`, testes de integração com Testcontainers |

---

## Funcionalidades

- [x] Criação de contas via endpoint REST (Corrente, Poupança, Empresarial)
- [x] Depósito com aplicação de taxa por tipo de conta
- [x] Saque com validação de saldo e limites por tipo de conta
- [x] Transferência entre contas com débito na origem e crédito no destino
- [x] Exclusão de conta por ID
- [x] Histórico de transações vinculado a cada conta (mapeamento bidirecional `@OneToMany`)
- [x] Listagem de todas as contas e transações
- [x] Rendimento automático na conta poupança (`getRendimento()`)
- [x] Hierarquia de exceções com `ErrorCode` enum: `SaldoInsuficienteException`, `LimiteExcedidoException`, `DBException`, `ValidacaoException`
- [x] Seed de dados automático no perfil `dev` via `CommandLineRunner`
- [x] Testes de integração com Testcontainers (13 testes, PostgreSQL real em container)

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|-----------|--------|-----|
| Java | 25 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework principal |
| Spring Data JPA | — | Repositórios e persistência |
| Spring Validation | — | Validação de dados de entrada |
| Hibernate | — | ORM, estratégia `SINGLE_TABLE` |
| PostgreSQL | 16 | Banco de dados de produção |
| Docker | — | Container do banco de dados |
| Testcontainers | 1.19.8 | PostgreSQL real nos testes de integração |
| Lombok | — | `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` |
| BigDecimal | — | Precisão financeira com `HALF_EVEN` |
| Maven | — | Gerenciamento de dependências |

---

## Estrutura do Projeto

```
src/
├── main/
│   └── java/com/eduardodev/banking_system_api/
│       ├── config/
│       ├── entities/
│       ├── enums/
│       ├── exceptions/
│       ├── interfaces/
│       │   └── impl/
│       ├── repository/
│       ├── resources/
│       └── service/
└── test/
    └── java/com/eduardodev/banking_system_api/
```

---

## Endpoints

> A aplicação roda por padrão na porta `8081`.

### Contas — `/accounts`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/accounts` | Lista todas as contas |
| `GET` | `/accounts/{id}` | Busca uma conta por ID |
| `POST` | `/accounts` | Cria uma nova conta |
| `PUT` | `/accounts/deposit/{id}` | Realiza um depósito na conta |
| `PUT` | `/accounts/saque/{id}` | Realiza um saque na conta |
| `PUT` | `/accounts/transf/{idOrigem}/{idDestino}` | Transferência entre contas |
| `PUT` | `/accounts/delete/{id}` | Remove uma conta por ID |

### Transações — `/transactions`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/transactions` | Lista todas as transações |
| `GET` | `/transactions/{id}` | Busca uma transação por ID |

---

## Regras de Negócio

### Conta Corrente
- Taxa de **2%** sobre depósitos e transferências
- Taxa fixa de **R$ 25,00** por saque
- Limite máximo por depósito (valor + taxa): **R$ 10.000,00**
- Limite máximo por saque: **R$ 20.000,00**
- Lança `SaldoInsuficienteException` se saldo insuficiente
- Lança `LimiteExcedidoException` se valor ultrapassa os limites

### Conta Poupança
- Taxa de **2%** em todas as operações
- Limite máximo por depósito (valor + taxa): **R$ 10.000,00**
- Rendimento de **0,8%** sobre o saldo atual (`getRendimento()`)

### Conta Empresarial
- Taxa de **2%** em todas as operações
- Limite máximo por depósito (valor + taxa): **R$ 5.000,00**
- Limite máximo por saque: **R$ 20.000,00**
- Campo adicional `emprestimo` personalizado por conta

### Geral
- Todas as operações usam `BigDecimal` com escala 2 e arredondamento `HALF_EVEN`
- Cada operação registra uma `Transacao` com tipo, valor, saldo pós-operação e timestamp (`Instant`)
- Transferências registram transação em **ambas** as contas (origem e destino)
- Controle transacional via `@Transactional` — rollback automático em `RuntimeException`
- Herança mapeada com `SINGLE_TABLE`: todas as contas na mesma tabela

---

## Conceitos Aplicados

- Herança com `@Inheritance(strategy = SINGLE_TABLE)` e polimorfismo
- Encapsulamento e Abstração (`Conta` abstrata com métodos abstratos)
- Interfaces (`Tax`, `OperacaoBanco`) e separação de contratos
- Service Layer com `@Service` e `@Transactional`
- Injeção de Dependência via Spring IoC (`@Autowired`)
- Separação de responsabilidades: Controller → Service → Repository → Entity
- Spring Data JPA com repositórios genéricos (`JpaRepository`)
- REST com Spring MVC (`@RestController`, `@GetMapping`, `@PostMapping`, `@PutMapping`)
- Hierarquia de exceções com `ErrorCode` enum
- `@JsonIgnore`, `@JsonPropertyOrder`, `@JsonFormat` para controle de serialização JSON
- Seed de dados com `CommandLineRunner` no perfil `dev`
- Testes de integração com `@SpringBootTest`, `@ActiveProfiles`, `@BeforeEach`, Testcontainers

---

## Como Executar

### Pré-requisitos

- Java 25+
- Docker Desktop
- Maven

### Passos

**1. Clone o repositório:**
```bash
git clone https://github.com/Antonio-Eduardo/Sistema_Bancario.git
cd Sistema_Bancario
```

**2. Suba o banco de dados com Docker:**
```bash
docker run --name banking-postgres \
  -e POSTGRES_PASSWORD=minhasenha \
  -e POSTGRES_DB=banking_system \
  -p 5432:5432 \
  -d postgres:16-alpine
```

**3. Execute com perfil de desenvolvimento (inclui seed de dados):**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**4. Acesse a API:**
```
GET  http://localhost:8081/accounts
GET  http://localhost:8081/transactions
```

---

## Testes

O projeto possui **13 testes de integração** usando `@SpringBootTest` com perfil `test` e Testcontainers — o Spring sobe completo contra um PostgreSQL real em container, sem banco em memória.

```bash
mvn test
```

### Cobertura

| Cenário testado | Status |
|----------------|--------|
| Criar conta corrente, poupança e empresarial | ✅ |
| Depósito com aplicação de taxa na conta corrente | ✅ |
| Saque com taxa fixa na conta corrente | ✅ |
| `SaldoInsuficienteException` no saque | ✅ |
| `LimiteExcedidoException` no depósito — corrente, poupança e empresarial | ✅ |
| `LimiteExcedidoException` no saque empresarial | ✅ |
| Rendimento da poupança (`getRendimento()`) | ✅ |
| Registro de `Transacao` ao depositar | ✅ |
| Registro de `Transacao` ao sacar | ✅ |

---

## Melhorias Futuras

- [ ] Tratamento global de exceções com `@ControllerAdvice`
- [ ] Documentação com Swagger / OpenAPI
- [ ] Autenticação com Spring Security
- [ ] Paginação nos endpoints de listagem
- [ ] Interface gráfica
