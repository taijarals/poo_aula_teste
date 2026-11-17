public abstract class Candidato {
    private final String nome;
    private final String numero;
    private Partido partido;

    public Candidato(String nome, String numero, Partido partido) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O nome do candidato é obrigatório."
            );
        }
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O número do candidato é obrigatório."
            );
        }
        if (!numero.trim().matches("\\d+")) {
            throw new IllegalArgumentException(
                "O número do candidato deve possuir somente dígitos."
            );
        }
        if (partido == null) {
            throw new IllegalArgumentException(
                "O candidato precisa estar associado a um partido."
            );
        }

        this.nome = nome.trim();
        this.numero = numero.trim();
        this.partido = partido;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public Partido getPartido() {
        return partido;
    }

    public abstract String getCargo();

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

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Número: " + numero);
        System.out.println("Cargo: " + getCargo());
        System.out.println("Partido: " + partido.getSigla());
    }
}
