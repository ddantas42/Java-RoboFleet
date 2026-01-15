package com.mycompany.java.robofleet.Robot;

/**
 * Enumeração que define as zonas operacionais do Complexo Industrial-Logístico.
 * Cada robô do sistema deve estar obrigatoriamente associado a uma destas localizações.
 */
public enum Zona {

    /** Zona destinada ao armazenamento e logística de materiais. */
    ARMAZEM,

    /** Laboratório onde é realizada a triagem de componentes e materiais. */
    TRIAGEM,

    /** Primeira unidade da linha de produção fabril. */
    LINHA_PROD_1,

    /** Segunda unidade da linha de produção fabril. */
    LINHA_PROD_2,

    /** Local central de carregamento onde os robôs sem atividade ficam estacionados. */
    ESTACAO_CARGA
}