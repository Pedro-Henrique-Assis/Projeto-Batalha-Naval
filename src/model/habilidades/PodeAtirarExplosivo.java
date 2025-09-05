package model.habilidades;

import model.enums.TipoTiro;

import java.util.Set;
public interface PodeAtirarExplosivo {
    default void adicionarTiroExplosivo(Set<TipoTiro> tiros) {
        if (getMunicaoTiroExplosivo() > 0) {
            tiros.add(TipoTiro.EXPLOSIVO);
        }
    }

    int getMunicaoTiroExplosivo();
    void usarTiroExplosivo();
}