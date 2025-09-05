package utils;

import model.core.Coordenada;
import model.enums.Direcao;
import model.enums.TipoTiro;

import java.util.ArrayList;
import java.util.List;

public class TiroUtils {

    public static List<Coordenada> getCoordenadasAfetadas(Coordenada alvo, TipoTiro tipoTiro) {
        if (tipoTiro == TipoTiro.DUPLO) {
            // Lança uma exceção se este método for chamado incorretamente para Tiro Duplo
            throw new IllegalArgumentException("Tiro Duplo requer uma direção.");
        }
        return calcularCoordenadas(alvo, tipoTiro, null);
    }

    public static List<Coordenada> getCoordenadasAfetadas(Coordenada alvo, TipoTiro tipoTiro, Direcao direcao) {
        if (tipoTiro != TipoTiro.DUPLO) {
            return getCoordenadasAfetadas(alvo, tipoTiro);
        }
        return calcularCoordenadas(alvo, tipoTiro, direcao);
    }

    private static List<Coordenada> calcularCoordenadas(Coordenada alvo, TipoTiro tipoTiro, Direcao direcao) {
        List<Coordenada> coordenadasAfetadas = new ArrayList<>();

        switch (tipoTiro) {
            case SIMPLES:
                coordenadasAfetadas.add(alvo);
                break;
            case DUPLO:
                coordenadasAfetadas.add(alvo);
                Coordenada segundoAlvo = switch (direcao) {
                    case CIMA -> new Coordenada(alvo.linha() - 1, alvo.coluna());
                    case BAIXO -> new Coordenada(alvo.linha() + 1, alvo.coluna());
                    case ESQUERDA -> new Coordenada(alvo.linha(), alvo.coluna() - 1);
                    case DIREITA -> new Coordenada(alvo.linha(), alvo.coluna() + 1);
                };
                coordenadasAfetadas.add(segundoAlvo);
                break;
            case EXPLOSIVO:
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        coordenadasAfetadas.add(new Coordenada(alvo.linha() + i, alvo.coluna() + j));
                    }
                }
                break;
        }
        return coordenadasAfetadas;
    }
}
