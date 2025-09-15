# Batalha Naval

Este é um projeto de jogo Batalha Naval desenvolvido em Java. O objetivo é permitir que um jogador humano enfrente o computador nesse clássico jogo de estratégia, afundando a frota inimiga antes que a sua seja destruída.

## Principais Features

* **Níveis de Dificuldade**: O jogo oferece três níveis de dificuldade que alteram o tamanho do tabuleiro:
    * Fácil (10x10)
    * Médio (15x15)
    * Difícil (30x30)
* **Posicionamento da Frota**:
    * O jogador humano pode posicionar suas embarcações manualmente, definindo a coordenada inicial e a orientação (horizontal ou vertical).
    * O computador posiciona sua frota de forma aleatória no tabuleiro.
* **Diversidade de Embarcações**: A frota é composta por cinco tipos de embarcações, cada uma com um tamanho específico:
    * Porta-aviões
    * Cruzador
    * Fragata
    * Destróier
    * Submarino
* **Habilidades Especiais**: Algumas embarcações possuem tiros especiais (duplo e explosivo), com munição que varia conforme a dificuldade do jogo.
* **Interface de Console**: O jogo é totalmente funcional via console, com menus interativos e exibição clara dos tabuleiros para uma experiência de jogo intuitiva.

## Design Pattern Utilizado

O projeto emprega o padrão de projeto **Factory**. A classe `FrotaFactory` é a implementação central desse padrão, sendo responsável por criar e fornecer a lista de embarcações que compõem a frota de cada jogador, de acordo com a dificuldade selecionada.

Essa abordagem desacopla a lógica de criação dos objetos da frota do resto da aplicação, o que torna o código mais modular, flexível e fácil de manter.
