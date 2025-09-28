# 📇 Cadastro de Usuários - Microserviço REST

Este projeto é um microserviço desenvolvido em Java com Spring Boot, que realiza operações de cadastro, consulta, atualização e deleção de usuários. Utiliza arquitetura hexagonal (Ports & Adapters), integração com a API ViaCEP para consulta de endereço, e cobertura completa de testes unitários e de integração.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Jakarta Bean Validation
- MapStruct
- Feign Client
- H2 Database (para testes)
- JUnit 5 + Mockito
- Swagger/OpenAPI
- Maven

---

## 📌 Funcionalidades

- Criar usuário com validação de dados
- Consultar todos os usuários
- Buscar usuário por email ou nome
- Atualizar dados completos ou apenas o email
- Deletar usuário por ID
- Consulta automática de endereço via CEP (ViaCEP)

---

## 📂 Estrutura do Projeto

```text
cadastro-usuario
├── adapter
│   ├── in
│   └── out
├── application
│   └── service
├── domain
│   ├── model
│   ├── port
│   └── repository
├── exceptions
│   └── handler
└── test
    └── unit e integration
```

📌 Legenda:
- `in`: Controllers e DTOs de entrada
- `out`: Cliente ViaCEP, mappers e DTOs de saída
- `service`: Lógica de negócio (casos de uso)
- `model`: Entidades do domínio
- `port`: Interfaces de entrada e saída (hexagonal)
- `repository`: Interface JPA para persistência
- `handler`: Tratamento centralizado de erros
- `unit e integration`: Testes de todas as camadas


---

## 📬 Endpoints da API

| Método | Endpoint               | Descrição                          |
|--------|------------------------|------------------------------------|
| POST   | `/usuario`             | Cria um novo usuário               |
| GET    | `/usuario`             | Lista todos os usuários            |
| GET    | `/usuario/{email}`     | Busca usuário por email            |
| GET    | `/usuario/nome?nome=X` | Busca usuários por nome            |
| PUT    | `/usuario/{id}`        | Atualiza todos os dados do usuário |
| PATCH  | `/usuario/{id}`        | Atualiza apenas o email            |
| DELETE | `/usuario/{id}`        | Deleta usuário por ID              |

---

## 🧪 Testes

- ✅ Controllers
- ✅ Serviços
- ✅ DTOs e validações
- ✅ Mappers com MapStruct
- ✅ Integração com ViaCEP
- ✅ Repositório JPA
- ✅ Exceções e tratamento de erros
- ✅ Serialização JSON

---

## 📖 Documentação Swagger

Acesse a documentação interativa da API em:  
`http://localhost:8080/swagger-ui/index.html`

---

## 🛠️ Como Rodar Localmente

```bash
# Clone o repositório
git clone https://github.com/davirfs/cadastro-usuario.git
cd cadastro-usuario

# Execute o projeto
./mvnw spring-boot:run
```

## 👤 Autor
Davi Ribeiro Fernandes da Silva
- Desenvolvedor Backend apaixonado por arquitetura limpa, testes e qualidade de código.
- 🔗 LinkedIn 
- 📦 GitHub
