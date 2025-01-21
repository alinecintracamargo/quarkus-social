
# Projeto Quarkus-Social

Este projeto utiliza o Quarkus, o framework Java Supersônico Subatômico.

Se você quiser aprender mais sobre o Quarkus, visite o seu site: https://quarkus.io/.


# Executando a aplicação em modo de desenvolvimento
Você pode rodar sua aplicação no modo de desenvolvimento, que habilita a codificação ao vivo, utilizando:

```bash
./mvnw compile quarkus:dev
````

# Empacotando e rodando a aplicação

A aplicação pode ser empacotada utilizando:

```bash
./mvnw package
```

Isso irá gerar o arquivo quarkus-run.jar no diretório target/quarkus-app/. Fique atento, pois esse não é um uber-jar, já que as dependências são copiadas para o diretório target/quarkus-app/lib/.

A aplicação agora pode ser executada utilizando:
```bash
java -jar target/quarkus-app/quarkus-run.jar
````

Se você deseja gerar um uber-jar, execute o seguinte comando:
```bash
./mvnw package -Dquarkus.package.type=uber-jar
```

A aplicação, empacotada como um uber-jar, agora pode ser executada utilizando:
```bash
java -jar target/*-runner.jar
```

# Criando um executável nativo

Você pode criar um executável nativo utilizando:
```bash
./mvnw package -Pnative
```
Ou, se você não tem o GraalVM instalado, pode rodar a construção do executável nativo em um container utilizando:

```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
```
Você pode então executar seu executável nativo com:

```bash
./target/quarkus-social-1.0-runner
```

Se quiser aprender mais sobre como construir executáveis nativos, consulte https://quarkus.io/guides/maven-tooling.html.

# Guias Relacionados
RESTEasy JAX-RS (guia): Framework de endpoints REST que implementa JAX-RS e mais.

# Código fornecido

RESTEasy JAX-RS: Inicie facilmente seus Serviços Web RESTful

