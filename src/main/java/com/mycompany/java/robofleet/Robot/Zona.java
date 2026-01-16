package com.mycompany.java.robofleet.Robot;

/**
 * Enumeração que define as zonas operacionais do Complexo Industrial-Logistico.
 * Cada robô do sistema deve estar obrigatoriamente associado a uma destas localizações.
 */
public enum Zona {

    /** Zona destinada ao armazenamento, receção e logistica de materias-primas e produtos acabados. */
    ARMAZEM,

    /** Laboratorio de analise e controlo onde e realizada a triagem tecnica de componentes. */
    TRIAGEM,

    /** Primeira unidade da linha de produção fabril, destinada à montagem primária. */
    LINHA_PROD_1,

    /** Segunda unidade da linha de produção fabril, destinada ao acabamento e testes. */
    LINHA_PROD_2,

    /** * Local central de carregamento de energia. 
     * Zona de repouso onde os robôs sem atividade ou com bateria baixa ficam estacionados. */
    ESTACAO_CARGA
}