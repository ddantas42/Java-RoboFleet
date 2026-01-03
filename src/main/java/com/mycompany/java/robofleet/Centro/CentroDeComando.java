package com.mycompany.java.robofleet.Centro;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import com.mycompany.java.robofleet.Robot.Robot;
import com.mycompany.java.robofleet.Robot.Zona;

/**
 * Classe que gere o Complexo Industrial-Logístico[cite: 11].
 * Controla o registo de técnicos, robots e a emissão de ordens[cite: 14].
 */
public class CentroDeComando implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Tecnico> tecnicos;
    private ArrayList<Robot> robots;
    private int proximoIdTecnico = 1; [cite: 32]
    private int proximoIdRobot = 1;   [cite: 39]
    private int ordensEnviadas = 0;   [cite: 26]

    public CentroDeComando() {
        this.tecnicos = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    // =========================== GESTÃO DE TÉCNICOS ===========================

    /**
     * Regista um novo técnico garantindo as restrições de idade e unicidade[cite: 33, 34].
     */
    public void registarTecnico(Tecnico tecnico) {
        // Regra: Engenheiros de Robótica devem ter pelo menos 30 anos [cite: 34]
        if (tecnico.getEspecialidade() == EspecialidadeTecnico.ROBOTICA && tecnico.getIdade() < 30) {
            throw new IllegalArgumentException("Engenheiros de Robótica devem ter pelo menos 30 anos.");
        }

        // Garantir que não existem duplicados (ID ou Nome/NIF) [cite: 33, 70]
        for (Tecnico t : this.tecnicos) {
            if (t.mesmoTecnico(tecnico)) {
                throw new IllegalArgumentException("Técnico já existente no sistema (NIF ou Nome duplicado).");
            }
        }

        tecnico.setId(proximoIdTecnico++); 
        this.tecnicos.add(tecnico);
    }

    public boolean removerTecnicoPorId(int id) {
        return tecnicos.removeIf(t -> t.getId() == id); [cite: 77]
    }

    /**
     * Lista os técnicos conforme critérios do enunciado[cite: 35, 36].
     */
    public void listarTecnicos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Listagem de Técnicos ---");
        System.out.println("(1) Ordem Alfabética (Nome)");
        System.out.println("(2) Ordem Crescente de ID");
        System.out.println("(3) Ordem Decrescente de Idade (Data Nasc.)");
        System.out.print("Escolha: ");

        int opcao = sc.hasNextInt() ? sc.nextInt() : 1;
        ArrayList<Tecnico> lista = new ArrayList<>(this.tecnicos);

        switch (opcao) {
            case 1 -> lista.sort(Comparator.comparing(Tecnico::getName, String.CASE_INSENSITIVE_ORDER)); [cite: 35]
            case 2 -> lista.sort(Comparator.comparingInt(Tecnico::getId)); [cite: 36]
            case 3 -> lista.sort(Comparator.comparing(Tecnico::getDataNascimento).reversed()); [cite: 36]
        }

        System.out.println("\nID | Nome | Especialidade | Idade");
        lista.forEach(System.out::println);
    }

    // =========================== GESTÃO DE ROBOTS ===========================

    public void registarRobot(Robot robot) {
        if (robot.getNome() == null || robot.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do robô é obrigatório."); [cite: 40]
        }

        for (Robot r : this.robots) {
            if (r.getNome().equalsIgnoreCase(robot.getNome())) {
                throw new IllegalArgumentException("Nome de robô já existe."); [cite: 40, 70]
            }
        }

        robot.setId(proximoIdRobot++); [cite: 39]
        this.robots.add(robot);
    }

    public boolean removerRobotPorId(int id) {
        return robots.removeIf(r -> r.getId() == id); [cite: 78]
    }

    /**
     * Lista robots por ID ou Marca/Ano.
     */
    public void listRobots() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Listagem de Robots ---");
        System.out.println("(1) Ordem Crescente de ID");
        System.out.println("(2) Ordem Crescente de Marca e Decrescente de Ano");
        System.out.print("Escolha: ");

        int opcao = sc.hasNextInt() ? sc.nextInt() : 1;
        ArrayList<Robot> lista = new ArrayList<>(this.robots);

        if (opcao == 2) {
            lista.sort(Comparator.comparing(Robot::getMarca, String.CASE_INSENSITIVE_ORDER)
                                .thenComparing(Comparator.comparingInt(Robot::getAnoFabrico).reversed())); [cite: 41]
        } else {
            lista.sort(Comparator.comparingInt(Robot::getId)); [cite: 41]
        }

        lista.forEach(System.out::println);
    }

    // =========================== OPERAÇÕES E ASSOCIAÇÕES ===========================

    public void associarTecnicoRobot(int idTecnico, int idRobot) {
        Tecnico t = getTecnicobyId(idTecnico);
        Robot r = getRobotbyId(idRobot);
        r.adicionarTecnico(t); [cite: 79]
    }

    public void desassociarTecnicoRobot(int idTecnico, int idRobot) {
        Tecnico t = getTecnicobyId(idTecnico);
        Robot r = getRobotbyId(idRobot);
        r.removerTecnico(t); [cite: 79]
    }

    public void ativarRobot(int idRobot) {
        Robot r = getRobotbyId(idRobot);
        r.ativar(); [cite: 42, 81]
        this.ordensEnviadas++; [cite: 26]
        System.out.println("Ordem nº " + ordensEnviadas + " processada para " + r.getNome());
    }

    public void radar() {
        System.out.println("\n--- Radar Ativo (Zonas de Carga/Produção) ---"); [cite: 27, 28]
        for (Robot r : this.robots) {
            if (r.getZona() == Zona.ESTACAO_CARGA || r.getZona() == Zona.LINHA_PROD_1 || r.getZona() == Zona.LINHA_PROD_2) {
                System.out.println("[DETETADO] ID: " + r.getId() + " | Zona: " + r.getZona() + " | " + r.getNome()); [cite: 68]
            }
        }
    }

    // =========================== AUXILIARES ===========================

    public Tecnico getTecnicobyId(int id) {
        return tecnicos.stream().filter(t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Técnico ID " + id + " não existe."));
    }

    public Robot getRobotbyId(int id) {
        return robots.stream().filter(r -> r.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Robot ID " + id + " não existe."));
    }

    public ArrayList<Tecnico> getTecnicos() { return tecnicos; }
    public ArrayList<Robot> getRobots() { return robots; }
    public int getOrdensEnviadas() { return ordensEnviadas; }
}