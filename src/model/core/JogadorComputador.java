package model.core;

import factory.FrotaFactory;
import model.embarcacoes.*;
import model.enums.Dificuldade;
import model.enums.EstadoCelula;
import model.enums.Orientacao;
import utils.PosicionamentoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogadorComputador extends Jogador {

    private final Dificuldade dificuldade;
    private final List<Coordenada> tirosDisponiveis;
    private final Random random = new Random();

    public JogadorComputador(Dificuldade dificuldade) {
        super("Computador", dificuldade);
        this.dificuldade = dificuldade;
        // Prepara uma lista de todas as coordenadas possíveis para atirar
        this.tirosDisponiveis = new ArrayList<>();
        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            for (int j = 0; j < tabuleiro.getTamanho(); j++) {
                tirosDisponiveis.add(new Coordenada(i, j));
            }
        }
    }

    /**
     * Realiza o posicionamento automático das embarcações.
     */
    @Override
    public void posicionarFrota() {
        System.out.println("\n" + this.nome + " está posicionando a frota...");
        List<Embarcacao> frotaParaPosicionar = FrotaFactory.criarFrota(dificuldade);

        for (Embarcacao modelo : frotaParaPosicionar) {
            boolean posicionado = false;
            while (!posicionado) {
                int linha = random.nextInt(tabuleiro.getTamanho());
                int coluna = random.nextInt(tabuleiro.getTamanho());
                Orientacao orientacao = Orientacao.values()[random.nextInt(Orientacao.values().length)];

                List<Coordenada> posicoes = PosicionamentoUtils.calcularPosicoes(modelo, new Coordenada(linha, coluna), orientacao);
                Embarcacao novaEmbarcacao = PosicionamentoUtils.criarEmbarcacaoFinal(modelo, posicoes, dificuldade);

                if (tabuleiro.posicionarEmbarcacao(novaEmbarcacao)) {
                    posicionado = true;
                }
            }
        }
        System.out.println("Frota do " + this.nome + " posicionada.");
    }

    /**
     * Método para atirar aleatoriamente com uma embarcação.
     * @param adversario O jogador que receberá o tiro.
     */
    @Override
    public void realizarJogada(Jogador adversario) {
        System.out.println("\n" + this.nome + " está realizando uma jogada...");

        int indiceAleatorio = random.nextInt(tirosDisponiveis.size());
        Coordenada alvo = tirosDisponiveis.remove(indiceAleatorio);

        System.out.println(this.nome + " atirou na coordenada (" + alvo.linha() + ", " + alvo.coluna() + ").");

        EstadoCelula resultado = adversario.getTabuleiro().receberTiro(alvo);

        if (resultado == EstadoCelula.ATINGIDO_OCUPADO) {
            System.out.println("-> ACERTOU!");
        } else {
            System.out.println("-> ÁGUA!");
        }
    }
}
