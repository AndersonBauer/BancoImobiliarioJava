package Jogo;

import Casas.CasaImovel;
import Gerenciadores.GerenciadorJogadores;
import Gerenciadores.GerenciadorImoveis;
import Jogadores.Jogador;
import Tabuleiro.TabuleiroLista;
public class Jogo {
    private GerenciadorJogadores gerenciadorJogadores;
    private GerenciadorImoveis gerenciadorImoveis;
    private TabuleiroLista tabuleiro;

    private int saldoinicial = 20000;
    private int salarioPorVolta = 2000;
    private int maxRodadas = 20;

    public Jogo(GerenciadorJogadores gerJog, GerenciadorImoveis gerImo, TabuleiroLista tabuleiro) {
        this.gerenciadorJogadores = gerJog;
        this.gerenciadorImoveis = gerImo;
        this.tabuleiro = tabuleiro;
    }

    public void setTabuleiro(TabuleiroLista tabuleiro){
        this.tabuleiro = tabuleiro;
    }

    public TabuleiroLista getTabuleiro(){
        return tabuleiro;
    }

    public void setSaldoinicial(int saldoinicial) {
        this.saldoinicial = saldoinicial;
        System.out.println("Saldo inicial definido para R$:" + saldoinicial);
    }

    public int getSaldoinicial() {
        return saldoinicial;
    }

    public void setSalarioPorVolta(int salarioPorVolta){
        this.salarioPorVolta = salarioPorVolta;
        System.out.println("Salario por volta definido para R$:" + salarioPorVolta);
    }

    public int getSalarioPorVolta(){
        return salarioPorVolta;
    }

    public void setMaxRodadas(int maxRodadas){
        this.maxRodadas = maxRodadas;
        System.out.println("Maximo de rodadas definido para " + maxRodadas);
    }

    public int getMaxRodadas(){
        return maxRodadas;
    }

    public boolean validarInicio(){
        if (gerenciadorImoveis.quantidade() < 10){
            System.out.println("ERRO: O jogo não pode ser iniciado!");
            System.out.println("Motivo: Minimo de 10 imoveis não alcançado(Atualmente:"+ gerenciadorImoveis.quantidade() +").");
            return false;
        }

        if (gerenciadorJogadores.quantidade() < 2){
            System.out.println("ERRO: O jogo não pode ser iniciado!");
            System.out.println("Motivo: Minimo de jogadores não alcançado (Atualmente: " + gerenciadorJogadores.quantidade() + ").");
            return false;
        }
        System.out.println("Tudo pronto. Iniciando jogo ...");
        return true;
    }

    public void tentarComprarImovel(Jogador jogador, CasaImovel imovel){
        System.out.println("\n" + jogador.getNome() + " caiu em " + imovel.getNome());

        if (jogador.getDinheiro() >= imovel.getPreco()){
            jogador.removerDinheiro(imovel.getPreco());
            jogador.adicionarImovel(imovel);
            imovel.setDono(jogador);

            System.out.println(jogador.getNome() + " comprou o imovel " + imovel.getNome() + " por R$ " + imovel.getPreco() + ".");
        }else {
            System.out.println(jogador.getNome() + " não tem dinheiro o suficiente.");
        }
    }

    public void pagarAluguel(Jogador jogador, Jogador dono, int valor){
        System.out.println("\n" + jogador.getNome() + " deve pagar R$" + valor + " para " + dono.getNome());

        if (jogador.getDinheiro() >= valor){
            jogador.removerDinheiro(valor);
            dono.adcicionarDinheiro(valor);
            System.out.println(jogador.getNome() + " pagou r$ " + valor + " para " + dono.getNome());
        }else{
            System.out.println(jogador.getNome() + " não tem dinheiro suficiente para pagar o aluguel");
            // falencia
            gerenciadorJogadores.declaraFalencia(jogador);
        }
    }

    public boolean verificarFimDeJogo(int rodadaAtual){
        if (rodadaAtual >= maxRodadas){
            System.out.println("\nFim de jogo! Todas as rodadas foram concluidas");
            declararVencedorPorPatrimonio();
            return true;
        }
        int jogadoresAtivos = 0;
        Jogador ultimoJogadorAtivo = null;
        for (Jogador j : gerenciadorJogadores.getLista()){
            if (j.getDinheiro() > 0){
                jogadoresAtivos++;
                ultimoJogadorAtivo = j;
            }
        }

        if (jogadoresAtivos <= 1){
            System.out.println("\nFim de jogo! Apenas um jogador não faliu!");
            if (ultimoJogadorAtivo != null){
                System.out.println("O vencedor é: " + ultimoJogadorAtivo.getNome());
            }
            return true;
        }
        return false;
    }

    public void declararVencedorPorPatrimonio(){
        if(gerenciadorJogadores.getLista().isEmpty()){
            System.out.println("Não há jogadores para declarar vencedor");
            return;
        }

        Jogador vencedor = null;
        int maiorPatrimonio = 0;

        for (Jogador j : gerenciadorJogadores.getLista()){
            int patrimonio = j.calcularPatrimonio();
            if(vencedor == null || patrimonio > maiorPatrimonio){
                vencedor = j;
                maiorPatrimonio = patrimonio;
            }
        }
        if(vencedor != null){
            System.out.println("\nO vencedor é: " + vencedor.getNome() + " com o patrimonio de R$" + maiorPatrimonio + "!");
        }
    }
}