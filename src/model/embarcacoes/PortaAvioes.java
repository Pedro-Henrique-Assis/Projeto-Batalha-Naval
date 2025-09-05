package model.embarcacoes;

import model.core.Coordenada;
import model.enums.Dificuldade;
import model.enums.TipoTiro;
import model.habilidades.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class PortaAvioes extends Embarcacao implements PodeAtirarSimples, PodeAtirarDuplo, PodeAtirarExplosivo {
    public PortaAvioes(List<Coordenada> posicoes, Dificuldade dificuldade) {
        super("Porta-avioes", 6, posicoes, dificuldade);
    }

    /**
     * Define qual a quantidade de tiros disponíveis a embarcação terá
     * @return Set com os tipos e as quantidades de tiros
     */
    @Override
    public Set<TipoTiro> getTirosDisponiveis() {
        Set<TipoTiro> tiros = new HashSet<>();
        PodeAtirarSimples.super.adicionarTiroSimples(tiros);
        PodeAtirarDuplo.super.adicionarTiroDuplo(tiros);
        PodeAtirarExplosivo.super.adicionarTiroExplosivo(tiros);
        return tiros;
    }

    @Override
    public int getMunicaoTiroDuplo() { return this.municaoTiroDuplo; }

    @Override
    public void usarTiroDuplo() { if (this.municaoTiroDuplo > 0) this.municaoTiroDuplo--; }

    @Override
    public int getMunicaoTiroExplosivo() { return this.municaoTiroExplosivo; }

    @Override
    public void usarTiroExplosivo() { if (this.municaoTiroExplosivo > 0) this.municaoTiroExplosivo--; }
}
