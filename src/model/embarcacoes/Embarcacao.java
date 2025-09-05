package model.embarcacoes;

import model.core.Coordenada;
import model.enums.Dificuldade;
import model.enums.TipoTiro;

import java.util.List;
import java.util.Set;
public abstract class Embarcacao {
    private final String nome;
    private final int tamanho;
    private List<Coordenada> posicoes;
    private int partesAtingidas;
    protected int municaoTiroDuplo;
    protected int municaoTiroExplosivo;

    public Embarcacao(String nome, int tamanho, List<Coordenada> posicoes, Dificuldade dificuldade) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.posicoes = posicoes;
        this.partesAtingidas = 0;
        inicializarMunicao(dificuldade);
    }

    /**
     * Construtor usado na classe FrotaFactory, que permite posições nulas.
     * @param nome Nome da embarcação.
     * @param tamanho Quantas posições a embarcação ocupará no reticulado.
     * @param dificuldade Dificuldade da partida.
     */
    public Embarcacao(String nome, int tamanho, Dificuldade dificuldade) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.posicoes = null;
        this.partesAtingidas = 0;
        inicializarMunicao(dificuldade);
    }

    /**
     * Define a quantidade de munição da embarcação de acordo com a dificuldade
     * @param dificuldade Dificuldade escolhida pelo usuário
     */
    private void inicializarMunicao(Dificuldade dificuldade) {
        switch (dificuldade) {
            case FACIL:
                // Integer.MAX_VALUE define munição "infinita"
                this.municaoTiroDuplo = Integer.MAX_VALUE;
                this.municaoTiroExplosivo = Integer.MAX_VALUE;
                break;
            case MEDIO:
                this.municaoTiroDuplo = 3;
                this.municaoTiroExplosivo = 3;
                break;
            case DIFICIL:
                this.municaoTiroDuplo = 1;
                this.municaoTiroExplosivo = 1;
                break;
        }
    }

    public void registrarAtingido() {
        if (!estaAfundada()) {
            this.partesAtingidas++;
        }
    }

    /**
     * Retorna o conjunto de tipos de tiro que esta embarcação pode realizar.
     * @return Set com os Tipos de Tiro disponíveis.
     */
    public abstract Set<TipoTiro> getTirosDisponiveis();

    public boolean estaAfundada() {
        return this.partesAtingidas >= this.tamanho;
    }

    public String getNome() { return nome; }
    public int getTamanho() { return tamanho; }
    public List<Coordenada> getPosicoes() { return posicoes; }
}
