package Gerenciadores;
import Casas.Casa;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class GerenciadorImoveis {
    private ArrayList<Casa> listaImoveis = new ArrayList<>();
    private static final int MAX_IMOVEIS = 40;
    public boolean adicionarImovel(Casa casa){
        if(listaImoveis.size() >= MAX_IMOVEIS ){
            System.out.println("Lista de imoveis cheia!");
            return false;
        }
        listaImoveis.add(casa);
        System.out.println("Imovel" + casa.getNome() + " cadastrado com sucesso!");
        return true;
    }

    public void listarImoveis(){
        System.out.println("\n Imoveis Cadastrados ---");
        if (listaImoveis.isEmpty()){
            System.out.println("Nenhum imovel cadastrado.");
            return;
        }
        for (int i = 0; i < listaImoveis.size(); i++){
            Casa c = listaImoveis.get(i);
            System.out.println((i + 1) + ". " + c.getNome());
        }
    }

    public boolean atualizarImovel(String nomeAntigo, String novoNome){
        Casa casa = buscarPorNome(nomeAntigo);
         if (casa == null){
             System.out.println("Imovel não encontrado;");
             return false;
         }

         try {
             var campo = Casa.class.getDeclaredField("nome");
             campo.setAccessible(true);
             campo.set(casa, novoNome);

             System.out.println("Imovel atualizado com sucesso!");
             return true;
         } catch (Exception e){
             System.out.println("Erro ao atualizar imovel.");
             return false;
         }
    }

    public boolean removerImovel(String nome){
        Casa casa = buscarPorNome(nome);
        if (casa == null){
            System.out.println("Imovel não encontrado.");
            return false;
        }

        listaImoveis.remove(casa);
        System.out.println("Imovel removido com sucesso!");
        return true;
    }

    public Casa buscarPorNome(String nome){
        for(Casa c : listaImoveis){
            if (c.getNome().equalsIgnoreCase(nome)){
                return c;
            }
        }
        return null;
    }
    public int quantidade(){
        return listaImoveis.size();
    }

    public ArrayList<Casa> getLista(){
        return listaImoveis;
    }
}