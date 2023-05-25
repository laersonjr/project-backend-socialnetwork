<h1 align="center"> Laerson Júnior Back-End - Social NetWork 🚀 </h1>

<h1 align="center">🎯 Sobre o projeto</h1>
<p align="center"> Este projeto é uma rede social que permite que os usuários cadastrem suas contas, criem posts, comentem em posts, interajam com outras postagens através de "likes" e enviem e aceitem convites de amizade. </p>

<p align="center"> O projeto foi desenvolvido utilizando a linguagem de programação Java e o framework Spring, com o objetivo de fornecer uma solução escalável e fácil de usar para as necessidades de uma rede social. </p>

<h1 align="center">🛠️ Tecnologia utilizadas</h1>

- [Maven](http://maven.apache.org)
- [Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17)
- [Spring Boot 3.0.6](https://spring.io/blog/2022/05/24/preparing-for-spring-boot-3-0)
- [MongoDB](https://www.mongodb.com/)
- [Spring Validation](https://docs.spring.io/spring-framework/docs/4.1.x/spring-framework-reference/html/validation.html)
- [Springdoc OpenAPI](https://springdoc.org/v2/)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [JWT](https://jwt.io/)
- [Lombok](https://projectlombok.org/)
- [ModelMapper](https://modelmapper.org/)

<h1 align="center">💻 Como executar o projeto</h1>
 
 1 - Vá para a pasta que deja realizar o clone do projeto e realize o seguinte comando: git clone https://github.com/bc-fullstack-03/laerson-junior-backend.git
 </br>
 2 - Para executar o projeto usando docker: 
 </br>
 - Abrir o terminal dentro da pasta do projeto.
 - executar o comando: docker compose up -d
 - Para aplicação Java o servidor vai rodar na porta 8082 no host e no container. Para o Mongo vai ser na porta 27070 do host e 27017 do container
 - A partir desse momento já é possícel acessar a aplicação através da url: http://localhost:8082
 - É possível obter informações das urls no swagger (http://localhost:8082/swagger-ui.html) ou importando arquivo disponibilizado na pasta "Postman" no aplicativo Postman. 
 </br>
 3 - Para finalizar a alicação apenas faz necessário utilizar o comando docker compose down
 </br>
 Certifique-se de ter o Docker instalado em sua máquina antes de executar esses comandos. Outro ponto de atenção é não ter outra aplicação rodando nas portas informadas, pois pode gerar erros.
 
 <h1 align="center">📌 Documentação </h1>
 <p align="center"> A documentação foi realizada com Swagger, que oferece uma interface amigável para documentação e testes dos endpoints. Para acessá-la, é necessário que a aplicação esteja em execução.</p></br>
 
 <p align="center">
  <a href="http://localhost:8082/swagger-ui.html">
     Swagger
  </a>
</p>

 <h1 align="center">🧩 Consumindo API </h1>
 <p align="center">Abaixo tem as rotas para serem importas no Postman, caso seja necessário.</p>
 
 <p align="center">
  <a href="https://github.com/bc-fullstack-03/laerson-junior-backend/tree/main/Postman">
     Postman
  </a>
</p>
  
 <h1 align="center">🔨 Funcionalidades do projeto </h1>
 
 - `Login` `Get Token [POST]`: Realiza login no sistema e obtem como resposta um token (/login).
 - `User` `Create User [POST]`: Cadastra um usuário com suas devidas informações no sistema (/api/v1/users/create).
 - `User` `Find User By Id [GET]`: Busca um usuário pelo id (/api/v1/users/{userId}).
 - `User` `Page User [GET]`: Paginação de usuário tendo como parâmetro opcional o nickname, page, size (/api/v1/users).
 - `User` `Update User By Id [GET]`: Atualiza um usuário com as suas devidas informações recebidas no sistema (/api/v1/users/{userId}).
 - `User` `Invite Request Friend [PUT]`: Encaminha uma requisição de amizada para o usuário (/api/v1/users/invite/{meuNickName}).
 - `User` `Accept or Reject Invite Request[PUT]`: Aceita ou recusa o pedido de amizada (/api/v1/users/invite/{meuNickName}/accept).
 - `User` `Delete User By Id [DELETE]`: Remove um usuário pelo id (/api/v1/users/{userId}).
 - `Post` `Create Post [POST]`: Cadastra um post com suas devidas informações no sistema (/api/v1/posts).
 - `Post` `List All Post [GET]`: Lista todos os posts (/api/v1/posts).
 - `Post` `List All Post From My User [GET]`: Lista todos os posts do usuário (/api/v1/posts/myposts).
 - `Post` `List All Post From Nickname [GET]`: Lista todos os posts pesquisando um nickname (/api/v1/posts/{nickname}).
 - `Post` `Get Post By Id [GET]`: Busca um Post pelo id (/api/v1/posts/id/{postId}).
 - `Post` `Update Post By Id [PUT]`: Atualiza um post com as informações recebidas (/api/v1/posts/{postId}).
 - `Post` `LikeOrUnlike Post [PUT]`: Ao acionar uma vez, adiciona um like no post. Se repetir a requisição o like é retirado (/api/v1/posts/{postId}/like).
 - `Post` `Delete Post By Id [DELETE]`: Remove um post pelo id (/api/v1/posts/{postId}).
 - `Comment` `Create Comment In Post [POST]`: Cria um comentário em um post (/api/v1/posts/{postId}/comment).
 - `Comment` `Update Comment In Post By Id [PUT]`: Atualiza comentário em um post (/api/v1/posts/{postId}/comment/{commentId}).
 - `Comment` `LikeOrUnlike Comment [PUT]`: Ao acionar uma vez, adiciona um like no comentário. Se repetir a requisição o like é retirado (/api/v1/posts/{postId}/comment/{commentId}/like).
 - `Comment` `Delete Post By Id [DELETE]`: Remove um comentário pelo id (/api/v1/posts/{postId}/comment/{commentId}).

 <h1 align="center">✒️  Desenvolvedor </h1>
 
<p align="center">
  <a href="https://github.com/laersonjr">
    <img src="https://avatars.githubusercontent.com/u/58311661?v=4" width="115" alt="Laerson Jr">
    <br>
    <sub>Laerson Jr</sub>
  </a>
</p>
