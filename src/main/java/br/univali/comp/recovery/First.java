package br.univali.comp.recovery;

import br.univali.comp.javacc.gen.SintaticoConstants;

// Describes the First set of a few terminals
public class First {

    static public final RecoverySet program = new RecoverySet();
    public static RecoverySet define = new RecoverySet();
    public static RecoverySet execute = new RecoverySet();
    public static RecoverySet expression = new RecoverySet();
    public static RecoverySet header = new RecoverySet();
    public static RecoverySet allCommands = new RecoverySet();
    public static RecoverySet type = new RecoverySet();

    static {
        program.add(SintaticoConstants.EOF);
        define.add(SintaticoConstants.R_EXECUTE);
        execute.add(SintaticoConstants.SS_CURLYBRACE_CLOSE);
        // expression
        expression.add(SintaticoConstants.R_IS);
        expression.add(SintaticoConstants.R_TO);
        // header
        header.add(SintaticoConstants.R_PROGRAM);
        // allCommands
        allCommands.add(SintaticoConstants.SS_DOT);
        // type
        type.add(SintaticoConstants.R_IS);
    }
}