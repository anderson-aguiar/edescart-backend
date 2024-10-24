# E-Descart Back-End

### Funcionalidade
Projeto utilizado no meu TCC do curso de Engenharia de Software pela Unicesumar. A aplicação visa facilitar o descarte consciente de resíduos na região de Campinas - SP, promovendo práticas sustentáveis e localizando pontos de coleta próximos.

### Principais Temas Abordados
- Injeção de Dependência
- Padrão Camadas
- Modelo de Domínio
- Mapeamento Objeto-Relacional (ORM)
- Tratamento de Exceções
- Validação de Dados
- Consultas e Transações com Banco de Dados
- Controle de Acesso (OAuth2, JWT)
- Variáveis de Ambiente e Perfis de Projeto

---

## Modelo de Domínio
![Modelo de Domínio](https://github.com/anderson-aguiar/edescart-backend/blob/main/img/Diagrama%20E-Descart.png)

---

## Tecnologias Utilizadas
- **Linguagem:** Java
- **Framework:** Spring Boot, Spring Security, JPA, OAuth2, JWT
- **Banco de Dados:** H2 (Testes), PostgreSQL (Produção)
- **Ferramentas:** Maven, Postman
- **IDE:** STS/Eclipse

---

## Como Rodar o Projeto

### Requisitos

- **Java 11** ou superior
- **Maven**
- **PostgreSQL** (ou H2 para testes)
- **Docker** (opcional)

### 1. Configuração do Banco de Dados

1. Crie o banco de dados PostgreSQL:
   ```sql
   CREATE DATABASE edescart_db;
   ```

2. Configure o arquivo `application.properties` com suas credenciais do banco:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/edescart_db
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

### 2. Executando a Aplicação Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/anderson-aguiar/edescart-backend.git
   cd edescart-backend
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`.

### 3. Executando com Docker

#### Pré-requisitos:

- **Docker** e **Docker Compose** instalados.

1. Construa a imagem Docker:
   ```bash
   docker build -t edescart-backend .
   ```

2. Execute os containers usando Docker Compose:
   ```bash
   docker-compose up
   ```

Isso iniciará o banco de dados PostgreSQL e o backend em containers.

---
## Variáveis de Ambiente

Certifique-se de configurar as seguintes variáveis de ambiente antes de rodar o projeto:

- `SPRING_DATASOURCE_URL` - URL do banco de dados PostgreSQL.
- `SPRING_DATASOURCE_USERNAME` - Nome de usuário do banco de dados.
- `SPRING_DATASOURCE_PASSWORD` - Senha do banco de dados.
- `JWT_SECRET` - Chave secreta para geração de tokens JWT.

Exemplo de configuração no Linux:
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/edescart_db
export SPRING_DATASOURCE_USERNAME=seu_usuario
export SPRING_DATASOURCE_PASSWORD=sua_senha
export JWT_SECRET=sua_chave_secreta
```

---


[Front-end](https://github.com/anderson-aguiar/edescart-frontend)

