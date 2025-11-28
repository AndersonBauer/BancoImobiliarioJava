package Tabuleiro;

import Casas.Casa;

public class NoCasa {
    public Casa casa;
    public NoCasa proximo;

    public NoCasa(Casa casa){
        this.casa = casa;
        this.proximo = null;
    }

    public Casa getCasa(){
        return casa;
    }
}
