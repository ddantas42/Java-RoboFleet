package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô de transporte do tipo R-Carry.
 * Este modelo é especializado na movimentação de cargas pesadas e possui limites 
 * específicos de hardware e pessoal (máximo de 4 motores e 3 técnicos).
 */
public class RCarry extends Robot {

    /** Capacidade máxima de carga que o robô consegue transportar em kg. */
    private double capacidadeCarga;
    
    /** Indica se o robô está equipado com um sistema de iluminação frontal (holofote). */
    private boolean temHolofote;

    /**
     * Construtor completo para o robô R-Carry.
     * * @param nome     Nome identificativo do robô.
     * @param marca    Fabricante do robô.
     * @param modelo   Modelo específico da série R-Carry.
     * @param ano      Ano de fabrico.
     * @param zona     Zona do complexo onde o robô irá operar.
     * @param bat      Objeto bateria associado ao robô.
     * @param carga    Capacidade de carga em quilogramas.
     * @param holofote Estado inicial do sistema de iluminação.
     */
    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote) {
        super(nome, marca, modelo, ano, zona, bat);
        this.capacidadeCarga = carga;
        this.temHolofote = holofote;
    }

    /**
     * Adiciona um motor ao robô, validando o limite físico de 4 motores para este modelo.
     * * @param m O motor a ser instalado.
     * @throws IllegalStateException Se o robô já possuir o limite máximo de 4 motores.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 4) {
            throw new IllegalStateException("Limite de 4 motores atingido para o modelo R-Carry.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa um técnico à equipa do robô, validando o limite de lotação.
     * * @param t O técnico a associar à equipa.
     * @throws IllegalStateException Se a equipa já possuir o limite máximo de 3 técnicos.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("Limite de 3 técnicos atingido para o modelo R-Carry.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa operacional deste robô.
     * * @param t O técnico a ser desassociado.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida se o robô cumpre os requisitos mínimos para entrar em operação.
     * * @return true se o robô estiver apto para ativação, false caso contrário.
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

    /** @return A capacidade de carga atual do robô. */
    public double getCapacidadeCarga() { return capacidadeCarga; }

    /** @return true se o holofote estiver presente/ativo. */
    public boolean isTemHolofote() { return temHolofote; }

    /**
     * Devolve uma representação textual detalhada do robô R-Carry, 
     * incluindo os dados base e os atributos específicos de transporte.
     * * @return String formatada com as informações do robô.
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t[Carga: " + capacidadeCarga + "kg | Holofote: " + temHolofote + "]";
    }
}