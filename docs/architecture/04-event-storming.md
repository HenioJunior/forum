# 04 - Event Storming

> **Objetivo**
>
> Este documento registra o **Event Storming** do projeto **Forum**.
>
> Diferentemente dos documentos anteriores, aqui o foco deixa de ser **"o que o sistema é"** para responder **"como o sistema se comporta"**.
>
> Nesta etapa identificamos:
>
> - Eventos de Domínio
> - Comandos
> - Atores
> - Políticas
> - Regras de Negócio
> - Invariantes
> - Fluxos principais
> - Dúvidas de descoberta
>
> **Importante:** nesta fase **não definimos agregados definitivamente**. Eles serão identificados posteriormente durante a construção do Modelo de Domínio.

---

# 1. Objetivo do Event Storming

O Event Storming é uma técnica criada por Alberto Brandolini para descobrir o comportamento de um domínio de negócio antes da implementação.

Ao invés de pensar em telas, APIs ou banco de dados, pensamos em perguntas como:

- O que aconteceu?
- Quem iniciou essa ação?
- Qual comando foi executado?
- Quais regras precisaram ser respeitadas?
- O que acontece em seguida?

O resultado é um mapa do comportamento do domínio.

---

# 2. Convenções

## Eventos de Domínio

Representam algo importante que **já aconteceu**.

São escritos no passado.

Exemplos:

- Comunidade Criada
- Categoria Criada
- Canal Criado
- Tópico Criado
- Resposta Publicada
- Resposta Marcada como Solução

---

## Comandos

Representam uma intenção.

São escritos no imperativo.

Exemplos:

- Criar Comunidade
- Criar Categoria
- Criar Canal
- Criar Tópico
- Publicar Resposta
- Fechar Tópico

---

## Atores

Quem executa o comando.

Exemplos:

- Visitante
- Membro
- Moderador
- Administrador

---

## Políticas

Automações do domínio disparadas por eventos.

Exemplo:

Quando um tópico for fechado:

- impedir novas respostas;
- manter leitura pública;
- registrar responsável.

---

## Invariantes

Regras que nunca podem ser violadas.

Exemplo:

- Um tópico fechado nunca recebe novas respostas.

---

# 3. Fluxo Principal do Fórum

O principal objetivo do Forum é transformar perguntas em conhecimento permanente.

Fluxo esperado:

```text
Criar Tópico
        │
        ▼
Tópico Criado
        │
        ▼
Adicionar Tag
        │
        ▼
Tag Adicionada ao Tópico
        │
        ▼
Publicar Resposta
        │
        ▼
Resposta Publicada
        │
        ▼
Marcar Resposta como Solução
        │
        ▼
Resposta Marcada como Solução
        │
        ▼
Tópico Resolvido
        │
        ▼
Fechar Tópico
        │
        ▼
Tópico Fechado
```

---

# 4. Descoberta dos Eventos

## 4.1 Comunidade

### Ator

Administrador

### Comandos

- Criar Comunidade
- Atualizar Comunidade
- Arquivar Comunidade
- Reativar Comunidade

### Eventos

- Comunidade Criada
- Comunidade Atualizada
- Comunidade Arquivada
- Comunidade Reativada

### Regras

- Toda comunidade possui nome.
- Comunidades arquivadas permanecem apenas para consulta.

### Invariantes

- Nome é obrigatório.
- Comunidade arquivada não aceita novas interações.

### Dúvidas

- Existe exclusão definitiva?
- O tipo da comunidade pode ser alterado?

---

## 4.2 Categoria

### Ator

Administrador

Moderador

### Comandos

- Criar Categoria
- Atualizar Categoria
- Reordenar Categoria
- Arquivar Categoria

### Eventos

- Categoria Criada
- Categoria Atualizada
- Categoria Reordenada
- Categoria Arquivada

### Regras

- Toda categoria pertence a uma comunidade.

### Invariantes

- Categoria sempre pertence a uma comunidade.

### Dúvidas

- Categorias possuem permissões próprias?

---

## 4.3 Canal

### Ator

Administrador

Moderador

### Comandos

- Criar Canal
- Atualizar Canal
- Arquivar Canal

### Eventos

- Canal Criado
- Canal Atualizado
- Canal Arquivado

### Regras

- Todo canal pertence a uma categoria.

### Invariantes

- Canal não existe sem categoria.

### Dúvidas

- Existem canais somente leitura?
- Existem canais exclusivos para anúncios?

---

## 4.4 Tópico

### Ator

Membro

Moderador

Administrador

### Comandos

- Criar Tópico
- Editar Tópico
- Fechar Tópico
- Reabrir Tópico
- Arquivar Tópico
- Adicionar Tag
- Remover Tag

### Eventos

- Tópico Criado
- Tópico Atualizado
- Tópico Fechado
- Tópico Reaberto
- Tópico Arquivado
- Tag Adicionada ao Tópico
- Tag Removida do Tópico

### Regras

- Todo tópico pertence a um canal.
- Um tópico pode possuir várias tags.
- Moderadores podem criar tópicos institucionais.

### Invariantes

- Todo tópico possui título.
- Todo tópico possui conteúdo inicial.
- Tópicos fechados não recebem respostas.

### Dúvidas

- O autor pode fechar o próprio tópico?
- Existe limite de tags?
- Tópicos podem ser movidos?

---

## 4.5 Resposta

### Ator

Membro

Moderador

Administrador

### Comandos

