package model.habilidades;

import model.enums.TipoTiro;

import java.util.Set;
public interface PodeAtirarSimples {
    // Método padrão que adiciona o tipo de tiro ao conjunto de tiros disponíveis.
    default void adicionarTiroSimples(Set<TipoTiro> tiros) {
        tiros.add(TipoTiro.SIMPLES);
    }
}