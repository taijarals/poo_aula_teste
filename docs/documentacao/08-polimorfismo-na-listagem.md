# 8. Polimorfismo durante a listagem

Polimorfismo permite tratar objetos de classes diferentes por meio de um tipo comum, preservando o comportamento específico de cada objeto.

## Uma lista do tipo geral

`CadastroEleitoral` declara:

```java
private final List<Candidato> candidatos;
```

Como `CandidatoPrefeito` e `CandidatoVereador` herdam de `Candidato`, objetos dessas duas classes podem ser armazenados na mesma lista.

```java
Candidato prefeito = new CandidatoPrefeito(...);
Candidato vereador = new CandidatoVereador(...);

cadastro.cadastrarCandidato(prefeito);
cadastro.cadastrarCandidato(vereador);
```

Não é necessário criar uma lista para cada cargo.

## Listagem polimórfica

Em `Main`, a listagem utiliza:

```java
for (Candidato candidato : candidatos) {
    candidato.exibirDados();
}
```

A variável do laço possui o tipo `Candidato`. Entretanto, em cada repetição ela pode apontar para um objeto concreto diferente.

O Java verifica a classe real do objeto durante a execução:

- para `CandidatoPrefeito`, executa `CandidatoPrefeito.exibirDados()`;
- para `CandidatoVereador`, executa `CandidatoVereador.exibirDados()`.

Esse processo é chamado de despacho dinâmico de métodos.

## Resultado esperado

Após cadastrar os dois candidatos do exemplo anterior, a opção `4` apresenta aproximadamente:

```text
Nome: Ana Silva
Número: 25
Cargo: Prefeito(a)
Partido: PH
Vice: Carlos Souza
-------------------------
Nome: Bruno Lima
Número: 25123
Cargo: Vereador(a)
Partido: MC
Bairro prioritário: Centro
-------------------------
```

O mesmo comando `candidato.exibirDados()` produziu resultados diferentes.

## Duas camadas de sobrescrita

Na subclasse, `exibirDados()` começa chamando:

```java
super.exibirDados();
```

A versão da superclasse imprime:

- nome;
- número;
- cargo;
- partido.

Dentro dela existe esta chamada:

```java
getCargo()
```

Mesmo executada dentro de `Candidato`, ela utiliza a implementação da classe real: `Prefeito(a)` ou `Vereador(a)`.

Depois, a versão da subclasse imprime o dado específico: vice ou bairro prioritário.

## Tipo da referência e tipo do objeto

```java
Candidato candidato = new CandidatoPrefeito(...);
```

O tipo da referência determina quais métodos podem ser chamados pelo compilador. A classe real do objeto determina qual implementação sobrescrita será executada.

Como `exibirDados()` e `getCargo()` existem em `Candidato`, podem ser chamados pela referência geral.

Já isto não compila:

```java
candidato.getNomeVice();
```

`getNomeVice()` não existe no tipo `Candidato`. Para acessar diretamente um comportamento exclusivo da subclasse, seria necessária uma referência específica ou uma conversão segura.

## Polimorfismo não é sobrecarga

Neste exemplo, o comportamento diferente decorre da sobrescrita em subclasses.

| Conceito | Característica |
|---|---|
| Sobrescrita | mesma assinatura em classes relacionadas por herança |
| Sobrecarga | mesmo nome com listas de parâmetros diferentes |
| Polimorfismo do projeto | referência geral executando a sobrescrita da classe real |

## Experimento

Substitua temporariamente o corpo da listagem por:

```java
for (Candidato candidato : candidatos) {
    System.out.println(candidato.getClass().getSimpleName());
    System.out.println(candidato.getCargo());
}
```

O resultado mostra a classe concreta e o cargo produzido por cada objeto.

## Verificação de aprendizagem

1. Por que a lista aceita objetos das duas subclasses?
2. Qual implementação de `exibirDados()` é executada?
3. O que significa despacho dinâmico?
4. Por que `getNomeVice()` não pode ser chamado diretamente pela referência `Candidato`?
5. Qual é a diferença entre sobrescrita e sobrecarga?

