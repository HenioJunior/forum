# 03 - Linguagem Ubíqua (Ubiquitous Language)

## Status

**Versão:** 1.0  
**Status:** ✅ Finalizado

---

# Objetivo

Este documento estabelece a linguagem oficial utilizada em todo o projeto **Forum**.

Todos os integrantes do projeto — desenvolvedores, analistas, product owners e especialistas do domínio — devem utilizar os mesmos termos para representar os mesmos conceitos.

Esta linguagem será utilizada na modelagem do domínio, código-fonte, APIs, banco de dados, documentação e comunicação do projeto.

---

# Glossário do Domínio

## Comunidade (Community)

Espaço principal onde pessoas se reúnem em torno de um interesse comum.

Uma comunidade possui identidade própria, regras, categorias e membros.

### Exemplos

- Comunidade Java
- Comunidade Spring
- Comunidade Kubernetes

### Características

- Possui membros.
- Possui moderadores.
- Possui categorias.
- Possui regras próprias.

> Não deve ser confundida com Categoria.

---

## Categoria (Category)

Divisão lógica dentro de uma comunidade.

Seu objetivo é organizar os tópicos por assunto.

### Exemplos

- Java Básico
- Spring Boot
- Banco de Dados
- Carreira

### Regras

- Uma categoria contém diversos tópicos.
- Todo tópico pertence a exatamente uma categoria.

---

## Canal (Channel)

Representa um espaço de comunicação com propósito específico.

Enquanto categorias organizam conteúdo permanente, canais representam fluxos contínuos de comunicação.

### Exemplos

- Avisos
- Eventos
- Chat Geral

> O conceito de Canal está previsto para evolução futura da plataforma. O MVP utilizará apenas Categorias.

---

## Tópico (Topic)

Discussão iniciada por um membro.

É a principal entidade funcional do sistema.

### Possui

- Título
- Conteúdo
- Autor
- Categoria
- Tags
- Respostas
- Status

### Estados

- Aberto
- Fechado
- Arquivado

---

## Resposta (Reply)

Contribuição publicada dentro de um tópico.

### Possui

- Autor
- Conteúdo
- Data de criação

Uma resposta pode ser marcada como **Solução**.

---

## Solução (Accepted Solution)

Resposta considerada correta para resolver o problema apresentado no tópico.

### Regras

- Um tópico possui no máximo uma solução.
- Normalmente é escolhida pelo autor do tópico.
- Moderadores também podem defini-la.

---

## Tag

Marcador utilizado para classificar tópicos.

### Exemplos

- spring
- kotlin
- docker
- jwt

### Regras

- Um tópico pode possuir várias tags.
- Uma mesma tag pode ser utilizada em vários tópicos.

---

## Evento (Event)

Atividade organizada pela comunidade.

### Exemplos

- Webinar
- Live
- Meetup
- Workshop

Eventos podem originar anúncios e tópicos oficiais.

---

## Anúncio (Announcement)

Publicação institucional da comunidade.

Possui maior destaque que um tópico comum.

Normalmente pode ser criado apenas por moderadores ou administradores.

---

## Membro (Member)

Pessoa cadastrada na plataforma.

### Pode

- Criar tópicos
- Responder tópicos
- Editar conteúdos próprios
- Votar
- Acompanhar discussões

Todo moderador também é um membro.

---

## Moderador (Moderator)

Membro responsável pela organização da comunidade.

### Pode

- Fechar tópicos
- Reabrir tópicos
- Remover conteúdo
- Mover tópicos
- Criar anúncios
- Marcar solução
- Aplicar sanções

---

## Administrador (Administrator)

Responsável pela administração completa da plataforma.

Possui todas as permissões disponíveis.

Também pode administrar comunidades, categorias e moderadores.

---

## Fechar Tópico (Close Topic)

Impede novas respostas.

O conteúdo continua disponível para consulta.

Fechar não significa excluir.

---

## Reabrir Tópico

Permite novamente a publicação de respostas em um tópico fechado.

---

## Arquivar Tópico (Archive Topic)

Move um tópico para estado histórico.

O conteúdo permanece disponível apenas para consulta.

Não pode mais sofrer alterações.

Arquivar é diferente de fechar.

---

## Excluir Tópico

Remove o tópico da visualização normal.

A estratégia de exclusão (lógica ou física) será definida durante a modelagem do domínio.

---

## Curtida (Upvote)

Avaliação positiva realizada pelos membros.

Pode ser aplicada em:

- Tópicos
- Respostas

Seu objetivo é destacar conteúdos relevantes.

---

## Voto Negativo (Downvote)

Avaliação negativa.

Não faz parte do MVP.

Poderá ser incorporado em versões futuras.

---

## Reputação (Reputation)

Pontuação obtida pelo membro em função de sua participação na comunidade.

Não faz parte do MVP.

---

## Notificação (Notification)

Mensagem enviada ao membro para informar acontecimentos relevantes.

### Exemplos

- Nova resposta
- Solução marcada
- Menção
- Anúncio

---

## Menção (Mention)

Referência direta a outro membro.

### Exemplo

```text
@joao
```

O membro mencionado recebe uma notificação.

---

## Seguidor (Follower)

Membro que acompanha determinado tópico ou categoria para receber atualizações.

---

## Estado do Tópico (Topic Status)

Representa a situação atual do tópico.

### Estados iniciais

- OPEN
- CLOSED
- ARCHIVED

---

## Autor (Author)

Membro responsável pela criação de um conteúdo.

Pode ser autor de:

- Tópico
- Resposta
- Anúncio

---

## Conteúdo (Content)

Texto publicado na plataforma.

Pode representar:

- Tópico
- Resposta
- Anúncio

---

## Publicação (Publication)

Termo genérico utilizado para representar qualquer conteúdo publicado.

Inclui:

- Tópicos
- Respostas
- Anúncios

---

# Regras de Linguagem

## Termos oficiais

| Utilizar | Evitar |
|----------|--------|
| Topic | Post |
| Reply | Comentário |
| Category | Canal (no MVP) |
| Member | Usuário |
| Accepted Solution | Resposta Correta |
| Archive | Excluir Histórico |

---

# Convenções para Código

| Conceito | Nome |
|----------|------|
| Comunidade | Community |
| Categoria | Category |
| Tópico | Topic |
| Resposta | Reply |
| Solução | AcceptedSolution |
| Tag | Tag |
| Evento | Event |
| Anúncio | Announcement |
| Membro | Member |
| Moderador | Moderator |
| Administrador | Administrator |
| Notificação | Notification |

---

# Convenções para APIs

```http
GET    /topics
POST   /topics
GET    /categories
POST   /topics/{id}/replies
POST   /topics/{id}/close
POST   /topics/{id}/archive
POST   /topics/{id}/accept-solution
```

---

# Convenções para Banco de Dados

```text
communities
categories
topics
replies
tags
topic_tags
announcements
members
notifications
```

---

# Evolução do Documento

A Linguagem Ubíqua é um documento vivo.

Sempre que novos conceitos de domínio forem incorporados ao Forum, este documento deverá ser atualizado antes da implementação.

Entretanto, para a versão atual do projeto, este documento é considerado **finalizado (v1.0)** e passa a ser a referência oficial de nomenclatura para:

- Modelo de Domínio
- Casos de Uso
- Event Storming
- Diagramas
- Código-fonte
- APIs
- Banco de Dados
- Eventos de Domínio
````
