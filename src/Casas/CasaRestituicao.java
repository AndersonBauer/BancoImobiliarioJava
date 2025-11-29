package Casas;

import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

public class CasaRestituicao extends Casa{
    // construtor define o nome da casa como Restituição
    public CasaRestituicao(){
        super("Restituicao");
    }

    // reescreve a função acao da classe Casa
    @Override
    public void acao(Jogador jogador, Jogo jogo, Scanner sc) {
        int bonus = (int)(jogo.getSalarioPorVolta() * 0.10); // define o o bonus como 10% do valor do salario por volta
        jogador.adcicionarDinheiro(bonus); // adiciona o valor do bonus ao saldo do jogador
        System.out.println(jogador.getNome() + " recebeu restituicao de R$" + bonus + "."); // imprime que o jogador recebeu o bonus e o valor
    }
}
