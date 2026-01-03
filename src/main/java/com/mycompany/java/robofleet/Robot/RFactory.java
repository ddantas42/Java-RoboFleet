package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;
import java.io.Serializable;

public class RFactory extends Robot implements Serializable {
    private int numBracos;
    private boolean orientacaoLaser;

    public RFactory(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, int bracos) {
        super(nome, marca, modelo, ano, zona, bat);
        
        // Regra: RFactory só opera em linhas de produção ou carga [cite: 54, 58]
        if (zona != Zona.LINHA_PROD_1 && zona != Zona.LINHA_PROD_2 && zona != Zona.ESTACAO_CARGA) {
            throw new IllegalArgumentException("R-Factory só permitido em Linhas de Produção ou Estação de Carga.");
        }

        // Regra: 2 a 4 braços robóticos 
        if (bracos < 2 || bracos > 4) {
            throw new IllegalArgumentException("R-Factory deve ter entre 2 e 4 braços robóticos.");
        }

        this.numBracos = bracos;
        this.orientacaoLaser = true; // Sistema de orientação laser [cite: 56]
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        // Regra: Exatamente 2 motores 
        if (motores.size() >= 2) {
            throw new IllegalStateException("R-Factory permite no máximo 2 motores.");
        }
        super.adicionarMotor(m);
    }

    @Override
    public void adicionarTecnico(Tecnico t) {
        // Regra: Máximo 3 técnicos 
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("R-Factory permite no máximo 3 técnicos.");
        }
        super.associarTecnico(t);
    }

    @Override
    public boolean validarEquipa() {
        // Regras para ativação:
        // 1. Equipa entre 2 e 3 elementos.
        // 2. Presença obrigatória de 1 Engenheiro de Robótica.
        
        if (equipa.size() < 2 || equipa.size() > 3) {
            return false;
        }

        boolean temEngenheiroRobotica = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidade() == EspecialidadeTecnico.ROBOTICA) {
                temEngenheiroRobotica = true;
                break;
            }
        }

        return temEngenheiroRobotica;
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    @Override
    public String toString() {
        return super.toString() + 
            " [Tipo: R-Factory | Braços: " + numBracos + " | Laser: " + (orientacaoLaser ? "Sim" : "Não") + "]";
    }
}