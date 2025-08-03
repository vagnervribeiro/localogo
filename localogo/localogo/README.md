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
OpenAPI/Swagger (futuro)

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
Abra uma issue ou envie um pull request.


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/localogo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
