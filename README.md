Orgs
===========================================

Orgs é um aplicativo Android desenvolvido como parte do curso de Android da Alura, que simula uma loja virtual de produtos, salvando informações como nome, descrição e preço.

Funcionalidades
---------------

*   **Tela Inicial**: A primeira tela do aplicativo exibirá um cabeçalho roxo com o nome "Orgs" no canto esquerdo e um botão com um "+" no canto inferior direito. Nessa tela, será exibida uma lista de produtos, inicialmente vazia.
    
*   **Adição de Produtos**: Ao clicar no botão de adição ("+"), seremos levados a um formulário onde poderemos adicionar novos produtos. O formulário incluirá campos para nome, descrição e preço do produto, bem como um botão "Salvar" para confirmar a adição.
    
*   **Listagem de Produtos**: Após adicionar um produto, ele será exibido na lista da tela inicial. Cada produto terá seu nome, descrição e preço listados.

Detalhes Técnicos
-----------------------

*   **Componentes Visuais**: Implementação de uma RecyclerView para exibir a lista de produtos na tela inicial do aplicativo. A RecyclerView permite exibir grandes conjuntos de dados de forma eficiente, fornecendo uma experiência de rolagem suave aos usuários.
    
*   **Adapter**: Criação de um Adapter personalizado para conectar a RecyclerView aos dados do aplicativo. O Adapter é responsável por inflar o layout de cada item da lista e vincular os dados aos elementos da interface do usuário.
    
*   **Interação com o Usuário**: Implementação de interações com o usuário, como cliques em botões e preenchimento de formulários. Além disso, foi usado um componente que lida com diferentes tipos de teclado, como teclados de texto e numéricos, para fornecer uma experiência de usuário intuitiva.
