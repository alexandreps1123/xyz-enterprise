# API XYZ

## Introdução

A API XYZ é um serviço para gerenciamento de pedidos e produtos. Esta documentação cobre os endpoints disponíveis para interagir com os recursos de pedidos e produtos.

## Como executar

Toda aplicação roda em docker, MySQL, RabbitMQ e, inclusive, a API está configurada para ser executada com docker.

Para versões Docker >= v2.20.2:

```
docker compose build
```
```
docker compose up
```

Para versões anteriores:

```
docker-compose build
```
```
docker-compose up
``` 

## Endpoints

### Pedidos

#### Listar todos os pedidos

- **Endpoint:** `GET /api/pedidos`
- **Descrição:** Retorna a lista de todos os pedidos.
- **Resposta Exemplo:**
    ```json
    [
        {
            "id": 1,
            "dataPedido": "2024-08-18T12:00:00",
            "status": "PENDENTE",
            "itens": [
                {
                    "id": 1,
                    "produto": {
                        "id": 1,
                        "nome": "Produto A",
                        "descricao": "Descrição do Produto A",
                        "preco": 10.00,
                        "quantidade": 100
                    },
                    "quantidade": 2
                }
            ]
        }
    ]
    ```

#### Buscar um pedido por ID
- **Endpoint:** `GET /api/pedidos/{id}`
- **Descrição:** Retorna os detalhes de um pedido específico.
- **Parâmetros:**
    - id (path): ID do pedido.
- **Resposta Exemplo:**
    ```json
    {
        "id": 1,
        "dataPedido": "2024-08-18T12:00:00",
        "status": "PENDENTE",
        "itens": [
            {
                "id": 1,
                "produto": {
                    "id": 1,
                    "nome": "Produto A",
                    "descricao": "Descrição do Produto A",
                    "preco": 10.00,
                    "quantidade": 100
                },
                "quantidade": 2
            }
        ]
    }
    ```
#### Criar um novo pedido
- **Endpoint:** POST /api/pedidos
- **Descrição:** Cria um novo pedido.
- **Requisição Exemplo:**
    ```json
    {
        "itens": [
            {
            "produto": {
                "id": 1
            },
            "quantidade": 2
            }
        ]
    }
    ```
- **Resposta Exemplo:**
    ```json
    {
        "id": 1,
        "dataPedido": "2024-08-18T12:00:00",
        "status": "PENDENTE",
        "itens": [
            {
            "id": 1,
            "produto": {
                "id": 1,
                "nome": "Produto A",
                "descricao": "Descrição do Produto A",
                "preco": 10.00,
                "quantidade": 100
            },
            "quantidade": 2
            }
        ]
    }
    ```
#### Atualizar um pedido
- **Endpoint:** PUT /api/pedidos/{id}
- **Descrição:** Atualiza um pedido existente.
- **Parâmetros:**
    - id (path): ID do pedido.
- **Requisição Exemplo:**
    ```json
    {
        "dataPedido": "2024-08-18T12:00:00",
        "status": "FINALIZADO",
        "itens": [
            {
            "produto": {
                "id": 1
            },
            "quantidade": 3
            }
        ]
    }
    ```
- **Resposta Exemplo:**
    ```json
    {
    "id": 1,
    "dataPedido": "2024-08-18T12:00:00",
    "status": "FINALIZADO",
    "itens": [
        {
        "id": 1,
        "produto": {
            "id": 1,
            "nome": "Produto A",
            "descricao": "Descrição do Produto A",
            "preco": 10.00,
            "quantidade": 100
        },
        "quantidade": 3
        }
    ]
    }
    ```
#### Deletar um pedido
- **Endpoint:** DELETE /api/pedidos/{id}
- **Descrição:** Remove um pedido existente.
- **Parâmetros:**
    - id (path): ID do pedido.
