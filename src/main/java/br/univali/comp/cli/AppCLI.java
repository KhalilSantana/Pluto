package br.univali.comp.cli;

import br.univali.comp.javacc.gen.ParseException;
import br.univali.comp.recovery.ParseEOFException;
import br.univali.comp.virtualmachine.*;

import java.io.IOException;
import java.util.Arrays;

public class AppCLI {
    // Dummy class for manual testing

    public static void main(String[] args) throws IOException, ParseException, ParseEOFException {

        var ins0 = new Instruction(InstructionMnemonic.LDI, new DataFrame(DataType.INTEGER, 1));
        var ins1 = new Instruction(InstructionMnemonic.LDI, new DataFrame(DataType.INTEGER, 1));
        var ins2 = new Instruction(InstructionMnemonic.ADD, new DataFrame(DataType.NONE, null));
        var ins3 = new Instruction(InstructionMnemonic.LDB, new DataFrame(DataType.BOOLEAN, true));
        var ins4 = new Instruction(InstructionMnemonic.LDR, new DataFrame(DataType.FLOAT, 10f));
        var ins5 = new Instruction(InstructionMnemonic.LDS, new DataFrame(DataType.LITERAL, "abc"));
        var ins6 = new Instruction(InstructionMnemonic.LDV, new DataFrame(DataType.ADDRESS, 1));
        var ins7 = new Instruction(InstructionMnemonic.ALB, new DataFrame(DataType.INTEGER, 3));
        var ins8 = new Instruction(InstructionMnemonic.ALI, new DataFrame(DataType.INTEGER, 2));
        var ins9 = new Instruction(InstructionMnemonic.ALR, new DataFrame(DataType.INTEGER, 2));
        var ins10 = new Instruction(InstructionMnemonic.ALS, new DataFrame(DataType.INTEGER, 2));
        var ins11 = new Instruction(InstructionMnemonic.LDI, new DataFrame(DataType.INTEGER, 3));
        var ins12 = new Instruction(InstructionMnemonic.LDI, new DataFrame(DataType.INTEGER, 4));
        var ins13 = new Instruction(InstructionMnemonic.SUB, new DataFrame(DataType.NONE, null));
        var insStop = new Instruction(InstructionMnemonic.STP, new DataFrame(DataType.NONE, null));

        var instructionList = Arrays.asList(ins0, ins1, ins2, ins3, ins4, ins5, ins6, ins7, ins8,
                ins9, ins10, ins11, ins12, ins13,
                insStop);

        VirtualMachine vm = new VirtualMachine(instructionList);
        vm.executeAll();
        System.out.println("==| EXEC RESULT |==");
        System.out.println(vm.printStack());
    }
}
