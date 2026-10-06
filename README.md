# 📘 Documentação do Sistema – API de Jogos

## 📌 Visão Geral

Este projeto é uma API REST desenvolvida com **Spring Boot** e **SQL Server** para gerenciamento de:

- 🎮 Jogos (`Jogo`)
- 🏢 Empresas (`Empresa`)

A API permite realizar operações CRUD (Create, Read, Update, Delete) sobre essas entidades, seguindo o padrão REST.

### 🧰 Tecnologias

- Java 17
- Spring Boot 4.0.6 (Web MVC, Data JPA, Validation)
- SQL Server (driver `mssql-jdbc`)
- ModelMapper
- Springdoc OpenAPI (Swagger UI)
- Lombok
- Maven (wrapper incluso — não é necessário instalar o Maven)

---

## 🏗️ Estrutura do Projeto

```
src/main/java/com/github/joaothamer/jogos
│
├── Application.java              # Classe principal (inicialização)
│
├── controller/
│   ├── JogosController.java      # Endpoints de jogos
│   └── EmpresasController.java   # Endpoints de empresas
│
├── dto/
│   ├── JogoCreateRequest.java    # DTO de entrada (criação) de Jogo
│   ├── JogoUpdateRequest.java    # DTO de entrada (atualização) de Jogo
│   ├── JogoResponse.java         # DTO de saída de Jogo
│   ├── JogoMapper.java           # Conversão entre Jogo e seus DTOs
│   ├── EmpresaCreateRequest.java # DTO de entrada (criação) de Empresa
│   ├── EmpresaUpdateRequest.java # DTO de entrada (atualização) de Empresa
│   ├── EmpresaResponse.java      # DTO de saída de Empresa
│   └── EmpresaMapper.java        # Conversão entre Empresa e seus DTOs
│
├── service/
│   ├── JogoService.java          # Regras de negócio de Jogo
│   └── EmpresaService.java       # Regras de negócio de Empresa
│
├── model/
│   ├── Jogo.java                 # Entidade Jogo
│   └── Empresa.java              # Entidade Empresa
│
└── repository/
    ├── JogoRepository.java       # Acesso ao banco (Jogos)
    └── EmpresaRepository.java    # Acesso ao banco (Empresas)
```

A API segue arquitetura em camadas: **Controller → Service → Repository**, com **DTOs** de entrada/saída e um **Mapper** (ModelMapper) isolando o modelo JPA da camada web.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- **Docker** (para subir o SQL Server e/ou a API em container)
- **Java 17+** (apenas para rodar a API localmente, fora do Docker)
- Um **SQL Server** acessível — você pode usar o seu próprio ou subir um via Docker (passo 1)

> 💡 Os comandos com `\` no fim da linha são para Linux, macOS e Git Bash. No **PowerShell** troque `\` por `` ` `` e, no **CMD**, por `^`. Em uma linha só, eles funcionam em qualquer terminal.

### Passo 1 – Subir o SQL Server com Docker

Crie uma rede Docker (permite que a API em container encontre o banco pelo nome) e suba o SQL Server:

```bash
docker network create jogos-net

docker run -d --name sqlserver --network jogos-net \
  -e ACCEPT_EULA=Y \
  -e MSSQL_SA_PASSWORD='1q2w3e4R@' \
  -p 1433:1433 \
  mcr.microsoft.com/mssql/server:2022-latest
```

- A senha do `sa` precisa ser forte (mínimo 8 caracteres, combinando maiúsculas, minúsculas, números e símbolos). A senha acima é a mesma usada como padrão nos profiles `default` e `dev`.
- Em Macs com chip Apple Silicon (M1/M2/M3...), adicione `--platform linux/amd64` ao comando.
- O SQL Server leva alguns segundos para ficar pronto. Acompanhe com `docker logs sqlserver` e aguarde a mensagem de que o banco está pronto para conexões.

### Passo 2 – Criar o banco de dados

⚠️ Diferente do MySQL, o driver do SQL Server **não cria o banco automaticamente**. O banco precisa existir antes de subir a API; as **tabelas** são criadas pelo Hibernate (`ddl-auto=update`) nos profiles `default` e `dev`.

```bash
docker exec -it sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P '1q2w3e4R@' -C \
  -Q "IF DB_ID('api') IS NULL CREATE DATABASE api"
```

Use o nome do banco conforme o profile que for rodar:

| Profile | Nome do banco |
|---|---|
| `default` (sem profile) | `api` (ou o valor de `DB_SCHEMA`) |
| `dev` | `dbdev` |
| `prd` | o valor de `DB_SCHEMA` |

Se for usar o profile `dev`, troque `api` por `dbdev` no comando acima.

### Passo 3A – Executar localmente (sem Docker para a API)

Com o SQL Server rodando e o banco criado, na pasta do projeto:

**Linux / macOS / Git Bash**

```bash
export DB_SERVER_URL=localhost
./mvnw spring-boot:run
```

**Windows (PowerShell)**

