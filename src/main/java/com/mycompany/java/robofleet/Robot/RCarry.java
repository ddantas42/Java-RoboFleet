package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;
import java.io.Serializable;

public class RCarry extends Robot implements Serializable {

    private double capacidadeCarga;
    private boolean temHolofote;

    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote) {
        super(nome, marca, modelo, ano, zona, bat);
        this.capacidadeCarga = carga;
        this.temHolofote = holofote;
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        // Regra: 2 a 4 motores elétricos 
        if (motores.size() >= 4) {
            throw new IllegalStateException("R-Carry já atingiu o limite máximo de 4 motores.");
        }
        super.adicionarMotor(m);
    }

    @Override
    public void adicionarTecnico(Tecnico t) {
        // Regra: 1 a 3 técnicos 
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("R-Carry permite no máximo 3 técnicos.");
        }
        super.associarTecnico(t);
    }

    @Override
    public boolean validarEquipa() {
        // Regras para ativação:
        // 1. Pelo menos 2 motores instalados (requisito de hardware).
        // 2. Equipa entre 1 e 3 elementos.
        // 3. Presença obrigatória de 1 Técnico de Manutenção.
        
        if (motores.size() < 2) {
            return false;
        }

        if (equipa.isEmpty() || equipa.size() > 3) {
            return false;
        }

        boolean temTecnicoManutencao = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidade() == EspecialidadeTecnico.MANUTENCAO) {
                temTecnicoManutencao = true;
                break;
            }
        }

        return temTecnicoManutencao;
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    @Override
    public String toString() {
        return super.toString() + 
            " [Tipo: R-Carry | Carga Máx: " + capacidadeCarga + "kg | Holofote: " + (temHolofote ? "Sim" : "Não") + "]";
    }
}