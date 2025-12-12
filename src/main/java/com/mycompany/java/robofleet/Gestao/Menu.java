package com.mycompany.java.robofleet.Gestao;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;

public class Menu {
    private Scanner sc;
    private CentroDeComando centro;

    // construtor
    public Menu(Scanner sc){
        this.sc = sc;
        this.centro = new CentroDeComando();
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


    public void menuInicialString(){
        int opcao;

        do {
            System.out.println("\nMenu Gestao\n");
            System.out.println("(1) Gerir tecnicos"); // criar, editar, remover, listar, associar/desassociar
            System.out.println("(2) Gerir Robots"); // criar, editar, remover, listar
            System.out.println("(3) Gerir complexo");
            System.out.println("(4) Utilizar radar");
            System.out.println("(5) Exportar dados");
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

        System.out.println("Indique a capacidade da bateria: ");
        int cap;
        try{
            cap = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Capacidade para a bateria invalido. Acao cancelada");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        System.out.println("Indique a potência do motor (W): "); // Pedir 1 motor inicial
        int potenciaMotor;
        try{
            potenciaMotor = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Potência do motor invalida. Acao cancelada");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        // criamos a bateria com a capacidade definida pelo utilizador e a autonomia é depois calculada
        Bateria bateria = new Bateria(cap, 100);

        System.out.println("Indique a zona de operacao:");
        Zona[] zonasDisponiveis = Zona.values();
        for(int i = 0; i < zonasDisponiveis.length; i++){
            System.out.println("(" + (i + 1) + ") " + zonasDisponiveis[i]);
        }
        System.out.println("Opcao de zona: ");
        int indiceZona = sc.nextInt() - 1;
        sc.nextLine();

        Zona zonaSelecionada = Zona.ARMAZEM;
        if(indiceZona >= 0 && indiceZona < zonasDisponiveis.length){
            zonaSelecionada = zonasDisponiveis[indiceZona];
        } else {
            System.out.println("Zona invalida. A usar ARMAZEM por defeito.");
        }

        // menu tipo de robot
        System.out.println("\nQual o tipo de Robot?");
        System.out.println("(1) R-Carry (Transporte)");
        System.out.println("(2) R-Clean (Limpeza)");
        System.out.println("(3) R-Factory (Producao)");
        System.out.println("(4) R-Inspect (Inspecao)");
        System.out.print("Opcao: ");
        
        int tipoRobot = sc.nextInt();
        sc.nextLine(); 

        Robot novoRobot = null;

        try {
            switch(tipoRobot){
                case 1:
                    System.out.println("Capacidade carga (kg): ");
                    double carga = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Tem holofote (s/n): ");
                    boolean holofote = sc.nextLine().equalsIgnoreCase("s");

                    novoRobot = new RCarry(nome, marca, modelo, ano, zonaSelecionada, bateria, carga, holofote);
                    break;
                case 2:
                    System.out.println("Modo de limpeza (ex: aspirar, secar): ");
                    String modo = sc.nextLine();
                    System.out.println("Tem luz auxiliar? (s/n): ");
                    boolean luz = sc.nextLine().equalsIgnoreCase("s");

                    novoRobot = new RClean(nome, marca, modelo, ano, zonaSelecionada, bateria, modo, luz);
                    break;
                case 3:
                    System.out.println("Numero de bracos roboticos: ");
                    int bracos = sc.nextInt();
                    sc.nextLine();

                    novoRobot = new RFactory(nome, marca, modelo, ano, zonaSelecionada, bateria, bracos);
                    break;

                case 4:
                    novoRobot = new RInspect(nome, marca, modelo, ano, zonaSelecionada, bateria);
                    break;
                default:
                    System.out.println("Tipo de robot  inválido.");
                    return;
            }

            if (novoRobot != null){
                Motor motorInicial = new Motor(potenciaMotor);

                if (novoRobot instanceof RCarry){
                    ((RCarry) novoRobot).adicionarMotor(motorInicial);
                }
                } else if (novoRobot instanceof RClean) {
                    ((RClean) novoRobot).adicionarMotor(motorInicial);
                } else if (novoRobot instanceof RFactory) {
                    ((RFactory) novoRobot).adicionarMotor(motorInicial);
                } else if (novoRobot instanceof RInspect) {
                    ((RInspect) novoRobot).adicionarMotor(motorInicial);
                }

                try {
                    centro.adicionarRobot(novoRobot);
                    System.out.println("Robot criado.");
                } catch (IllegalArgumentException e){
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao criar robot" + e.getMessage());

        } catch(Exception e){
            System.out.println("\nErro inesperado: " + e.getMessage());
        }
    }
        
    // edita um robot
    private void editarRobot() { 
        listarRobots();

        if(centro.getFrota().isEmpty()) return;

        System.out.println("ID do robot a editar: ");
        int id;
        try {
            id = sc.nextInt();

        } catch(java.util.InputMismatchException e){
            System.out.println("Entrada invalida. Acao cancelada.");
            return;
        }
        sc.nextLine();
        
        Robot r = centro.buscarRobot(id);

        if(r == null){
            System.out.println("Robot nao encontrado.");
            return;
        }

        // logica edicao
        int op;
        do {
            System.out.println("\n--- Editar: " + r.getNome() + " (ID: " + r.getId() + ") ---");
            System.out.println("(1) Editar Nome");
            System.out.println("(2) Editar Marca");
            System.out.println("(3) Editar Modelo");
            System.out.println("(4) Editar Zona");
            System.out.println("(0) Concluir edicao");
            System.out.print("Opcao: ");

            op = sc.nextInt();
            sc.nextLine();

            try {

                switch (op){

                    case 1: 
                        System.out.println("Novo nome (Atual: " + r.getNome() + "): ");
                        String novoNome = sc.nextLine();
                        
                        // nome deve ser unico
                        if(!r.getNome().equalsIgnoreCase(novoNome) && !centro.isNomeUnico(novoNome)){
                            throw new IllegalArgumentException("Nome " + novoNome + " ja esta a ser utilizado.");
                        }

                        r.setNome(novoNome);
                        System.out.println("Nome alterado com sucesso.");
                        break;

                    case 2:
                        System.out.println("Editar marca (Atual: " + r.getMarca() + "): ");
                        String novaMarca = sc.nextLine();

                        r.setMarca(novaMarca);
                        System.out.println("Marca alterada!");
                        break;

                    case 3:
                        System.out.println("Editar modelo (Atual: " + r.getModelo() + "): ");
                        String novoModelo = sc.nextLine();

                        r.setModelo(novoModelo);
                        System.out.println("Modelo alterado!");
                        break;

                    case 4:
                        System.out.println("Editar zona (Atual: " + r.getZona() + "): ");
                        Zona[] zonasDisponiveis = Zona.values();
                        for(int i = 0; i < zonasDisponiveis.length; i++){
                            System.out.println("(" + (i + 1) + ") " + zonasDisponiveis[i]);
                        }
                        System.out.println("Opcao de zona: ");
                        int indiceZona = sc.nextInt() - 1;
                        sc.nextLine();

                        Zona zonaSelecionada = Zona.ARMAZEM;
                        if(indiceZona >= 0 && indiceZona < zonasDisponiveis.length){
                            zonaSelecionada = zonasDisponiveis[indiceZona];
                        } else {
                            System.out.println("Zona invalida. A usar ARMAZEM por defeito.");
                        }
                        r.setZona(zonaSelecionada);
                        System.out.println("Zona alterada com sucesso.");
                        break;

                    case 0:
                        System.out.println();
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                }

            }catch (IllegalArgumentException e){
                System.out.println("Erro de edicao: " + e.getMessage());

            } catch (java.util.InputMismatchException e){
                System.out.println("Erro: entrada numerica invalida. Tente novamente");
                sc.nextLine();
            }

        }while(op != 0);
    }

    // remove um robot da frota
    private void removerRobot() { 
        listarRobots();
        System.out.println("ID do robot a remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        if (centro.removerRobot(id)){
            System.out.println("Robot removido com sucesso.");
        } else {
            System.out.println("Erro: Robot nao encontrado.");
        }
    }

    // mostra a lista de robots
    private void listarRobots() { 
        List<Robot> lista = centro.getFrota();

        if(lista.isEmpty()){
            System.out.println("Nenhum robot registado.");
            return;
        }

        System.out.println("\nFrota de robots: ");
        for(int i = 0; i < lista.size(); i++){
            Robot r = lista.get(i);

            System.out.println("(" + (i + 1) + ") " + r.toString());

        } 
    }

}

