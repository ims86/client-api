# _client-api_
**API completa para cadastro de clientes**

Tecnologias utilizadas
Java 21 + Spring Boot 3.3.3 <br>
Gerenciador de dependencias Maven <br>
H2 como banco para testes <br>
OpenAPI para documentação <br>

**Swagger** <br>
URL Base: localhost:8080/swagger-ui/index.html#/

**Roteiro de testes** <br>
**_<p>Buscar todos os clientes_** <br>
_Metodo GET_ <br>
URL Base: localhost:8080/api/v1/clients <br></p>

**_<p>Buscar cliente pos ID_** <br>
_Metodo GET_ <br>
URL Base: localhost:8080/api/v1/clients/{id} <br></p>

**_<p>Inserir novo cliente_** <br>
_Metodo POST_ <br>
URL Base: localhost:8080/api/v1/clients <br>
Exemplo JSON <br>
{ <br>
    "name":"ana", <br>
    "cpf":"20196914000", <br>
    "birthdate":"24/08/2024", <br>
    "email":"a@a.com" <br>
}</p>

**_<p>Atualizar um cliente_** <br>
_Metodo PUT_ <br>
URL Base: localhost:8080/api/v1/clients/{id} <br>
Exemplo JSON <br>
{ <br>
    "name":"aba", <br>
    "birthdate":"01/01/1980", <br>
    "email":"teste@teste.com" <br>
}</p>

**_<p>Deletar um cliente_** <br>
_Metodo PUT_ <br>
URL Base: localhost:8080/api/v1/clients/{id}</p> <br> <br>
