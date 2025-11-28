package Casas;

import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

public class CasaImposto extends Casa {

    public CasaImposto(){
        super("Imposto");
    }

    @Override
    public void acao(Jogador jogador, Jogo jogo, Scanner sc){
        int patrimonio = jogador.calcularPatrimonio();
        int taxa = (int)(patrimonio * 0.05);
        jogador.removerDinheiro(taxa);
        System.out.println(jogador.getNome() + " pagou R$"+ taxa + " de imposto.");
    }
}
