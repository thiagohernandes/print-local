# API Print Simplus Challenge

API para busca de estados e cidades do Brasil

## Instruções
- utiliza o serviço público do IBGE - Brasil
- a aplicação utiliza como proteção a biblioteca JWT Token - in memorian
- foi utilizada o pattern "Clean Architecture" para facilitar o desenvolvimento e a testabilidade da API
- para testes, foi utilizado o JUnit, juntamente do Mockito
- no que tange ao Circuit Bracker Pattern, foi implementado o "Fallback" no "Feign Client"
- todo o código foi feito nos moldes recomendados internacionalmente com o idioma inglês
- a nível de cache, foi utilizada a lib "EhCache", que possibilita configurações simples e objetivas. Nos níveis: heap, offheap e disk
- os logs são emitidos em arquivos por conta do "logback.xml"

## Instalação

```bash
git clone git@github.com:thiagohernandes/print-local.git
cd print-local/api-print-local
mvn clean test package
mvn spring-boot:run
```
## Handler Genérico de Erros
-> Para evitar tratativas individuais por serviços, foi construído um handler genérico de tratativas de exceções/erros de chamadas HTTP

-> contrato do objeto a ser retornado:
```bash
{
    "errorMessage": "URL não encontrada para a requisição",
    "dateTime": "09/08/2020 15:10:57",
    "calledUrl": "/v1/places/states/MG/citiesf",
    "statusCode": 404,
    "method": "GET"
}
```

## Token

```bash
http://localhost:8080/login - POST
-> enviar no body:
{
    "username": "admin",
    "password": "123"
}
-> pegar o Token retornado no Headers/Authorization para poder 
realizar as chamadas dos enpoints

```

## Enpoints

```bash
http://localhost:8080/v1/places/states - retonar os estados
em formato JSON

http://localhost:8080/v1/places/states/{uf}/cities - retorna as
cidades por unidade federativa em formato JSON

http://localhost:8080/v1/places/states/SP/cities/pdf - retorna as
cidades por unidade federativa em formato PDF
```

## Swagger

```bash
http://localhost:8080/swagger-ui.html
```


