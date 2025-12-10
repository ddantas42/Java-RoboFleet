/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.java.robofleet.Gestao;
import java.util.Scanner;
import java.util.ArrayList;
// importo so a classe robot
import com.mycompany.java.robofleet.Robot.*;

/**
 *
 * @author migue
 */
public class Menu {
    private Scanner sc;
    private ArrayList<Robot> frota;

    // construtor
    public Menu(Scanner sc){
        this.sc = sc;
        this.frota = new ArrayList<>();
    }

    public void menuInicialString(){
        int opcao;

        do {
            System.out.println("\nMenu Gestao\n");
            System.out.println("(1) Gerir tecnicos"); // criar, editar, remover, listar, associar/desassociar
            System.out.println("(2) Gerir Robots"); // criar, editar, remover, listar
            System.out.println("(3) Gerir zonas");
            System.out.println("(4) Gerir complexo");
            System.out.println("(5) Utilizar radar");
            System.out.println("(6) Exportar dados");
            System.out.println("(0) Sair");
            System.out.println("Opcao: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // consume \n

            switch(opcao) {
                case 1: menuTecnicos(); break;
                case 2: menuRobots(); break;
                case 3: menuComplexo(); break;
                case 4: menuRadar(); break;
                case 5: exportarDados(); break;
                case 0: System.out.println("A sair..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while(opcao != 0);
    }

    private void menuTecnicos() {
        int opcao;
        do {
            System.out.println("\nGestao de Tecnicos\n");
            System.out.println("(1) Criar tecnico");
            System.out.println("(2) Editar tecnico");
            System.out.println("(3) Remover tecnico");
            System.out.println("(4) Listar tecnicos");
            System.out.println("(5) Associar/desassociar tecnico a robot");
            System.out.println("(0) Voltar ao menu principal");
            System.out.print("Opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1: criarTecnico(); break;
                case 2: editarTecnico(); break;
                case 3: removerTecnico(); break;
                case 4: listarTecnicos(); break;
                case 5: associarTecnicoRobot(); break;
                case 0: System.out.println("A regressar ao menu principal..."); break;
                default: System.out.println("Opcao invalida!");
            }
        }while(opcao != 0);
    }

    private void menuRobots() {
        int opcao;
        do {
            System.out.println("\nGestao de Robots\n");
            System.out.println("(1) Criar robot");
            System.out.println("(2) Editar robot");
            System.out.println("(3) Remover robot");
            System.out.println("(4) Listar robots");
            System.out.println("(0) Voltar ao menu principal");
            System.out.print("Opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1: criarRobot(); break;
                case 2: editarRobot(); break;
                case 3: removerRobot(); break;
                case 4: listarRobots(); break;
                case 0: System.out.println("A regressar ao menu principal."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while(opcao != 0);
    }


    // Submenu Complexo
    private void menuComplexo() {
        System.out.println("\nPor implementar\n");
    }

    // Submenu Radar
    private void menuRadar() {
        System.out.println("\nPor implementar\n");
    }

    // Submenu Exportar Dados
    private void exportarDados() {
        System.out.println("\nPor implementar\n");
    }

    // Métodos de ações - Tecnicos
    private void criarTecnico() {
        System.out.println("Criar tecnico..."); 
    }
    private void editarTecnico() { 
        System.out.println("Editar tecnico..."); 
    }
    private void removerTecnico() { 
        System.out.println("Remover tecnico..."); 
    }
    private void listarTecnicos() { 
        System.out.println("Listar tecnicos..."); 
    }
    private void associarTecnicoRobot() { 
        System.out.println("Associar/desassociar tecnico a robot..."); 
    }

    // Métodos de ações - Robots
    private void criarRobot() { 
        System.out.println("Indique o nome do Robot: ");
        String nome = sc.nextLine();

        System.out.println("Indique a marca: ");
        String marca = sc.nextLine();

        System.out.println("Indique o modelo: ");
        String modelo = sc.nextLine();

        System.out.println("Indique o ano de fabrico: ");
        int ano;
        try {
            ano = sc.nextInt();
        } catch (java.util.InputMismatchException e){
            System.out.println("Ano de fabrico invalido. Acao cancelada");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        // dados rcarry
        System.out.println("Capacidade de carga (em kg): ");
        double capacidadeCarga = sc.nextDouble();
        sc.nextLine();

        System.out.println("Inclui holofote? (s/n): ");
        boolean temHolofote = sc.nextLine().equalsIgnoreCase("s");

        // criar bateria
        Bateria bateria = new Bateria(200, 12);
        System.out.println("Bateria padrao (200Ah, 12h) criada.");

        // selcionar zona
        System.out.println("\nSelecionar zona de operacao:");
        Zona[] zonasDisponiveis = Zona.values();
        for(int i = 0; i < zonasDisponiveis.length; i++){
            System.out.println("(" + (i + 1) + ")" + zonasDisponiveis[i]);
        }

        System.out.println("Opcao de zona (numero):");
        int indiceZona = sc.nextInt() - 1;
        sc.nextLine();

        Zona zonaSelecionada;
        if(indiceZona >= 0 && indiceZona < zonasDisponiveis.length){
            zonaSelecionada = zonasDisponiveis[indiceZona];
        } else {
            System.out.println("Selecao de zona invalida. A atribuir armazem por defeito.");
            zonaSelecionada = Zona.ARMAZEM;
        }

        // criar e adicionar rcarry 
        try {
            RCarry novoRobot = new RCarry(nome, marca, modelo, ano, zonaSelecionada, bateria, capacidadeCarga, temHolofote);
            this.frota.add(novoRobot);
            
            System.out.println("\nRCarry " + nome + " (ID: " + novoRobot.getId() + ") criado e adicionado à frota!");
        } catch (Exception e) {
            System.out.println("Erro fatal ao criar RCarry: " + e.getMessage());
        }
    }
        
    private void editarRobot() { 
        System.out.println("Editar robot..."); 
    }
    private void removerRobot() { 
        System.out.println("Remover robot..."); 
    }
    private void listarRobots() { 
        System.out.println("Listar robots..."); 
    }

}