- Publicar Resposta
- Editar Resposta
- Remover Resposta
- Marcar Resposta como Solução
- Remover Solução

### Eventos

- Resposta Publicada
- Resposta Atualizada
- Resposta Removida
- Resposta Marcada como Solução
- Solução Removida

### Regras

- Toda resposta pertence a um tópico.
- Um tópico pode possuir apenas uma solução.

### Invariantes

- Toda resposta possui conteúdo.
- Apenas uma solução por tópico.

### Dúvidas

- Quem pode marcar solução?
- A solução pode ser alterada?

---

## 4.6 Tags

### Ator

Administrador

Moderador

Membro

### Comandos

- Criar Tag
- Renomear Tag
- Desativar Tag

### Eventos

- Tag Criada
- Tag Renomeada
- Tag Desativada

### Regras

- Tags facilitam organização e pesquisa.

### Invariantes

- Não existem tags duplicadas em um mesmo tópico.

### Dúvidas

- Tags serão globais?
- Usuários poderão criar novas tags?

---

## 4.7 Eventos da Comunidade

### Ator

Administrador

Moderador

### Comandos

- Criar Evento
- Atualizar Evento
- Publicar Evento
- Cancelar Evento
- Encerrar Evento

### Eventos

- Evento Criado
- Evento Atualizado
- Evento Publicado
- Evento Cancelado
- Evento Encerrado

---

## 4.8 Anúncios

### Ator

Administrador

Moderador

### Comandos

- Criar Anúncio
- Atualizar Anúncio
- Publicar Anúncio
- Arquivar Anúncio

### Eventos

- Anúncio Criado
- Anúncio Atualizado
- Anúncio Publicado
- Anúncio Arquivado

---

## 4.9 Participação

### Ator

Visitante

Membro

Administrador

Moderador

### Comandos

- Solicitar Entrada
- Aprovar Entrada
- Recusar Entrada
- Entrar na Comunidade
- Sair da Comunidade

### Eventos

- Entrada Solicitada
- Entrada Aprovada
- Entrada Recusada
- Membro Entrou na Comunidade
- Membro Saiu da Comunidade

---

# 5. Políticas Descobertas

## Quando um tópico for fechado

Evento:

- Tópico Fechado

Então:

- impedir novas respostas;
- manter leitura disponível;
- registrar quem realizou o fechamento;
- registrar motivo (opcional).

---

## Quando uma resposta for marcada como solução

Evento:

- Resposta Marcada como Solução

Então:

- destacar a resposta;
- marcar o tópico como resolvido;
- atualizar mecanismos de pesquisa.

---

## Quando uma comunidade for arquivada

Evento:

- Comunidade Arquivada

Então:

- impedir criação de novos canais;
- impedir criação de categorias;
- impedir novos tópicos;
- manter todo conteúdo disponível para consulta.

---

## Quando um anúncio for publicado

Evento:

- Anúncio Publicado

Então:

- disponibilizar o anúncio aos membros;
- enviar notificações, quando configurado.

---

## Quando um evento for publicado

Evento:

- Evento Publicado

Então:

- disponibilizar o evento;
- permitir discussões relacionadas.

---

# 6. Invariantes Gerais

As seguintes regras nunca podem ser violadas:

- Toda comunidade possui nome.
- Toda categoria pertence a uma comunidade.
- Todo canal pertence a uma categoria.
- Todo tópico pertence a um canal.
- Toda resposta pertence a um tópico.
- Um tópico pode possuir diversas tags.
- Apenas uma resposta pode ser marcada como solução.
- Tópicos fechados não recebem novas respostas.
- Apenas membros autenticados podem interagir.
- Conteúdo arquivado permanece disponível para consulta.

---

# 7. Fluxos Descobertos

## Fluxo 1 — Resolver uma dúvida

- Criar Tópico
- Publicar Resposta
- Marcar Solução
- Fechar Tópico

---

## Fluxo 2 — Criar uma comunidade

- Criar Comunidade
- Criar Categoria
- Criar Canal

---

## Fluxo 3 — Publicar um anúncio

- Criar Anúncio
- Publicar Anúncio
- Notificar membros

---

## Fluxo 4 — Criar um evento

- Criar Evento
- Publicar Evento
- Criar discussão relacionada (opcional)

---

# 8. Questões em Aberto

## Comunidade

- Pode ser excluída definitivamente?
- Pode alterar sua visibilidade?

## Categoria

- Possui permissões próprias?

## Canal

- Pode ser somente leitura?
- Pode aceitar apenas anúncios?

## Tópico

- O autor pode fechá-lo?
- Pode ser movido para outro canal?
- Pode ser mesclado?

## Resposta

- Existe limite para edição?
- Quem pode marcar solução?

## Tags

- Serão globais?
- Serão moderadas?
- Terão sinônimos?

## Eventos

- Possuem inscrições?
- Criam canais automaticamente?

## Anúncios

- Possuem comentários?
- Possuem data de expiração?

---

# 9. Próximos Passos

Após validar este Event Storming, os próximos documentos da arquitetura serão:

1. **05 - Casos de Uso**
2. **06 - Modelo de Domínio**
3. Diagrama de Domínio
4. Diagrama de Sequência
5. Modelo de Classes
6. Arquitetura da Aplicação

Somente durante o **Modelo de Domínio** serão definidos os **Aggregates**, **Aggregate Roots**, **Entidades**, **Objetos de Valor**, **Serviços de Domínio** e demais elementos táticos do Domain-Driven Design.