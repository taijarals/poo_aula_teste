public class CandidatoPrefeito extends Candidato {
    private final String nomeVice;

    public CandidatoPrefeito(
        String nome,
        String numero,
        Partido partido,
        String nomeVice
    ) {
        super(nome, numero, partido);

        if (!numero.trim().matches("\\d{2}")) {
            throw new IllegalArgumentException(
                "O número de prefeito deve possuir exatamente 2 dígitos."
            );
        }
        if (nomeVice == null || nomeVice.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O nome do vice é obrigatório."
            );
        }

        this.nomeVice = nomeVice.trim();
    }

    public String getNomeVice() {
        return nomeVice;
    }

    @Override
    public String getCargo() {
        return "Prefeito(a)";
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Vice: " + nomeVice);
        System.out.println("-------------------------");
    }
}
