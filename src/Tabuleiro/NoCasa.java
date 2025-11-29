package Tabuleiro;

import Casas.Casa;

// classe do nó da lista ligada (tabuleiroLista)
public class NoCasa {
    // atributos da classe
    public Casa casa;
    public NoCasa proximo;

    // construtor da classe
    public NoCasa(Casa casa){
        this.casa = casa;
        this.proximo = null;
    }

    public Casa getCasa(){
        return casa;
    } // retorna a casa do nó
}
