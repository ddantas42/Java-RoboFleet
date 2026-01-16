package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô de limpeza industrial do tipo R-Clean.
 * Este modelo é responsavel pela higienização e recolha de resíduos, 
 * operando especificamente com sistemas de sucção e iluminação de inspeção.
 */
public class RClean extends Robot {

    /** Indica se o sistema de sucção de resíduos esta ativo/operacional. */
    private boolean sistemaSuccao;
    
    /** Indica se o robô possui luz auxiliar para inspeção de resíduos no piso. */
    private boolean luzInspecao;

    /**
     * Construtor para o robô R-Clean.
     */
    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, boolean succao, boolean luz) {
        super(nome, marca, modelo, ano, zona, bat);
        this.sistemaSuccao = succao;
        this.luzInspecao = luz;
    }

    /**
     * Adiciona motores ao robô de limpeza, respeitando o limite físico do modelo.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 2) {
            throw new IllegalStateException("Limite atingido: O R-Clean permite no maximo 2 motores.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa técnicos à equipa de limpeza, validando a lotação maxima.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("Limite atingido: O R-Clean permite no maximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa do robô de limpeza.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida os requisitos técnicos e humanos para a ativação do robô.
     */
    @Override
    public boolean podeSerAtivado() {
        boolean motoresOk = motores.size() >= 1 && motores.size() <= 2;
        boolean equipaOk = equipa.size() >= 1 && equipa.size() <= 2;
        
        boolean temManutencao = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidades().contains(Especializacao.MANUTENCAO)) {
                temManutencao = true;
                break;
            }
        }
        return motoresOk && equipaOk && temManutencao;
    }

    /**
     * Devolve uma representação textual dos dados do robô de limpeza.
     * Inclui as informações base herdadas e o estado dos sistemas de sucção e luz.
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t[Especifico R-Clean: Succao " + sistemaSuccao + " | Luz de Piso: " + luzInspecao + "]";
    }
}