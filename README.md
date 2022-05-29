
## Requests: 
  - `GET /pokedex?sort=`
    - Retorna uma lista com todos os pokemons
    - O parâmetro `sort`pode assumir 3 valores:
      - `alf` que ordena a lista de pokemons na ordem alfabetica;
      - `comp`que ordena a lista de pokemons pelo comprimento do nome em ordem crescente;
      - e caso não seja especificado valor a ordem da lista permanece a padrão da api, nesse caso basta so escrever
        `GET /pokedex`;
    - Exemplo: `GET /pokedex?sort=comp` ordenando pelo comprimento do nome
    - Resposta esperada ```{"results":[{"name":"muk","highlight":"<pre>muk</pre>"},{"name":"mew","highlight":"<pre>mew</pre>"},       {"name":"abra","highlight":"<pre>abra</pre>"}, . . . .]}```
    - ### Demonstração usando o postman:
    ## GIF EXEMPLO
    
  - `GET /pokemons?name=&sort=`
    - Retorna uma lista com os pokemons relacionados com o nome buscado
    - Os parâmetros `name` e `sort` sao respectivamente os valores do nome do pokemon buscado e o tipo de ordenação
    - O parâmetro `sort`pode assumir 3 valores:
      - `alf` que ordena a lista de pokemons na ordem alfabetica;
      - `comp`que ordena a lista de pokemons pelo comprimento do nome em ordem crescente;
      - e caso não seja especificado valor a ordem da lista permanece a padrao da api, nesse caso basta so escrever
        `GET /pokemons?name=` ;
    - Exemplo: `GET /pokemons?name=pidge&sort=alf`
    - Resposta esperada `{"results":[{"name":"pidgeot","highlight":"<pre>pidge</pre>ot"},{"name":"pidgeot-mega","highlight":"         <pre>pidge</pre>ot-mega"},{"name":"pidgeotto","highlight":"<pre>pidge</pre>otto"},{"name":"pidgey","highlight":"                 <pre>pidge</pre>y"}]}`
    - ### Demonstração usando o postman:
    
    ![pokeGet](https://user-images.githubusercontent.com/81039247/170880512-6d2e116a-8605-4e41-b704-15ddfb9a55b8.gif)
