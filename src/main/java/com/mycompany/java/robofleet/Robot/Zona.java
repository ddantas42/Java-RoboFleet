package com.mycompany.java.robofleet.Robot;

/**
 * Enumeração que define as zonas operacionais do Complexo Industrial-Logístico.
 * Cada robô do sistema deve estar obrigatoriamente associado a uma destas localizações.
 */
public enum Zona {

    /** Zona destinada ao armazenamento, receção e logística de matérias-primas e produtos acabados. */
    ARMAZEM,

    /** Laboratório de análise e controlo onde é realizada a triagem técnica de componentes. */
    TRIAGEM,

    /** Primeira unidade da linha de produção fabril, destinada à montagem primária. */
    LINHA_PROD_1,

    /** Segunda unidade da linha de produção fabril, destinada ao acabamento e testes. */
    LINHA_PROD_2,

    /** * Local central de carregamento de energia. 
     * Zona de repouso onde os robôs sem atividade ou com bateria baixa ficam estacionados. */
    ESTACAO_CARGA
}