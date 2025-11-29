package Casas;

import Jogadores.Jogador;
import Jogo.Jogo;

import java.util.Scanner;

// Casa imovel (Casas que podem ser compradas pelos jogadores )
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

    //função usada para ver se o usuário quer e pode comprar o imovel
    @Override
    public void acao(Jogador jogador, Jogo jogo,Scanner sc) {

        // se o imovel não tiver dono ele pergunta se o jogador quer comprar
        if (dono == null) {
            System.out.println("\nVocê caiu em: " + getNome());
            System.out.println("Preço do imóvel: R$" + preco);
            System.out.println("Deseja comprar este imóvel? (1-Sim / 2-Não)");
            int escolha = sc.nextInt();
            if (escolha == 1) {
                // verifica se o jogador tem dinheiro
                // se sim
                if (jogador.getDinheiro() >= preco) {
                    // retira o dinheiro do jogador
                    jogador.removerDinheiro(preco);

                    // adicionao imovel na lista de imoveis do jogador
                    jogador.adicionarImovel(this);

                    // define o jogador como dono
                    setDono(jogador);
                    System.out.println(jogador.getNome() + " comprou " + getNome() + " por R$" + preco);
                } else {
                    // se o jogador falar que quer comprar o imovel mas não tem dinheiro
                    System.out.println("Você não tem dinheiro suficiente para comprar esta casa.");
                }
            } else {
                // caso ele não queira, imprime uma mensagem
                System.out.println("Você decidiu não comprar o imóvel.");
            }

        // se o jogador que cair não for o dono
        } else if (dono != jogador) {
            System.out.println("\nVocê caiu em " + getNome() + " que pertence a " + dono.getNome());
            System.out.println("Aluguel a pagar: R$" + aluguel);

            // chama a função de pagar o alugel
            jogo.pagarAluguel(jogador, dono, aluguel);
        } else {
            // se o jogador que caiu na casa é o dono apenas imprime uma mensagem
            System.out.println("\nVocê caiu na sua própria casa: " + getNome());
        }
    }

    // GET's e SET's

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