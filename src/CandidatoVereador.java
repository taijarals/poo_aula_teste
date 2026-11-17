public class CandidatoVereador extends Candidato {
    private final String bairroPrioritario;

    public CandidatoVereador(
        String nome,
        String numero,
        Partido partido,
        String bairroPrioritario
    ) {
        super(nome, numero, partido);

        if (!numero.trim().matches("\\d{5}")) {
            throw new IllegalArgumentException(
                "O número de vereador deve possuir exatamente 5 dígitos."
            );
        }
        if (bairroPrioritario == null
                || bairroPrioritario.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O bairro prioritário é obrigatório."
            );
        }

        this.bairroPrioritario = bairroPrioritario.trim();
    }

    public String getBairroPrioritario() {
        return bairroPrioritario;
    }

    @Override
    public String getCargo() {
        return "Vereador(a)";
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Bairro prioritário: " + bairroPrioritario);
        System.out.println("-------------------------");
    }
}
