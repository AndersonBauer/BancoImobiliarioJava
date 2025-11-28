package Gerenciadores;

import Casas.CasaImovel;
import Jogadores.Jogador;
import Tabuleiro.TabuleiroLista;

import java.util.ArrayList;

public class GerenciadorJogadores {
    private ArrayList<Jogador> listaJogadores = new ArrayList<>();
    private static final int MAX_JOGADORES = 6;

    public boolean adicionarJogador(String nome, int dinheiroInicial, TabuleiroLista tabuleiro){
        if (listaJogadores.size() >= MAX_JOGADORES ){
            System.out.println("Limite maximo de jogadores atingido (6)");
            return false;
        }
        Jogador jogador = new Jogador(nome, dinheiroInicial, tabuleiro);
        listaJogadores.add(jogador);
        System.out.println("Jogador " + jogador.getNome() + " cadastrado!");
        return true;
    }

    public void declaraFalencia(Jogador jogador){
        System.out.println("\n" + jogador.getNome() + " faliu!!!");

        for(CasaImovel imovel : jogador.getImoveis()){
            imovel.setDono(null);
        }

        jogador.getImoveis().clear();
        jogador.removerDinheiro(jogador.getDinheiro());
        removerJogador(jogador.getNome());
    }

    public void listarJogadores(){
        System.out.println("\n --- Jogadores Cadastrados --- ");
        if (listaJogadores.isEmpty()){
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }
        for (int i = 0; i < listaJogadores.size(); i++) {
            Jogador j = listaJogadores.get(i);
            System.out.println((i + 1) + ". " + j.getNome() + " - Saldo: R$" + j.getDinheiro());
        }
    }

    public boolean atualizarJogador(String nomeAntigo, String novoNome){
        Jogador jogador = buscarPorNome(nomeAntigo);

        if (jogador == null ){
            System.out.println("Jogador não encontrado.");
            return false;
        }

        jogador.setNome(novoNome);
        System.out.println("Jogador atualizado.");
        return true;
    }

    public boolean removerJogador(String nome){
        Jogador jogador = buscarPorNome(nome);

        if (jogador == null){
            System.out.println("Jogador não encontrado.");
            return false;
        }

        listaJogadores.remove(jogador);
        System.out.println("Jogador removido com sucesso.");
        return true;
    }

    public Jogador buscarPorNome(String nome){
        for (Jogador j : listaJogadores){
            if (j.getNome().equalsIgnoreCase(nome)){
                return j;
            }
        }
        return null;
    }

    public int quantidade(){
        return listaJogadores.size();
    }

    public ArrayList<Jogador> getLista(){
        return listaJogadores;
    }
}