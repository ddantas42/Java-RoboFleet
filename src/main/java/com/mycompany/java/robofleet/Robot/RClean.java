package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;

/**
 * Representa um robô de limpeza industrial do tipo R-Clean.
 * Responsável pela higienização e recolha de resíduos, operando com sistemas de sucção.
 * Requer supervisão de técnicos de manutenção para garantir o funcionamento do sistema de aspiração.
 */
public class RClean extends Robot {

    private boolean sistemaSuccao;
    private boolean luzInspecao;

    /**
     * Construtor para o robô R-Clean.
     *
     * @param nome Nome do robô.
     * @param marca Marca do fabricante.
     * @param modelo Modelo do robô.
     * @param ano Ano de fabrico.
     * @param zona Zona de operação.
     * @param bat Bateria associada.
     * @param succao Define se o sistema de sucção está ativo.
     * @param luz Define se possui luz de inspeção de piso.
     */
    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, boolean succao, boolean luz) {
        super(nome, marca, modelo, ano, zona, bat);
        this.sistemaSuccao = succao;
        this.luzInspecao = luz;
    }

    /**
     * Adiciona motores ao robô de limpeza conforme os limites do modelo.
     *
     * @param m O motor a instalar.
     * @throws IllegalStateException Se ultrapassar o limite de 2 motores.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 2) {
            throw new IllegalStateException("O R-Clean permite no máximo 2 motores.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Gere a associação de técnicos à equipa de limpeza.
     *
     * @param t Técnico a associar.
     * @throws IllegalStateException Se ultrapassar o limite de 2 técnicos na equipa.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("O R-Clean permite no máximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida a ativação do robô de limpeza.
     * Requisitos: 1 a 2 motores e 1 a 2 técnicos, sendo um de Manutenção obrigatório.
     *
     * @return true se estiver apto para operação industrial.
     */
    @Override
    public boolean podeSerAtivado() {
        boolean motoresOk = motores.size() >= 1 && motores.size() <= 2;
        boolean equipaOk = equipa.size() >= 1 && equipa.size() <= 2;
        
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
        return super.toString() + "\n\t[Específico R-Clean: Sucção " + sistemaSuccao + " | Luz de Piso: " + luzInspecao + "]";
    }
}