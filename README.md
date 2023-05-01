

`````mermaid
classDiagram
    class Categoria {
        +idCategoria : int
        +nomeCategoria : varchar
    }

    class Produto {
        +idProduto : int
        +idCategoria : int
        +nomeProduto : varchar
        +descricaoProduto : text
        +precoProduto : decimal
        +quantidadeProduto : int
    }

    class Imagem {
        +idImagem : int
        +idProduto : int
        +caminhoImagem : varchar
    }

    class Pedido {
        +idPedido : int
        +dataPedido : datetime
        +idUsuario : int
        +valorTotal : decimal
        +status : varchar
    }

    class ItemPedido {
        +idItemPedido : int
        +idPedido : int
        +idProduto : int
        +quantidade : int
        +precoUnitario : decimal
    }

    class Pagamento {
        +idPagamento : int
        +idPedido : int
        +dataPagamento : datetime
        +valorPagamento : decimal
        +tipoPagamento : varchar
        +status : varchar
    }

    class Usuario {
        +idUsuario : int
        +nomeUsuario : varchar
        +email : varchar
        +senha : varchar
        +endereco : varchar
        +telefone : varchar
        +tipoUsuario : varchar
    }

    class Carrinho {
        +idCarrinho : int
        +idUsuario : int
    }

    class ItemCarrinho {
        +idItemCarrinho : int
        +idCarrinho : int
        +idProduto : int
        +quantidade : int
        +precoUnitario : decimal
    }

    Produto "1" --> "1" Categoria
    Produto "1" --> "n" Imagem
    Pedido "n" --> "1" Usuario
    ItemPedido "n" --> "1" Pedido
    ItemPedido "1" --> "1" Produto
    Pagamento "1" --> "1" Pedido
    Carrinho "n" --> "1" Usuario
    ItemCarrinho "n" --> "1" Carrinho
    ItemCarrinho "1" --> "1" Produto

`````