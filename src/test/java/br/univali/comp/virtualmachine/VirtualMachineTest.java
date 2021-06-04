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

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
//            X  Y  ExpectedResult
            " 1, 2, -1",
            "-1, 1, -2"
    })
    @Name("SUB-Integers")
    void subIntegers(Integer x, Integer y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.SUB, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
//            X    Y    ExpectedResult
            " 1.0, 2.0, -1.0",
            "-1.0, 1.0, -2.0"
    })
    @Name("SUB-Floats")
    void subFloats(Float x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.SUB, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
//           X    Y    ExpectedResult
            "1,   2.0, -1.0",
    })
    @Name("SUB-IntegerFloat")
    void subIntegerFloat(Integer x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.SUB, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
//           X    Y  ExpectedResult
            "1.0, 2, -1.0",
    })
    @Name("SUB-FloatInteger")
    void subFloatInteger(Float x, Integer y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.SUB, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
//            X  Y  ExpectedResult
            " 3, 5, 0",
            " 5, 3, 1"
    })
    @Name("DIV-Integers")
    void divIntegers(Integer x, Integer y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.DIV, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
//            X    Y     ExpectedResult
            " 3.0, 5.0,  0.6",
            " 5.0, 3.0,  1.6666666"
    })
    @Name("DIV-Floats")
    void divFloats(Float x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.DIV, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
//           X    Y    ExpectedResult
            "3,   5.0, 0.6",
    })
    @Name("DIV-IntegerFloat")
    void divIntegerFloat(Integer x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.DIV, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
//           X    Y  ExpectedResult
            "3.0, 5, 0.6",
    })
    @Name("DIV-FloatInteger")
    void divFloatInteger(Float x, Integer y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.DIV, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
//            X  Y  ExpectedResult
            " 1, 2, 2",
            "-1, 1, -1"
    })
    @Name("MUL-Integers")
    void mulIntegers(Integer x, Integer y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.MUL, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
//            X    Y     ExpectedResult
            " 1.0, 2.0,  2.0",
            "-1.0, 1.0, -1.0"
    })
    @Name("MUL-Floats")
    void mulFloats(Float x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.MUL, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
//           X    Y    ExpectedResult
            "1,   2.0, 2.0",
    })
    @Name("MUL-IntegerFloat")
    void mulIntegerFloat(Integer x, Float y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.MUL, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
//           X    Y  ExpectedResult
            "1.0, 2, 2.0",
    })
    @Name("MUL-FloatInteger")
    void mulFloatInteger(Float x, Integer y, Float expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.MUL, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.FLOAT, stackElement.type);
        assertEquals(expectedResult, (Float) stackElement.content);
    }
    @ParameterizedTest(name = "{0} OR {1} = {2}")
    @CsvSource({
//           X      Y      ExpectedResult
            "true,  true,  true",
            "true,  false, true",
            "false, true,  true",
            "false, false, false"
    })
    @Name("Logical-OR")
    void logicalOr(Boolean x, Boolean y, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, y)));
        insList.add(new Instruction(Instruction.Mnemonic.OR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, (Boolean) stackElement.content);
    }
    @ParameterizedTest(name = "{0} AND {1} = {2}")
    @CsvSource({
//           X      Y      ExpectedResult
            "true,  true,  true",
            "true,  false, false",
            "false, true,  false",
            "false, false, false"
    })
    @Name("Logical-AND")
    void logicalAnd(Boolean x, Boolean y, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, y)));
        insList.add(new Instruction(Instruction.Mnemonic.AND, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, (Boolean) stackElement.content);
    }
    @ParameterizedTest(name = "NOT {0} = {1}")
    @CsvSource({
//           X      ExpectedResult
            "true,  false",
            "false, true",
    })
    @Name("Logical-NOT")
    void logicalNot(Boolean x, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, x)));
        insList.add(new Instruction(Instruction.Mnemonic.NOT, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, (Boolean) stackElement.content);
    }
}