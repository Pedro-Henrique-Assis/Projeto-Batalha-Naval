package utils;

import model.core.Coordenada;
import model.embarcacoes.*;
import model.enums.Dificuldade;
import model.enums.Orientacao;

import java.util.ArrayList;
import java.util.List;

public class PosicionamentoUtils {

    /**
     * Calcula a posição que cada embarcação ocupará no reticulado.
     * @param embarcacao Objeto do tipo Embarcacao
     * @param inicio Coordenada da primeira posição da embarcação.
     * @param orientacao Vertical ou Horizontal.
     * @return Lista com as coordenadas da embarcação.
     */
    public static List<Coordenada> calcularPosicoes(Embarcacao embarcacao, Coordenada inicio, Orientacao orientacao) {
        List<Coordenada> posicoes = new ArrayList<>();
        for (int i = 0; i < embarcacao.getTamanho(); i++) {
            if (orientacao == Orientacao.HORIZONTAL) {
                posicoes.add(new Coordenada(inicio.linha(), inicio.coluna() + i));
            } else { // VERTICAL
                posicoes.add(new Coordenada(inicio.linha() + i, inicio.coluna()));
            }
        }
        return posicoes;
    }

    /**
     * Cria a embarcação, seguindo as particularidades de cada dificuldade e de
     * cada tipo de embarcação.
     * @param modelo Objeto do tipo Embarcacao.
     * @param posicoes Lista com as coordenadas que a embarcação ocupará no reticulado.
     * @return Objeto do tipo Embarcacao com os atributos preenchidos.
     */
    public static Embarcacao criarEmbarcacaoFinal(Embarcacao modelo, List<Coordenada> posicoes, Dificuldade dificuldade) {
        if (modelo instanceof PortaAvioes) return new PortaAvioes(posicoes, dificuldade);
        if (modelo instanceof Cruzador) return new Cruzador(posicoes, dificuldade);
        if (modelo instanceof Fragata) return new Fragata(posicoes, dificuldade);
        if (modelo instanceof Destroier) return new Destroier(posicoes, dificuldade);
        if (modelo instanceof Submarino) return new Submarino(posicoes, dificuldade);
        throw new IllegalArgumentException("Tipo de embarcação desconhecido");
    }
}
