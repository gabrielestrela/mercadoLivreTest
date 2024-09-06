# Teste Mercado Livre

Olá pessoal!

Este repo consiste em um app simples que consome uma API do mercado livre de busca, com intuito de abordar temas como arquitetura e boas práticas.

## Detalhes

- A API:
```

  curl -X GET -H 'Authorization: Bearer $ACCESS_TOKEN' https://api.mercadolibre.com/sites/MLA/search?q=Motorola%20G6

```

```json
  {
   "site_id": "MLA",
   "query": "Motorola G6",
   "paging": {
       "total": 916,
       "offset": 0,
       "limit": 50,
       "primary_results": 916
   },
   "results": [
       {
           "id": "MLA810645375",
           "site_id": "MLA",
           "title": "Motorola G6 Plus 64 Gb Nimbus",
           "price": 17999,
           "currency_id": "ARS",
           "available_quantity": 100,
           "buying_mode": "buy_it_now",
           "listing_type_id": "gold_special",
           "stop_time": "2039-08-17T04:00:00.000Z",
           "condition": "new",
           "permalink": "https://www.mercadolibre.com.ar/p/MLA9452524",
           "thumbnail": "http://mla-s2-p.mlstatic.com/795558-MLA31003306206_062019-I.jpg",
           "accepts_mercadopago": true,
           "installments": {
               "quantity": 12,
               "amount": 2456.41,
               "rate": 63.77,
               "currency_id": "ARS"
           },
           "shipping": {
               "free_shipping": false,
               "mode": "custom",
               "tags": [],
               "logistic_type": "custom",
               "store_pick_up": true
           },
           "attributes": [
               {
                   "name": "Marca",
                   "value_id": "2503",
                   "value_name": "Motorola",
                   "value_struct": null,
                   "attribute_group_id": "OTHERS",
                   "attribute_group_name": "Otros",
                   "source": 1,
                   "id": "BRAND"
               },
               {
                   "attribute_group_name": "Otros",
                   "source": 1,
                   "id": "CPU_MODEL",
                   "name": "Modelo de CPU",
                   "value_id": "7070889",
                   "value_name": "4x2.2 GHz Cortex-A53/4x1.8 GHz Cortex-A53",
                   "value_struct": null,
                   "attribute_group_id": "OTHERS"
               },
           ],
           "original_price": null,
           "category_id": "MLA1055",
           "official_store_id": 229,
           "catalog_product_id": "MLA9452524",
           "catalog_listing": true
       },
   ]
}



```

  <strong> Id </strong>: Id do produto
  
  <strong> Title </strong>: Nome do produto
  
  <strong> Price </strong>: Preço do produto
  
---

## Detalhes sobre o projeto

 - Desenvolvido em Kotlin
 - Segue arquitetura CLEAN e MVVM
 - Utiliza StateFlow para estado
 - Testes unitários (Data, Domain e Presentation)

---

## Telas
|Inicial|Resultado Busca|Detalhes|
|---|---|---|
|<img width="363" alt="image" src="https://github.com/user-attachments/assets/db9fac6f-d7f3-4c9f-a360-54c55e934566">|<img width="361" alt="image" src="https://github.com/user-attachments/assets/18949389-4676-4659-be9e-f4652f39f9e3">|<img width="373" alt="image" src="https://github.com/user-attachments/assets/b2feac40-e8be-4286-af79-e0221620546c">| 

<strong>Tela Inicial</strong>

<br/>

Ao entrar no app será apresentado o input para busca de produtos.
Ao pesquisar, um loading será exibido para o usuário.

<br/>

<strong>Resultado da Busca</strong>

<br/>

Quando a busca termina, uma lista com os resultados é apresentada.

<br/>

<strong>Tela de detalhes</strong>

<br/>

Ao clicar em algum item da lista, o usuário é levado para a tela de detalhes.

---

## TODO

- Cenário de erro para requests com ktor
- Melhor Ux
- Json nao completamente parseado
- Testes unitários
- Design System
- Much More ...
