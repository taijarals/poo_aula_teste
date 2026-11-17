# 2. A classe `Partido`

A classe `Partido` é o exemplo mais direto do projeto para compreender classe, objeto, atributos, métodos, construtor, `this` e encapsulamento.

## Classe e objeto

Uma classe funciona como uma definição. Ela descreve quais dados e comportamentos os objetos daquele tipo terão.

```java
public class Partido {
    // definição da classe
}
```

Um objeto é uma ocorrência concreta criada a partir dessa classe:

```java
Partido partido = new Partido("Partido Horizonte", "PH");
```

| Trecho | Significado |
|---|---|
| `Partido` | Tipo da variável |
| `partido` | Variável que guarda a referência |
| `new` | Solicita a criação do objeto |
| `Partido(...)` | Chamada do construtor |
| `"Partido Horizonte", "PH"` | Argumentos enviados ao construtor |

## Atributos

```java
private final String nome;
private final String sigla;
```

Os atributos representam o estado de cada objeto. Dois objetos da classe `Partido` podem possuir valores diferentes para `nome` e `sigla`.

```java
Partido primeiro = new Partido("Partido Horizonte", "PH");
Partido segundo = new Partido("Movimento Cidadão", "MC");
```

`String` indica o tipo do dado. `private` protege o acesso direto. `final` impede que a referência do atributo seja substituída depois de inicializada.

## Construtor

```java
public Partido(String nome, String sigla) {
    if (nome == null || nome.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "O nome do partido é obrigatório."
        );
    }

    if (sigla == null || sigla.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "A sigla do partido é obrigatória."
        );
    }

    this.nome = nome.trim();
    this.sigla = sigla.trim().toUpperCase();
}
```

O construtor possui o mesmo nome da classe e não declara tipo de retorno. Ele é executado durante o `new` e tem duas responsabilidades neste projeto:

1. verificar se os dados são válidos;
2. inicializar os atributos do novo objeto.

Se um valor obrigatório não for informado, o construtor lança uma `IllegalArgumentException`. Assim, um objeto `Partido` não deve ser criado em estado inválido.

## O uso de `this`

Na linha:

```java
this.nome = nome.trim();
```

- `this.nome` é o atributo pertencente ao objeto atual;
- `nome` é o parâmetro recebido pelo construtor.

O mesmo nome pode ser usado porque `this` permite distinguir o atributo do parâmetro.

## Encapsulamento e getters

Como os atributos são privados, outras classes não podem fazer isto:

```java
partido.nome = "Outro nome"; // não compila
```

Para consultar os valores, são oferecidos métodos públicos:

```java
public String getNome() {
    return nome;
}

public String getSigla() {
    return sigla;
}
```

Encapsular não significa apenas tornar tudo privado. Significa controlar por quais operações o estado interno poderá ser consultado ou alterado.

## Métodos de exibição

```java
public void exibirDados() {
    System.out.println(toString());
}
```

O método `exibirDados()` não retorna um valor porque possui `void`. Ele solicita a impressão do texto produzido por `toString()`.

```java
@Override
public String toString() {
    return sigla + " - " + nome;
}
```

`toString()` existe originalmente na classe `Object`. O projeto sobrescreve esse método para fornecer uma representação mais útil, como:

```text
PH - Partido Horizonte
```

## Regras preservadas pela classe

- nome e sigla são obrigatórios;
- espaços no início e no fim são removidos com `trim()`;
- a sigla é convertida para maiúsculas;
- nome e sigla não podem ser alterados depois da construção.

## Verificação de aprendizagem

1. Qual é a diferença entre a classe `Partido` e a variável `partido`?
2. Em que momento o construtor é executado?
3. Qual é a função de `this`?
4. Por que os atributos são privados?
5. Qual é a diferença entre `getSigla()` e `exibirDados()`?
6. O que ocorrerá com `new Partido("", "PH")`?

