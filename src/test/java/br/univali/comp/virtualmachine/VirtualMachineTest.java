package br.univali.comp.virtualmachine;

import jdk.jfr.Name;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VirtualMachineTest {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
//            X  Y  ExpectedResult
            " 1, 2, 3",
            "-1, 1, 0"
    })
    @Name("ADD-Integers")
    void addIntegers(Integer x, Integer y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.ADD, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
//            X    Y    ExpectedResult
            " 1.0, 2.0, 3.0",
            "-1.0, 1.0, 0.0"
    })
    @Name("ADD-Floats")
    void addFloats(Float x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.ADD, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
//           X    Y    ExpectedResult
            "1,   2.0, 3.0",
    })
    @Name("ADD-IntegerFloat")
    void addIntegerFloat(Integer x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.ADD, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
//           X    Y  ExpectedResult
            "1.0, 2, 3.0",
    })
    @Name("ADD-FloatInteger")
    void addFloatInteger(Float x, Integer y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.ADD, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
}