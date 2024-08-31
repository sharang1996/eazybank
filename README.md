
# EazyBank Microservices

A collection of microservices offering banking functionality


## Build and Push

build docker images for each individual microservice and get them up with docker compose
```bash
  cd eazybank/account
  ./mvnw compile jib:dockerBuild  
  cd ../loan
  ./mvnw compile jib:dockerBuild  
  cd ../card
  ./mvnw compile jib:dockerBuild  
  cd ../configserver
  ./mvnw compile jib:dockerBuild  
  cd ../docker-compose/default
  docker compose up -d  
```
    