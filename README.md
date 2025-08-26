# Securitas Cash 💸

Securitas Cash é uma aplicação web para controlo financeiro pessoal, que permite aos utilizadores gerir transações em contas-correntes e cartões de crédito. O projeto foi desenvolvido como parte da disciplina de Programação para a Web II.

## Descrição do Projeto 📝

A aplicação, originalmente denominada SpendWise, permite que um utilizador, o "Correntista", registre as suas transações financeiras. Estas transações podem ser de crédito ou débito e são associadas a categorias predefinidas (ex: Saúde, Salário, Lazer) ou a categorias criadas por um utilizador "Administrador".

O sistema suporta dois tipos de contas: Conta-Corrente e Cartão de Crédito. As transações podem incluir comentários para detalhar informações adicionais, como a origem de uma herança ou detalhes de uma compra específica.

## Atores do Sistema 👥

O sistema possui dois tipos de utilizadores:

* **Correntista** 👨‍💻: O utilizador principal da aplicação. Pode criar e gerir as suas contas, registar transações, adicionar comentários e visualizar relatórios.
* **Administrador** 👑: Tem acesso a todas as funcionalidades do Correntista e, adicionalmente, pode gerir os Correntistas (criar, editar, bloquear) e as Categorias de transações (criar, editar, desativar).

## Funcionalidades 🚀

### Correntista
* **Gestão de Contas**: Criar contas do tipo "Corrente" ou "Cartão de Crédito".
* **Visualização de Contas**: Listar todas as contas registadas.
* **Gestão de Transações**: Criar e editar transações de crédito ou débito para as suas contas.
* **Gestão de Comentários**: Adicionar, editar e excluir comentários associados a uma transação.
* **Extrato Mensal**: Visualizar o extrato de transações do mês corrente, com a opção de filtrar por um período específico.

### Administrador
* Todas as funcionalidades do Correntista.
* **Gestão de Correntistas**: Criar, editar e excluir Correntistas.
* **Bloqueio de Utilizadores**: Bloquear o acesso de um Correntista ao sistema.
* **Gestão de Categorias**: Criar, editar e desativar categorias de transações.

## Tecnologias Utilizadas 💻

A aplicação foi construída com as seguintes tecnologias:
* **Backend**: Spring Boot 3.4.4, Spring Web, Spring Data JPA.
* **Base de Dados**: PostgreSQL (produção) e H2 (testes).
* **Frontend**: Thymeleaf.
* **Build**: Maven.
* **Linguagem**: Java 21.
* **Utilitários**: Lombok.


## Como Executar o Projeto ▶️

### Pré-requisitos ✅
* Java 21 ou superior.
* Maven.
* PostgreSQL.

### Passos 👣
1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd securitascash
    ```
2.  **Configure a base de dados:**
    * Crie uma base de dados no PostgreSQL com o nome `securitascash`.
    * Verifique as credenciais de acesso em `src/main/resources/application.properties` e ajuste-as se necessário.
3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
4.  A aplicação estará disponível em `http://localhost:8080`.

## Utilizadores Padrão 👤

Para facilitar os testes, a aplicação inicializa com os seguintes utilizadores ao arrancar:
* **Administrador**:
    * **Email**: `lucas@teste.com`
    * **Senha**: `admin123`
* **Correntista 1**:
    * **Email**: `lf@email.com`
    * **Senha**: `teste123`
* **Correntista 2**:
    * **Email**: `lf2@email.com`
    * **Senha**: `teste123`
