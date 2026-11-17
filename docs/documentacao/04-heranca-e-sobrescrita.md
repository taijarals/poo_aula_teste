# 4. `CandidatoPrefeito` e `CandidatoVereador`

As classes `CandidatoPrefeito` e `CandidatoVereador` representam especializações de `Candidato`. A comparação entre elas permite compreender herança, `extends`, `super`, especialização e sobrescrita.

## Herança com `extends`

```java
public class CandidatoPrefeito extends Candidato
```

```java
public class CandidatoVereador extends Candidato
```

`extends Candidato` expressa uma relação “é um”:

- candidato a prefeito é um candidato;
- candidato a vereador é um candidato.

As subclasses recebem os métodos acessíveis da superclasse e acrescentam seus próprios elementos.

## Elementos comuns e específicos

| Elemento | Onde é declarado |
|---|---|
| `nome` | `Candidato` |
| `numero` | `Candidato` |
| `partido` | `Candidato` |
| `trocarPartido()` | `Candidato` |
| `nomeVice` | somente em `CandidatoPrefeito` |
| `bairroPrioritario` | somente em `CandidatoVereador` |

As subclasses não precisam repetir os atributos comuns. Essa reutilização é uma das finalidades da herança.

## Construtor e `super`

O construtor de `CandidatoPrefeito` começa com:

```java
public CandidatoPrefeito(
    String nome,
    String numero,
    Partido partido,
    String nomeVice
) {
    super(nome, numero, partido);
    // restante do construtor
}
```

`super(...)` chama o construtor da superclasse `Candidato`. Ele inicializa `nome`, `numero` e `partido` e executa as validações comuns.

Depois, a subclasse valida e inicializa seu atributo próprio:

```java
this.nomeVice = nomeVice.trim();
```

O construtor de `CandidatoVereador` segue a mesma estrutura:

```java
super(nome, numero, partido);
this.bairroPrioritario = bairroPrioritario.trim();
```

Construtores não são herdados. Por isso, cada subclasse declara seu construtor e chama explicitamente o construtor adequado da classe mãe.

## Validações especializadas

Além das regras gerais de `Candidato`, cada subclasse aplica suas regras.

```java
// Prefeito: exatamente dois dígitos
if (!numero.trim().matches("\\d{2}")) {
    throw new IllegalArgumentException(...);
}
```

```java
// Vereador: exatamente cinco dígitos
if (!numero.trim().matches("\\d{5}")) {
    throw new IllegalArgumentException(...);
}
```

A superclasse verifica se o número contém somente dígitos. As subclasses verificam a quantidade apropriada para cada cargo.

## Sobrescrita de `getCargo()`

As duas subclasses implementam o método abstrato herdado:

```java
@Override
public String getCargo() {
    return "Prefeito(a)";
}
```

```java
@Override
public String getCargo() {
    return "Vereador(a)";
}
```

Sobrescrever significa fornecer uma nova implementação para um método previsto na superclasse, mantendo a assinatura compatível.

`@Override` solicita que o compilador verifique se existe realmente um método correspondente na classe mãe. Uma assinatura incorreta produz erro de compilação.

## Sobrescrita de `exibirDados()`

Em `CandidatoPrefeito`:

```java
@Override
public void exibirDados() {
    super.exibirDados();
    System.out.println("Vice: " + nomeVice);
    System.out.println("-------------------------");
}
```

`super.exibirDados()` executa a versão da classe mãe para imprimir os dados comuns. Em seguida, a subclasse acrescenta o nome do vice.

`CandidatoVereador` utiliza o mesmo padrão, acrescentando o bairro prioritário.

## `this` e `super`

| Palavra | Referência |
|---|---|
| `this` | objeto atual |
| `super` | parte do objeto definida pela superclasse |
| `this.nomeVice` | atributo da classe atual |
| `super.exibirDados()` | método implementado na superclasse |

## Herança não cria um objeto

```java
public class CandidatoPrefeito extends Candidato
```

Essa declaração define a relação entre classes. Nenhum objeto é criado por ela.

```java
CandidatoPrefeito candidato = new CandidatoPrefeito(...);
```

Aqui, `new` cria um objeto. Portanto, `extends` define herança e `new` realiza instanciação.

## Verificação de aprendizagem

1. Quais atributos são herdados de `Candidato`?
2. Qual é o papel de `super(...)` no construtor?
3. Construtores são herdados?
4. Por que as subclasses sobrescrevem `getCargo()`?
5. O que aconteceria se `super.exibirDados()` fosse removido?
6. Qual é a diferença entre `extends` e `new`?

