package br.univali.comp.cli;

import br.univali.comp.gui.EditorFile;
import br.univali.comp.javacc.gen.ParseException;
import br.univali.comp.javacc.gen.Sintatico;
import br.univali.comp.recovery.*;
import br.univali.comp.recovery.ParseEOFException;
import br.univali.comp.virtualmachine.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class AppCLI {
    // Dummy class for manual testing

    public static void main(String[] args) throws IOException, ParseException, ParseEOFException {

        var ins0 = new Instruction(InstructionMnemonic.LDI, new InstructionParameter(ParameterType.INTEGER_CONSTANT, 1));
        var ins1 = new Instruction(InstructionMnemonic.LDI, new InstructionParameter(ParameterType.INTEGER_CONSTANT, 1));
        var ins2 = new Instruction(InstructionMnemonic.ADD, new InstructionParameter(ParameterType.NONE, null));
        var ins3 = new Instruction(InstructionMnemonic.LDB, new InstructionParameter(ParameterType.BOOLEAN_CONSTANT, true));
        var ins4 = new Instruction(InstructionMnemonic.LDR, new InstructionParameter(ParameterType.FLOAT_CONSTANT, 10f));
        var ins5 = new Instruction(InstructionMnemonic.LDS, new InstructionParameter(ParameterType.LITERAL_CONSTANT, "abc"));
        var ins6 = new Instruction(InstructionMnemonic.LDV, new InstructionParameter(ParameterType.ADDRESS, 1));
        var insStop = new Instruction(InstructionMnemonic.STP, new InstructionParameter(ParameterType.NONE, null));

        var instructionList = Arrays.asList(ins0, ins1, ins2, ins3, ins4, ins5, ins6,insStop);

        VirtualMachine vm = new VirtualMachine(instructionList);
        vm.executeAll();
        System.out.println("==| EXEC RESULT |==");
        System.out.println(vm.printStack());
    }
}
