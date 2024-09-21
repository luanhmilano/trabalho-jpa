# Testes de Desempenho de Transações em Banco de Dados usando JMeter

Este projeto tem como objetivo a criação e teste de uma aplicação Java Spring Boot com transações em banco de dados.

### Alunos:
* Emily Almeida Abreu
* Luan Henrique dos Santos Silva

## Organização do Projeto

### main

* controller/ (Controladores para requisições HTTP)
* model/ (Entidades do banco de dados)
* repository/ (Repositórios JPA)
* service/ (Regras de negócio)

### test

* java/ (Testes unitários)
* jmeter/ (Testes de carga para 1 conta)

### Entidades

* ContaBancaria
* ContaBancariaVersionada

### Configuração do Banco de Dados H2

1. Ajustes em ```application.properties```:
```bash
# Configurações do Datasource
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:file:./data/contas_db
spring.datasource.username=sa
spring.datasource.password=
spring.sql.init.mode=always

# Configurações do JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Credenciais de acesso ao console:

* JDBC URL: jdbc:h2:file:./data/contas_db
* Username: sa
* Password: (deixe em branco)

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/luanhmilano/trabalho-jpa.git
```
2. Navegue até o diretório do projeto:
```bash
cd trabalho-jpa
```
3. Compile o projeto com Maven:
```bash
mvn clean install
```
4. Rode a aplicação:
```bash
mvn spring-boot:run
```
5. Acesse o console H2 no navegador em:
```bash
http://localhost:8080/h2-console
```

## Execução dos Testes de Concorrência

### Teste de Carga com JMeter
O projeto inclui 2 arquivoa de teste JMeter localizado no diretório jmeter/:

* ```deposito-retirada.jmx```: Este arquivo contém o plano de teste configurado para simular requisições concorrentes às APIs de depósito e retirada referentes à entidade "```ContaBancaria```". Você pode carregá-lo no JMeter para executar testes de carga.
* ```conta-versionada.jmx```: Este arquivo contém o plano de teste configurado para simular requisições concorrentes às APIs de depósito e retirada referentes à entidade "```ContaBancariaVersionada```". Você pode carregá-lo no JMeter para executar testes de carga.

### Executando os Testes
1. Inicie a aplicação Spring Boot.
2. Abra o JMeter e carregue o arquivo de teste escolhido.
3. Configure o número de threads (usuários) e o número de requisições.
4. Execute o teste e visualize os resultados nos relatórios do JMeter.

### Requisitos
* Java 17 ou superior
* Maven (para compilar e rodar a aplicação)
* JMeter (para executar os testes de carga)