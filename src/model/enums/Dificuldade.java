package model.enums;

public enum Dificuldade {
    FACIL(10),
    MEDIO(15),
    DIFICIL(30);

    private final int tamanhoTabuleiro;

    Dificuldade(int tamanhoTabuleiro) {
        this.tamanhoTabuleiro = tamanhoTabuleiro;
    }

    public int getTamanhoTabuleiro() {
        return tamanhoTabuleiro;
    }
}
