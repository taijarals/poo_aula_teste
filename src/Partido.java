public class Partido {
    private final String nome;
    private final String sigla;

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

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void exibirDados() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return sigla + " - " + nome;
    }
}
