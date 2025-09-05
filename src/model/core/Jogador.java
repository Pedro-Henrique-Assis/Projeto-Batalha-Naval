package model.core;

import model.embarcacoes.Embarcacao;
import model.enums.Dificuldade;

public abstract class Jogador {
    protected final String nome;
    protected final Tabuleiro tabuleiro;
    protected boolean vencedor;

    public Jogador(String nome, Dificuldade dificuldade) {
        this.nome = nome;
        this.tabuleiro = new Tabuleiro(dificuldade);
        this.vencedor = false;
    }


    /**
     * Método para posicionar o conjunto de embarcações.
     * A implementação varia entre jogador humano (manual) e computador (aleatório).
     */
    public abstract void posicionarFrota();


    /**
     * Método para realizar uma jogada (atirar).
     * @param adversario O jogador que receberá o tiro.
     */
    public abstract void realizarJogada(Jogador adversario);


    /**
     * Verifica se o jogador perdeu o jogo (todas as embarcações afundadas).
     * @return true se todas as embarcações foram afundadas.
     */
    public boolean perdeu() {
        for (Embarcacao e : tabuleiro.getEmbarcacoes()) {
            if (!e.estaAfundada()) {
                return false; // Se encontrar pelo menos uma não afundada, não perdeu.
            }
        }
        return true;
    }

    /**
     * Define qual jogador é o vencedor da partida.
     * @param ehVencedor O status do jogador (vencedor ou não).
     */
    public void setVencedor(boolean ehVencedor) {
        this.vencedor = ehVencedor;
    }

    public String getNome() {
        return nome;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public boolean isVencedor() {
        return vencedor;
    }
}
