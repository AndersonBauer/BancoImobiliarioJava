package Casas;
import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

public abstract class Casa {
    protected String nome;

    public Casa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void acao(Jogador jogador, Jogo jogo, Scanner sc);
}
