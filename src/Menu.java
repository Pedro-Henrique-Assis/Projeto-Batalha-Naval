import model.enums.Dificuldade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Exibe o menu principal do jogo.
     */
    public void exibir() {
        while (true) {
            System.out.println("\n===== BATALHA NAVAL =====");
            System.out.println("1. Iniciar Novo Jogo");
            System.out.println("2. Tutorial");
            System.out.println("3. Sair");
            System.out.print("> ");

            try {
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        iniciarNovoJogo();
                        break;
                    case 2:
                        exibirTutorial();
                        break;
                    case 3:
                        System.out.println("Obrigado por jogar! Até a proxima.");
                        return; // Sai do método e encerra o loop
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer
            }
        }
    }

    /**
     * Inicializa o jogo permitindo que o usuário escolha a dificuldade desejada.
     */
    private void iniciarNovoJogo() {
        System.out.println("\n--- ESCOLHA A DIFICULDADE ---");
        System.out.println("1. Fácil (10x10)");
        System.out.println("2. Médio (15x15)");
        System.out.println("3. Difícil (30x30)");
        System.out.print("> ");

        try {
            int escolha = scanner.nextInt();
            Dificuldade dificuldade;
            switch (escolha) {
                case 1:
                    dificuldade = Dificuldade.FACIL;
                    break;
                case 2:
                    dificuldade = Dificuldade.MEDIO;
                    break;
                case 3:
                    dificuldade = Dificuldade.DIFICIL;
                    break;
                default:
                    System.out.println("Opção inválida. Iniciando no modo Fácil por padrão.");
                    dificuldade = Dificuldade.FACIL;
            }
            Jogo jogo = new Jogo(dificuldade);
            jogo.iniciar();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Iniciando no modo Fácil por padrão.");
            Jogo jogo = new Jogo(Dificuldade.FACIL);
            jogo.iniciar();
            scanner.next(); // Limpa o buffer
        }
    }

    private void exibirTutorial() {
        System.out.println("\n--- TUTORIAL DE BATALHA NAVAL ---");
        System.out.println("OBJETIVO: Afundar todas as embarcações do seu adversário (o Computador) antes que ele afunde as suas.");
        System.out.println("\nFASES DO JOGO:");
        System.out.println("1. POSICIONAMENTO: Você e o computador posicionam suas frotas em seus respectivos tabuleiros. As embarcações não podem se sobrepor.");
        System.out.println("2. BATALHA: Em turnos alternados, você escolhe uma de suas embarcações para atirar, seleciona um tipo de tiro e uma coordenada alvo no tabuleiro inimigo.");
        System.out.println("\nSÍMBOLOS DO TABULEIRO:");
        System.out.println("  ~ : Água (território inexplorado no seu tabuleiro)");
        System.out.println("  N : Navio (uma parte de sua embarcação)");
        System.out.println("  o : Tiro na água");
        System.out.println("  X : Acerto em uma embarcação");
        System.out.println("  ? : 'Névoa de Guerra' no tabuleiro inimigo (você ainda não atirou ali)");
        System.out.println("\nVITÓRIA: O primeiro a afundar todas as embarcações inimigas vence a partida!");
        System.out.print("\nPressione Enter para voltar ao menu...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
