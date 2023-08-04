import random
from dataclasses import dataclass, asdict

from faker import Faker
from faker.providers import BaseProvider
from locust import HttpUser, task, constant

programming_languages = [
    'Python', 'Go', 'Node', 'Java', 'C#',
    'C++', 'Ruby', 'Rust', 'Crystal', 'PHP',
]


class ProgrammingLanguagesProvider(BaseProvider):
    def programming_languages(self) -> list[str] | None:
        if random.choice([True, False]):
            return None
        return random.choices(population=programming_languages, k=5)


fake = Faker('pt_BR')
fake.add_provider(ProgrammingLanguagesProvider)


@dataclass
class Pessoa:
    apelido: str
    nome: str
    nascimento: str
    stack: list[str]


def generate_random_pessoa() -> Pessoa:
    simple_profile = fake.simple_profile()
    return Pessoa(
        apelido=simple_profile['username'],
        nome=simple_profile['name'],
        nascimento=str(simple_profile['birthdate']),
        stack=fake.programming_languages(),
    )


class PessoaLoadTesting(HttpUser):
    host = 'http://localhost:9999'
    wait_time = constant(1)

    @task
    def create_and_get_pessoa(self):
        post_res = self.client.post(
            '/pessoas',
            json=asdict(generate_random_pessoa()),
            name='/create-pessoa'
        )
        if not post_res.ok:
            return
        pessoa_location = post_res.headers.get('location')
        self.client.get(pessoa_location, name='/get-pessoa-by-id')

    @task(3)
    def get_pessoas_by_search_term(self):
        search_term = random.choice(programming_languages)
        self.client.get(f'/pessoas?t={search_term}', name='/get-pessoas-by-search-term')
