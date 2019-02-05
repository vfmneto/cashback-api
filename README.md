# Cashback API REST

Exemplo de e-commerce de disco de vinil

## Github

https://github.com/vfmneto/cashback-api

## Circleci

https://circleci.com/gh/vfmneto

### Para rodar os testes
```
export CLIENT_ID=CHANGE_IN_PRODUCTION
export CLIENT_SECRET=CHANGE_IN_PRODUCTION
mvn install -Dapplication.spotify.clientId=${CLIENT_ID} -Dapplication.spotify.clientSecret=${CLIENT_SECRET}
```

### Para rodar a aplicação
```
export CLIENT_ID=CHANGE_IN_PRODUCTION
export CLIENT_SECRET=CHANGE_IN_PRODUCTION
mvn -Dspring-boot.run.arguments=--application.spotify.clientId=${CLIENT_ID},--application.spotify.clientSecret=${CLIENT_SECRET}
```

### Para construir imagem Docker
```
mvn clean package verify jib:dockerBuild -DskipTests=true
```

### Para rodar com Docker
```
docker run -d -p 8080:8080 -e APPLICATION_SPOTIFY_CLIENTID='CHANGE_IN_PRODUCTION' -e APPLICATION_SPOTIFY_CLIENTSECRET='CHANGE_IN_PRODUCTION' --name cashback-api cashback-api:latest
```

### Para acessar os serviços

http://localhost:8080

http://localhost:8080/swagger-ui.html#/

http://localhost:8080/h2-console/