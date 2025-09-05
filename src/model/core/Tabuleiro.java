package model.core;

import model.embarcacoes.Embarcacao;
import model.enums.Dificuldade;
import model.enums.EstadoCelula;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private final int tamanho;
    private final EstadoCelula[][] reticulado;
    private final List<Embarcacao> embarcacoes;

    public Tabuleiro(Dificuldade dificuldade) {
        this.tamanho = dificuldade.getTamanhoTabuleiro();
        this.reticulado = new EstadoCelula[tamanho][tamanho];
        this.embarcacoes = new ArrayList<>();
        inicializarReticulado();
    }

    /**
     * Registra um tiro em uma coordenada específica do tabuleiro.
     * @param coordenada A coordenada do tiro.
     * @return O estado da célula APÓS o tiro.
     */
    public EstadoCelula receberTiro(Coordenada coordenada) {
        if (!estaDentroDosLimites(coordenada)) {
            return null;
        }

        EstadoCelula estadoAtual = reticulado[coordenada.linha()][coordenada.coluna()];

        if (estadoAtual == EstadoCelula.OCUPADO) {
            reticulado[coordenada.linha()][coordenada.coluna()] = EstadoCelula.ATINGIDO_OCUPADO;

            // Encontra a embarcação na coordenada e registra o dano
            for (Embarcacao e : embarcacoes) {
                if (e.getPosicoes().contains(coordenada)) {
                    e.registrarAtingido();
                    if (e.estaAfundada()) {
                        System.out.println("-> AFUNDOU O " + e.getNome().toUpperCase() + "!");
                    }
                    break;
                }
            }
            return EstadoCelula.ATINGIDO_OCUPADO;

        } else if (estadoAtual == EstadoCelula.AGUA) {
            reticulado[coordenada.linha()][coordenada.coluna()] = EstadoCelula.ATINGIDO_AGUA;
            return EstadoCelula.ATINGIDO_AGUA;
        }
        return estadoAtual;
    }

    /**
     * Exibe o tabuleiro na perspectiva do adversário (com "névoa de guerra").
     * Mostra apenas os tiros dados, sem revelar as posições das embarcações não atingidas.
     */
    public void exibirParaAdversario() {
        System.out.print("   ");
        for (int i = 0; i < tamanho; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < tamanho; j++) {
                char simbolo = switch (reticulado[i][j]) {
                    case AGUA, OCUPADO -> '?'; // "Névoa de guerra"
                    case ATINGIDO_AGUA -> 'o'; // Tiro na água
                    case ATINGIDO_OCUPADO -> 'X'; // Acerto
                };
                System.out.printf(" %c ", simbolo);
            }
            System.out.println();
        }
    }


    /**
     * Inicializa o reticulado com as posições de acordo com a dificuldade.
     */
    private void inicializarReticulado() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                reticulado[i][j] = EstadoCelula.AGUA;
            }
        }
    }

    /**
     * Tenta posicionar uma embarcação no tabuleiro.
     * @param embarcacao A embarcação a ser posicionada.
     * @return true se o posicionamento foi bem-sucedido, false caso contrário.
     */
    public boolean posicionarEmbarcacao(Embarcacao embarcacao) {
        List<Coordenada> posicoes = embarcacao.getPosicoes();

        // Valida se todas as posições são válidas
        for (Coordenada c : posicoes) {
            if (!estaDentroDosLimites(c) || estaSobreposto(c)) {
                return false;
            }
        }

        // Se todas as posições são válidas, ocupa as células no reticulado
        for (Coordenada c : posicoes) {
            reticulado[c.linha()][c.coluna()] = EstadoCelula.OCUPADO;
        }

        this.embarcacoes.add(embarcacao);
        return true;
    }

    /**
     * Verifica se a embarcação está dentro dos limites do reticulado.
     * @param coordenada A coordenada que a embarcação será colocada.
     * @return true se está dentro dos limites, false caso contrário
     */
    private boolean estaDentroDosLimites(Coordenada coordenada) {
        return coordenada.linha() >= 0 && coordenada.linha() < tamanho &&
                coordenada.coluna() >= 0 && coordenada.coluna() < tamanho;
    }

    /**
     * Verifica se a célula que a embarcação será colocada já está ocupada.
     * @param coordenada A coordenada que a embarcação será colocada.
     * @return true se a célula estiver ocupada, false caso contrário
     */
    private boolean estaSobreposto(Coordenada coordenada) {
        return reticulado[coordenada.linha()][coordenada.coluna()] == EstadoCelula.OCUPADO;
    }

    /**
     * Exibe o reticulado no console
     */
    public void exibir() {
        // Imprime o cabeçalho das colunas
        System.out.print("   ");
        for (int i = 0; i < tamanho; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            // Imprime o cabeçalho da linha
            System.out.printf("%2d ", i);
            for (int j = 0; j < tamanho; j++) {
                char simbolo = switch (reticulado[i][j]) {
                    case AGUA -> '~';
                    case OCUPADO -> 'N'; //Navio
                    case ATINGIDO_AGUA -> 'o';
                    case ATINGIDO_OCUPADO -> 'X';
                };
                System.out.printf(" %c ", simbolo);
            }
            System.out.println();
        }
    }

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public int getTamanho() {
        return tamanho;
    }
}
