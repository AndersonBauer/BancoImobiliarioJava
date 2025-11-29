package Tabuleiro;
import Casas.Casa;
import Jogo.Jogo;

import java.util.ArrayList;

// classe TabuleiroLista
public class TabuleiroLista {
    // atributos da classe
    private NoCasa inicio;
    private Jogo jogo;

    // construtor da classe
    public TabuleiroLista(Jogo jogo){
        this.jogo = jogo;
    }

    // retorna o jogo
    public Jogo getJogo(){
        return jogo;
    }

    // função que adiciona uma casa no tabuleiro
    public void adicionarCasa(Casa casa){
        NoCasa novo = new NoCasa(casa); // atribui casa na variavel novo

        if (inicio == null){ // verifica de o inicio é nulo
            inicio = novo; // se sim seta novo como inicio
            inicio.proximo = inicio; // e define o proprio inicio como a proxima casa
        }else{
            // se o inicio não for nulo
            NoCasa aux = inicio; // seta uma casa auxiliar sendo o inicio
            while(aux.proximo != inicio){ // percorre a lista até a proxima casa da variavel auxiliar ser diferente de inicio
                aux = aux.proximo; // seta a variavel auxiliar como sendo a proxima dela mesma
            }
            aux.proximo = novo; // define a proxima casa depois da auxiliar sendo a nova casa
            novo.proximo = inicio; // e a proxima casa da casa novo sendo o inicio
            // MUITO CONFUSO KK
        }
    }

    public NoCasa getInicio(){
        return inicio;
    } // retorna o inicio


}
