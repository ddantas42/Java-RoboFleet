package com.mycompany.java.robofleet.Robot;

/**
 * Define as áreas de especialização técnica disponíveis no complexo industrial.
 * Estas especialidades são utilizadas para validar se um robô possui a equipa 
 * necessária para ser ativado.
 */
public enum Especializacao 
{
    /** Especialização necessária para operar robôs de produção (R-Factory). */
    ROBOTICA,

    /** Especialização obrigatória para robôs de transporte (R-Carry) e limpeza (R-Clean). */
    MANUTENCAO,

    /** Especialização em sistemas de suporte e controlo industrial. */
    SISTEMAS
}