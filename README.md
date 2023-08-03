# Rinha de Backend 2023

Para saber mais: https://github.com/zanfranceschi/rinha-de-backend-2023-q3

> Atenção! Algumas "técnicas" (gambiarras) foram usadas neste projeto para melhorar o tempo de resposta das requests, favor não repetir em casa. Não apenas "técnicas" mas também configurações. ESTEJE avisado. :)


## Time Dupla de 2

- [@willy-r](https://github.com/willy-r)
- [@allanCordeiro](https://github.com/allanCordeiro)


## Rodando localmente

> Necessário Docker

```bash
docker compose up -d
```

API disponível em: [localhost:9999](http://localhost:9999)


## Testes de carga

> Necessário **Python 10+**

1. Entre no diretório [`api_load_testing`](./api_load_testing/), crie um ambiente virtual e ative-o:
```bash
cd api_load_testing
python -m venv venv
source venv/bin/activate
```

2. Instale as dependências e rode o teste de carga com o [`Locust`](https://locust.io/):
```bash
pip install -r requirements.txt
locust
```

3. Acesse o dashboard em [`localhost:8089`](http://localhost:8089) e defina a quantidade de usuários simultâneos você quer acessando a API (teste com 100 inicialmente rs)
