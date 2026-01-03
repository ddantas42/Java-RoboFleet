package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;

public class RInspect extends Robot implements Serializable {

    private boolean temCamara;
    private boolean scannerTermico;
    private boolean scannerOtico; // Adicionado conforme requisitos 3.2.4

    public RInspect(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat) {
        super(nome, marca, modelo, ano, zona, bat);
        this.temCamara = true;
        this.scannerTermico = true;
        this.scannerOtico = true;
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        // Regra: Exatamente 1 Motor
        if (motores.size() >= 1) {
            throw new IllegalStateException("R-Inspect permite apenas 1 motor.");
        }
        super.adicionarMotor(m);
    }

    @Override
    public void adicionarTecnico(Tecnico t) {
        // Regra: 1 a 2 técnicos
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("R-Inspect permite no máximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    @Override
    public boolean validarEquipa() {
        // Regras para ativação (3.2.4):
        // 1. Deve ter exatamente 1 motor.
        // 2. Equipa de 1 a 2 técnicos (sem especialização obrigatória).
        
        return motores.size() == 1 && !equipa.isEmpty() && equipa.size() <= 2;
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    // Regra Funcional 3.2.4: Quando deteta um problema, todos os R-Inspect da mesma zona regressam.
    // Esta lógica deve ser chamada pelo Centro de Comando ao processar um alerta.
    public void alertarProblema() {
        System.out.println("R-Inspect [" + nome + "] detetou uma anomalia! Notificando frota de inspeção...");
        this.desativar(); // Regressa ao Centro/Estação de Carga
    }

    @Override
    public String toString() {
        return super.toString() + 
            " [Tipo: R-Inspect | Sensores: Câmara Hi-Res, Scanner Térmico e Ótico]";
    }
}