package com.mycompany.java.robofleet.Gestao;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;

public class Menu {

    private Scanner sc;
    private CentroDeComando centro;

    public Menu(Scanner sc) {
        this.sc = sc;
        this.centro = new CentroDeComando();
    }

    // ================= MENU PRINCIPAL =================
    public void menuInicialString() {
        int opcao;

        do {
            System.out.println("\n=== ROBOFLEET: Menu Principal ===");
            System.out.println("(1) Gerir Técnicos");
            System.out.println("(2) Gerir Robots");
            System.out.println("(3) Ativar Robot (Enviar Ordem)"); // Requisito 3.3 e 3.5 [cite: 66, 81]
            System.out.println("(4) Utilizar Radar");            // Requisito 2.3 e 3.3 [cite: 27, 68]
            System.out.println("(5) Exportar Dados (TXT)");      // Requisito 3.4 [cite: 73]
            System.out.println("(6) Recuperar Dados (RECOVER)");  // Requisito 3.5 [cite: 86]
            System.out.println("(0) Sair e Gravar");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> menuTecnicos();
                case 2 -> menuRobots();
                case 3 -> enviarOrdemAtivacao();
                case 4 -> centro.radar();
                case 5 -> centro.exportarRobotsParaTxt("frota_robots.txt");
                case 6 -> recuperarDados();
                case 0 -> {
                    System.out.println("A gravar dados...");
                    GestorDeFicheiros.guardarDadosBinario(this.centro, "dados.dat"); // Requisito 3.4 [cite: 72]
                    System.out.println("A sair...");
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ================= GESTÃO DE TÉCNICOS =================
    private void menuTecnicos() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Técnicos ---");
            System.out.println("(1) Criar técnico | (2) Remover técnico | (3) Listar técnicos");
            System.out.println("(4) Editar técnico | (5) Associar a Robot | (6) Desassociar de Robot | (0) Voltar");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> criarTecnico();
                case 2 -> removerTecnico();
                case 3 -> centro.listarTecnicos(); // Requisito 3.1 [cite: 35, 36]
                case 4 -> editarTecnico();
                case 5 -> associarTecnicoRobot();
                case 6 -> desassociarTecnicoRobot();
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void criarTecnico() {
        try {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("NIF: "); int nif = lerInteiro();
            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(sc.nextLine());
            EspecialidadeTecnico esp = escolherEspecialidade();

            Tecnico t = new Tecnico(nome, nif, data, esp);
            centro.registarTecnico(t); // Valida restrição de idade (30 anos para Robótica) [cite: 34]
            System.out.println("Técnico criado com sucesso.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data inválido.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ================= GESTÃO DE ROBOTS =================
    private void menuRobots() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Robots ---");
            System.out.println("(1) Criar robot | (2) Editar robot | (3) Remover robot | (4) Listar robots | (0) Voltar");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> criarRobot();
                case 2 -> editarRobot();
                case 3 -> removerRobot();
                case 4 -> centro.listRobots(); // Requisito 3.2 [cite: 41]
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void criarRobot() {
        try {
            System.out.print("Nome (único): "); String nome = sc.nextLine();
            System.out.print("Marca: "); String marca = sc.nextLine();
            System.out.print("Modelo: "); String modelo = sc.nextLine();
            System.out.print("Ano Fabrico: "); int ano = lerInteiro();
            System.out.print("Capacidade Bateria (Ah): "); int cap = lerInteiro();
            Bateria bateria = new Bateria(cap, 100);

            System.out.println("Tipo: (1) R-Carry | (2) R-Clean | (3) R-Factory | (4) R-Inspect");
            int tipo = lerInteiro();

            Robot novoRobot = switch (tipo) {
                case 1 -> {
                    System.out.print("Capacidade carga (kg): ");
                    double carga = sc.nextDouble(); sc.nextLine();
                    yield new RCarry(nome, marca, modelo, ano, Zona.ESTACAO_CARGA, bateria, carga, true);
                }
                case 2 -> new RClean(nome, marca, modelo, ano, Zona.ESTACAO_CARGA, bateria, true, true);
                case 3 -> {
                    System.out.print("Braços (2-4): ");
                    int bracos = lerInteiro();
                    yield new RFactory(nome, marca, modelo, ano, Zona.ESTACAO_CARGA, bateria, bracos);
                }
                case 4 -> new RInspect(nome, marca, modelo, ano, Zona.ESTACAO_CARGA, bateria);
                default -> throw new IllegalArgumentException("Tipo inválido.");
            };

            configurarMotores(novoRobot);
            centro.registarRobot(novoRobot); // Atribui ID e valida unicidade de nome [cite: 39, 40]
            System.out.println("Robot criado na Estação de Carga.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ================= OPERAÇÕES DO SISTEMA =================
    private void enviarOrdemAtivacao() {
        System.out.print("ID do Robot para ativação: ");
        int id = lerInteiro();
        try {
            centro.ativarRobot(id); // Valida requisitos de equipa [cite: 42]
        } catch (Exception e) {
            System.out.println("Falha na ativação: " + e.getMessage());
        }
    }

    private void recuperarDados() {
        CentroDeComando recuperado = GestorDeFicheiros.recuperarDadosBinario("dados.dat");
        if (recuperado != null) {
            this.centro = recuperado;
            System.out.println("Função RECOVER concluída com sucesso."); [cite: 86]
        }
    }

    // ================= MÉTODOS AUXILIARES E EDIÇÃO =================
    private int lerInteiro() {
        while (!sc.hasNextInt()) {
            System.out.print("Erro: Insira um número inteiro: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); 
        return val;
    }

    private EspecialidadeTecnico escolherEspecialidade() {
        System.out.println("Especialidade: (1) Robótica | (2) Manutenção | (3) Sistemas");
        int op = lerInteiro();
        return switch (op) {
            case 1 -> EspecialidadeTecnico.ROBOTICA;
            case 2 -> EspecialidadeTecnico.MANUTENCAO;
            default -> EspecialidadeTecnico.SISTEMAS;
        };
    }

    private void configurarMotores(Robot r) {
        int max = (r instanceof RCarry) ? 4 : (r instanceof RInspect ? 1 : 2); // Regras 3.2.1-3.2.4 [cite: 45, 50, 55, 62]
        System.out.printf("Indique o número de motores (Máx %d): ", max);
        int n = lerInteiro();
        if (n > max) n = max;

        for (int i = 0; i < n; i++) {
            System.out.print("Potência Motor " + (i + 1) + " (W): ");
            r.adicionarMotorChild(new Motor(lerInteiro()));
        }
    }

    private void editarTecnico() {
        try {
            System.out.print("ID do técnico: ");
            Tecnico t = centro.getTecnicobyId(lerInteiro());
            System.out.print("Novo nome (Enter p/ manter): ");
            String novoNome = sc.nextLine();
            if (!novoNome.isEmpty()) t.setName(novoNome);
            t.setEspecialidade(escolherEspecialidade());
            System.out.println("Técnico atualizado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void editarRobot() {
        try {
            System.out.print("ID do robot: ");
            Robot r = centro.getRobotbyId(lerInteiro());
            System.out.print("Novo nome (Enter p/ manter): ");
            String novoNome = sc.nextLine();
            if (!novoNome.isEmpty()) r.setNome(novoNome);
            System.out.print("Nova Marca: "); r.setMarca(sc.nextLine());
            System.out.println("Deseja substituir motores? (s/n)");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                r.limparMotores();
                configurarMotores(r);
            }
            System.out.println("Robot atualizado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void removerTecnico() {
        System.out.print("ID do técnico a remover: ");
        if (centro.removerTecnicoPorId(lerInteiro())) System.out.println("Removido.");
        else System.out.println("ID não encontrado.");
    }

    private void removerRobot() {
        System.out.print("ID do robot a remover: ");
        if (centro.removerRobotPorId(lerInteiro())) System.out.println("Removido.");
        else System.out.println("ID não encontrado.");
    }

    private void associarTecnicoRobot() {
        try {
            System.out.print("ID Técnico: "); int idT = lerInteiro();
            System.out.print("ID Robot: "); int idR = lerInteiro();
            centro.associarTecnicoRobot(idT, idR);
            System.out.println("Técnico associado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void desassociarTecnicoRobot() {
        try {
            System.out.print("ID Técnico: "); int idT = lerInteiro();
            System.out.print("ID Robot: "); int idR = lerInteiro();
            centro.desassociarTecnicoRobot(idT, idR);
            System.out.println("Técnico desassociado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}