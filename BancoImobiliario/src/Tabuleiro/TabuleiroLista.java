package Tabuleiro;
import Casas.Casa;
import Jogo.Jogo;

import java.util.ArrayList;

public class TabuleiroLista {

    private NoCasa inicio;
    private Jogo jogo;

    public TabuleiroLista(Jogo jogo){
        this.jogo = jogo;
    }

    public Jogo getJogo(){
        return jogo;
    }

    public void adicionarCasa(Casa casa){
        NoCasa novo = new NoCasa(casa);

        if (inicio == null){
            inicio = novo;
            inicio.proximo = inicio;
        }else{
            NoCasa aux = inicio;
            while(aux.proximo != inicio){
                aux = aux.proximo;
            }
            aux.proximo = novo;
            novo.proximo = inicio;
        }
    }

    public NoCasa getInicio(){
        return inicio;
    }


}
