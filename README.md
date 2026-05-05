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
│   └── EmpresasController.java  # Endpoints de empresas
│
├── model/
│   ├── Jogo.java                # Entidade Jogo
│   └── Empresa.java             # Entidade Empresa
│
├── repository/
│   ├── JogoRepository.java      # Acesso ao banco (Jogos)
│   └── EmpresaRepository.java   # Acesso ao banco (Empresas)
🚀 Como Executar o Projeto
Pré-requisitos
Java 17+
Maven
Passos
Navegue até a pasta do projeto:
cd CP02-MS
Execute:
./mvnw spring-boot:run

ou no Windows:

mvnw.cmd spring-boot:run
A API estará disponível em:
http://localhost:8080
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
🎮 Jogos (/jogos)
🔍 Listar todos os jogos
GET /jogos
🔎 Buscar jogo por ID
GET /jogos/{id}
➕ Criar novo jogo
POST /jogos

Body (JSON):

{
  "nome": "The Witcher 3"
}
✏️ Atualizar jogo
PUT /jogos/{id}
❌ Deletar jogo
DELETE /jogos/{id}
🏢 Empresas (/empresas)
🔍 Listar todas as empresas
GET /empresas
🔎 Buscar empresa por ID
GET /empresas/{id}
➕ Criar empresa
POST /empresas

Body:

{
  "nome": "CD Projekt Red"
}
✏️ Atualizar empresa
PUT /empresas/{id}
❌ Deletar empresa
DELETE /empresas/{id}
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
