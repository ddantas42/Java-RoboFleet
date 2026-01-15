package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;

/**
 * Representa um robô fabril do tipo R-Factory, utilizado em linhas de produção.
 * Equipado com braços robóticos e sistemas laser, requer obrigatoriamente um Engenheiro de Robótica.
 * Este robô está restrito a operar apenas em zonas de produção fabril.
 */
public class RFactory extends Robot {

    private int numBracos;
    private boolean orientacaoLaser;

    /**
     * Construtor para o robô R-Factory.
     *
     * @param nome Nome do robô.
     * @param marca Marca do fabricante.
     * @param modelo Modelo do robô.
     * @param ano Ano de fabrico.
     * @param zona Zona de operação (deve ser uma linha de produção).
     * @param bat Bateria associada.
     * @param bracos Quantidade de braços robóticos instalados (2 a 4).
     * @throws IllegalArgumentException Se a zona definida não for uma linha de produção.
     */
    public RFactory(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, int bracos) {
        super(nome, marca, modelo, ano, zona, bat);
        
        if (zona != Zona.LINHA_PROD_1 && zona != Zona.LINHA_PROD_2 && zona != Zona.ESTACAO_CARGA) {
            throw new IllegalArgumentException("R-Factory só pode ser alocado em Linhas de Produção.");
        }
        
        this.numBracos = bracos;
        this.orientacaoLaser = true;
    }

    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 2) {
            throw new IllegalStateException("O R-Factory deve possuir exatamente 2 motores.");
        }
        super.adicionarMotor(m);
    }

    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 3) {
            throw new IllegalStateException("O R-Factory permite no máximo 3 técnicos.");
        }
        super.associarTecnico(t);
    }

    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida os requisitos rigorosos de ativação para a linha de produção.
     * Requisitos: Exatamente 2 motores e equipa de 2 a 3 técnicos, incluindo um de Robótica.
     *
     * @return true se o robô estiver pronto para a linha fabril.
     */
    @Override
    public boolean podeSerAtivado() {
        boolean motoresOk = motores.size() == 2;
        boolean equipaOk = equipa.size() >= 2 && equipa.size() <= 3;
        
        boolean temRobotica = false;
        for (Tecnico t : equipa) {
            if (t.getEspecialidade() == EspecialidadeTecnico.ROBOTICA) {
                temRobotica = true;
                break;
            }
        }
        return motoresOk && equipaOk && temRobotica;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t[Específico R-Factory: Braços " + numBracos + " | Laser Ativo: " + orientacaoLaser + "]";
    }
}