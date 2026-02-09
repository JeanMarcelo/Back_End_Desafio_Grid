# Back_End_Desafio_Grid

Os plugins e dependências utilizadas no projeto são respectivamente: 

Spring Boot version 4.0.2
Spring Dependency Management version 1.1.7
kotlin("jvm") version "2.2.21"
kotlin("plugin.spring") version "2.2.21"
kotlin("plugin.jpa") version "2.2.21"

Spring-boot-starter-data-jpa version 4.0.2
Spring-boot-starter-security version 4.0.2
Spring-boot-starter-webmvc version 4.0.2
Kotlin-reflect version 2.2.21
Jackson-module-kotlin 2.18.x
Java-jwt version 4.4.0
Postgresql version 42.7.x

Siga os seguintes passos para rodar o projeto:

Clone o projeto usando o seguinte comando no terminal: 

git clone https://github.com/JeanMarcelo/Back_End_Desafio_Grid.git

Após clonar o projeto e instalar as dependências, vá até o ProjectAplication e rode o projeto. 

Os endpoints com as finalidades do projeto são:

http://localhost:8080/register - Registrar um usuário.
http://localhost:8080/login - Logar um usuário.
http://localhost:8080/posts - Criar uma publicação.
http://localhost:8080/posts/{id} - Deletar um usuário.
http://localhost:8080/posts/{id} - Atualizar um usuário.
http://localhost:8080/posts/{id} - Pegar uma publicação pelo id.
http://localhost:8080/posts?order=oldest - Pegar as publicações da mais velha para a mais recente. 
http://localhost:8080/posts - Pegar todas as publicações (Por padrão da mais recente para a mais velha e de todos os usuários).
http://localhost:8080/posts?scope=me - Pegar as publicações criadas somente pelo usuário logado. 





