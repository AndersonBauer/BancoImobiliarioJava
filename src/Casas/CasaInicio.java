package Casas;
 import Jogadores.Jogador;
 import Jogo.Jogo;

 import java.util.Scanner;

public class CasaInicio extends Casa {
    // construtor da casa inicio que apenas define o nome da casa como Inicio
    public CasaInicio() {
        super("Inicio");
    }

    // reescrevendo o funcao acao da classe Casa
    @Override
    public void acao(Jogador jogador, Jogo jogo, Scanner sc) {
        //
    }
}
