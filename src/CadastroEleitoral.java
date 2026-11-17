import java.util.ArrayList;
import java.util.List;

public class CadastroEleitoral {
    private final List<Partido> partidos;
    private final List<Candidato> candidatos;

    public CadastroEleitoral() {
        this.partidos = new ArrayList<>();
        this.candidatos = new ArrayList<>();
    }

    public void cadastrarPartido(String nome, String sigla) {
        Partido novoPartido = new Partido(nome, sigla);

        for (Partido partido : partidos) {
            if (partido.getSigla().equals(novoPartido.getSigla())) {
                throw new IllegalArgumentException(
                    "Já existe um partido com essa sigla."
                );
            }
        }

        partidos.add(novoPartido);
    }

    public List<Partido> listarPartidos() {
        return List.copyOf(partidos);
    }

    public Partido buscarPartidoPorIndice(int indice) {
        return partidos.get(indice);
    }

    public void cadastrarCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException(
                "O candidato é obrigatório."
            );
        }

        for (Candidato cadastrado : candidatos) {
            if (cadastrado.getNumero().equals(candidato.getNumero())) {
                throw new IllegalArgumentException(
                    "Já existe um candidato com esse número."
                );
            }
        }

        candidatos.add(candidato);
    }

    public List<Candidato> listarCandidatos() {
        return List.copyOf(candidatos);
    }

    public Candidato buscarCandidatoPorIndice(int indice) {
        return candidatos.get(indice);
    }
}
