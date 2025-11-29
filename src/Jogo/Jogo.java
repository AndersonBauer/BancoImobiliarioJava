package Jogo;

import Casas.CasaImovel;
import Gerenciadores.GerenciadorJogadores;
import Gerenciadores.GerenciadorImoveis;
import Jogadores.Jogador;
import Tabuleiro.TabuleiroLista;

// classe jogo
public class Jogo {

    // atributos da classe jogo
    private GerenciadorJogadores gerenciadorJogadores;
    private GerenciadorImoveis gerenciadorImoveis;
    private TabuleiroLista tabuleiro;

    private int saldoinicial = 20000;
    private int salarioPorVolta = 2000;
    private int maxRodadas = 20;

    // construtor da classe
    public Jogo(GerenciadorJogadores gerJog, GerenciadorImoveis gerImo, TabuleiroLista tabuleiro) {
        this.gerenciadorJogadores = gerJog;
        this.gerenciadorImoveis = gerImo;
        this.tabuleiro = tabuleiro;
    }

    public void setTabuleiro(TabuleiroLista tabuleiro){
        this.tabuleiro = tabuleiro;
    } // define o tabuleiro

    public TabuleiroLista getTabuleiro(){
        return tabuleiro;
    } // retorna o tabuleiro setado para a classe

    // função que seta o saldo inicial
    public void setSaldoinicial(int saldoinicial) {
        this.saldoinicial = saldoinicial;
        System.out.println("Saldo inicial definido para R$:" + saldoinicial);
    }

    public int getSaldoinicial() {
        return saldoinicial;
    } // retorna o saldo inicial atual

    public void setSalarioPorVolta(int salarioPorVolta){ // função que seta o salario por volta
        this.salarioPorVolta = salarioPorVolta;
        System.out.println("Salario por volta definido para R$:" + salarioPorVolta);
    }

    public int getSalarioPorVolta(){
        return salarioPorVolta;
    } // retorna o salario por volta atual

    public void setMaxRodadas(int maxRodadas){ // função que seta o maximo de rodadas
        this.maxRodadas = maxRodadas;
        System.out.println("Maximo de rodadas definido para " + maxRodadas);
    }

    public int getMaxRodadas(){
        return maxRodadas;
    } // retorna a quantidade maxima de rodadas

    // função que verifica se podemos começar o jogo
    public boolean validarInicio(){
        if (gerenciadorImoveis.quantidade() < 10){ // verifica se a lista de imoveis é maior que o minimo
            System.out.println("ERRO: O jogo não pode ser iniciado!");
            System.out.println("Motivo: Minimo de 10 imoveis não alcançado(Atualmente:"+ gerenciadorImoveis.quantidade() +").");
            return false;
        }

        if (gerenciadorJogadores.quantidade() < 2){ // veridica se a lista de jogadores é maior que o minimo
            System.out.println("ERRO: O jogo não pode ser iniciado!");
            System.out.println("Motivo: Minimo de jogadores não alcançado (Atualmente: " + gerenciadorJogadores.quantidade() + ").");
            return false;
        }
        System.out.println("Tudo pronto. Iniciando jogo ...");
        return true;
    }

    // função que paga alugel
    public void pagarAluguel(Jogador jogador, Jogador dono, int valor){
        System.out.println("\n" + jogador.getNome() + " deve pagar R$" + valor + " para " + dono.getNome());

        if (jogador.getDinheiro() >= valor){ // veridica se o dinheiro do jogador que caiu na casa é maior que o valor do aluguel
            jogador.removerDinheiro(valor); // remove o valor do aluguel do dinheiro do jogador que caiu na casa
            dono.adcicionarDinheiro(valor); // adiciona o mesmo valor na conta do dono do imovel
            System.out.println(jogador.getNome() + " pagou r$ " + valor + " para " + dono.getNome());
        }else{
            System.out.println(jogador.getNome() + " não tem dinheiro suficiente para pagar o aluguel");
            // falencia
            gerenciadorJogadores.declaraFalencia(jogador); // se o jogador que caiu na casa não tiver dinheiro pra pagar ele vai a falencia
        }
    }

    // verifica se o fim do jogo chegou
    public boolean verificarFimDeJogo(int rodadaAtual){
        // verifica se está dentro do limite de rodadas
        if (rodadaAtual >= maxRodadas){
            System.out.println("\nFim de jogo! Todas as rodadas foram concluidas");
            declararVencedorPorPatrimonio(); // declara o vencedor sendo o jogador com maior patrimonio
            return true;
        }
        int jogadoresAtivos = 0; // armazena quantos jogadores ainda tem em jogo
        Jogador ultimoJogadorAtivo = null; // armazena nenhum como sendo o ultimo
        for (Jogador j : gerenciadorJogadores.getLista()){ // percorre a lista de jogadores
            if (j.getDinheiro() > 0){ // verifica de o dinheiro de todos os jogadores é maior que 0
                jogadoresAtivos++; // soma na quantidade de jogadores ativos
                ultimoJogadorAtivo = j; // define como o ultimo jogador ativo
            }
        }

        // se a quantidade de jogadores ativos for menor ou igual a 1 significa que 1 jogador ainda está ativo e é o ultimo
        if (jogadoresAtivos <= 1){
            System.out.println("\nFim de jogo! Apenas um jogador não faliu!");
            if (ultimoJogadorAtivo != null){  // se o ultimo jogador ativo não for nulo
                System.out.println("O vencedor é: " + ultimoJogadorAtivo.getNome());
            }
            return true;
        }
        return false;
    }

    // função que declara vencedor por maior patrimonio
    public void declararVencedorPorPatrimonio(){
        if(gerenciadorJogadores.getLista().isEmpty()){ // verifica se a lista está vazia
            System.out.println("Não há jogadores para declarar vencedor");
            return;
        }

        Jogador vencedor = null; // define vencedor como nulo
        int maiorPatrimonio = 0; // define maiorPatrimonio como 0

        // percorre a lista de jogadores
        for (Jogador j : gerenciadorJogadores.getLista()){
            int patrimonio = j.calcularPatrimonio(); // adiciona o total do patrimonio na variavel pratrimonio
            if(vencedor == null || patrimonio > maiorPatrimonio){ // verifica se ainda não foi selecionado o vencedor e se o patrimonio é menor que o maior
                vencedor = j; // define o vencedor
                maiorPatrimonio = patrimonio; // define o maior patrimonio
            }
        }
        if(vencedor != null){ // quando o vencedor deixar de ser nulo ele declara o vencedor
            System.out.println("\nO vencedor é: " + vencedor.getNome() + " com o patrimonio de R$" + maiorPatrimonio + "!");
        }
    }
}