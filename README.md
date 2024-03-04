<center>
  <p align="center">
    <img src="https://icon-library.com/images/java-icon-png/java-icon-png-15.jpg"  width="150" />
  </p>  
  <h1 align="center">Sistema Bancário</h1>
  <p align="center">
    Applicação desenvolvida para o Renew Career na Accenture para turma de Java.
  </p>
</center>
<br />

## Ferramentas necessárias
- JDK 21
- Docker

## Como executar?

1. Subir o container do Postgres com Docker:
````
docker-compose up -d
````

2. Executar as migrações do Postgres com o Flyway:
```
./gradlew flywayMigrate
```

## Gerando o artefato produtivo (jar)
```
./gradlew bootJar
```

## COMMAND LINE INTERFACE

### Como utilizar?

1. Listar todos os comandos

``` Shell
help

AVAILABLE COMMANDS

Built-In Commands
help: Display help about available commands
stacktrace: Display the full stacktrace of the last error.
clear: Clear the shell screen.
quit, exit: Exit the shell.
history: Display or save the history of previously run commands
version: Show version info
script: Read and execute commands from a file.

Component
transaction-withdraw: Transaction Withdraw
transaction-transfer: Transaction Transfer
create-account: Create Account
create-client: Create Client
transaction-deposit: Transaction Deposit
update-account-limit: Update Account Limit
```

2. Os Components correspondem aos recursos disponíveis

3. Exemplo de utilização:

``` Shell
create-account e71b4579-888a-4e33-add1-084942a4f793 8885585 1500 2000 1
```
``` Shell
create-client User_Test test@mail.com 1970/09/21 66688899911
```
``` Shell
transaction-withdraw ceef04c3-959b-41f6-9890-78d07ea6be23 200
```
``` Shell
transaction-deposit e71b4579-888a-4e33-add1-084942a4f793 200
```
``` Shell
transaction-transfer e71b4579-888a-4e33-add1-084942a4f793 a2014579-888a-4e33-add1-084942a4f854 200
```
``` Shell
update-account-limit e71b4579-888a-4e33-add1-084942a4f793 400
```

4. Observação: Para nome e sobrenome, juntar os dois separando por "_". Exemplo:

```
Paulo Ricardo -> Paulo_Ricardo
```
