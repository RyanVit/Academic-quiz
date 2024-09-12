# API Academic Quiz

Este projeto foi criado com o intuito de criar uma forma de estudos divertida e interativa, baseado em um quiz, onde o usuario pode se cadastrar e responder perguntas de varias tematicas, e assim, aprender e se divertir ao mesmo tempo.

Tecnologias:

- Linguagem: Java 17
- Spring Boot
- Postgres

## Executar o projeto

### Requisitos

- ter o Java 17 instalado
- ter o Maven instalado

### Executar

- Clone o projeto para sua maquina local
- Adicione um arquivo .env no diretorio raiz do projeto seguindo o exemplo do arquivo do exemplo.env passando as informações do banco
- Execute o comando `mvn spring-boot:run` no diretorio raiz do projeto
- Acessar o endereço `http://localhost:8080` em seu navegador

## Endpoints

### Temas

#### Listar todos os temas

- **GET /themes**
  - Resposta: lista de temas

#### Criar um tema

- **POST /themes**

  - name: nome do tema

  Caso de sucesso:

  - id
  - theme

  Caso de erro:

  - message

#### Buscar um tema por id

- **GET /themes/{id}**

  - Parâmetro: id do tema

  Caso de sucesso:

  - id
  - theme

  Caso de erro:

  - message

#### Atualizar um tema

- **PUT /themes/{id}**

  - Parâmetro: id do tema
  - Corpo da requisição: nome do tema

  Caso de sucesso:

  - id
  - theme

  Caso de erro:

  - message

#### Deletar um tema

- **DELETE /theme/{id}**
  - Parâmetro: id do tema
  - Resposta: tema deletado

### Questões

#### Listar todas as perguntas de um tema

- **GET /questions/{id}**
  - Parâmetro: id da questão
  - Resposta: lista de perguntas do tema

#### Criar uma pergunta

- **POST /questions**

  - Corpo da requisição:
  - themeId: Number
  - statement: String
  - alternatives: Array
    - description: String
    - isCorrect: Boolean

  Caso de sucesso:

  - questão criada com sucesso

  Caso de erro:

  - message

#### Buscar uma pergunta por id

- **GET questions/{id}**

  - Parâmetro: id da pergunta
  - Resposta:

  - themeId: Number
    - statement: String
    - alternatives: Array
    - id: Number
      - description: String
      - isCorrect: Boolean

#### Atualizar uma pergunta

- **PUT /questions/{id}**

  - Parâmetro: id da pergunta
  - Corpo da requisição:
    - statement: String
    - alternatives: Array
    - id: Number
      - description: String
      - isCorrect: Boolean

  Caso de sucesso: - pergunta atualizada

  caso de erro:

  - message

#### Deletar uma pergunta

- **DELETE/questions/{id}**
  - Parâmetro: id da pergunta
  - Resposta: pergunta deletada

## Quiz

#### Listar todas as perguntas por tema

- **GET quiz/{themeId}**

  - Parâmetro: id do tema
  - Resposta:

  - themeId: Number
    - questions: Array
    - statement:
    - id: Number
      - alternatives: Array
      - id: Number
      - description: String
      - isCorrect: Boolean

#### Confirmar Respostas

- **POST quiz/{themeId}**

  - Parâmetro: id do tema
  - Resposta:

  - themeId: Number
    - questions: Array
    - statement:
    - id: Number
      - alternatives: Array
      - id: Number
      - description: String
      - isCorrect: Boolean

caso de sucesso:

- themeId: Number,
- questions: Array
  - questionId: Number
  - alternativeId: Number
  - isCorrect: Boolean
  - alternativeCorrectId: Number
