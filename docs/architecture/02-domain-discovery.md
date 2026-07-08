# Descoberta de Domínio

## Objetivo

Este documento registra a descoberta inicial do domínio do projeto **Forum**.

Seu objetivo é compreender o problema de negócio antes da implementação, identificando conceitos, regras, fluxos e decisões arquiteturais que servirão de base para a modelagem do domínio.

Este documento é um documento vivo e deverá evoluir conforme novas decisões forem sendo tomadas.

---

# 1. O problema

Plataformas de comunicação instantânea são excelentes para conversas em tempo real, porém apresentam limitações quando o objetivo é preservar conhecimento.

Entre os principais problemas observados estão:

- Dificuldade em localizar discussões antigas.
- Perda de conhecimento ao longo do tempo.
- Baixa organização das conversas.
- Repetição frequente das mesmas dúvidas.
- Dificuldade para destacar respostas corretas.
- Pouca estrutura para construção colaborativa de conhecimento.

O **Forum** nasce para resolver esses problemas oferecendo uma plataforma organizada para criação, compartilhamento e preservação do conhecimento.

---

# 2. O que é o Forum?

O **Forum** é uma plataforma de comunidades voltada para colaboração e compartilhamento de conhecimento.

Cada comunidade organiza seus conteúdos em categorias e canais temáticos.

Dentro dos canais são criados tópicos de discussão, nos quais os participantes publicam respostas relacionadas ao assunto.

O sistema busca combinar características presentes em fóruns tradicionais e plataformas modernas de comunidade, priorizando organização, pesquisa e permanência do conhecimento.

---

# 3. Participantes do domínio

O sistema possui uma entidade principal denominada **Pessoa Usuária**.

Uma pessoa usuária pode possuir uma ou mais funções (*roles*), que determinam suas permissões dentro da plataforma.

Funções inicialmente identificadas:

- Membro
- Moderador
- Administrador
- Instrutor

Uma mesma pessoa poderá exercer mais de uma função simultaneamente.

---

# 4. Principais conceitos do domínio

Durante a descoberta inicial foram identificados os seguintes conceitos:

- Comunidade
- Categoria
- Canal
- Tópico
- Resposta
- Tag
- Pessoa Usuária
- Permissão
- Evento
- Anúncio

> **Observação**
>
> Esta lista representa conceitos do domínio, e não necessariamente entidades. Alguns poderão futuramente ser modelados como Objetos de Valor (*Value Objects*), Serviços de Domínio (*Domain Services*) ou Agregados (*Aggregates*).

---

# 5. Regras de negócio identificadas

## Comunidade

- Uma comunidade organiza categorias.
- Uma comunidade possui seus próprios membros.
- Uma comunidade pode possuir moderadores e administradores próprios.

## Categoria

- Toda categoria pertence a uma única comunidade.
- Uma comunidade pode possuir diversas categorias.

## Canal

- Todo canal pertence a uma categoria.
- Uma categoria pode possuir diversos canais.
- Canais representam áreas temáticas de discussão.

## Tópico

- Todo tópico pertence a um único canal.
- Um tópico pode possuir zero ou várias tags.
- Um tópico pode ser criado por qualquer pessoa usuária que possua a permissão adequada.
- Um tópico pode ser fechado.
- Um tópico fechado não aceita novas respostas.
- Um tópico pode possuir apenas uma resposta marcada como solução.

## Resposta

- Toda resposta pertence a um único tópico.
- Apenas respostas pertencentes ao próprio tópico podem ser marcadas como solução.
- Apenas uma resposta pode ser considerada solução.

## Moderação

- Moderadores podem fechar tópicos.
- Administradores podem fechar tópicos.
- Moderadores podem editar, ocultar ou remover conteúdos inadequados.

---

# 6. Fluxos principais identificados

Os principais fluxos identificados até o momento são:

- Entrar em uma comunidade.
- Explorar categorias.
- Navegar pelos canais.
- Criar tópico.
- Responder tópico.
- Marcar resposta como solução.
- Fechar tópico.
- Criar anúncio.
- Criar evento.

Esses fluxos servirão como base para a elaboração dos casos de uso.

---

# 7. Perguntas em aberto

As seguintes questões ainda deverão ser respondidas durante a evolução do domínio:

- Existirá mais de uma comunidade ou apenas uma?
- Moderadores pertencem à plataforma ou a cada comunidade?
- Tags são globais ou pertencem à comunidade?
- Um canal pode ser exclusivo para anúncios?
- Um canal pode ser somente leitura?
- Um canal pode ser temporário?
- Um tópico pode ser movido entre canais?
- Eventos criam automaticamente um canal?
- Existe arquivamento automático de tópicos?
- Quem pode marcar uma resposta como solução?
- Um tópico resolvido é automaticamente fechado?
- Existirá sistema de reputação?
- Usuários poderão votar em respostas?
- Usuários poderão votar em tópicos?

---

# 8. Próximos passos

Após consolidar a descoberta do domínio, a documentação será desenvolvida na seguinte ordem:

1. Visão do Produto (*Vision*)
2. Linguagem Ubíqua (*Ubiquitous Language*)
3. Requisitos Funcionais e Não Funcionais
4. Casos de Uso
5. Modelo de Domínio
6. Diagramas UML
7. Modelo de Banco de Dados
8. Arquitetura da Aplicação

---

## Observações

Este documento representa o ponto de partida da modelagem do domínio.

As decisões registradas aqui poderão evoluir durante o processo de descoberta, sendo refinadas continuamente conforme novos requisitos e regras de negócio forem identificados.