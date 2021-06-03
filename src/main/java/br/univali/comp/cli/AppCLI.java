package br.univali.comp.cli;

import br.univali.comp.javacc.gen.ParseException;
import br.univali.comp.recovery.ParseEOFException;
import br.univali.comp.virtualmachine.*;

import java.io.IOException;
import java.util.Arrays;

public class AppCLI {
    // Dummy class for manual testing

    public static void main(String[] args) throws IOException, ParseException, ParseEOFException {

        var ins0 = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, 1));
        var ins1 = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, 1));
        var ins2 = new Instruction(Instruction.Mnemonic.ADD, new DataFrame(DataType.NONE, null));
        var ins3 = new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, true));
        var ins4 = new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, 10f));
        var ins5 = new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, "abc"));
        var ins6 = new Instruction(Instruction.Mnemonic.LDV, new DataFrame(DataType.ADDRESS, 1));
        var ins7 = new Instruction(Instruction.Mnemonic.ALB, new DataFrame(DataType.INTEGER, 3));
        var ins8 = new Instruction(Instruction.Mnemonic.ALI, new DataFrame(DataType.INTEGER, 2));
        var ins9 = new Instruction(Instruction.Mnemonic.ALR, new DataFrame(DataType.INTEGER, 2));
        var ins10 = new Instruction(Instruction.Mnemonic.ALS, new DataFrame(DataType.INTEGER, 2));
        var ins11 = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, 3));
        var ins12 = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, 1));
        var ins13 = new Instruction(Instruction.Mnemonic.SUB, new DataFrame(DataType.NONE, null));
        var ins14 = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, 5));
        var ins15 = new Instruction(Instruction.Mnemonic.MUL, new DataFrame(DataType.NONE, null));
        var ins16 = new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, false));
        var ins17 = new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, true));
        var ins18 = new Instruction(Instruction.Mnemonic.AND, new DataFrame(DataType.NONE, null));
        var ins19 = new Instruction(Instruction.Mnemonic.NOT, new DataFrame(DataType.NONE, null));
        var ins20 = new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, true));
        var ins21 = new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, false));
        var ins22 = new Instruction(Instruction.Mnemonic.OR, new DataFrame(DataType.NONE, null));
        var insStop = new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null));

        var instructionList = Arrays.asList(ins0, ins1, ins2, ins3, ins4, ins5, ins6, ins7, ins8,
                ins9, ins10, ins11, ins12, ins13, ins14, ins15, ins16, ins17, ins18, ins19, ins20, ins21, ins22,
                insStop);

        VirtualMachine vm = new VirtualMachine(instructionList);
        vm.executeAll();
        System.out.println("==| EXEC RESULT |==");
        System.out.println(vm.printStack());
    }
}