```powershell
$env:DB_SERVER_URL = "localhost"
.\mvnw.cmd spring-boot:run
```

**Windows (CMD)**

```cmd
set DB_SERVER_URL=localhost
mvnw.cmd spring-boot:run
```

> O valor padrão de `DB_SERVER_URL` é `host.docker.internal`, pensado para quando a API roda em container. Ao rodar a API diretamente na sua máquina, informe `localhost` (ou o host do seu SQL Server).

Para usar o profile `dev`:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Alternativa — gerar o `.jar` e executá-lo:

```bash
./mvnw clean package -DskipTests
java -jar target/app.jar
```

A API estará disponível em: **http://localhost:8080**

### Passo 3B – Executar a API com Docker

**Build local da imagem:**

```bash
docker build -t <docker-hub-usuario>/cp01-ms-jogos .
```

> O nome da imagem do Docker deve estar em **letras minúsculas**.

**Baixando a imagem publicada no Docker Hub (alternativa ao build):**

```bash
docker pull <docker-hub-usuario>/cp01-ms-jogos
```

**Executando o container (profile padrão) conectando ao SQL Server do passo 1:**

```bash
docker run -d --name jogos-api --network jogos-net \
  -p 8080:8080 \
  -e DB_SERVER_URL=sqlserver \
  <docker-hub-usuario>/cp01-ms-jogos
```

- `--network jogos-net` coloca a API na mesma rede do SQL Server.
- `DB_SERVER_URL=sqlserver` é o **nome do container** do banco. Dentro de um container, `localhost` aponta para o próprio container, não para a sua máquina.
- Porta, banco, usuário e senha assumem os valores padrão (`1433`, `api`, `sa`, `1q2w3e4R@`).

**Executando com o profile `dev`:**

```bash
docker run -d --name jogos-api --network jogos-net \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  -e DB_SERVER_URL=sqlserver \
  <docker-hub-usuario>/cp01-ms-jogos
```

**Executando com o profile `prd`** (todas as variáveis são obrigatórias):

```bash
docker run -d --name jogos-api -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prd \
  -e DB_SERVER_URL=<host_do_sqlserver> \
  -e DB_SERVER_PORT=1433 \
  -e DB_SCHEMA=<nome_do_banco> \
  -e DB_USER=<usuario_do_banco> \
  -e DB_PWD=<senha_do_banco> \
  <docker-hub-usuario>/cp01-ms-jogos
```

**SQL Server rodando na própria máquina (fora do Docker):** use `DB_SERVER_URL=host.docker.internal`, que já é o valor padrão. No **Linux**, adicione também `--add-host=host.docker.internal:host-gateway` ao `docker run`.

**Comandos úteis:**

```bash
docker logs -f jogos-api                 # acompanhar os logs da API
docker stop jogos-api sqlserver          # parar os containers
docker rm jogos-api sqlserver            # remover os containers
docker network rm jogos-net              # remover a rede
```

---

## 🔧 Variáveis de Ambiente

| Variável | Obrigatória em `prd` | Padrão (`default` / `dev`) | Descrição |
|---|---|---|---|
| `SPRING_PROFILES_ACTIVE` | — | *(vazio = `default`)* | Profile ativo (`dev`, `prd` ou omitido) |
| `DB_SERVER_URL` | Sim | `host.docker.internal` | Host do servidor SQL Server |
| `DB_SERVER_PORT` | Sim | `1433` | Porta do servidor SQL Server |
| `DB_SCHEMA` | Sim | `api` | Nome do banco de dados (ignorado no profile `dev`, que usa `dbdev`) |
| `DB_USER` | Sim | `sa` | Usuário do banco |
| `DB_PWD` | Sim | `1q2w3e4R@` | Senha do banco |

⚠️ Os valores padrão existem apenas por conveniência em desenvolvimento. **Nunca** use as credenciais padrão em produção.

---

## ⚙️ Perfis (profiles)

| Profile | Arquivo | Comportamento |
|---|---|---|
| `default` | `application.properties` | Valores padrão para as variáveis de banco; `ddl-auto=update` (cria/atualiza as tabelas); `show-sql=true` |
| `dev` | `application-dev.properties` | Banco fixo `dbdev`; demais variáveis com valores padrão; `ddl-auto=update`; `show-sql=true` |
| `prd` | `application-prd.properties` | Todas as variáveis são obrigatórias (sem valores padrão); `ddl-auto=none`; `show-sql=false` |

⚠️ No profile `prd` nem o banco nem as tabelas são criados automaticamente — a estrutura (incluindo eventuais sequences usadas para gerar os IDs) deve existir previamente. Uma forma de obter o DDL é rodar a API uma vez com o profile `default` ou `dev` em um banco de testes e aproveitar o SQL exibido nos logs (`show-sql=true`).

---

## 📑 Acessando o Swagger/OpenAPI

Com a aplicação em execução, a documentação interativa (Swagger UI) fica disponível em:

```
http://localhost:8080/
```

A especificação OpenAPI "crua" (JSON) fica disponível em:

