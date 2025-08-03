# localogo

API de Gerenciamento de Veículos
API REST para cadastro, consulta, alteração de status e exclusão de veículos, desenvolvida com Quarkus, Java e Maven.

Autor
Vagner Vinicius Ribeiro

## Sobre
Esta API permite gerenciar veículos, incluindo cadastro, consulta, alteração de status (disponível, alugado, manutenção) e exclusão. Ideal para sistemas de locação ou gestão de frotas.


## Tecnologias
Quarkus
Java 17+
Maven
SQL (futuro)

## Configuração
Por padrão, a API utiliza armazenamento em memória.
Para uso com banco de dados, configure o application.properties conforme necessário.

## Validações e Regras de Negócio
Só é possível excluir veículos que não estejam alugados.
Transições de status seguem regras definidas:
Disponível → Alugado ou Manutenção
Alugado → Disponível ou Manutenção
Manutenção → Disponível

## Contribuição
Contribuições são bem-vindas!



## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.


