# Cashback API REST

Exemplo de e-commerce de disco de vinil

## Github

https://github.com/vfmneto/cashback-api

## Circleci

https://circleci.com/gh/vfmneto

### Para rodar a aplicação

```maven
export CLIENT_ID=90ff7c96027c4aa4953ac55d46517f4f
export CLIENT_SECRET=664cbb31787449ca960af5d528de552a
mvn -Dspring-boot.run.arguments=--application.spotify.clientId=${CLIENT_ID},--application.spotify.clientSecret=${CLIENT_SECRET}
```