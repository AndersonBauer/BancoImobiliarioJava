package Casas;

import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

public class CasaImposto extends Casa {

    // construtor apenas define o nome da casa como Imposto
    public CasaImposto(){
        super("Imposto");
    }

    // reescrevendo a função acao que a classe casa obriga
    @Override
    public void acao(Jogador jogador, Jogo jogo, Scanner sc){

        // a função foi reescrita para fazer a lógica da casa de imposto
        int patrimonio = jogador.calcularPatrimonio(); // guarda o patrimonio do jogador
        int taxa = (int)(patrimonio * 0.05); // define o valor a ser pago como 5% do patrimonio total do jogador
        jogador.removerDinheiro(taxa); // remove o valor da taxa do saldo do jogador
        System.out.println(jogador.getNome() + " pagou R$"+ taxa + " de imposto."); // imprime o valor da taca paga pelo jogador
    }
}
