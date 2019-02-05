# Cashback API REST

Exemplo de e-commerce de disco de vinil

## Github

https://github.com/vfmneto/cashback-api

## Circleci

https://circleci.com/gh/vfmneto

### Para rodar a aplicação

```maven
export CLIENT_ID=CHANGE_IN_PRODUCTION
export CLIENT_SECRET=CHANGE_IN_PRODUCTION
mvn -Dspring-boot.run.arguments=--application.spotify.clientId=${CLIENT_ID},--application.spotify.clientSecret=${CLIENT_SECRET}
```