package model.embarcacoes;

import model.core.Coordenada;
import model.enums.Dificuldade;
import model.enums.TipoTiro;
import model.habilidades.PodeAtirarSimples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Destroier extends Embarcacao implements PodeAtirarSimples {
    public Destroier(List<Coordenada> posicoes, Dificuldade dificuldade) {
        super("Destroier", 3, posicoes, dificuldade);
    }

    /**
     * Define qual a quantidade de tiros disponíveis a embarcação terá.
     * Nesse caso, terá uma quantidade ilimitada de tiros simples.
     * @return Set com os tiros simples
     */
    @Override
    public Set<TipoTiro> getTirosDisponiveis() {
        Set<TipoTiro> tiros = new HashSet<>();
        PodeAtirarSimples.super.adicionarTiroSimples(tiros);
        return tiros;
    }
}