📘 Documentação do Sistema – API de Jogos
📌 Visão Geral

Este projeto é uma API REST desenvolvida com Spring Boot para gerenciamento de:

🎮 Jogos (Jogo)
🏢 Empresas (Empresa)

A API permite realizar operações CRUD (Create, Read, Update, Delete) sobre essas entidades, seguindo o padrão REST.

🏗️ Estrutura do Projeto
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
│   ├── Jogo.java                # Entidade Jogo
│   └── Empresa.java             # Entidade Empresa
│
├── repository/
│   ├── JogoRepository.java      # Acesso ao banco (Jogos)
│   └── EmpresaRepository.java   # Acesso ao banco (Empresas)

A API segue arquitetura em camadas: **Controller → Service → Repository**, com **DTOs** de entrada/saída e um **Mapper** (ModelMapper) isolando o modelo JPA da camada web.

🚀 Como Executar o Projeto
Pré-requisitos
Java 17+
Maven
MySQL (ou Docker)

Passos
Navegue até a pasta do projeto e execute:
./mvnw spring-boot:run

ou no Windows:

mvnw.cmd spring-boot:run
A API estará disponível em:
http://localhost:8080

🐳 Executando com Docker

**Build local da imagem:**
```
docker build -t <docker-hub-usuario>/CP01-MS-Jogos .
```

**Baixando a imagem publicada no Docker Hub:**
```
docker pull <docker-hub-usuario>/CP01-MS-Jogos
```

**Executando o container** (mapeando a porta 8080, selecionando o profile e informando as variáveis de ambiente do banco de dados):
```
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prd \
  -e DB_SERVER_URL=<host_do_mysql> \
  -e DB_SERVER_PORT=3306 \
  -e DB_SCHEMA=<nome_do_schema> \
  -e DB_USER=<usuario_do_banco> \
  -e DB_PWD=<senha_do_banco> \
  <docker-hub-usuario>/CP01-MS-Jogos
```

Para rodar no profile padrão (default), basta omitir `SPRING_PROFILES_ACTIVE` — nesse caso o schema é criado automaticamente e as credenciais assumem valores padrão (`root` / `root_pwd`) caso não sejam informadas:
```
docker run -p 8080:8080 <docker-hub-usuario>/CP01-MS-Jogos
```

**Variáveis de ambiente:**

| Variável | Obrigatória em `prd` | Descrição |
|---|---|---|
| `SPRING_PROFILES_ACTIVE` | — | Profile ativo (`prd` ou omitido para o padrão) |
| `DB_SERVER_URL` | Sim | Host do servidor MySQL |
| `DB_SERVER_PORT` | Sim | Porta do servidor MySQL |
| `DB_SCHEMA` | Sim | Nome do schema/banco de dados |
| `DB_USER` | Sim | Usuário do banco |
| `DB_PWD` | Sim | Senha do banco |

⚠️ No profile `prd`, o banco e as tabelas **não** são criados automaticamente (`ddl-auto=none`) — o schema e a estrutura devem existir previamente.

📑 Acessando o Swagger/OpenAPI

Com a aplicação em execução, a documentação interativa (Swagger UI) fica disponível em:
```
http://localhost:8080/
```

A especificação OpenAPI "crua" (JSON) fica disponível em:
```
http://localhost:8080/v3/api-docs
```

⚙️ Perfis (profiles)
- `application.properties` — configuração padrão (profile default)
- `application-dev.properties` — ambiente de desenvolvimento (schema `dbdev`)
- `application-prd.properties` — ambiente de produção (todas as variáveis são obrigatórias, sem valores padrão, e sem criação automática de banco/tabelas)
🧩 Entidades do Sistema
🎮 Jogo

Representa um jogo cadastrado.

Atributos esperados (baseado no model):

id
nome
(possivelmente outros campos dependendo da implementação)
🏢 Empresa

Representa uma empresa desenvolvedora ou publicadora.

Atributos esperados:

id
nome
🌐 Endpoints da API
Todos os endpoints são versionados sob o prefixo `api/${api.version}` (padrão: `api/v1`).

🎮 Jogos (/api/v1/jogos)
🔍 Listar todos os jogos
GET /api/v1/jogos
🔎 Buscar jogo por ID
GET /api/v1/jogos/{id}
➕ Criar novo jogo
POST /api/v1/jogos

Body (JSON):

{
  "nome": "The Witcher 3",
  "franquia": "The Witcher",
  "classificacao": "18",
  "fabricante": "CD Projekt Red"
}
✏️ Atualizar jogo
PUT /api/v1/jogos/{id}
❌ Deletar jogo
DELETE /api/v1/jogos/{id}
🏢 Empresas (/api/v1/empresas)
🔍 Listar todas as empresas
GET /api/v1/empresas
🔎 Buscar empresa por ID
GET /api/v1/empresas/{id}
➕ Criar empresa
POST /api/v1/empresas

Body:

{
  "nome": "CD Projekt Red",
  "pais": "Polônia",
  "ramo": "Desenvolvedora de jogos",
  "sede": "Varsóvia"
}
✏️ Atualizar empresa
PUT /api/v1/empresas/{id}
❌ Deletar empresa
DELETE /api/v1/empresas/{id}
🗄️ Camadas da Aplicação
📌 Controller

Responsável por expor os endpoints REST.

Exemplo:

JogosController
EmpresasController
📌 Repository

Interface que faz comunicação com o banco de dados usando Spring Data JPA.

Exemplo:

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
📌 Model

Define as entidades do sistema (tabelas do banco).

Exemplo:

@Entity
public class Jogo {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
}
⚙️ Configuração

Arquivo:

src/main/resources/application.properties

Aqui você configura:

conexão com banco
porta do servidor
JPA / Hibernate
