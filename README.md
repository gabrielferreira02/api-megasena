# Megasena API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

# Sobre

Projeto de API utilizando Java + Spring Boot simulando o funcionamento do jogo da megasena. Existem apenas dois endpoints, o de registrar um jogo e um de gerar o sorteio com o resultado. Como é um projeto simples optei por usar o banco de dados em memória H2. No projeto já vem com alguns dados de inicialização, porém como a probabilidade de ganhar a megasena é baixa para obter alguma requisição com resultado diferente de vazio terá que ter persistência. Nos testes eu crio ambiente simulado onde é mais fácil validar o resultado gerado. No projeto utilizo Global Handler exception para personalizar a resposta das exceções, logs para auxiliar na observabilidade e docker para facilitar na utilização da api.

# Como usar?

1 - Primeiro clone o repositório

```
git clone https://github.com/gabrielferreira02/api-megasena
cd api-megasena
```

2 - Caso queira testar a aplicação

```
mvn test
```

3 - Instale as dependências

```
mvn clean install
```

4 - Inicilize a api

```
docker build -t megasena .
docker run -d -p 8080:8080 --name megasena megasena
```

5 - Acesse a documentação em

```
http://localhost:8080/swagger-ui.html
```
