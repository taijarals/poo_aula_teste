# 5. A classe `CadastroEleitoral`

`CadastroEleitoral` administra os objetos criados durante a execução. Nela aparecem coleções, encapsulamento das listas, laços de repetição, validação de duplicidades e separação de responsabilidades.

## Listas de objetos

```java
private final List<Partido> partidos;
private final List<Candidato> candidatos;
```

`List<Partido>` aceita objetos do tipo `Partido`. `List<Candidato>` aceita objetos de `Candidato` e também de suas subclasses.

Os atributos são inicializados no construtor:

```java
public CadastroEleitoral() {
    this.partidos = new ArrayList<>();
    this.candidatos = new ArrayList<>();
}
```

`List` é a interface usada como tipo da variável. `ArrayList` é a implementação concreta criada com `new`.

## O significado de `final` nas listas

```java
private final List<Partido> partidos;
```

`final` impede que o atributo passe a apontar para outra lista. Ele não impede a inclusão de elementos na lista existente.

```java
partidos.add(novoPartido); // permitido
partidos = new ArrayList<>(); // não permitido
```

## Cadastro de partido

```java
public void cadastrarPartido(String nome, String sigla) {
    Partido novoPartido = new Partido(nome, sigla);

    for (Partido partido : partidos) {
        if (partido.getSigla().equals(novoPartido.getSigla())) {
            throw new IllegalArgumentException(
                "Já existe um partido com essa sigla."
            );
        }
    }

    partidos.add(novoPartido);
}
```

O método executa três etapas:

1. cria um objeto `Partido`;
2. percorre os partidos já cadastrados;
3. adiciona o objeto somente se a sigla ainda não existir.

O `for-each` entrega cada elemento da lista à variável `partido`. `equals()` compara o conteúdo das siglas.

## Cadastro de candidato

```java
public void cadastrarCandidato(Candidato candidato) {
    if (candidato == null) {
        throw new IllegalArgumentException(
            "O candidato é obrigatório."
        );
    }

    for (Candidato cadastrado : candidatos) {
        if (cadastrado.getNumero().equals(candidato.getNumero())) {
            throw new IllegalArgumentException(
                "Já existe um candidato com esse número."
            );
        }
    }

    candidatos.add(candidato);
}
```

O parâmetro usa o tipo geral `Candidato`. Assim, o mesmo método recebe tanto `CandidatoPrefeito` quanto `CandidatoVereador`.

## Proteção das listas

```java
public List<Partido> listarPartidos() {
    return List.copyOf(partidos);
}
```

Retornar diretamente `partidos` permitiria que outra classe executasse operações como `clear()` ou `add()` sem passar pelas regras de cadastro.

`List.copyOf(partidos)` fornece uma visão não modificável com os elementos atuais. Com isso, o conteúdo interno continua protegido.

```java
List<Partido> lista = cadastro.listarPartidos();
lista.clear(); // lança UnsupportedOperationException
```

O mesmo cuidado é aplicado a `listarCandidatos()`.

## Busca por índice

```java
public Partido buscarPartidoPorIndice(int indice) {
    return partidos.get(indice);
}
```

As listas começam no índice zero. A primeira posição é `0`, a segunda é `1` e assim por diante. A classe `Main` converte a escolha apresentada ao usuário para o índice correspondente.

## Separação de responsabilidades

`CadastroEleitoral` não lê o teclado nem imprime o menu. Sua responsabilidade é administrar os dados e as regras de cadastro. A interação com o usuário pertence a `Main`.

Essa separação facilita:

- compreender o código;
- reutilizar a lógica em outra interface;
- testar as regras sem depender do terminal;
- localizar onde uma regra deve ser alterada.

## Verificação de aprendizagem

1. Qual é a diferença entre `List` e `ArrayList` neste código?
2. Por que `List<Candidato>` aceita objetos das subclasses?
3. O que `final` impede no atributo `candidatos`?
4. Como o sistema detecta uma sigla repetida?
5. Por que o método não retorna diretamente a lista interna?
6. Qual responsabilidade pertence a `CadastroEleitoral` e não a `Main`?

