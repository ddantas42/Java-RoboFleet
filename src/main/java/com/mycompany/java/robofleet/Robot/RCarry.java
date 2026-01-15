package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;

/**
 * Representa um robô de transporte do tipo R-Carry.
 * Este robô é especializado no transporte interno de materiais dentro do complexo industrial.
 * Possui restrições específicas de carga e requer uma equipa com técnicos de manutenção para operar.
 */
public class RCarry extends Robot {

    private double capacidadeCarga;
    private boolean temHolofote;

    /**
     * Construtor para o robô R-Carry.
     *
     * @param nome Nome único do robô.
     * @param marca Marca do fabricante.
     * @param modelo Modelo do robô.
     * @param ano Ano de fabrico.
     * @param zona Zona de operação inicial.
     * @param bat Objeto bateria contendo capacidade e autonomia.
     * @param carga Capacidade máxima de carga em quilogramas (kg).
     * @param holofote Indica se o robô está equipado com holofote auxiliar.
     */
    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote) {
        super(nome, marca, modelo, ano, zona, bat);
        this.capacidadeCarga = carga;
        this.temHolofote = holofote;
    }

    /**
     * Adiciona um motor ao robô de transporte, respeitando o limite máximo definido.
     *
     * @param m O motor a ser instalado.
     * @throws IllegalStateException Se o robô já possuir o limite máximo de 4 motores.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 4) {
            throw new IllegalStateException("O R-Carry permite no máximo 4 motores.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa um técnico à equipa do R-Carry, respeitando o limite de membros.
     *
     * @param t O técnico a ser associado.
     * @throws IllegalStateException Se a equipa já atingiu o limite de 3 técnicos.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("O R-Carry permite no máximo 3 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa atual do robô.
     *
     * @param t O técnico a ser removido.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida os requisitos de ativação para o R-Carry.
     * Requisitos: 2 a 4 motores e 1 a 3 técnicos, incluindo obrigatoriamente um de Manutenção.
     *
     * @return true se o robô cumprir todos os requisitos para ativação.
     */
    @Override
    public boolean podeSerAtivado() {
        boolean motoresOk = motores.size() >= 2 && motores.size() <= 4;
        boolean equipaOk = equipa.size() >= 1 && equipa.size() <= 3;
        
        boolean temManutencao = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidade() == EspecialidadeTecnico.MANUTENCAO) {
                temManutencao = true;
                break;
            }
        }
        return motoresOk && equipaOk && temManutencao;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t[Específico R-Carry: Carga " + capacidadeCarga + "kg | Holofote: " + temHolofote + "]";
    }
}