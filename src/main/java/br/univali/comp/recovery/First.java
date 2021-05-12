package br.univali.comp.recovery;

import br.univali.comp.javacc.gen.*;


public class First { //implementa os conjuntos first p/ alguns n.terminais

    static public final RecoverySet program = new RecoverySet();
    public static RecoverySet define = new RecoverySet();
    public static RecoverySet execute = new RecoverySet();
    public static RecoverySet expression = new RecoverySet();
    public static RecoverySet header = new RecoverySet();
    public static RecoverySet all_commands = new RecoverySet();
    public static RecoverySet type = new RecoverySet();
    static {
        //DEPOIS da configuração ajustar o próximo token
        //program.add(new Integer(SintaticoConstants.R_PROGRAM));
        program.add(Linguagem2021_1Constants.EOF);
        define.add(Linguagem2021_1Constants.R_EXECUTE);
        execute.add(Linguagem2021_1Constants.SS_CURLYBRACE_CLOSE);
        // expressao
        expression.add(Linguagem2021_1Constants.R_IS);
        expression.add(Linguagem2021_1Constants.R_TO);
        // header
        header.add(Linguagem2021_1Constants.R_PROGRAM);
        // todos_os_comandos
        all_commands.add(Linguagem2021_1Constants.SS_DOT);
        // tipo
        type.add(Linguagem2021_1Constants.R_IS);
    }
}