- **Resposta Exemplo:** Código de status HTTP 204 No Content.
#### Alterar status de um pedido
- **Endpoint:** PUT /api/pedidos/{id}/status
- **Descrição:** Altera o status de um pedido.
- **Parâmetros:**
    - id (path): ID do pedido.
    - status (query): Novo status do pedido. (Ex.: PENDENTE, FINALIZADO)
- **Requisição Exemplo:** PUT /api/pedidos/1/status?status=FINALIZADO
- **Resposta Exemplo:**
    ```json
    {
    "id": 1,
    "dataPedido": "2024-08-18T12:00:00",
    "status": "FINALIZADO",
    "itens": [
        {
        "id": 1,
        "produto": {
            "id": 1,
            "nome": "Produto A",
            "descricao": "Descrição do Produto A",
            "preco": 10.00,
            "quantidade": 100
        },
        "quantidade": 3
        }
    ]
    }
    ```
### Produtos
#### Listar todos os produtos
- **Endpoint:** GET /api/produtos
- **Descrição:** Retorna a lista de todos os produtos.
- **Resposta Exemplo:**
    ```json
    [
    {
        "id": 1,
        "nome": "Produto A",
        "descricao": "Descrição do Produto A",
        "preco": 10.00,
        "quantidade": 100
    }
    ]
    ```
#### Buscar um produto por ID
- **Endpoint:** GET /api/produtos/{id}
- **Descrição:** Retorna os detalhes de um produto específico.
- **Parâmetros:**
id (path): ID do produto.
- **Resposta Exemplo:**
    ```json
    {
    "id": 1,
    "nome": "Produto A",
    "descricao": "Descrição do Produto A",
    "preco": 10.00,
    "quantidade": 100
    }
    ```
#### Criar um novo produto
- **Endpoint:** POST /api/produtos
- **Descrição:** Cria um novo produto.
- **Requisição Exemplo:**
    ```json
    {
    "nome": "Produto B",
    "descricao": "Descrição do Produto B",
    "preco": 20.00,
    "quantidade": 50
    }
    ```
- **Resposta Exemplo:**
    ```json
    {
    "id": 2,
    "nome": "Produto B",
    "descricao": "Descrição do Produto B",
    "preco": 20.00,
    "quantidade": 50
    }
    ```
#### Atualizar um produto
- **Endpoint:** PUT /api/produtos/{id}
- **Descrição:** Atualiza um produto existente.
- **Parâmetros:**
id (path): ID do produto.
- **Requisição Exemplo:**
    ```json
    {
    "nome": "Produto B Atualizado",
    "descricao": "Descrição atualizada",
    "preco": 25.00,
    "quantidade": 30
    }
    ```
- **Resposta Exemplo:**
    ```json
    {
    "id": 2,
    "nome": "Produto B Atualizado",
    "descricao": "Descrição atualizada",
    "preco": 25.00,
    "quantidade": 30
    }
    ```
#### Deletar um produto
- **Endpoint:** DELETE /api/produtos/{id}
- **Descrição:** Remove um produto existente.
- **Parâmetros:**
id (path): ID do produto.
- **Resposta Exemplo:** Código de status HTTP 204 No Content.

## Passos para implementar a aplicação na AWS
### Configurar o Amazon RDS para MySQL
#### Criar uma Instância RDS:

- Acessar o Console de Gerenciamento da AWS.
- Naveguar até RDS e clicar em Criar banco de dados.
- Selecionar MySQL como o mecanismo do banco de dados.
- Configurar os detalhes da instância (tamanho, armazenamento, etc.).
- Definir um nome para o banco de dados, usuário e senha.
- Configurar as opções de rede e segurança (subnets, VPC, grupos de segurança).
- Revisar as configurações e clicar em Criar banco de dados.
#### Obter as Credenciais e Endpoint:

- Após a criação, anotar o endpoint do banco de dados, o nome do banco de dados, o usuário e a senha. Para conectar a aplicação ao RDS.

### Configurar o Amazon ECS
#### Criar um Repositório no Amazon ECR (Elastic Container Registry):

