# game-store

No projeto, foi adicionada conexão com o banco de dados mySQL e usado o Postman para teste de requisições.
endpoints usados: 

Para adicionar produtos no carrinho: /order, verbo http POST, com parâmetros orderCode(número da ordem de pedido), productCode(código do produto) e quantity;

Para excluir items do carrinho: /order/{orderCode} (o código da ordem), sendo passado como uma varíavel no endpoint, e nos parâmetros, productCode e quantity.

Para ordenar os produtos do carrinho: /order, e nos parâmetros, orderCode, e sort, com o meio de ordenação preferido (name, score ou price).

Para fazer o checkout: /order/{orderCode}, (o código da ordem), sendo passado como uma varíavel no endpoint.



O projeto encontra-se também documentado no Swagger: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

