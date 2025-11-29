package Jogo;

import java.util.Random;

// classe dado
public class Dado {
    private Random random = new Random(); // instanciamos a classe random para poder sortear o valor dos dados

    // a função rolar
    // como a ele gera apenas numeros de 0 a 5 nesse caso nós somamos 1 ao resultado
    // assim os numeros só podem ser de 1 a 6
    // tal qual um dado real e retorna o valor
    public int rolar(){
        return random.nextInt(6) + 1;
    }
}