```
http://localhost:8080/v3/api-docs
```

---

## 🧩 Entidades do Sistema

### 🎮 Jogo (tabela `jogos`)

Representa um jogo cadastrado. Todos os campos são obrigatórios.

| Campo | Tipo | Observação |
|---|---|---|
| `id` | Long | Gerado automaticamente |
| `nome` | String | Mínimo de 2 caracteres |
| `franquia` | String | — |
| `classificacao` | String | — |
| `fabricante` | String | — |

### 🏢 Empresa (tabela `empresas`)

Representa uma empresa desenvolvedora ou publicadora. Todos os campos são obrigatórios.

| Campo | Tipo | Observação |
|---|---|---|
| `id` | Long | Gerado automaticamente |
| `nome` | String | Mínimo de 2 caracteres |
| `pais` | String | — |
| `ramo` | String | — |
| `sede` | String | — |

---

## 🌐 Endpoints da API

Todos os endpoints são versionados sob o prefixo `api/${api.version}` (padrão: `api/v1`).

### 🎮 Jogos (`/api/v1/jogos`)

| Método | Rota | Descrição | Respostas |
|---|---|---|---|
| `GET` | `/api/v1/jogos` | Listar todos os jogos | `200` |
| `GET` | `/api/v1/jogos/{id}` | Buscar jogo por ID | `200`, `404` |
| `POST` | `/api/v1/jogos` | Criar novo jogo | `201`, `400` (validação) |
| `PUT` | `/api/v1/jogos/{id}` | Atualizar jogo | `200`, `404` |
| `DELETE` | `/api/v1/jogos/{id}` | Deletar jogo | `204`, `404` |

Exemplo de body (`POST` / `PUT`):

```json
{
  "nome": "The Witcher 3",
  "franquia": "The Witcher",
  "classificacao": "18",
  "fabricante": "CD Projekt Red"
}
```

### 🏢 Empresas (`/api/v1/empresas`)

| Método | Rota | Descrição | Respostas |
|---|---|---|---|
| `GET` | `/api/v1/empresas` | Listar todas as empresas | `200` |
| `GET` | `/api/v1/empresas/{id}` | Buscar empresa por ID | `200`, `404` |
| `POST` | `/api/v1/empresas` | Criar empresa | `201`, `400` (validação) |
| `PUT` | `/api/v1/empresas/{id}` | Atualizar empresa | `200`, `404` |
| `DELETE` | `/api/v1/empresas/{id}` | Deletar empresa | `204`, `404` |

Exemplo de body (`POST` / `PUT`):

```json
{
  "nome": "CD Projekt Red",
  "pais": "Polônia",
  "ramo": "Desenvolvedora de jogos",
  "sede": "Varsóvia"
}
```

Exemplo rápido com `curl`:

```bash
curl -X POST http://localhost:8080/api/v1/jogos \
  -H "Content-Type: application/json" \
  -d '{"nome":"The Witcher 3","franquia":"The Witcher","classificacao":"18","fabricante":"CD Projekt Red"}'

curl http://localhost:8080/api/v1/jogos
```

---

## 🗄️ Camadas da Aplicação

- **Controller** — expõe os endpoints REST (`JogosController`, `EmpresasController`).
- **Service** — concentra as regras de negócio (`JogoService`, `EmpresaService`).
- **Repository** — interfaces Spring Data JPA que acessam o SQL Server, por exemplo:

  ```java
  public interface JogoRepository extends JpaRepository<Jogo, Long> {
  }
  ```

- **Model** — entidades JPA mapeadas para as tabelas `jogos` e `empresas`.
- **DTO / Mapper** — objetos de entrada/saída e conversão (ModelMapper), isolando o modelo JPA da camada web.

---

## 🛠️ Solução de Problemas

| Erro / sintoma | Causa provável | Como resolver |
|---|---|---|
| `Cannot open database "api" requested by the login` | O banco ainda não foi criado | Execute o **Passo 2** (use `dbdev` no profile `dev`) |
| `Login failed for user 'sa'` | Usuário ou senha diferentes dos configurados no SQL Server | Confira `DB_USER` e `DB_PWD` e a senha usada em `MSSQL_SA_PASSWORD` |
| `Connection refused` / timeout | SQL Server ainda iniciando, porta incorreta ou host errado | Aguarde o banco subir (`docker logs sqlserver`) e revise `DB_SERVER_URL` / `DB_SERVER_PORT` |
| API em container não encontra o banco | Uso de `localhost` dentro do container ou containers em redes diferentes | Use `--network jogos-net` com `DB_SERVER_URL=sqlserver`, ou `host.docker.internal` para um banco na máquina host |
| Container do SQL Server encerra logo após subir | Senha do `sa` fraca ou `ACCEPT_EULA` ausente | Use uma senha forte e `-e ACCEPT_EULA=Y` |
| `port is already allocated` | A porta `1433` ou `8080` já está em uso | Pare o processo que usa a porta ou altere o mapeamento (`-p 14330:1433`, por exemplo) |
