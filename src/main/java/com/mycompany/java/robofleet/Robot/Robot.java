package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public abstract class Robot implements Serializable {
    // Removido o static contadorIds para garantir persistência via CentroDeComando
    protected int id;
    protected String nome;
    protected String marca;
    protected String modelo;
    protected int anoFabrico;
    protected Zona zona;
    protected Bateria bateria;
    protected List<Motor> motores;
    protected List<Tecnico> equipa;
    protected boolean ativo; // Indica se o robô recebeu ordem de ativação

    public Robot(String nome, String marca, String modelo, int anoFabrico, Zona zona, Bateria bateria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do robô é obrigatório e único."); [cite: 40]
        }
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.zona = zona;
        this.bateria = bateria;
        this.motores = new ArrayList<>();
        this.equipa = new ArrayList<>();
        this.ativo = false; // Por defeito, robôs iniciam estacionados [cite: 67]
    }

    // Métodos Abstratos Obrigatórios
    public abstract void adicionarMotorChild(Motor m);
    public abstract boolean validarEquipa(); // Valida requisitos de ativação 
    public abstract void adicionarTecnico(Tecnico t);
    public abstract void removerTecnico(Tecnico t);

    // Lógica de Ativação
    public void ativar() {
        if (validarEquipa()) {
            this.ativo = true;
        } else {
            throw new IllegalStateException("Falha na ativação: Requisitos mínimos de equipa não cumpridos."); [cite: 74]
        }
    }

    public void desativar() {
        this.ativo = false;
        this.zona = Zona.ESTACAO_CARGA; // Regressam ao centro ao concluir [cite: 47, 52]
    }

    // Cálculo de Idade conforme enunciado 
    public String getIdadeFormatada() {
        LocalDate dataFabrico = LocalDate.of(this.anoFabrico, 1, 1);
        Period periodo = Period.between(dataFabrico, LocalDate.now());
        return periodo.getYears() + " anos e " + periodo.getMonths() + " meses";
    }

    // Gestão de Motores
    public void adicionarMotor(Motor m) {
        this.motores.add(m);
    }

    public void limparMotores() {
        this.motores.clear();
    }

    // Getters e Setters
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
    public boolean isAtivo() { return ativo; }
    public List<Tecnico> getEquipa() { return equipa; }

    public double getConsumoTotal() {
        return motores.stream().mapToDouble(Motor::getPotencia).sum();
    }

    public String calcularAutonomia() {
        double consumo = getConsumoTotal();
        if (consumo == 0) return "Infinito (Sem motores)";
        double autonomia = (double) bateria.getCapacidade() / consumo;
        return String.format("%.2f horas", autonomia);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append(" [").append(ativo ? "ATIVO" : "ESTACIONADO").append("]\n");
        sb.append("\tNome: ").append(nome).append("\n");
        sb.append("\tMarca/Modelo: ").append(marca).append(" ").append(modelo).append("\n");
        sb.append("\tIdade: ").append(getIdadeFormatada()).append(" (Fabrico: ").append(anoFabrico).append(")\n");
        sb.append("\tZona Atual: ").append(zona).append("\n");
        sb.append("\tBateria: ").append(bateria).append(" | Autonomia Est.: ").append(calcularAutonomia()).append("\n");
        sb.append("\tEquipa (").append(equipa.size()).append(" técnicos): ");
        for (Tecnico t : equipa) sb.append(t.getName()).append("; ");
        return sb.toString();
    }
}