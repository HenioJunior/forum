# ADR 0001 - Escolha da Stack Tecnológica

## Status

Accepted

## Contexto

O projeto tem como objetivo servir como um estudo completo de Engenharia de Software, Arquitetura de Software, Domain-Driven Design e desenvolvimento backend moderno utilizando o ecossistema Spring.

A stack escolhida deve ser:

- amplamente utilizada no mercado;
- moderna;
- estável;
- adequada para projetos de médio e grande porte;
- alinhada ao objetivo de portfólio profissional.

## Decisão

Foram adotadas as seguintes tecnologias:

| Tecnologia | Versão |
|------------|---------|
| Java | 21 (LTS) |
| Kotlin | 1.9.25 |
| Spring Boot | 3.5.16 |
| Gradle | Kotlin DSL |
| PostgreSQL | 16+ |
| Flyway | Controle de migrações |
| Spring Data JPA | Persistência |
| Spring Security | Segurança |
| Docker Compose | Ambiente local |

## Consequências

### Benefícios

- Stack consolidada no mercado.
- Excelente suporte do ecossistema Spring.
- Compatibilidade com Java LTS.
- Facilidade de manutenção.
- Forte aderência a projetos corporativos.

### Trade-offs

- Curva inicial de aprendizado do Kotlin.
- Integração JPA requer plugins específicos do Kotlin.