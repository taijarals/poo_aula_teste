# 3. A classe abstrata `Candidato`

`Candidato` reúne os dados e os comportamentos compartilhados por candidatos a diferentes cargos. Ela permite estudar associação, abstração, métodos abstratos e a diferença entre as relações “tem um” e “é um”.

## Características comuns

```java
public abstract class Candidato {
    private final String nome;
    private final String numero;
    private Partido partido;
}
```

Todo candidato do projeto possui nome, número e partido. Esses elementos são declarados uma única vez na classe geral, evitando repetição nas classes específicas.

## Associação: candidato tem um partido

```java
private Partido partido;
```

O tipo do atributo é `Partido`, e não `String`. Isso significa que o candidato guarda uma referência para um objeto da classe `Partido`.

```java
Partido partido = new Partido("Partido Horizonte", "PH");

Candidato candidato = new CandidatoPrefeito(
    "Ana Silva",
    "25",
    partido,
    "Carlos Souza"
);
```

O mesmo objeto `partido` é entregue ao construtor do candidato. A relação pode ser lida como:

> Um candidato tem um partido.

Isso é associação, não herança. Para obter a sigla, o candidato chama um método do objeto associado:

```java
partido.getSigla();
```

## Abstração

A palavra `abstract` indica que `Candidato` representa um conceito geral que não deve ser instanciado diretamente.

```java
public abstract class Candidato
```

Este código não compila:

```java
new Candidato("Ana", "25", partido);
```

O sistema precisa saber qual é o cargo. Por isso, objetos concretos são criados a partir de `CandidatoPrefeito` ou `CandidatoVereador`.

Apesar disso, `Candidato` pode ser usado como tipo de uma variável:

```java
Candidato candidato = new CandidatoPrefeito(...);
```

O tipo da referência é geral, enquanto o objeto criado é específico.

## Construtor de uma classe abstrata

Uma classe abstrata pode possuir construtor:

```java
public Candidato(String nome, String numero, Partido partido) {
    // validações
    this.nome = nome.trim();
    this.numero = numero.trim();
    this.partido = partido;
}
```

Ele não é executado por meio de `new Candidato(...)`. As subclasses chamam esse construtor com `super(...)`. Assim, as validações e inicializações comuns ficam centralizadas.

O construtor impede:

- nome vazio;
- número vazio;
- número com caracteres que não sejam dígitos;
- candidato sem partido.

## Método abstrato

```java
public abstract String getCargo();
```

Um método abstrato declara uma operação, mas não fornece seu corpo. Ele termina com `;` e estabelece uma obrigação para as subclasses concretas.

`Candidato` sabe que todo candidato precisa informar um cargo, mas não sabe se o objeto representa prefeito ou vereador. Cada subclasse fornece sua resposta.

```java
// Em CandidatoPrefeito
@Override
public String getCargo() {
    return "Prefeito(a)";
}
```

```java
// Em CandidatoVereador
@Override
public String getCargo() {
    return "Vereador(a)";
}
```

## Método concreto usando um método abstrato

```java
public void exibirDados() {
    System.out.println("Nome: " + nome);
    System.out.println("Número: " + numero);
    System.out.println("Cargo: " + getCargo());
    System.out.println("Partido: " + partido.getSigla());
}
```

`exibirDados()` possui implementação, mas chama `getCargo()`. Durante a execução, o Java escolhe a implementação correspondente à classe real do objeto.

## Alteração controlada do partido

O atributo `partido` não é `final`, pois pode ser alterado. Entretanto, a alteração ocorre por um método que aplica regras:

```java
public void trocarPartido(Partido novoPartido) {
    if (novoPartido == null) {
        throw new IllegalArgumentException(
            "O novo partido é obrigatório."
        );
    }

    if (this.partido == novoPartido) {
        throw new IllegalArgumentException(
            "O candidato já está associado a esse partido."
        );
    }

    this.partido = novoPartido;
}
```

Esse é um exemplo de encapsulamento: o estado pode mudar, mas somente por uma operação controlada.

## Associação e herança

| Relação | Exemplo | Leitura |
|---|---|---|
| Associação | `private Partido partido` | Candidato tem um partido |
| Herança | `CandidatoPrefeito extends Candidato` | Candidato a prefeito é um candidato |

## Verificação de aprendizagem

1. Por que `Candidato` foi declarada abstrata?
2. Uma classe abstrata pode possuir construtor?
3. Por que `getCargo()` não possui corpo?
4. Qual classe deve implementar `getCargo()`?
5. Por que `Partido` dentro de `Candidato` representa associação?
6. Por que `partido` não foi declarado `final`?

