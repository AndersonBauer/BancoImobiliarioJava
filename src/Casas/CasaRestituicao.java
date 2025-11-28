package Casas;

import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

public class CasaRestituicao extends Casa{
    public CasaRestituicao(){
        super("Restituicao");
    }

    @Override
    public void acao(Jogador jogador, Jogo jogo, Scanner sc) {
        int bonus = (int)(jogo.getSalarioPorVolta() * 0.10);
        jogador.adcicionarDinheiro(bonus);
        System.out.println(jogador.getNome() + " recebeu restituicao de R$" + bonus + ".");
    }
}
