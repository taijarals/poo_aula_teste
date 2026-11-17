import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CadastroEleitoral cadastro = new CadastroEleitoral();
        String opcao;

        do {
            exibirMenu();
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrarPartido(scanner, cadastro);
                case "2" -> listarPartidos(cadastro);
                case "3" -> cadastrarCandidato(scanner, cadastro);
                case "4" -> listarCandidatos(cadastro);
                case "5" -> trocarPartido(scanner, cadastro);
                case "0" -> System.out.println("Programa encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nCADASTRO ELEITORAL");
        System.out.println("1 - Cadastrar partido");
        System.out.println("2 - Listar partidos");
        System.out.println("3 - Cadastrar candidato");
        System.out.println("4 - Listar candidatos");
        System.out.println("5 - Trocar partido de um candidato");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void cadastrarPartido(
        Scanner scanner,
        CadastroEleitoral cadastro
    ) {
        System.out.print("Nome do partido: ");
        String nome = scanner.nextLine();
        System.out.print("Sigla: ");
        String sigla = scanner.nextLine();

        try {
            cadastro.cadastrarPartido(nome, sigla);
            System.out.println("Partido cadastrado.");
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Não foi possível cadastrar: " + e.getMessage()
            );
        }
    }

    private static void listarPartidos(CadastroEleitoral cadastro) {
        List<Partido> partidos = cadastro.listarPartidos();

        if (partidos.isEmpty()) {
            System.out.println("Nenhum partido cadastrado.");
            return;
        }

        for (int i = 0; i < partidos.size(); i++) {
            System.out.print((i + 1) + " - ");
            partidos.get(i).exibirDados();
        }
    }

    private static void cadastrarCandidato(
        Scanner scanner,
        CadastroEleitoral cadastro
    ) {
        List<Partido> partidos = cadastro.listarPartidos();

        if (partidos.isEmpty()) {
            System.out.println(
                "Cadastre um partido antes de cadastrar candidatos."
            );
            return;
        }

        System.out.println("Tipo do candidato:");
        System.out.println("1 - Prefeito(a)");
        System.out.println("2 - Vereador(a)");
        System.out.print("Escolha: ");
        String tipo = scanner.nextLine();

        if (!tipo.equals("1") && !tipo.equals("2")) {
            System.out.println("Tipo de candidato inválido.");
            return;
        }

        System.out.print("Nome do candidato: ");
        String nome = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();

        Partido partido = escolherPartido(scanner, cadastro);
        if (partido == null) {
            return;
        }

        try {
            Candidato candidato;

            if (tipo.equals("1")) {
                System.out.print("Nome do vice: ");
                String nomeVice = scanner.nextLine();

                candidato = new CandidatoPrefeito(
                    nome,
                    numero,
                    partido,
                    nomeVice
                );
            } else {
                System.out.print("Bairro prioritário: ");
                String bairro = scanner.nextLine();

                candidato = new CandidatoVereador(
                    nome,
                    numero,
                    partido,
                    bairro
                );
            }

            cadastro.cadastrarCandidato(candidato);
            System.out.println("Candidato cadastrado.");
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Não foi possível cadastrar: " + e.getMessage()
            );
        }
    }

    private static void listarCandidatos(CadastroEleitoral cadastro) {
        List<Candidato> candidatos = cadastro.listarCandidatos();

        if (candidatos.isEmpty()) {
            System.out.println("Nenhum candidato cadastrado.");
            return;
        }

        for (Candidato candidato : candidatos) {
            candidato.exibirDados();
        }
    }

    private static void trocarPartido(
        Scanner scanner,
        CadastroEleitoral cadastro
    ) {
        List<Candidato> candidatos = cadastro.listarCandidatos();

        if (candidatos.isEmpty()) {
            System.out.println("Nenhum candidato cadastrado.");
            return;
        }

        System.out.println("Escolha o candidato:");
        for (int i = 0; i < candidatos.size(); i++) {
            Candidato candidato = candidatos.get(i);
            System.out.println(
                (i + 1) + " - " + candidato.getNome()
                + " / " + candidato.getPartido().getSigla()
            );
        }

        Integer indice = lerIndice(
            scanner,
            "Candidato: ",
            candidatos.size()
        );
        if (indice == null) {
            return;
        }

        Candidato candidato = cadastro.buscarCandidatoPorIndice(indice);
        Partido novoPartido = escolherPartido(scanner, cadastro);
        if (novoPartido == null) {
            return;
        }

        try {
            candidato.trocarPartido(novoPartido);
            System.out.println("Partido alterado.");
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Não foi possível alterar: " + e.getMessage()
            );
        }
    }

    private static Partido escolherPartido(
        Scanner scanner,
        CadastroEleitoral cadastro
    ) {
        List<Partido> partidos = cadastro.listarPartidos();

        System.out.println("Escolha o partido:");
        for (int i = 0; i < partidos.size(); i++) {
            System.out.print((i + 1) + " - ");
            partidos.get(i).exibirDados();
        }

        Integer indice = lerIndice(
            scanner,
            "Partido: ",
            partidos.size()
        );

        if (indice == null) {
            return null;
        }

        return cadastro.buscarPartidoPorIndice(indice);
    }

    private static Integer lerIndice(
        Scanner scanner,
        String mensagem,
        int quantidade
    ) {
        System.out.print(mensagem);

        try {
            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha < 1 || escolha > quantidade) {
                System.out.println("Opção inexistente.");
                return null;
            }

            return escolha - 1;
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return null;
        }
    }
}
