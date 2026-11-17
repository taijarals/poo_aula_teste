# 9. Atividades práticas de modificação

As atividades a seguir partem do código funcionando e acrescentam pequenas mudanças. Realize uma atividade por vez, compile o projeto e teste o comportamento antes de avançar.

## Atividade 1 — completar um construtor

Remova temporariamente o corpo do construtor de `Partido` e reescreva-o para:

- validar nome e sigla;
- remover espaços externos;
- transformar a sigla em maiúsculas;
- inicializar os atributos com `this`.

Teste com dados válidos, texto vazio e `null`.

## Atividade 2 — buscar candidato pelo número

Acrescente em `CadastroEleitoral`:

```java
public Candidato buscarCandidatoPorNumero(String numero) {
    // implementar
}
```

O método deve percorrer a lista, comparar os números e retornar o candidato encontrado. Defina também o que acontecerá quando não existir correspondência: retornar `null` ou lançar uma exceção.

## Atividade 3 — novo atributo para prefeito

Adicione a `CandidatoPrefeito` o atributo:

```java
private final String planoDeGoverno;
```

Atualize:

- o construtor;
- a validação;
- o método de consulta;
- `exibirDados()`;
- a criação do objeto em `Main`.

Observe como uma mudança no construtor afeta o ponto de instanciação.

## Atividade 4 — criar `CandidatoGovernador`

Crie uma classe que:

- herde de `Candidato`;
- possua o atributo `estado`;
- receba nome, número, partido e estado no construtor;
- chame `super(...)`;
- aceite exatamente dois dígitos;
- implemente `getCargo()`;
- sobrescreva `exibirDados()`.

Depois, altere `Main` para permitir o cadastro desse tipo.

## Atividade 5 — proteger uma regra

Crie uma regra que impeça siglas com menos de duas ou mais de cinco letras. A regra pertence a `Partido`, pois qualquer objeto dessa classe deve respeitá-la, independentemente de onde tenha sido criado.

Uma expressão possível é:

```java
sigla.trim().matches("[A-Za-z]{2,5}")
```

## Atividade 6 — criar um método de resumo

Acrescente a `CadastroEleitoral`:

```java
public void exibirResumo() {
    // quantidade de partidos e candidatos
}
```

Depois, inclua uma opção no menu para chamar esse método. Analise se imprimir dentro da classe de cadastro é a melhor separação de responsabilidades. Uma alternativa é retornar os números e deixar `Main` imprimir.

## Atividade 7 — identificar e explicar

Localize no projeto um exemplo de cada conceito:

| Conceito | Trecho encontrado | Explicação |
|---|---|---|
| Classe |  |  |
| Objeto |  |  |
| Construtor |  |  |
| Encapsulamento |  |  |
| Associação |  |  |
| Herança |  |  |
| Abstração |  |  |
| Sobrescrita |  |  |
| Polimorfismo |  |  |

Não basta copiar o código: explique por que o trecho representa o conceito.

## Atividade 8 — prever o resultado

Analise sem executar:

```java
Partido partido = new Partido("Movimento Cidadão", "mc");

Candidato candidato = new CandidatoVereador(
    "João Lima",
    "40123",
    partido,
    "Centro"
);

System.out.println(partido.getSigla());
System.out.println(candidato.getCargo());
candidato.exibirDados();
```

Escreva a saída esperada e justifique:

- a transformação da sigla;
- a escolha de `getCargo()`;
- a presença do bairro na exibição.

## Atividade 9 — corrigir um erro conceitual

Considere:

```java
public class Partido extends Candidato {
}
```

Explique por que essa herança não representa corretamente o domínio. Depois, indique a relação correta já utilizada pelo projeto.

Uma boa herança precisa passar no teste “é um”. Partido não é um candidato. Candidato tem um partido, portanto a relação adequada é associação.

## Desafio final

Implemente uma opção que permita buscar um candidato pelo número e exibir seus dados. A solução deve:

1. ler o número em `Main`;
2. solicitar a busca a `CadastroEleitoral`;
3. tratar o caso não encontrado;
4. chamar `exibirDados()` sem verificar manualmente o cargo;
5. funcionar para qualquer subclasse de `Candidato`.

O último requisito utiliza polimorfismo: o código de busca trabalha com o tipo geral, e o objeto encontrado apresenta seu comportamento específico.

