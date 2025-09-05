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
                        System.out.println("Obrigado por jogar! Ate a proxima.");
                        return; // Sai do método e encerra o loop
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Por favor, digite um numero.");
                scanner.next(); // Limpa o buffer
            }
        }
    }

    /**
     * Inicializa o jogo permitindo que o usuário escolha a dificuldade desejada.
     */
    private void iniciarNovoJogo() {
        System.out.println("\n--- ESCOLHA A DIFICULDADE ---");
        System.out.println("1. Facil (10x10)");
        System.out.println("2. Medio (15x15)");
        System.out.println("3. Dificil (30x30)");
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
                    System.out.println("Opcao invalida. Iniciando no modo Facil por padrao.");
                    dificuldade = Dificuldade.FACIL;
            }
            Jogo jogo = new Jogo(dificuldade);
            jogo.iniciar();
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Iniciando no modo Facil por padrao.");
            Jogo jogo = new Jogo(Dificuldade.FACIL);
            jogo.iniciar();
            scanner.next(); // Limpa o buffer
        }
    }

    private void exibirTutorial() {
        System.out.println("\n--- TUTORIAL DE BATALHA NAVAL ---");
        System.out.println("OBJETIVO: Afundar todas as embarcacoes do seu adversario (o Computador) antes que ele afunde as suas.");
        System.out.println("\nFASES DO JOGO:");
        System.out.println("1. POSICIONAMENTO: Voce e o computador posicionam suas frotas em seus respectivos tabuleiros. As embarcacoes nao podem se sobrepor.");
        System.out.println("2. BATALHA: Em turnos alternados, voce escolhe uma de suas embarcacoes para atirar, seleciona um tipo de tiro e uma coordenada alvo no tabuleiro inimigo.");
        System.out.println("\nSÍMBOLOS DO TABULEIRO:");
        System.out.println("  ~ : Agua (territorio inexplorado no seu tabuleiro)");
        System.out.println("  N : Navio (uma parte de sua embarcacao)");
        System.out.println("  o : Tiro na agua");
        System.out.println("  X : Acerto em uma embarcacao");
        System.out.println("  ? : 'Nevoa de Guerra' no tabuleiro inimigo (voce ainda nao atirou ali)");
        System.out.println("\nVITÓRIA: O primeiro a afundar todas as embarcacoes inimigas vence a partida!");
        System.out.print("\nPressione Enter para voltar ao menu...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
