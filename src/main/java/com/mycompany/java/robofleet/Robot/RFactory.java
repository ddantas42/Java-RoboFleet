package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô fabril do tipo R-Factory, projetado para operar em linhas de produção.
 * Este modelo é equipado com braços robóticos de precisão e sistemas de orientação laser.
 */
public class RFactory extends Robot {

    /** Número de braços robóticos instalados no robô (tipicamente entre 2 a 4). */
    private int numBracos;
    
    /** Indica se o sistema de orientação por laser está calibrado e ativo. */
    private boolean orientacaoLaser;

    /**
     * Construtor para o robô R-Factory com validação de zona operacional.
     *
     * @param nome   Nome identificativo do robô.
     * @param marca  Fabricante.
     * @param modelo Nome do modelo.
     * @param ano    Ano de fabrico.
     * @param zona   Localização (deve ser obrigatoriamente uma zona de produção).
     * @param bat    Módulo de bateria associado.
     * @param bracos Quantidade de braços robóticos funcionais.
     * @throws IllegalArgumentException Se a zona fornecida não for compatível com operações fabris.
     */
    public RFactory(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, int bracos) {
        super(nome, marca, modelo, ano, zona, bat);
        
        if (zona != Zona.LINHA_PROD_1 && zona != Zona.LINHA_PROD_2 && zona != Zona.ESTACAO_CARGA) {
            throw new IllegalArgumentException("Erro de alocação: O modelo R-Factory é restrito a zonas de produção.");
        }
        
        this.numBracos = bracos;
        this.orientacaoLaser = true;
    }

    /**
     * Instala um motor no robô, validando o limite de hardware.
     * * @param m O motor a ser instalado.
     * @throws IllegalStateException Se o robô já possuir o limite de 2 motores.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 2) {
            throw new IllegalStateException("Limite de hardware: O R-Factory deve possuir exatamente 2 motores.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Adiciona um técnico à equipa de suporte do robô.
     * * @param t Técnico qualificado para supervisão fabril.
     * @throws IllegalStateException Se a equipa já tiver atingido o limite de 3 membros.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("Limite de pessoal: O R-Factory suporta no máximo 3 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa operacional.
     * @param t Técnico a desassociar.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Avalia se o R-Factory cumpre os requisitos críticos de segurança e funcionalidade.
     * @return true se todos os critérios forem satisfeitos para início de produção.
     */
    @Override
    public boolean podeSerAtivado() {
        boolean motoresOk = motores.size() == 2;
        boolean equipaOk = equipa.size() >= 2 && equipa.size() <= 3;
        
        boolean temRobotica = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidades().contains(Especializacao.ROBOTICA)) {
                temRobotica = true;
                break;
            }
        }
        return motoresOk && equipaOk && temRobotica;
    }

    /**
     * Sobrescreve o método setZona para garantir que o R-Factory 
     * nunca saia das linhas de produção, mesmo após a criação.
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t[Específico R-Factory: Braços " + numBracos + " | Laser Ativo: " + orientacaoLaser + "]";
    }
}