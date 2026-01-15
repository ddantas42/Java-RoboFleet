package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 * Representa um robô de análise e controlo de qualidade do tipo R-Inspect.
 * Este modelo utiliza hardware especializado, como câmaras de alta resolução e 
 * sensores térmicos, para a deteção de anomalias no complexo industrial.
 */
public class RInspect extends Robot {

    /** Indica se a câmara de alta resolução está instalada e funcional. */
    private boolean temCamara;
    
    /** Indica se o scanner térmico para deteção de calor está operacional. */
    private boolean scannerTermico;

    /**
     * Construtor para o robô R-Inspect. 
     * Inicializa os sistemas de sensorização (câmara e scanner) como ativos por defeito.
     *
     * @param nome  Nome identificativo do robô.
     * @param marca Fabricante do dispositivo.
     * @param modelo Nome do modelo da série Inspect.
     * @param ano   Ano de fabrico do hardware.
     * @param zona  Zona específica de inspeção onde o robô atuará.
     * @param bat   Módulo de bateria associado.
     */
    public RInspect(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat) {
        super(nome, marca, modelo, ano, zona, bat);
        this.temCamara = true;
        this.scannerTermico = true;
    }

    /**
     * Adiciona um motor ao robô de inspeção, validando o limite físico do modelo.
     *
     * @param m O motor a ser instalado.
     * @throws IllegalStateException Se for tentado adicionar mais do que 1 motor, 
     * pois o chassis do R-Inspect não suporta propulsão extra.
     */
    @Override
    public void adicionarMotorChild(Motor m) {
        if (motores.size() >= 1) {
            throw new IllegalStateException("Limite de hardware: O R-Inspect utiliza obrigatoriamente apenas 1 motor.");
        }
        super.adicionarMotor(m);
    }

    /**
     * Associa um técnico à equipa de inspeção.
     *
     * @param t Técnico a associar para supervisionar a recolha de dados.
     * @throws IllegalStateException Se a equipa já possuir o limite máximo de 2 técnicos.
     */
    @Override
    public void adicionarTecnico(Tecnico t) {
        if (this.equipa.size() >= 2) {
            throw new IllegalStateException("Limite de lotação: O R-Inspect permite no máximo 2 técnicos.");
        }
        super.associarTecnico(t);
    }

    /**
     * Remove um membro da equipa de técnicos associada a este robô.
     * * @param t Técnico a remover da operação.
     */
    @Override
    public void removerTecnico(Tecnico t) {
        super.desassociarTecnico(t);
    }

    /**
     * Valida se o R-Inspect cumpre os requisitos mínimos para iniciar uma missão de patrulha.
     * @return true se o robô possuir o hardware e pessoal mínimo necessário; false caso contrário.
     */
    @Override
    public boolean podeSerAtivado() {
        return motores.size() == 1 && equipa.size() >= 1 && equipa.size() <= 2;
    }

    /** * Verifica o estado da câmara.
     * @return true se a câmara de alta resolução estiver ativa. 
     */
    public boolean isTemCamara() {
        return temCamara;
    }

    /** * Verifica o estado do scanner térmico.
     * @return true se o scanner térmico estiver operacional. 
     */
    public boolean isScannerTermico() {
        return scannerTermico;
    }

    /**
     * Devolve a representação textual detalhada do robô de inspeção.
     * Concatena os dados base do robô com o estado atual dos sensores específicos.
     *
     * @return String formatada com as especificações técnicas e sensores ativos.
     */
    @Override
    public String toString() {
        return super.toString() + 
            "\n\t[Específico R-Inspect: Câmara=" + (temCamara ? "Ativa" : "Inativa") + 
            " | Scanner Térmico=" + (scannerTermico ? "Ativo" : "Inativo") + "]";
    }
}