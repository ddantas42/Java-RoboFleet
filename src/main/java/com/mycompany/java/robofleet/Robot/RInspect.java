package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô de análise e controlo de qualidade do tipo R-Inspect.
 * Utiliza câmaras de alta resolução e sensores térmicos para deteção de problemas no complexo.
 * Possui a configuração de motores e equipa mais leve do sistema.
 */
public class RInspect extends Robot {

    private boolean temCamara;
    private boolean scannerTermico;

    /**
     * Construtor para o robô R-Inspect.
     *
     * @param nome Nome do robô.
     * @param marca Marca do fabricante.
     * @param modelo Modelo do robô.
     * @param ano Ano de fabrico.
     * @param zona Zona de inspeção operacional.
     * @param bat Bateria associada.
     */
    public RInspect(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat) {
        super(nome, marca, modelo, ano, zona, bat);
        this.temCamara = true;
        this.scannerTermico = true;
    }

    /**
     * Adiciona um motor ao robô de inspeção, respeitando o limite do modelo.
     *
     * @param m O motor a instalar.
     * @throws IllegalStateException Se ultrapassar o limite de 1 motor.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 1) {
            throw new IllegalStateException("O R-Inspect utiliza apenas 1 motor.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa um técnico à equipa de inspeção.
     *
     * @param t Técnico a associar.
     * @throws IllegalStateException Se ultrapassar o limite de 2 técnicos.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("O R-Inspect permite no máximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um técnico da equipa do robô.
     *
     * @param t Técnico a remover.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida os requisitos de ativação para o R-Inspect.
     * Requisitos: 1 motor e 1 a 2 técnicos instalados.
     *
     * @return true se o robô puder ser ativado para missões.
     */
    @Override
    public boolean podeSerAtivado() {
        return motores.size() == 1 && equipa.size() >= 1 && equipa.size() <= 2;
    }

    /** @return true se o robô possuir câmara de alta resolução ativa. */
    public boolean isTemCamara() {
        return temCamara;
    }

    /** @return true se o scanner térmico estiver operacional. */
    public boolean isScannerTermico() {
        return scannerTermico;
    }

    /**
     * Devolve a representação textual do robô, utilizando os valores das variáveis locais.
     *
     * @return String com detalhes técnicos do robô de inspeção.
     */
    @Override
    public String toString() {
        return super.toString() + 
            "\n\t[Específico R-Inspect: Câmara=" + (temCamara ? "Ativa" : "Inativa") + 
            " | Scanner Térmico=" + (scannerTermico ? "Ativo" : "Inativo") + "]";
    }
}