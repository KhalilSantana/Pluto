package br.univali.comp.cli;

import br.univali.comp.javacc.gen.ParseException;
import br.univali.comp.recovery.ParseEOFException;
import br.univali.comp.virtualmachine.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AppCLI {
    // Dummy class for manual testing

    public static void main(String[] args) throws IOException, ParseException, ParseEOFException {
        var insStop = new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null));
        var instructionList = Arrays.asList(
                new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, "Hello World!")),
                new Instruction(Instruction.Mnemonic.WRT, new DataFrame(DataType.NONE, null)),
                new Instruction(Instruction.Mnemonic.REA, new DataFrame(DataType.INTEGER, 1)),
                new Instruction(Instruction.Mnemonic.WRT, new DataFrame(DataType.NONE, null)),
                insStop
        );

        VirtualMachine vm = new VirtualMachine(instructionList);
        while (vm.getStatus() != VMStatus.HALTED) {
            vm.executeAll();
            switch (vm.getStatus()) {
                case SYSCALL_IO_READ -> {
                    vm.setSyscallData(
                            handleSyscallRead(
                                    vm.getSyscallDataType()
                            )
                    );
                }
                case SYSCALL_IO_WRITE -> handleSyscallWrite(vm.getSyscallData());
            }
        };
        System.out.println("==| EXEC RESULT |==");
        System.out.println(vm.printStack());
    }

    private static Object handleSyscallRead(DataType requestedType) {
        Scanner sc = new Scanner(System.in);
        switch (requestedType) {
            case INTEGER -> {
                return sc.nextInt();
            }
            case FLOAT -> {
                return sc.nextFloat();
            }
            case LITERAL -> {
                return sc.next();
            }
        }
        throw new RuntimeException(String.format("Invalid read instruction argument, received %s, must be one of INTEGER, FLOAT, LITERAL", requestedType));
    }
    private static void handleSyscallWrite(Object o) {
        System.out.println(o);
    }
}
