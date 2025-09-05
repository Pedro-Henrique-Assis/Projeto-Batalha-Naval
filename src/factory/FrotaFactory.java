package factory;

import model.embarcacoes.*;
import model.enums.Dificuldade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FrotaFactory {

    /**
     * Devolve os construtores vazios, de acordo com a dificuldade escolhida pelo usuário
     * Nota: usa-se o tipo Supplier para adiar a criação dos objetos até ser necessário.
     * O construtor da classe Embarcacao exige as coordenadas que ainda não estarão disponíveis
     * no contexto de execução. Portanto, serão fornecidos os construtores vazios.
     */
    private static final Map<Dificuldade, List<Supplier<Embarcacao>>> frotasPorDificuldade = Map.of(
            Dificuldade.FACIL, List.of(
                    () -> new PortaAvioes(null, Dificuldade.FACIL),
                    () -> new Cruzador(null, Dificuldade.FACIL),
                    () -> new Fragata(null, Dificuldade.FACIL),
                    () -> new Fragata(null, Dificuldade.FACIL),
                    () -> new Destroier(null, Dificuldade.FACIL),
                    () -> new Destroier(null, Dificuldade.FACIL),
                    () -> new Submarino(null, Dificuldade.FACIL),
                    () -> new Submarino(null, Dificuldade.FACIL)
            ),
            Dificuldade.MEDIO, List.of(
                    () -> new PortaAvioes(null, Dificuldade.MEDIO),
                    () -> new Cruzador(null, Dificuldade.MEDIO),
                    () -> new Cruzador(null, Dificuldade.MEDIO),
                    () -> new Fragata(null, Dificuldade.MEDIO),
                    () -> new Fragata(null, Dificuldade.MEDIO),
                    () -> new Fragata(null, Dificuldade.MEDIO),
                    () -> new Destroier(null, Dificuldade.MEDIO),
                    () -> new Destroier(null, Dificuldade.MEDIO),
                    () -> new Submarino(null, Dificuldade.MEDIO),
                    () -> new Submarino(null, Dificuldade.MEDIO),
                    () -> new Submarino(null, Dificuldade.MEDIO)
            ),
            Dificuldade.DIFICIL, List.of(
                    () -> new PortaAvioes(null, Dificuldade.DIFICIL),
                    () -> new PortaAvioes(null, Dificuldade.DIFICIL),
                    () -> new Cruzador(null, Dificuldade.DIFICIL),
                    () -> new Cruzador(null, Dificuldade.DIFICIL),
                    () -> new Cruzador(null, Dificuldade.DIFICIL),
                    () -> new Fragata(null, Dificuldade.DIFICIL),
                    () -> new Fragata(null, Dificuldade.DIFICIL),
                    () -> new Fragata(null, Dificuldade.DIFICIL),
                    () -> new Fragata(null, Dificuldade.DIFICIL),
                    () -> new Destroier(null, Dificuldade.DIFICIL),
                    () -> new Destroier(null, Dificuldade.DIFICIL),
                    () -> new Destroier(null, Dificuldade.DIFICIL),
                    () -> new Destroier(null, Dificuldade.DIFICIL),
                    () -> new Submarino(null, Dificuldade.DIFICIL),
                    () -> new Submarino(null, Dificuldade.DIFICIL),
                    () -> new Submarino(null, Dificuldade.DIFICIL),
                    () -> new Submarino(null, Dificuldade.DIFICIL),
                    () -> new Submarino(null, Dificuldade.DIFICIL)
            )
    );

    /**
     * Retorna a lista de embarcações que devem ser criadas para cada dificuldade.
     * @param dificuldade Dificuldade do jogo.
     * @return Lista de embarcações a serem posicionadas.
     */
    public static List<Embarcacao> criarFrota(Dificuldade dificuldade) {
        List<Embarcacao> frota = new ArrayList<>();
        List<Supplier<Embarcacao>> suppliers = frotasPorDificuldade.get(dificuldade);
        if (suppliers != null) {
            for (Supplier<Embarcacao> supplier : suppliers) {
                frota.add(supplier.get());
            }
        }
        return frota;
    }
}
