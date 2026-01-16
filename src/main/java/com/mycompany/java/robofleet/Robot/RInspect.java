package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

public class RInspect extends Robot {

    /*Indica se a câmara de alta resolução esta instalada e funcional. */
    private boolean temCamara;
    
    /* Indica se o scanner termico para deteção de calor esta operacional. */
    private boolean scannerTermico;

    
    public RInspect(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat) {
        super(nome, marca, modelo, ano, zona, bat);
        this.temCamara = true;
        this.scannerTermico = true;
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 1) {
            throw new IllegalStateException("Limite de hardware: O R-Inspect utiliza obrigatoriamente apenas 1 motor.");
        }
        super.adicionarMotor(m);
    }


    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("Limite de lotação: O R-Inspect permite no maximo 2 tecnicos.");
        }
        super.associarTecnico(t);
    }


    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    @Override
    public boolean podeSerAtivado() {
        return motores.size() == 1 && equipa.size() >= 1 && equipa.size() <= 2;
    }


    public boolean isTemCamara() {
        return temCamara;
    }

    
    public boolean isScannerTermico() {
        return scannerTermico;
    }

    
    @Override
    public String toString() {
        return super.toString() + 
            "\n\t[Especifico R-Inspect: Câmara=" + (temCamara ? "Ativa" : "Inativa") + 
            " | Scanner Termico=" + (scannerTermico ? "Ativo" : "Inativo") + "]";
    }
}