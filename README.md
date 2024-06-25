# Biblioteca de Gerenciamento - Relatório

## Introdução

Este projeto é uma aplicação de gerenciamento de biblioteca desenvolvida em Java. A aplicação permite gerenciar inventário de livros, registros de clientes, transações de empréstimos e inclui um sistema de autenticação de usuários para controle de acesso.

## Funcionalidades do Sistema

### 1. Gerenciamento de Livros
- **Adicionar Livro**: Permite adicionar novos livros ao inventário da biblioteca.
- **Excluir Livro**: Permite remover livros do inventário da biblioteca com base no ISBN.
- **Pesquisar Livro**: Permite procurar livros no inventário por ISBN e exibir suas informações.

### 2. Gerenciamento de Clientes
- **Adicionar Cliente**: Permite adicionar novos clientes ao sistema.
- **Excluir Cliente**: Permite remover clientes do sistema com base no nome.
- **Pesquisar Cliente**: Permite procurar clientes no sistema por nome e exibir suas informações.

### 3. Gerenciamento de Empréstimos
- **Adicionar Empréstimo**: Permite registrar novos empréstimos de livros para clientes.
- **Excluir Empréstimo**: Permite remover registros de empréstimos do sistema com base no ISBN do livro e no nome do cliente.
- **Pesquisar Empréstimo**: Permite procurar empréstimos no sistema e exibir informações detalhadas sobre eles.

### 4. Autenticação de Usuários
- **Login de Usuários**: Sistema de login para autenticação de usuários, restringindo o acesso às funcionalidades da aplicação com base em suas credenciais.
- **Adicionar Usuário**: Permite adicionar novos usuários ao sistema (apenas administradores).
- **Remover Usuário**: Permite remover usuários do sistema (apenas administradores).

## Estrutura do Código

### 1. `Livro.java`
Classe que representa um livro com atributos como título, autor, ISBN, categoria e disponibilidade.

### 2. `Cliente.java`
Classe que representa um cliente com atributos como nome, endereço e contato.

### 3. `Emprestimo.java`
Classe que representa um empréstimo com atributos como livro, cliente, data de empréstimo e data de devolução.

### 4. `CSVUtils.java`
Classe utilitária para manipulação de arquivos CSV, permitindo salvar, ler e excluir dados de livros, clientes e empréstimos.

### 5. `UserAuth.java`
Classe responsável pela autenticação de usuários, mantendo um mapa de usuários e senhas e métodos para autenticação, adição e remoção de usuários.

### 6. `LibraryGUI.java`
Classe que implementa a interface gráfica da aplicação usando Swing. Inclui telas para login, gerenciamento de livros, gerenciamento de clientes e exibição de informações.

## Como Executar o Programa

1. **Pré-requisitos**: Certifique-se de ter o JDK (Java Development Kit) instalado na sua máquina.
2. **Compilação**: Compile as classes Java utilizando o comando:
    ```sh
    javac *.java
    ```
3. **Execução**: Execute a aplicação com o comando:
    ```sh
    java LibraryGUI
    ```
4. **Login**: Ao iniciar a aplicação, será exibida uma tela de login. Use as credenciais padrão:
    - **Usuário**: `librarian`
    - **Senha**: `lib123`
    ou
    - **Usuário**: `admin`
    - **Senha**: `admin123`

## Possíveis Melhorias
- **Botão de emprestimo**: Incluir nas opções da interface, um botão para empréstimo do livro.
- **Validação de Entradas**: Implementar validação de entradas para garantir que os dados fornecidos pelos usuários sejam válidos.
- **Relatórios e Estatísticas**: Adicionar funcionalidades para geração de relatórios e estatísticas sobre livros, clientes e empréstimos.
- **Interface Gráfica Aprimorada**: Melhorar a interface gráfica para uma melhor experiência do usuário.
- **Persistência em Banco de Dados**: Substituir os arquivos CSV por um banco de dados para maior escalabilidade e eficiência.

## Conclusão

Este projeto fornece uma base sólida para um sistema de gerenciamento de biblioteca com funcionalidades essenciais e uma interface gráfica intuitiva. Com algumas melhorias e expansões, pode se tornar uma ferramenta ainda mais poderosa e útil para gerenciamento de bibliotecas.
