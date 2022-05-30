
## Diagrama do projeto
![Endpoint que retorna todos os pokémons disponibilizados pela PokéApi ou os pokemosn buscados (1)](https://user-images.githubusercontent.com/81039247/170919147-590643a7-bd6d-4809-919d-c99bf7281740.png)
 
  - ### Request:`GET /pokemons?name=[nome do pokemon]&sort=[tipo de ordenação]`
    - Retorna uma lista de pokémons com o mesmo nome busacdo ou que iniciem com ele.
    - Os parâmetros `name` e `sort` são respectivamente os valores do nome do pokémon buscado e o tipo de ordenação
    - O parâmetro `sort`pode assumir 2 valores:
      - `alf` que ordena a lista de pokémons na ordem alfabética;
      - `comp`que ordena a lista de pokémons pelo comprimento do nome em ordem crescente;
    - Obs.: Caso não seja especificado valor a ordem da lista permanece a padrão da PokéAPI, nesse caso basta só escrever GET /pokemons ;
    - Exemplo: `GET /pokemons?name=pidge&sort=alf`
    - Resposta esperada `{"results":[{"name":"pidgeot","highlight":"<pre>pidge</pre>ot"},{"name":"pidgeot-mega","highlight":"         <pre>pidge</pre>ot-mega"},{"name":"pidgeotto","highlight":"<pre>pidge</pre>otto"},{"name":"pidgey","highlight":"                 <pre>pidge</pre>y"}]}`

## Demonstração usando o Postman:
    
    ![pokeGet](https://user-images.githubusercontent.com/81039247/170880512-6d2e116a-8605-4e41-b704-15ddfb9a55b8.gif)
    
## Docker
      
```bash
# Fazer build
./gradlew build
      
# Gerar imagem
docker build -t pokemon-api .

# Rodar container
docker run -p 8080:8080 pokemon-api   
```
## Executar o projeto sem o Docker
```bash 
./gradlew bootRun
```
