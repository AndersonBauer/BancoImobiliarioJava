package Jogadores;

import Casas.CasaImovel;
import Tabuleiro.NoCasa;
import Tabuleiro.TabuleiroLista;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogador{
    private String nome;
    private int dinheiro;
    private NoCasa posicaoAtual;
    private TabuleiroLista tabuleiro;

    private ArrayList<CasaImovel> imoveis = new ArrayList<>();

    public Jogador(String nome, int dinheiroInicial, TabuleiroLista tabuleiro){
        this.nome = nome;
        this.dinheiro = dinheiroInicial;
        this.tabuleiro = tabuleiro;

        if (tabuleiro.getInicio() == null){
            throw new IllegalStateException("ERRO: Tentando criar jogador antes");
        }
        this.posicaoAtual = null;
    }

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

    public void mover(int quantidade, Scanner sc){
        for( int i = 0; i < quantidade; i++){
            posicaoAtual = posicaoAtual.proximo;

            if (posicaoAtual == tabuleiro.getInicio()){
                dinheiro+= tabuleiro.getJogo().getSalarioPorVolta();
                System.out.println(nome + " recebeu um salario por completar uma volta!");
            }
        }
        System.out.println(nome + " andou " + quantidade + " casas e está em:" + posicaoAtual.casa.getNome());

        posicaoAtual.casa.acao(this, tabuleiro.getJogo(), sc);
    }

    public void adicionarImovel(CasaImovel imovel){
        imoveis.add(imovel);
    }

    public ArrayList<CasaImovel> getImoveis(){
        return imoveis;
    }

    public int calcularPatrimonio() {
        int patrimonio = dinheiro;
        for (CasaImovel imovel : imoveis){
            patrimonio += imovel.getPreco();
        }
        return patrimonio;
    }
}