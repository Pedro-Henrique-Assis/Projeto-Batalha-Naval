package utils;

import model.core.Coordenada;
import model.embarcacoes.Embarcacao;
import model.enums.Direcao;
import model.enums.Orientacao;
import model.enums.TipoTiro;

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
                System.out.println((i + 1) + " - " + embarcacoesDisponiveis.get(i).getNome());
            }
            System.out.print("> ");

            try {
                int escolha = scanner.nextInt();
                if (escolha > 0 && escolha <= embarcacoesDisponiveis.size()) {
                    return embarcacoesDisponiveis.get(escolha - 1);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer
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
                scanner.next(); // Limpa o buffer
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
}