- Acessar o Console do Amazon ECR.
- Clicar em Criar repositório.
- Dar um nome ao repositório e clicar em Criar repositório.
#### Fazer o Push da Imagem Docker para o ECR:

- Fazer login no ECR usando o CLI do AWS:
```bash
aws ecr get-login-password --region <sua-região> | docker login --username AWS --password-stdin <seu-id-da-conta>.dkr.ecr.<sua-região>.amazonaws.com
```
- Marcar a imagem Docker com a tag do ECR:
```bash
docker tag sua-aplicacao:latest <seu-id-da-conta>.dkr.ecr.<sua-região>.amazonaws.com/seu-repositorio:latest
```
- Fazer o push da imagem para o ECR:
```bash
docker push <seu-id-da-conta>.dkr.ecr.<sua-região>.amazonaws.com/seu-repositorio:latest
```
#### Criar uma Task Definition no ECS:

- Acessar o Console do Amazon ECS.
- Navegar até Task Definitions e clicar em Create new Task Definition.
- Selecionar Fargate ou EC2 como o tipo de lançamento.
- Configurar os detalhes da task (nome, memória, CPU).
- Adicionar um container à task, especificando a imagem do ECR, portas expostas (8080 para a aplicação Spring Boot, por exemplo) e variáveis de ambiente necessárias para conectar ao RDS e RabbitMQ.
- Clicar em Create para salvar a task definition.
#### Criar um Cluster ECS:

- Navegar até Clusters e clicar em Create Cluster.
- Selecionar EC2 Linux + Networking ou Fargate, conforme necessário.
- Configurar o nome do cluster e outras configurações.
- Clicar em Create para criar o cluster.
#### Criar um Serviço ECS:

- No cluster criado, vá para a guia Services e clicar em Create.
- Selecionar o tipo de serviço (por exemplo, Fargate).
- Configurar o nome do serviço, a quantidade de tarefas desejadas e selecionar a task definition criada anteriormente.
- Configurar o balanceador de carga (se necessário) e as configurações de rede (subnets, grupos de segurança).
- Clicar em Create Service para implantar o serviço.
### Configurar o Amazon RabbitMQ
Para o RabbitMQ, usar uma imagem Docker no ECS ou utilizar o Amazon MQ:

#### Usar o Amazon MQ:

- Acessar o Console do Amazon MQ.
- Clicar em Create broker.
- Escolher RabbitMQ como o tipo de broker.
- Configurar o nome do broker, tipo de instância, e outras opções de configuração.
- Configurar a segurança (VPC, grupos de segurança).
- Revisar as configurações e clicar em Create broker.
#### Obter as Credenciais e Endpoint do RabbitMQ:

- Após a criação, anotar o endpoint e as credenciais do RabbitMQ.
### Configurar a Conexão da Aplicação
Atualizar as variáveis de ambiente no serviço ECS com as informações do RDS e RabbitMQ:

- Para MySQL:

    ```properties
        SPRING_DATASOURCE_URL=jdbc:mysql://<endpoint-rds>:3306/nome_do_banco_de_dados?useSSL=false&serverTimezone=UTC
        SPRING_DATASOURCE_USERNAME=seu_usuario
        SPRING_DATASOURCE_PASSWORD=sua_senha
    ```
- Para RabbitMQ:

    ```properties
        SPRING_RABBITMQ_HOST=<endpoint-rabbitmq>
        SPRING_RABBITMQ_PORT=5672
        SPRING_RABBITMQ_USERNAME=guest
        SPRING_RABBITMQ_PASSWORD=guest
    ```
### Monitoramento e Gerenciamento
- CloudWatch Logs: Configurcar o Amazon CloudWatch para monitorar logs e métricas da aplicação e dos serviços.
- RDS Monitoring: Usar o painel de monitoramento do RDS para verificar o desempenho do banco de dados.
- ECS Monitoring: Monitorar as tarefas e serviços no Console do ECS para garantir que estão funcionando corretamente.
