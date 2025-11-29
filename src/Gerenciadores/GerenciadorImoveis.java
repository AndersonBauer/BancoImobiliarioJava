package Gerenciadores;
import Casas.Casa;
import Casas.CasaImovel;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class GerenciadorImoveis {
    private ArrayList<Casa> listaImoveis = new ArrayList<>(); // lista de imoveis
    private static final int MAX_IMOVEIS = 40; // numero maximo de imoveis que podem ser cadastrados
    // adiciona um imovel na lista de imoveis
    // retorna verdadeiro ou falso
    public boolean adicionarImovel(Casa casa){
        if(listaImoveis.size() >= MAX_IMOVEIS ){ // verifica se a lista de imoveis está cheia
            System.out.println("Lista de imoveis cheia!");
            return false;
        }
        // se não estiver cheia ele adiciona a lsita de imoveis
        listaImoveis.add(casa);
        System.out.println("Imovel" + casa.getNome() + " cadastrado com sucesso!");
        return true;
    }

    // a função que lista os imoveis cadastrados
    public void listarImoveis(){
        System.out.println("\n--- Imóveis Cadastrados ---");

        if (listaImoveis.isEmpty()){
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }

        for (int i = 0; i < listaImoveis.size(); i++){ // percorre a lista de imoveis
            Casa c = listaImoveis.get(i); // atribui o imovel em uma variavel

            // Checa se é CasaImovel antes de acessar preço e aluguel
            if (c instanceof CasaImovel) { // verifica se c é da Classe CasaImovel
                CasaImovel ci = (CasaImovel) c; // (CasaImovel) obriga a saida ser do tipo CasaImovel
                System.out.println(
                        (i + 1) + ". " + ci.getNome() +
                                " | Preço: R$" + ci.getPreco() +
                                " | Aluguel: R$" + ci.getAluguel()
                );
            } else {
                // Para casos especiais: CasaInicio, CasaImposto, etc.
                System.out.println((i + 1) + ". " + c.getNome() + " (Casa especial)");
            }
        }
    }


    // função que remove o imovel da lista pelo nome
    public boolean removerImovel(String nome){
        Casa casa = buscarPorNome(nome); // salva na variavel o nome do imovel a ser deletado

        // se não tiver um imovel com esse nome ele diz que não foi encontrado
        if (casa == null){
            System.out.println("Imovel não encontrado.");
            return false;
        }

        // remove o imovel da lista de imoveis
        listaImoveis.remove(casa);
        System.out.println("Imovel removido com sucesso!");
        return true;
    }

    // função que busca por nome
    public Casa buscarPorNome(String nome){
        for(Casa c : listaImoveis){ // percorre a lista de imoveis
            if (c.getNome().equalsIgnoreCase(nome)){ // verifica em cada indice da lista se o nome é o mesmo que foi passado por parametro
                return c;
            }
        }
        return null;
    }

    // função que retorna a quantidade de imoveis cadastrados
    public int quantidade(){
        return listaImoveis.size();
    }

    // get da lista de imoveis
    public ArrayList<Casa> getLista(){
        return listaImoveis;
    }
}