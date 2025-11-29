import Casas.*;
import Gerenciadores.GerenciadorImoveis;
import Gerenciadores.GerenciadorJogadores;
import Jogadores.Jogador;
import Tabuleiro.TabuleiroLista;
import Tabuleiro.NoCasa;
import Jogo.Jogo;
import Jogo.Dado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<CasaImovel> listaCasasUsuario = new ArrayList<>();


        // Criar gerenciadores
        GerenciadorJogadores gerJog = new GerenciadorJogadores();
        GerenciadorImoveis gerImo = new GerenciadorImoveis();

        // Criar jogo SEM tabuleiro ainda
        Jogo jogo = new Jogo(gerJog, gerImo, null);

        // Criar tabuleiro com referência ao jogo
        TabuleiroLista tabuleiro = new TabuleiroLista(jogo);

        // Setar tabuleiro no jogo
        jogo.setTabuleiro(tabuleiro);

        // Criar CasaInicio obrigatória
        CasaInicio inicio = new CasaInicio();
        tabuleiro.adicionarCasa(inicio);

        // Criar 10 casas-imóveis de teste
        for (int i = 1; i <= 10; i++) {
            String nome = "Casa " + i;
            int preco = 1000 + i * 5000;
            int aluguel = 10 + i * 100;
            if (i % 6 == 0){
                CasaImposto casaImposto = new CasaImposto();
                tabuleiro.adicionarCasa(casaImposto);
            }

            if (i % 8 == 0){
                CasaRestituicao casaRestituicao = new CasaRestituicao();
                tabuleiro.adicionarCasa(casaRestituicao);
            }

            CasaImovel casa = new CasaImovel(nome, preco, aluguel);
            gerImo.adicionarImovel(casa);
            tabuleiro.adicionarCasa(casa);
        }

        System.out.println("=========================================");
        System.out.println("=== SIMULADOR DE JOGO DE TABULEIRO ===");
        System.out.println("=========================================");
        System.out.println("Seja bem-vindo! Vamos configurar a partida.");

        boolean sair = false;
        while (!sair) {
            jogo.menuConfig();
            int opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    boolean voltarJog = false;
                    while (!voltarJog) {

                        int op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                System.out.print("Digite o nome do novo jogador: ");
                                String nome = sc.nextLine();
                                gerJog.adicionarJogador(nome, jogo.getSaldoinicial(), tabuleiro);
                                break;

                            case 2:
                                gerJog.listarJogadores();
                                break;

                            case 3:
                                System.out.print("Digite o nome do jogador a remover: ");
                                String rNome = sc.nextLine();
                                gerJog.removerJogador(rNome);
                                break;

                            case 4:
                                voltarJog = true;
                                break;
                        }
                    }
                    break;

                case 2:
                    boolean voltarImo = false;
                    while (!voltarImo) {
                        System.out.println("\n--- Menu de Imóveis ---");
                        System.out.println("Atualmente: " + gerImo.quantidade() + "/40 imóveis cadastrados");
                        System.out.println("1. Cadastrar Novo Imóvel");
                        System.out.println("2. Listar Imóveis Cadastrados");
                        System.out.println("3. Remover Imóvel");
                        System.out.println("4. Voltar ao Menu Principal");
                        System.out.print(">> Escolha uma opção: ");
                        int op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                System.out.print("Digite o nome do imóvel: ");
                                String nImo = sc.nextLine();
                                System.out.print("Digite o preço do imóvel: ");
                                int preco = sc.nextInt();
                                System.out.print("Digite o valor do aluguel: ");
                                int aluguel = sc.nextInt();
                                sc.nextLine();

                                CasaImovel ci = new CasaImovel(nImo, preco, aluguel);
                                gerImo.adicionarImovel(ci);
                                listaCasasUsuario.add(ci); // salva na lista, sem adicionar no tabuleiro ainda
                                System.out.println("Imóvel salvo para carregamento no tabuleiro.");
                                break;

                            case 2:
                                gerImo.listarImoveis();
                                break;

                            case 3:
                                System.out.print("Digite o nome do imóvel a remover: ");
                                String rImo = sc.nextLine();
                                gerImo.removerImovel(rImo);
                                break;

                            case 4:
                                voltarImo = true;
                                break;
                        }
                    }
                    break;

                case 3:
                    boolean voltarConf = false;
                    while (!voltarConf) {
                        System.out.println("\n--- Configurações da Partida ---");
                        System.out.println("1. Definir Saldo Inicial (Atual: R$" + jogo.getSaldoinicial() + ")");
                        System.out.println("2. Definir Salário por volta (Atual: R$" + jogo.getSalarioPorVolta() + ")");
                        System.out.println("3. Definir Nº Máximo de Rodadas (Atual: " + jogo.getMaxRodadas() + ")");
                        System.out.println("4. Voltar ao Menu Principal");
                        System.out.print(">> Escolha uma opção: ");
                        int op = sc.nextInt();
                        sc.nextLine();

                        switch (op) {
                            case 1:
                                System.out.print("Digite o novo saldo inicial: ");
                                jogo.setSaldoinicial(sc.nextInt());
                                sc.nextLine();
                                break;

                            case 2:
                                System.out.print("Digite o novo salário por volta: ");
                                jogo.setSalarioPorVolta(sc.nextInt());
                                sc.nextLine();
                                break;

                            case 3:
                                System.out.print("Digite o máximo de rodadas: ");
                                jogo.setMaxRodadas(sc.nextInt());
                                sc.nextLine();
                                break;

                            case 4:
                                voltarConf = true;
                                break;
                        }
                    }
                    break;

                case 4:
                    if (!jogo.validarInicio()) {
                        System.out.println("Pressione Enter para voltar...");
                        sc.nextLine();
                        break;
                    }

                    // Carregar imóveis do usuário no tabuleiro ANTES de começar
                    for (CasaImovel casa : listaCasasUsuario) {
                        tabuleiro.adicionarCasa(casa);
                    }

                    System.out.println("Casas criadas pelo usuário foram carregadas no tabuleiro!");

                    // Começar o jogo
                    int rodada = 1;
                    Dado dado = new Dado();

                    while (true) {
                        for (Jogador j : gerJog.getLista()) {
                            if (j.getDinheiro() <= 0) continue;

                            // Proteção caso posição ainda seja nula
                            if (j.getPosicaoAtual() == null) {
                                j.setPosicaoInicial(tabuleiro.getInicio());
                            }

                            System.out.println("\n=== RODADA " + rodada + " / " + jogo.getMaxRodadas() + " - VEZ DE: " + j.getNome() + " ===");
                            System.out.println("Saldo: R$" + j.getDinheiro());
                            System.out.println("Posição: " + j.getPosicaoAtual().casa.getNome());

                            System.out.println("1. Lançar Dados e Mover");
                            System.out.println("2. Ver Meu Status Completo");
                            System.out.println("0. Desistir do Jogo");
                            System.out.print(">> Escolha uma opção: ");
                            int escolha = sc.nextInt();
                            sc.nextLine();

                            if (escolha == 0) {
                                gerJog.declaraFalencia(j);
                                continue;

                            } else if (escolha == 1) {
                                int d1 = dado.rolar();
                                int d2 = dado.rolar();
                                int total = d1 + d2;
                                System.out.println("Você tirou " + d1 + " e " + d2 + ". Total: " + total);
                                j.mover(total, sc);

                            } else if (escolha == 2) {
                                System.out.println("Saldo: R$" + j.getDinheiro());
                                System.out.println("Imóveis: ");
                                for (CasaImovel ci2 : j.getImoveis()) {
                                    System.out.println("- " + ci2.getNome());
                                }
                            }

                            if (jogo.verificarFimDeJogo(rodada)) {
                                jogo.declararVencedorPorPatrimonio();
                                System.exit(0);
                            }
                        }
                        rodada++;
                    }

                case 0:
                    sair = true;
                    break;
            }
        }

        sc.close();
        System.out.println("Programa encerrado!");
    }
}
