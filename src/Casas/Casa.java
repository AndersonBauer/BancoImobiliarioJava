package Casas;
import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;
// Casa usada de padrão para as outras
public abstract class Casa {
    protected String nome;

    public Casa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // função abistrata que força as outras casas a implementar ela
    public abstract void acao(Jogador jogador, Jogo jogo, Scanner sc);
}
