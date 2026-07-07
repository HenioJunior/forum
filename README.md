# Forum API

> API REST para gerenciamento de um fórum de discussão educacional desenvolvida com Kotlin, Spring Boot e PostgreSQL.

O objetivo deste projeto não é apenas implementar uma API, mas servir como estudo completo de Engenharia de Software, Domain-Driven Design (DDD), Arquitetura de Software e boas práticas de desenvolvimento backend.

---

# Objetivos

Este projeto busca simular um ambiente real de desenvolvimento, desde a descoberta do domínio até a implementação da solução.

Durante o desenvolvimento serão abordados temas como:

- Domain Discovery
- Levantamento de requisitos
- Modelagem de domínio
- Arquitetura em Camadas
- Clean Architecture (quando aplicável)
- REST API
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Docker
- OpenAPI
- Testes automatizados
- Boas práticas de desenvolvimento

---

# Descoberta do Domínio

Antes da implementação foi realizada uma etapa de entendimento do domínio do problema.

O objetivo é responder à pergunta:

> **O que é um fórum?**

A partir dessa análise foram identificados os principais atores, entidades e processos do sistema.

---

# Atores

- Visitante
- Usuário
- Moderador
- Administrador

---

# Principais Entidades

Até o momento foram identificadas as seguintes entidades do domínio:

- User
- Category
- SubCategory
- Course
- Topic
- Reply

Novas entidades poderão surgir conforme o domínio evoluir.

---

# Hierarquia do Domínio

A estrutura inicial do fórum segue a seguinte organização:

```text
Category
    └── SubCategory
            └── Course
                    └── Topic
                            └── Reply
```

Exemplo:

```text
Back-End
    └── Arquitetura de Software
            └── Arquitetura de Soluções
                    └── Como modelar agregados?
```

---

# Navegação Pública

Mesmo sem autenticação, um visitante pode:

- visualizar categorias
- visualizar subcategorias
- visualizar cursos
- listar tópicos
- pesquisar tópicos
- filtrar tópicos
- navegar utilizando paginação

---

# Filtros disponíveis

A listagem de tópicos suporta os seguintes filtros:

- Categoria
- Subcategoria
- Curso
- Busca textual
- Todos
- Sem resposta
- Solucionados

---

# Requisitos Funcionais Descobertos

| Código | Descrição |
|---------|-----------|
| RF-001 | Visitante pode visualizar categorias. |
| RF-002 | Visitante pode visualizar tópicos recentes. |
| RF-003 | Visitante pode filtrar tópicos por categoria. |
| RF-004 | Visitante pode filtrar tópicos por subcategoria. |
| RF-005 | Visitante pode filtrar tópicos por curso. |
| RF-006 | Visitante pode pesquisar tópicos por assunto. |
| RF-007 | Visitante pode visualizar tópicos solucionados. |
| RF-008 | Visitante pode visualizar tópicos sem respostas. |
| RF-009 | Visitante pode navegar através de paginação. |

---

# Regras de Negócio Descobertas

- Apenas usuários autenticados podem criar tópicos.
- Apenas usuários autenticados podem responder tópicos.
- Um tópico pertence a um curso.
- Um curso pertence a uma subcategoria.
- Uma subcategoria pertence a uma categoria.
- Um tópico pode possuir várias respostas.
- Apenas uma resposta pode ser marcada como solução.
- Um tópico solucionado permanece disponível para consulta.
- Um tópico fechado não aceita novas respostas.

---

# Modelo Conceitual Inicial

```text
Category
    └── SubCategory
            └── Course
                    └── Topic
                            └── Reply
```

---

# Próximas Etapas

Este projeto será desenvolvido seguindo as seguintes fases:

- [x] Descoberta do domínio
- [ ] Levantamento de requisitos
- [ ] Modelagem do domínio
- [ ] Casos de uso
- [ ] Modelagem do banco de dados
- [ ] Arquitetura da aplicação
- [ ] Definição da API REST
- [ ] Implementação
- [ ] Testes
- [ ] Docker
- [ ] Observabilidade
- [ ] Deploy

---

# Tecnologias

- Kotlin
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Docker
- OpenAPI
- JUnit 5
- Testcontainers
- Gradle

---

> Este projeto está sendo desenvolvido seguindo uma abordagem **Domain First**, onde o entendimento do negócio precede a implementação do código.
