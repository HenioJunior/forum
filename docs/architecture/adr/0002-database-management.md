# ADR 0002 - Gerenciamento do Schema

## Status

Accepted

## Decisão

O esquema do banco de dados será gerenciado exclusivamente pelo Flyway.

O Hibernate não será utilizado para criar ou alterar tabelas.

Configuração adotada:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: validate
```

## Consequências

### Benefícios

- Ambiente reproduzível.
- Migrações versionadas.
- Mesmo comportamento entre desenvolvimento e produção.

### Recriação do banco

Durante o desenvolvimento o banco será recriado através do Docker.

```bash
docker compose down -v
docker compose up -d
```