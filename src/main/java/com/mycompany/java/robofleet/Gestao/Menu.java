package com.mycompany.java.robofleet.Gestao;
import java.util.Scanner;
import java.util.ArrayList;
// importo so a classe robot
import com.mycompany.java.robofleet.Robot.Robot;

public class Menu {
    private Scanner sc;

    // construtor
    public Menu(Scanner sc){
        this.sc = sc;
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
                case 3: menuZonas(); break;
                case 4: menuComplexo(); break;
                case 5: menuRadar(); break;
                case 6: exportarDados(); break;
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

    private void menuZonas(){
        int opcao;
        do {
            System.out.println("\nGestao de Zonas\n");
            System.out.println("(1) Criar zona");
            System.out.println("(2) Editar zona");
            System.out.println("(3) Remover zona");
            System.out.println("(4) Listar zonas");
            System.out.println("(0) Voltar ao menu principal");
            System.out.print("Opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1: criarZona(); break;
                case 2: editarZona(); break;
                case 3: removerZona(); break;
                case 4: listarZonas(); break;
                case 0: System.out.println("A regressar ao menu principal"); break;
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
        System.out.println("\nA criar novo robot.");
        
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

    // Métodos de ações - Zonas
    private void criarZona() { 
        System.out.println("Criar zona..."); 
    }
    private void editarZona() { 
        System.out.println("Editar zona..."); 
    }
    private void removerZona() { 
        System.out.println("Remover zona..."); 
    }
    private void listarZonas() { 
        System.out.println("Listar zonas..."); 
    }
}