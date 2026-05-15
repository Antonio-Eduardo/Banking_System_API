# Sistema Bancário (Java)

Projeto de um sistema bancário desenvolvido em Java com foco em Programação Orientada a Objetos, simulando operações básicas de uma instituição financeira. O projeto permite a criação e gerenciamento de contas, além da realização de transações como depósitos, saques e consultas de saldo.

## O que tem no projeto

### Criação de contas

- Conta Corrente
- Conta Empresarial
- Conta Poupança

### Operações

- Depósito
- Saque
- Transferência entre contas

### Histórico de transações

- Cada operação é registrada no sistema

### Tratamento de erros

- Saldo insuficiente
- Limite excedido
- Exceções de banco de dados

### Persistência de dados

- Persistência com MySQL utilizando JDBC
- Controle transacional com:
  - `commit`
  - `rollback`
  - `setAutoCommit(false)`

## Estrutura

- `entities` → classes das contas e transações
- `dao` → interfaces DAO
- `dao.impl` → implementação JDBC/MySQL
- `services` → regras de negócio
- `db` → conexão com banco de dados
- `exceptions` → tratamento de erros
- `main` → execução no console

## Regras de negócio

- Conta Corrente possui taxa fixa por operação
- Conta Empresarial possui limite de saque
- Conta Poupança possui comportamento diferenciado
- Transferências utilizam transações SQL para garantir consistência dos dados

## Conceitos aplicados

- Herança
- Polimorfismo
- Encapsulamento
- Abstração
- Interfaces
- DAO Pattern
- Service Layer
- Injeção de Dependência
- JDBC Transactions
- Separação de responsabilidades

## Tecnologias utilizadas

- Java 17+
- JDBC
- MySQL
- Git/GitHub

## Exemplo de saída

```text
Conta [Corrente]
Titular: Eduardo
Número: 1273
Saldo: 300.00

Transações:
DEPOSITO | R$ 200.00 | Saldo: 200.00
DEPOSITO | R$ 200.00 | Saldo: 400.00
SAQUE | R$ 50.00 | Saldo: 350.00
TRANSFERENCIA | R$ 100.00 | Saldo: 250.00
```

## Como rodar

1. Clonar o repositório
2. Configurar o banco de dados MySQL
3. Ajustar as credenciais de conexão na classe `DB.java`
4. Executar a classe `Main.java`

## Observações

Fiz esse projeto para praticar conceitos importantes de backend Java, como Programação Orientada a Objetos, JDBC, persistência de dados, arquitetura em camadas e controle transacional.

O projeto começou como um sistema bancário simples utilizando arquivos `.txt` e evoluiu para uma arquitetura baseada em DAO + MySQL utilizando JDBC.

## Melhorias futuras

- API REST com Spring Boot
- Hibernate/JPA
- Interface gráfica
- Testes automatizados
- Uso de BigDecimal para operações financeiras
- Sistema de autenticação de usuários