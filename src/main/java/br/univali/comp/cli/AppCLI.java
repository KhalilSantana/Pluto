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
        var insStop = new Instruction(InstructionMnemonic.STP, new InstructionParameter(ParameterType.NONE, null));

        var instructionList = Arrays.asList(ins0, ins1, ins2, insStop);

        VirtualMachine vm = new VirtualMachine(instructionList);
        vm.executeAll();
        System.out.println(vm.printStack());
    }
}
