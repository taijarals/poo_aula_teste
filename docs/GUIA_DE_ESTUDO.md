# Guia de estudo do código

Este guia ajuda a estudar o projeto para uma avaliação baseada em leitura, explicação e escrita de código.

## 1 Classe e objeto

`Partido`, `CandidatoPrefeito` e `CandidatoVereador` são classes. Cada execução de `new` cria um objeto.

```java
Partido partido = new Partido("Partido Horizonte", "PH");
```

- `Partido` é a classe e também o tipo da variável;
- `partido` é uma variável de referência;
- `new Partido(...)` cria o objeto;
- os textos são argumentos enviados ao construtor.

## 2 Construtor

O construtor possui o mesmo nome da classe e não declara tipo de retorno. Ele valida e inicializa o objeto.

```java
public Partido(String nome, String sigla) {
    // validações
    this.nome = nome.trim();
    this.sigla = sigla.trim().toUpperCase();
}
```

`this.nome` representa o atributo do objeto. `nome` representa o parâmetro recebido.

## 3 Encapsulamento

Os atributos são privados. Outras classes consultam os valores por métodos públicos.

```java
private final String nome;

public String getNome() {
    return nome;
}
```

`final` impede que a referência do atributo seja reatribuída depois do construtor. `partido` não é final porque o sistema permite a troca controlada pelo método `trocarPartido`.

## 4 Associação

`Candidato` possui um objeto `Partido`:

```java
private Partido partido;
```

Essa relação significa “Candidato tem um Partido”. Ela não é herança.

## 5 Herança

```java
public class CandidatoPrefeito extends Candidato
```

Essa relação significa “CandidatoPrefeito é um Candidato”. A subclasse recebe os métodos acessíveis da superclasse e pode acrescentar atributos e comportamentos.

O construtor da subclasse chama o construtor da superclasse:

```java
super(nome, numero, partido);
```

Construtores não são herdados. `super(...)` solicita a execução do construtor da classe mãe.

## 6 Abstração

`Candidato` é abstrata porque representa características comuns, mas o sistema não deve criar um candidato sem definir seu tipo.

```java
public abstract class Candidato
```

Por isso, esta instrução não compila:

```java
new Candidato("Ana", "41", partido);
```

O método abstrato obriga cada subclasse a informar o cargo:

```java
public abstract String getCargo();
```

## 7 Sobrescrita

As subclasses fornecem implementações próprias:

```java
@Override
public String getCargo() {
    return "Prefeito(a)";
}
```

`@Override` informa que o método já existe na superclasse e está sendo redefinido.

## 8 Polimorfismo

Uma referência do tipo mais geral pode apontar para objetos de subclasses:

```java
Candidato candidato = new CandidatoPrefeito(
    "Ana",
    "41",
    partido,
    "Bruno"
);
```

Quando `candidato.exibirDados()` é chamado, o Java executa a versão correspondente ao objeto `CandidatoPrefeito`.

## 9 CadastroEleitoral

Essa classe concentra as listas e as operações de cadastro. `Main` controla a interação com o usuário, enquanto `CadastroEleitoral` administra os dados.

`List.copyOf` evita que uma classe externa altere diretamente a lista interna:

```java
public List<Candidato> listarCandidatos() {
    return List.copyOf(candidatos);
}
```

## 10 Questões para revisão

1. Qual é a diferença entre classe e objeto?
2. O que acontece quando `new CandidatoPrefeito(...)` é executado?
3. Para que serve `super(...)`?
4. Quais atributos pertencem à classe `Candidato`?
5. Qual atributo existe apenas em `CandidatoPrefeito`?
6. Qual atributo existe apenas em `CandidatoVereador`?
7. Por que `Candidato` foi declarada abstrata?
8. Qual é a diferença entre `extends Candidato` e `new CandidatoPrefeito(...)`?
9. Onde ocorre associação entre classes?
10. Onde ocorre polimorfismo?
11. Por que a lista usa `List<Candidato>`?
12. O que aconteceria se uma subclasse não implementasse `getCargo()`?
13. Por que os atributos são privados?
14. Por que `partido` não é `final`?
15. Como o código impede números repetidos?

## 11 Exercícios de código

### Exercício A

Reescreva no papel o construtor de `Partido`, incluindo as validações.

### Exercício B

Crie uma classe `CandidatoGovernador` que herde de `Candidato`, use número com dois dígitos, possua o atributo `estado` e sobrescreva `getCargo()` e `exibirDados()`.

### Exercício C

Explique a saída:

```java
Candidato candidato = new CandidatoVereador(
    "Carlos",
    "12345",
    partido,
    "Centro"
);

System.out.println(candidato.getCargo());
candidato.exibirDados();
```
