package Casas;

import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

public class CasaImovel extends Casa {
    private int preco;
    private int aluguel;
    private Jogador dono;

    public CasaImovel(String nome, int preco, int aluguel) {
        super(nome);
        this.preco = preco;
        this.aluguel = aluguel;
        this.dono = null;
    }

    @Override
    public void acao(Jogador jogador, Jogo jogo,Scanner sc) {

        if (dono == null) {
            System.out.println("\nVocê caiu em: " + getNome());
            System.out.println("Preço do imóvel: R$" + preco);
            System.out.println("Deseja comprar este imóvel? (1-Sim / 2-Não)");
            int escolha = sc.nextInt();
            if (escolha == 1) {
                if (jogador.getDinheiro() >= preco) {
                    jogador.removerDinheiro(preco);
                    jogador.adicionarImovel(this);
                    setDono(jogador);
                    System.out.println(jogador.getNome() + " comprou " + getNome() + " por R$" + preco);
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar esta casa.");
                }
            } else {
                System.out.println("Você decidiu não comprar o imóvel.");
            }
        } else if (dono != jogador) {
            System.out.println("\nVocê caiu em " + getNome() + " que pertence a " + dono.getNome());
            System.out.println("Aluguel a pagar: R$" + aluguel);
            jogo.pagarAluguel(jogador, dono, aluguel);
        } else {
            System.out.println("\nVocê caiu na sua própria casa: " + getNome());
        }
    }


    public int getPreco() {
        return preco;
    }

    public int getAluguel() {
        return aluguel;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador jogador) { this.dono = jogador; }
}