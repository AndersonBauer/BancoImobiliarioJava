package Jogadores;

import Casas.CasaImovel;
import Tabuleiro.NoCasa;
import Tabuleiro.TabuleiroLista;

import java.util.ArrayList;
import java.util.Scanner;
// classe jogador
public class Jogador{
    // atributos da classe
    private String nome;
    private int dinheiro;
    private NoCasa posicaoAtual;
    private TabuleiroLista tabuleiro;
    private ArrayList<CasaImovel> imoveis = new ArrayList<>();
    private  boolean falido = false;

    // construtor da classe
    public Jogador(String nome, int dinheiroInicial, TabuleiroLista tabuleiro){
        this.nome = nome;
        this.dinheiro = dinheiroInicial;
        this.tabuleiro = tabuleiro;

        // essa parte corrige um erro que estava acontecendo
        // o jogo criava o jogador mas não colocava a casa Inicio como posição inicial dos jogadores
        if (tabuleiro.getInicio() == null){
            throw new IllegalStateException("ERRO: Tentando criar jogador antes");
        }
        this.posicaoAtual = null;
    }

    // gets e sets da classe
    public void setPosicaoInicial(NoCasa inicio){
        this.posicaoAtual = tabuleiro.getInicio();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void adcicionarDinheiro(int valor) {
        this.dinheiro += valor;
    }

    public void removerDinheiro(int valor) {
        this.dinheiro -= valor;
    }

    public NoCasa getPosicaoAtual() {
        return posicaoAtual;
    }

    // função que move o jogador pelo tabuleiro
    public void mover(int quantidade, Scanner sc){
        for( int i = 0; i < quantidade; i++){ // percorre a quantidade que tirou no dado
            posicaoAtual = posicaoAtual.proximo; // anda para a proxima casa (uma de cada vez)

            // verifica se a posição que o jogador passou é a casa inicio
            if (posicaoAtual == tabuleiro.getInicio()){ // se for
                dinheiro+= tabuleiro.getJogo().getSalarioPorVolta(); // soma o salario por volta na conta do jogador
                System.out.println(nome + " recebeu um salario por completar uma volta!");
            }
        }
        // mostra a quantidade de casas que o jogador andou e a posição final dele
        System.out.println(nome + " andou " + quantidade + " casas e está em:" + posicaoAtual.casa.getNome());

        posicaoAtual.casa.acao(this, tabuleiro.getJogo(), sc); // chama a ação da casa que o jogador parou
    }

    // função que adiciona a casa a lista de imoveis do jogador
    public void adicionarImovel(CasaImovel imovel){
        imoveis.add(imovel);
    }

    public ArrayList<CasaImovel> getImoveis(){
        return imoveis;
    } // retorna a lista de imoveis

    // função que calcula o patrimonio
    public int calcularPatrimonio() {
        // aqui criei a variavel que vai receber todo o dinheiro do jogador
        int patrimonio = dinheiro;
        for (CasaImovel imovel : imoveis){
            patrimonio += imovel.getPreco(); // e aqui vai somar o valor de todos os imoveis que pertemcem a ele
        }
        return patrimonio; // retorna o valor total do patrimonio
    }

    public boolean estaFalido() {return falido;} // retorna se está falido ou não

    public void setFalido(boolean f) {this.falido = f;} // define se está falido ou não
}