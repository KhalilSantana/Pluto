package br.univali.comp.recovery;

import br.univali.comp.javacc.gen.*;


public class First { //implementa os conjuntos first p/ alguns n.terminais

    static public final RecoverySet program = new RecoverySet();

    static {
        //DEPOIS da configuração ajustar o próximo token
        //program.add(new Integer(SintaticoConstants.R_PROGRAM));
        program.add(new Integer(SintaticoConstants.EOF));
    }
}
