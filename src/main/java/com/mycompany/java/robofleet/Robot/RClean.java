package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô de limpeza industrial do tipo R-Clean.
 * Este modelo é responsável pela higienização e recolha de resíduos, 
 * operando especificamente com sistemas de sucção e iluminação de inspeção.
 */
public class RClean extends Robot {

    /** Indica se o sistema de sucção de resíduos está ativo/operacional. */
    private boolean sistemaSuccao;
    
    /** Indica se o robô possui luz auxiliar para inspeção de resíduos no piso. */
    private boolean luzInspecao;

    /**
     * Construtor para o robô R-Clean.
     *
     * @param nome   Nome identificativo do robô.
     * @param marca  Marca do fabricante.
     * @param modelo Modelo do robô.
     * @param ano    Ano de fabrico.
     * @param zona   Zona de operação atribuída.
     * @param bat    Bateria associada para fornecimento de energia.
     * @param succao Define se o sistema de sucção está instalado de origem.
     * @param luz    Define se possui luz de inspeção de piso ativa.
     */
    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, boolean succao, boolean luz) {
        super(nome, marca, modelo, ano, zona, bat);
        this.sistemaSuccao = succao;
        this.luzInspecao = luz;
    }

    /**
     * Adiciona motores ao robô de limpeza, respeitando o limite físico do modelo.
     *
     * @param m O motor a instalar.
     * @throws IllegalStateException Se o robô já possuir o limite de 2 motores.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 2) {
            throw new IllegalStateException("Limite atingido: O R-Clean permite no máximo 2 motores.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa técnicos à equipa de limpeza, validando a lotação máxima.
     *
     * @param t Técnico a associar à equipa operacional.
     * @throws IllegalStateException Se o robô já possuir o limite de 2 técnicos.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("Limite atingido: O R-Clean permite no máximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa do robô de limpeza.
     * * @param t Técnico a remover da equipa.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida os requisitos técnicos e humanos para a ativação do robô.
     * @return true se o robô cumprir todos os requisitos para operação industrial.
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
     * * @return String com os detalhes técnicos formatados.
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t[Específico R-Clean: Sucção " + sistemaSuccao + " | Luz de Piso: " + luzInspecao + "]";
    }
}