package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;
import java.io.Serializable;

public class RClean extends Robot implements Serializable {

    private boolean sistemaSuccao;
    private boolean luzInspecao;

    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, boolean succao, boolean luz) {
        super(nome, marca, modelo, ano, zona, bat);
        this.sistemaSuccao = succao;
        this.luzInspecao = luz;
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        // Regra: 1 a 2 motores 
        if (motores.size() >= 2) {
            throw new IllegalStateException("R-Clean permite no máximo 2 motores.");
        }
        super.adicionarMotor(m);
    }

    @Override
    public void adicionarTecnico(Tecnico t) {
        // Regra: 1 a 2 técnicos 
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("R-Clean permite no máximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    @Override
    public boolean validarEquipa() {
        // Regras para ativação[cite: 42, 50]:
        // 1. Pelo menos 1 motor instalado.
        // 2. Equipa entre 1 e 2 elementos.
        // 3. Obrigatório pelo menos 1 Técnico de Manutenção.
        
        if (motores.isEmpty()) {
            return false;
        }

        if (equipa.isEmpty() || equipa.size() > 2) {
            return false;
        }

        boolean temManutencao = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidade() == EspecialidadeTecnico.MANUTENCAO) {
                temManutencao = true;
                break;
            }
        }

        return temManutencao;
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    @Override
    public String toString() {
        return super.toString() + 
            " [Tipo: R-Clean | Sucção: " + (sistemaSuccao ? "Sim" : "Não") + 
            " | Luz Inspeção: " + (luzInspecao ? "Sim" : "Não") + "]";
    }
}