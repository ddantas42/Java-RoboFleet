package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;

/**
 * Robô de transporte (R-Carry). 
 * Requisitos: 2-4 motores e 1-3 técnicos (mínimo 1 de Manutenção).
 */
public class RCarry extends Robot {

    private double capacidadeCarga;
    private boolean temHolofote;

    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote) {
        super(nome, marca, modelo, ano, zona, bat);
        this.capacidadeCarga = carga;
        this.temHolofote = holofote;
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 4) {
            throw new IllegalStateException("Limite de 4 motores atingido.");
        }
        super.adicionarMotor(m);
    }

    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("Limite de 3 técnicos atingido.");
        }
        super.associarTecnico(t);
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    @Override
    public boolean podeSerAtivado() {
        if (motores.isEmpty()) return false;
        
        for (Tecnico t : equipa) {
            // Assume-se que o técnico pode ter várias especializações
            if (t.getEspecialidades().contains(Especializacao.MANUTENCAO)) {
                return true;
            }
        }
        return false;
    }

    public double getCapacidadeCarga() { return capacidadeCarga; }
    public boolean isTemHolofote() { return temHolofote; }

    @Override
    public String toString() {
        return super.toString() + "\n\t[Carga: " + capacidadeCarga + "kg | Holofote: " + temHolofote + "]";
    }
}