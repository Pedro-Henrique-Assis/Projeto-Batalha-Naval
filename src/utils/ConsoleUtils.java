package utils;

import model.core.Coordenada;
import model.embarcacoes.Embarcacao;
import model.enums.Direcao;
import model.enums.Orientacao;
import model.enums.TipoTiro;
import java.util.regex.Pattern;

import java.util.*;

public class ConsoleUtils {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma coordenada (linha e coluna) do usuário.
     * @param tamanhoMax O tamanho máximo do tabuleiro para validação.
     * @return A Coordenada lida.
     */
    public static Coordenada lerCoordenada(int tamanhoMax) {
        while (true) {
            try {
                System.out.print("Digite a linha: ");
                int linha = scanner.nextInt();
                System.out.print("Digite a coluna: ");
                int coluna = scanner.nextInt();

                if (linha >= 0 && linha < tamanhoMax && coluna >= 0 && coluna < tamanhoMax) {
                    return new Coordenada(linha, coluna);
                } else {
                    System.out.println("Coordenada fora dos limites do tabuleiro. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite números inteiros.");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }

    /**
     * Lê a orientação (Horizontal ou Vertical) do usuário.
     * @return A Orientacao escolhida.
     */
    public static Orientacao lerOrientacao() {
        while (true) {
            System.out.print("Digite a orientação (H para Horizontal, V para Vertical): ");
            String input = scanner.next().trim().toUpperCase();
            if (input.equals("H")) {
                return Orientacao.HORIZONTAL;
            } else if (input.equals("V")) {
                return Orientacao.VERTICAL;
            } else {
                System.out.println("Orientação inválida. Tente novamente.");
            }
        }
    }

    /**
     * Permite ao usuário escolher uma embarcação de uma lista.
     * @param embarcacoesDisponiveis A lista de embarcações que podem ser escolhidas.
     * @return A embarcação escolhida.
     */
    public static Embarcacao escolherEmbarcacao(List<Embarcacao> embarcacoesDisponiveis) {
        while (true) {
            System.out.println("Escolha uma embarcação para atirar:");
            for (int i = 0; i < embarcacoesDisponiveis.size(); i++) {
                Embarcacao e = embarcacoesDisponiveis.get(i);

                String nome = e.getNome();
                if (e.estaAfundada()) {
                    nome = utils.Ansi.red(nome) + " (AFUNDADA)";
                }

                System.out.println((i + 1) + " - " + nome);
            }
            System.out.print("> ");

            try {
                int escolha = scanner.nextInt();
                if (escolha > 0 && escolha <= embarcacoesDisponiveis.size()) {
                    Embarcacao escolhida = embarcacoesDisponiveis.get(escolha - 1);
                    if (escolhida.estaAfundada()) {
                        System.out.println("Essa embarcação está afundada. Escolha outra.");
                        continue;
                    }
                    return escolhida;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
    }


    /**
     * Permite ao usuário escolher um tipo de tiro de um conjunto de opções.
     * @param tirosDisponiveis O conjunto de tiros que a embarcação pode usar.
     * @return O TipoTiro escolhido.
     */
    public static TipoTiro escolherTipoTiro(Set<TipoTiro> tirosDisponiveis) {
        List<TipoTiro> listaDeTiros = new ArrayList<>(tirosDisponiveis);
        while (true) {
            System.out.println("Escolha o tipo de tiro:");
            for (int i = 0; i < listaDeTiros.size(); i++) {
                System.out.println((i + 1) + " - " + listaDeTiros.get(i));
            }
            System.out.print("> ");

            try {
                int escolha = scanner.nextInt();
                if (escolha > 0 && escolha <= listaDeTiros.size()) {
                    return listaDeTiros.get(escolha - 1);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); //
            }
        }
    }

    /**
     * Permite ao usuário escolher a direção para o segundo tiro do disparo duplo.
     * @return A Direcao escolhida.
     */
    public static Direcao lerDirecaoTiroDuplo() {
        while (true) {
            System.out.println("Escolha a direção do segundo tiro do disparo duplo:");
            System.out.println("1. Cima");
            System.out.println("2. Baixo");
            System.out.println("3. Esquerda");
            System.out.println("4. Direita");
            System.out.print("> ");

            try {
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1: return Direcao.CIMA;
                    case 2: return Direcao.BAIXO;
                    case 3: return Direcao.ESQUERDA;
                    case 4: return Direcao.DIREITA;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer
            }
        }
    }

    public static void mostrarPainelEmbarcacoes(List<Embarcacao> minhas, List<Embarcacao> doAdversario) {
        List<String[]> esquerda = new ArrayList<>();
        esquerda.add(new String[]{Ansi.bold("Minhas Embarcações"), Ansi.bold("Status")});
        for (Embarcacao e : minhas) {
            String nome   = e.estaAfundada() ? Ansi.red(e.getNome())   : Ansi.green(e.getNome());
            String status = e.estaAfundada() ? Ansi.red("AFUNDADA")     : Ansi.green("ATIVA");
            esquerda.add(new String[]{nome, status});
        }

        List<String[]> direita = new ArrayList<>();
        direita.add(new String[]{Ansi.bold("Embarcações do Adversário"), Ansi.bold("Status")});
        for (Embarcacao e : doAdversario) {
            String nome   = e.estaAfundada() ? Ansi.red(e.getNome())    : Ansi.green(e.getNome());
            String status = e.estaAfundada() ? Ansi.red("AFUNDADA")      : Ansi.green("RESTANTE");
            direita.add(new String[]{nome, status});
        }

        int L1 = 22, L2 = 10, R1 = 26, R2 = 10; // larguras
        int linhas = Math.max(esquerda.size(), direita.size());

        System.out.println();
        System.out.println(Ansi.bold("=== Situação das Frotas ==="));

        for (int i = 0; i < linhas; i++) {
            String[] esq = i < esquerda.size() ? esquerda.get(i) : new String[]{"", ""};
            String[] dir = i < direita.size() ? direita.get(i) : new String[]{"", ""};

            String linha =
                    padAnsi(esq[0], L1) + " " + padAnsi(esq[1], L2) + "  |  " +
                            padAnsi(dir[0], R1) + " " + padAnsi(dir[1], R2);

            System.out.println(linha);
        }
        System.out.println();
    }

    // ===== helpers que tratam ANSI =====
    private static final Pattern ANSI_PATTERN = Pattern.compile("\\u001B\\[[;\\d]*m");

    private static String stripAnsi(String s) {
        return ANSI_PATTERN.matcher(s == null ? "" : s).replaceAll("");
    }

    private static String padAnsi(String s, int width) {
        if (s == null) s = "";
        int visible = stripAnsi(s).length();
        StringBuilder out = new StringBuilder(s);
        while (visible < width) { out.append(' '); visible++; }
        return out.toString();
    }



}
