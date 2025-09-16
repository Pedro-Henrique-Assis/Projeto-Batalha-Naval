import model.core.Jogador;
import model.core.JogadorComputador;
import model.core.JogadorHumano;
import model.enums.Dificuldade;

public class Jogo {

    private final Jogador jogador1;
    private final Jogador jogador2;
    private Jogador jogadorAtual;

    public Jogo(Dificuldade dificuldade) {
        // O jogador humano sempre começa.
        this.jogador1 = new JogadorHumano("Jogador 1", dificuldade);
        this.jogador2 = new JogadorComputador(dificuldade);
        this.jogadorAtual = jogador1;
    }

    /**
     * Inicia e executa o jogo até que haja um vencedor.
     */
    public void iniciar() {
        System.out.println("--- BEM-VINDO À BATALHA NAVAL ---");
        System.out.println("Dificuldade: " + jogador1.getTabuleiro().getTamanho() + "x" + jogador1.getTabuleiro().getTamanho());

        // Fase 1: Posicionamento das frotas
        System.out.println("\n--- FASE DE POSICIONAMENTO ---");
        jogador1.posicionarFrota();
        jogador2.posicionarFrota();

        // Fase 2: Batalha (Loop do Jogo)
        System.out.println("\n--- QUE A BATALHA COMECE! ---");
        while (true) {
            System.out.println("\n======================================");
            System.out.println("TURNO DE: " + jogadorAtual.getNome());

            // Se for o turno do humano, mostra ambos os tabuleiros
            if (jogadorAtual instanceof JogadorHumano) {
                System.out.println("\n--- SEU TABULEIRO DE NAVIOS ---");
                jogador1.getTabuleiro().exibir();
                System.out.println("\n--- TABULEIRO DE ALVOS (Computador) ---");
                jogador2.getTabuleiro().exibirParaAdversario();
            }

            // Jogador atual realiza a jogada
            jogadorAtual.realizarJogada(getAdversario());

            // Verifica se o adversário perdeu
            if (getAdversario().perdeu()) {
                jogadorAtual.setVencedor(true);
                break; // Fim de jogo
            }
            trocarTurno();
        }
        anunciarVencedor();
    }

    private Jogador getAdversario() {
        return (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }

    private void trocarTurno() {
        jogadorAtual = getAdversario();
    }

    private void anunciarVencedor() {
        System.out.println("\n--- FIM DE JOGO ---");
        if (jogador1.isVencedor()) {
            System.out.println("Parabéns, " + jogador1.getNome() + "! Você venceu!");
        } else if (jogador2.isVencedor()) {
            System.out.println("O " + jogador2.getNome() + " venceu. Mais sorte na próxima vez!");
        }
    }
}
