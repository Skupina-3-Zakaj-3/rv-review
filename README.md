# Rv-review microservice

## Build the image

```bash
docker build -t rv-review .
```

## Create network for all our microservices

```bash
docker network create rso
```

## Run database in network
```bash
docker run -d --name pg-rv-review --network="rso" -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=rv_reviews -p 5434:5432 postgres:13
```

## Run the container in network

```bash
docker run -p 8082:8082 --name rv-review --network="rso" -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-rv-review:5432/rvs rv-review
```

## Run the container from Docker hub in network

```bash
docker run -p 8082:8082 --name rv-review --network="rso" anzeha/rv-review:latest
```