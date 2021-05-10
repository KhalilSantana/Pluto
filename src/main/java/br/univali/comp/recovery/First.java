package br.univali.comp.recovery;

import br.univali.comp.javacc.gen.*;


public class First { //implementa os conjuntos first p/ alguns n.terminais

    static public final RecoverySet program = new RecoverySet();
    public static RecoverySet define = new RecoverySet();
    public static RecoverySet execute = new RecoverySet();
    public static RecoverySet expressao = new RecoverySet();
    static {
        //DEPOIS da configuração ajustar o próximo token
        //program.add(new Integer(SintaticoConstants.R_PROGRAM));
        program.add(new Integer(SintaticoConstants.EOF));
        define.add(SintaticoConstants.R_EXECUTE);
        execute.add(SintaticoConstants.SS_CURLYBRACE_CLOSE);
        // expressao
        expressao.add(SintaticoConstants.R_IS);
        expressao.add(SintaticoConstants.R_TO);
    }
}