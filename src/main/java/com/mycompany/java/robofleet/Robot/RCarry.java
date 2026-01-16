package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô de transporte do tipo R-Carry.
 * Este modelo e especializado na movimentação de cargas pesadas e possui limites 
 */
public class RCarry extends Robot {

    /** Capacidade maxima de carga que o robô consegue transportar em kg. */
    private double capacidadeCarga;
    
    /** Indica se o robô esta equipado com um sistema de iluminação frontal (holofote). */
    private boolean temHolofote;

    /**
     * Construtor completo para o robô R-Carry.
     */
    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote) {
        super(nome, marca, modelo, ano, zona, bat);
        this.capacidadeCarga = carga;
        this.temHolofote = holofote;
    }

    /**
     * Adiciona um motor ao robô, validando o limite fisico de 4 motores para este modelo.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 4) {
            throw new IllegalStateException("Limite de 4 motores atingido para o modelo R-Carry.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa um tecnico à equipa do robô, validando o limite de lotação.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("Limite de 3 tecnicos atingido para o modelo R-Carry.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um tecnico da equipa operacional deste robô.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida se o robô cumpre os requisitos minimos para entrar em operação.
     * Valida os requisitos de ativação para o R-Carry.
     */
    @Override
    public boolean podeSerAtivado() {
        boolean motoresOk = motores.size() >= 2 && motores.size() <= 4;
        boolean equipaOk = equipa.size() >= 1 && equipa.size() <= 3;
        
        boolean temManutencao = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidades().contains(Especializacao.MANUTENCAO)) {
                temManutencao = true;
                break;
            }
        }
        return motoresOk && equipaOk && temManutencao;
    }

    public double getCapacidadeCarga() { return capacidadeCarga; }

    public boolean isTemHolofote() { return temHolofote; }

    /**
     * Devolve uma representação textual detalhada do robô R-Carry, 
     * incluindo os dados base e os atributos especificos de transporte.
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t[Carga: " + capacidadeCarga + "kg | Holofote: " + temHolofote + "]";
    }
}