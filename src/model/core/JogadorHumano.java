package model.core;

import factory.FrotaFactory;
import model.embarcacoes.*;
import model.enums.*;
import model.habilidades.*;
import utils.ConsoleUtils;
import utils.PosicionamentoUtils;
import utils.TiroUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JogadorHumano extends Jogador {

    private final Dificuldade dificuldade;

    public JogadorHumano(String nome, Dificuldade dificuldade) {
        super(nome, dificuldade);
        this.dificuldade = dificuldade;
    }

    /**
     * Realiza o posicionamento manual das embarcações
     */
    @Override
    public void posicionarFrota() {
        System.out.println("\n" + this.nome + ", posicione sua frota.");
        List<Embarcacao> frotaParaPosicionar = FrotaFactory.criarFrota(dificuldade);

        for (Embarcacao modelo : frotaParaPosicionar) {
            boolean posicionado = false;
            while (!posicionado) {
                System.out.println("\nSeu tabuleiro atual:");
                tabuleiro.exibir();
                System.out.println("Posicionando: " + modelo.getNome() + " (Tamanho: " + modelo.getTamanho() + ")");

                Coordenada inicio = ConsoleUtils.lerCoordenada(tabuleiro.getTamanho());
                Orientacao orientacao = ConsoleUtils.lerOrientacao();

                List<Coordenada> posicoes = PosicionamentoUtils.calcularPosicoes(modelo, inicio, orientacao);
                Embarcacao novaEmbarcacao = PosicionamentoUtils.criarEmbarcacaoFinal(modelo, posicoes, dificuldade);

                if (tabuleiro.posicionarEmbarcacao(novaEmbarcacao)) {
                    System.out.println(modelo.getNome() + " posicionado com sucesso!");
                    posicionado = true;
                } else {
                    System.out.println("Posicao invalida! A embarcacao sairia do tabuleiro ou se sobrepoe a outra. Tente novamente.");
                }
            }
        }
        System.out.println("\nSua frota foi posicionada! Tabuleiro final:");
        tabuleiro.exibir();
    }

    /**
     * Método para atirar com uma embarcação
     * @param adversario O jogador que receberá o tiro.
     */
    @Override
    public void realizarJogada(Jogador adversario) {
        System.out.println("\nSua vez de atirar, " + this.nome + "!");

        // Filtrar para obter apenas embarcações que não afundaram
        List<Embarcacao> embarcacoesAtivas = this.tabuleiro.getEmbarcacoes().stream()
                .filter(e -> !e.estaAfundada())
                .collect(Collectors.toList());

        // Deixar o jogador escolher a embarcação
        Embarcacao embarcacaoEscolhida = ConsoleUtils.escolherEmbarcacao(embarcacoesAtivas);

        // Deixar o jogador escolher o tipo de tiro
        Set<TipoTiro> tirosDisponiveis = embarcacaoEscolhida.getTirosDisponiveis();
        TipoTiro tipoTiroEscolhido = ConsoleUtils.escolherTipoTiro(tirosDisponiveis);

        // Deixar o jogador escolher a coordenada alvo
        System.out.println("Escolha a coordenada alvo.");
        Coordenada alvo = ConsoleUtils.lerCoordenada(adversario.getTabuleiro().getTamanho());

        // Calcular as coordenadas afetadas
        List<Coordenada> coordenadasAfetadas = TiroUtils.getCoordenadasAfetadas(alvo, tipoTiroEscolhido);
        if (tipoTiroEscolhido == TipoTiro.DUPLO) {
            Direcao direcao = ConsoleUtils.lerDirecaoTiroDuplo();
            coordenadasAfetadas = TiroUtils.getCoordenadasAfetadas(alvo, tipoTiroEscolhido, direcao);
        } else {
            coordenadasAfetadas = TiroUtils.getCoordenadasAfetadas(alvo, tipoTiroEscolhido);
        }

        // Processar cada tiro
        System.out.println("\n--- RESULTADO DOS TIROS ---");
        for (Coordenada c : coordenadasAfetadas) {
            EstadoCelula resultado = adversario.getTabuleiro().receberTiro(c);
            if (resultado != null) { // Se o tiro foi dentro do tabuleiro
                String msg = "Tiro em (" + c.linha() + ", " + c.coluna() + "): ";
                if (resultado == EstadoCelula.ATINGIDO_OCUPADO) {
                    msg += "ACERTOU!";
                } else {
                    msg += "AGUA!";
                }
                System.out.println(msg);
            }
        }

        // Atualizar munição
        if (embarcacaoEscolhida instanceof PodeAtirarDuplo && tipoTiroEscolhido == TipoTiro.DUPLO) {
            ((PodeAtirarDuplo) embarcacaoEscolhida).usarTiroDuplo();
        } else if (embarcacaoEscolhida instanceof PodeAtirarExplosivo && tipoTiroEscolhido == TipoTiro.EXPLOSIVO) {
            ((PodeAtirarExplosivo) embarcacaoEscolhida).usarTiroExplosivo();
        }
    }
}
