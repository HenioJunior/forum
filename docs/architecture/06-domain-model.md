# 06 - Modelo de Domínio

## Objetivo

Este documento descreve o Modelo de Domínio do projeto **Forum**, identificando os principais conceitos do negócio, seus agregados, entidades, objetos de valor, eventos de domínio, invariantes e relacionamentos.

O objetivo é servir como referência para toda a implementação, mantendo a linguagem ubíqua definida anteriormente e evitando que decisões de infraestrutura influenciem a modelagem do domínio.

---

# Princípios de Modelagem

Durante a construção deste modelo foram adotados os seguintes princípios:

- foco no domínio, não no banco de dados;
- agregados pequenos;
- baixo acoplamento entre agregados;
- comunicação entre agregados por identificadores;
- regras de negócio encapsuladas no domínio;
- entidades ricas em comportamento;
- objetos de valor imutáveis;
- repositórios apenas para Aggregate Roots;
- invariantes protegidas pelos agregados.

---

# Visão Geral do Domínio

O Forum é uma plataforma voltada para organização, colaboração e preservação do conhecimento.

Sua estrutura hierárquica é composta por:

```
Comunidade
    └── Categoria
            └── Canal
                    └── Tópico
                            └── Resposta
```

Além das discussões, a plataforma possui:

- usuários;
- papéis e permissões;
- tags;
- anúncios;
- eventos;
- solução de tópicos.

---

# Agregados Identificados

Após a análise do domínio, os seguintes agregados foram identificados.

## Comunidade

Aggregate Root responsável pela organização geral da comunidade.

Responsabilidades:

- alterar informações da comunidade;
- controlar seu estado;
- controlar acesso;
- gerenciar moderadores.

---

## Categoria

Representa uma divisão lógica dentro de uma comunidade.

Responsabilidades:

- organizar canais;
- definir ordem de exibição;
- controlar visibilidade.

---

## Canal

Representa um espaço destinado às discussões de determinado assunto.

Responsabilidades:

- organizar tópicos;
- controlar configurações específicas;
- controlar permissões locais.

---

## Tópico

Principal agregado do sistema.

Responsabilidades:

- criar tópico;
- editar conteúdo;
- adicionar respostas;
- fechar tópico;
- reabrir tópico;
- marcar solução;
- controlar estado da discussão.

Este agregado protege grande parte das regras do domínio.

---

## Usuário

Representa uma pessoa participante da plataforma.

Responsabilidades:

- manter informações do perfil;
- controlar funções;
- controlar participação.

---

## Evento

Representa eventos promovidos pela comunidade.

Responsabilidades:

- cadastro;
- alteração;
- publicação;
- cancelamento.

---

## Anúncio

Representa comunicados oficiais.

Responsabilidades:

- publicação;
- edição;
- arquivamento.

---

# Entidades

## Comunidade

Representa uma comunidade temática.

Possui identidade própria.

---

## Categoria

Agrupa canais relacionados.

---

## Canal

Agrupa tópicos relacionados.

---

## Tópico

Representa uma discussão.

Possui ciclo de vida completo.

---

## Resposta

Representa uma contribuição dentro de um tópico.

Embora possua identidade própria, pertence ao ciclo de vida do Tópico.

---

## Usuário

Representa um participante da plataforma.

---

## Evento

Representa um evento organizado pela comunidade.

---

## Anúncio

Representa uma comunicação oficial.

---

# Objetos de Valor

Os seguintes conceitos são fortes candidatos a Objetos de Valor.

## Nome

Representa nomes válidos.

---

## Email

Representa endereço eletrônico válido.

---

## Slug

Identificador amigável utilizado em URLs.

---

## Conteúdo

Texto rico utilizado em tópicos, respostas e anúncios.

---

## Tag

Representa uma classificação de um tópico.

---

## Permissão

Representa uma capacidade concedida a um usuário.

---

## Papel

Representa uma função exercida pelo usuário.

Exemplos:

- Administrador
- Moderador
- Membro

---

## Estado do Tópico

Exemplos:

- Aberto
- Fechado
- Arquivado

---

# Eventos de Domínio

Entre os principais eventos identificados:

- ComunidadeCriada
- ComunidadeAtualizada

- CategoriaCriada
- CategoriaRemovida

- CanalCriado
- CanalArquivado

- TopicoCriado
- TopicoEditado
- TopicoFechado
- TopicoReaberto
- TopicoArquivado
- SolucaoMarcada

- RespostaPublicada
- RespostaEditada
- RespostaRemovida

- EventoPublicado
- EventoCancelado

- AnuncioPublicado
- AnuncioArquivado

---

# Invariantes

## Comunidade

- deve possuir nome único;
- não pode ser removida caso existam categorias ativas.

---

## Categoria

- pertence a uma única comunidade.

---

## Canal

- pertence a uma única categoria.

---

## Tópico

- pertence a um único canal;
- deve possuir autor;
- deve possuir título;
- deve possuir conteúdo;
- tópico fechado não aceita respostas;
- somente uma resposta pode ser marcada como solução.

---

## Resposta

- pertence a um único tópico;
- possui autor obrigatório;
- não pode existir sem o tópico.

---

## Usuário

- email único;
- pode exercer mais de um papel.

---

# Relacionamentos entre Agregados

```
Usuário
    │
    ├──────────────┐
    │              │
    ▼              ▼

Comunidade

    │

Categoria

    │

Canal

    │

Tópico

    │

Resposta
```

Os agregados devem referenciar outros agregados preferencialmente por seus identificadores, reduzindo o acoplamento.

---

# Repositórios

Existem repositórios apenas para Aggregate Roots.

- ComunidadeRepository
- CategoriaRepository
- CanalRepository
- TopicoRepository
- UsuarioRepository
- EventoRepository
- AnuncioRepository

Não devem existir repositórios para:

- Resposta
- Tag
- Conteúdo
- Nome
- Email
- Slug

Esses objetos são manipulados através de seus respectivos agregados.

---

# Limites Transacionais

Cada Aggregate Root define uma fronteira transacional.

Operações envolvendo múltiplos agregados devem ser coordenadas pela camada de aplicação, utilizando eventos de domínio sempre que possível.

---

# Decisões Arquiteturais

Durante a modelagem foram adotadas as seguintes decisões:

- Domínio independente da infraestrutura.
- Sem dependência de JPA no domínio.
- IDs utilizados para comunicação entre agregados.
- Objetos de Valor imutáveis.
- Regras de negócio centralizadas nos agregados.
- Eventos de domínio para comunicação entre contextos.

---

# Riscos de Modelagem

Alguns pontos ainda deverão ser refinados:

- Categoria deve realmente ser Aggregate Root?
- Canal deve ser um agregado independente?
- Resposta deve permanecer como entidade interna do agregado Tópico ou tornar-se um agregado próprio?
- Como modelar reputação dos usuários?
- Como modelar notificações?
- Como modelar busca?
- Como modelar anexos?
- Como modelar reações?
- Como modelar votação?
- Como modelar auditoria?

---

# Próximos Passos

Após a consolidação deste documento, recomenda-se elaborar:

- Diagrama de Domínio
- Diagrama de Classes Conceitual
- Bounded Contexts
- Mapa de Contextos (Context Map)
- Modelo de Persistência
- Diagramas de Sequência dos principais Casos de Uso