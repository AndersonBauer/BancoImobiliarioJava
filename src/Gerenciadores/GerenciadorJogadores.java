package Gerenciadores;

import Casas.CasaImovel;
import Jogadores.Jogador;
import Tabuleiro.TabuleiroLista;

import java.util.ArrayList;

public class GerenciadorJogadores {
    private ArrayList<Jogador> listaJogadores = new ArrayList<>(); // lista de jogadores
    private static final int MAX_JOGADORES = 6; // numeor maximo de jogadores

    // função que adiciona jogadores
    public boolean adicionarJogador(String nome, int dinheiroInicial, TabuleiroLista tabuleiro){
        if (listaJogadores.size() >= MAX_JOGADORES ){ // verifica se passou do limite
            System.out.println("Limite maximo de jogadores atingido (6)");
            return false;
        }
        // caso não tenha passado
        Jogador jogador = new Jogador(nome, dinheiroInicial, tabuleiro); // cria um novo jogador
        listaJogadores.add(jogador); // adiciona o jogador na lista de jogadores
        System.out.println("Jogador " + jogador.getNome() + " cadastrado!");
        return true;
    }

    // função que declara a falencia de um jogador
    public void declaraFalencia(Jogador jogador){
        System.out.println("\n" + jogador.getNome() + " faliu!!!");

        // retina o jogador como dono dos imoveis que pertemcem a ele (devolve pro tabuleiro)
        for (CasaImovel imovel : jogador.getImoveis()){
            imovel.setDono(null);
        }

        jogador.getImoveis().clear(); // limpa a lista de imoveis
        jogador.removerDinheiro(jogador.getDinheiro()); // remove todo o dinheiro do jogador

        // Marca como falido (não remove ainda)
        jogador.setFalido(true);
    }


    // função que lita os jogadores
    public void listarJogadores(){
        System.out.println("\n --- Jogadores Cadastrados --- ");
        if (listaJogadores.isEmpty()){ //  se a lista de jogadores estiver vazia
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }

        // percorre a lista de jogadores
        for (int i = 0; i < listaJogadores.size(); i++) {
            Jogador j = listaJogadores.get(i); // pega todos o jogadores percorrendo o indice
            System.out.println((i + 1) + ". " + j.getNome() + " - Saldo: R$" + j.getDinheiro());
        }
    }

    // função que remove o jogador
    public boolean removerJogador(String nome){
        Jogador jogador = buscarPorNome(nome); // chama uma função que acha o jogador pelo nome

        if (jogador == null){ // se o jogador for nulo (no caso o jogador não existe)
            System.out.println("Jogador não encontrado.");
            return false;
        }

        // se achar o jogador, ele é removido
        listaJogadores.remove(jogador);
        System.out.println("Jogador removido com sucesso.");
        return true;
    }

    // função que procura o jogador pelo nome
    public Jogador buscarPorNome(String nome){
        for (Jogador j : listaJogadores){ // percorre a lista de jogadores
            if (j.getNome().equalsIgnoreCase(nome)){ // verifica se o nome de cada jogador é igual ao nome que quer ser achado
                return j; // retorna o nome do jogador se achar
            }
        }
        return null; // caso não ache retorna nulo
    }

    public int quantidade(){
        return listaJogadores.size();
    } // função que retorna a quantidade de jogadores na lista

    public ArrayList<Jogador> getLista(){
        return listaJogadores;
    } // retorna a lista de jogadores
}