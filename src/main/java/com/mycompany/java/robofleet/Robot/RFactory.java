package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

public class RFactory extends Robot {

    private int numBracos;
    
    private boolean orientacaoLaser;

    public RFactory(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, int bracos) {
        super(nome, marca, modelo, ano, zona, bat);
        
        if (zona != Zona.LINHA_PROD_1 && zona != Zona.LINHA_PROD_2 && zona != Zona.ESTACAO_CARGA) {
            throw new IllegalArgumentException("Erro de alocação: O modelo R-Factory é restrito a zonas de produção.");
        }
        
        if (bracos < 2 || bracos > 4) {
            throw new IllegalArgumentException("R-Factory deve possuir entre 2 e 4 braços robóticos.");
        }

        this.numBracos = bracos;
        this.orientacaoLaser = true;
    }

    /**
     * Instala um motor no robô, validando o limite de hardware.
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
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("Limite de pessoal: O R-Factory suporta no maximo 3 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa operacional.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Avalia se o R-Factory cumpre os requisitos críticos de segurança e funcionalidade.
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
        return super.toString() + "\n\t[Especifico R-Factory: Bracos " + numBracos + " | Laser Ativo: " + orientacaoLaser + "]";
    }
}