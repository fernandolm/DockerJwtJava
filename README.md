# Instruções

## Containers

- Banco de Dados Postgres: Armazena as informações recebidas pelo endpoint /log. Está localizada na porta 5432;
- Adminer: Container opcional com aplicação web para checar o banco de dados postgres;
- TicketReceiver: Container com a aplicação que possui os endpoints /login, /log e /client. Está localizada na porta 9099;
- CreditCard: Container com a aplicação que gera informações de cartões de crédito para o endpoint /log. Está localizada na porta 9098.

## Instalação

1. Baixar a imagem do postgres com o comando: docker pull postgres;
1.1 Este passo é opcional. Baixar a imagem do adminer para ter uma interface web para leitura do banco de dados: docker pull adminer;
2. Executar o arquivo DockerBuild.bat na pasta creditcard ou copiar o comando dentro do bat e executá-lo no seu terminal. Comando: docker build --tag=creditcard:latest .
3. Executar o arquivo DockerBuild.bat na pasta ticketreceiver ou copiar o comando dentro do bat e executá-lo no seu terminal. Comando: docker build --tag=ticketreceiver:latest .

## Executando a aplicação

1. Executar o bat 1-DockerRunPostGres.bat ou o comando docker run -e POSTGRES_USER=test_user -e POSTGRES_PASSWORD=test_password -e POSTGRES_DB=TicketDatabase --network=bridge -p 5432:5432 --name db postgres:latest;
1.1 Opcionalmente pode-se executar o adminer através do arquivo bat 2-DockerRunAdminer.bat ou executando o comando docker run --network=bridge -p 8081:8080 --name adminer adminer:latest
2. Executar o bat 3-DockerRunTicketReceiver.bat ou o comando docker run -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/TicketDatabase --network=bridge -p 9098:9098 --name ticketreceiver ticketreceiver:latest
;
3.Executar o bat 4-DockerRunCreditCard.bat ou o comando docker run -e APP_RETROFIT_BASE_URL=http://host.docker.internal:9098/ --network=bridge -p 9099:9099 --name creditcard creditcard:latest

## Testes

1. Pode-se abrir o swagger pelo endereço: http://localhost:9098/swagger-ui/index.html;
2. Deve-se chamar o endpoint login com usuário test_user e senha test_password;
3. Com o token, fazer a autenticação no botão Authorize (não é necessário colocar o bearer na frente do token como é feito no springfox);
4. Executar o endpoint /client para obter o resultado dos clientes com maiores gastos por autorizadora.

## Aplicação CreditCard
- Esta aplicação fica enviando requisições para a aplicação TicketReceiver através do endpoint /log para gravar as informações no postgres.

## Banco de Dados PostGres
- O usuário é o test_user e a senha é test_password. O banco é o TicketDatabase.

## Adminer
- Está localizado no endpoint http://localhost:8081
