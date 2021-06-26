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
    @ParameterizedTest(name = "{0} ^ {1} = {2}")
    @CsvSource({
//           X    Y  ExpectedResult
            "2,   5, 32",
    })
    @Name("PWR-Integers")
    void potentiation(Integer x, Integer y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.PWR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }

    @ParameterizedTest(name = "{0} % {1} = {2}")
    @CsvSource({
//           X    Y   ExpectedResult
            "25,  12, 1",
            "11,  12, 11",
            "12,  12, 0"
    })
    @Name("MOD-Integers")
    void modulo(Integer x, Integer y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, y)));
        insList.add(new Instruction(Instruction.Mnemonic.MOD, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }

    @ParameterizedTest(name = "{0} % {1} = {2}")
    @CsvSource({
//           X    Y   ExpectedResult
            "5.0,  3.0, 1",
    })
    @Name("DVW-Integers")
    void divisionWhole(Float x, Float y, Integer expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, x)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, y)));
        insList.add(new Instruction(Instruction.Mnemonic.DVW, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.INTEGER, stackElement.type);
        assertEquals(expectedResult, (Integer) stackElement.content);
    }
    @ParameterizedTest(name = "SPos0:{0} SPos1:{1} SPos2:{2} ADDR:{3} => SPos0:{4} SPos1:{5}")
    @CsvSource({
            "a, b, c, 1, c, b",
            "a, b, c, 2, a, c",
    })
    @Name("Mem-Store-Literal")
    void storeValueAtLiteral(String sPos0, String sPos1, String sPos2, Integer addr,
                             String expectedSPos0, String expectedSPos1) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, sPos0)));
        insList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, sPos1)));
        insList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, sPos2)));
        insList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.ADDRESS, addr)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(2, stack.size());
        // we start at 1 here because it's a stack, so LIFO ordering
        var stackElement1 = stack.get(1);
        assertEquals(DataType.LITERAL, stackElement1.type);
        assertEquals(expectedSPos1, stackElement1.content);
        var stackElement0 = stack.get(0);
        assertEquals(DataType.LITERAL, stackElement0.type);
        assertEquals(expectedSPos0, stackElement0.content);
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
        assertEquals(expectedResult, stackElement.content);
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
        assertEquals(expectedResult, stackElement.content);
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
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} > {1} = {2}")
    @CsvSource({
//            S   T  ExpectedResult
            " 1,  2, false",
            " 2,  1, true",
            "-1,  1, false",
            " 1, -1, true"
    })
    @Name("Relational-BGR-Integers")
    void relationalBgrIntegers(Integer subTop, Integer top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, top)));
        insList.add(new Instruction(Instruction.Mnemonic.BGR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} > {1} = {2}")
    @CsvSource({
//            S     T    ExpectedResult
            " 1.0,  2.0, false",
            " 2.0,  1.0, true",
            "-1.0,  1.0, false",
            " 1.0, -1.0, true"
    })
    @Name("Relational-BGR-Floats")
    void relationalBgrFloats(Float subTop, Float top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, top)));
        insList.add(new Instruction(Instruction.Mnemonic.BGR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} > {1} = {2}")
    @CsvSource({
//            S     T    ExpectedResult
            " 1.0,  2,   false",
            " 2.0,  1,   true",
            "-1.0,  1,   false",
            " 1.0, -1,   true"
    })
    @Name("Relational-BGR-Floats")
    void relationalBgrFloatsIntegers(Float subTop, Integer top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, top)));
        insList.add(new Instruction(Instruction.Mnemonic.BGR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} > {1} = {2}")
    @CsvSource({
//            S   T    ExpectedResult
            " 1,  2.0, false",
            " 2,  1.0, true",
            "-1,  1.0, false",
            " 1, -1.0, true"
    })
    @Name("Relational-BGR-FloatsIntegers")
    void relationalBgrIntegersFloats(Integer subTop, Float top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, top)));
        insList.add(new Instruction(Instruction.Mnemonic.BGR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} != {1} = {2}")
    @CsvSource({
//            S  T  ExpectedResult
            " 1, 2, true",
            " 2, 1, true",
            " 1, 1, false",
            " 0, 0, false"
    })
    @Name("Relational-BGR-FloatsIntegers")
    void relationalDifIntegers(Integer subTop, Integer top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, top)));
        insList.add(new Instruction(Instruction.Mnemonic.DIF, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} = {1} = {2}")
    @CsvSource({
//            S  T  ExpectedResult
            " 1, 2, false",
            " 2, 1, false",
            " 1, 1, true",
            " 0, 0, true"
    })
    @Name("Relational-BGR-FloatsIntegers")
    void relationalEqualsIntegers(Integer subTop, Integer top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, top)));
        insList.add(new Instruction(Instruction.Mnemonic.EQL, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} <= {1} = {2}")
    @CsvSource({
//            S   T  ExpectedResult
            " 1,  2, true",
            " 2,  1, false",
            "-1,  1, true",
            " 1, -1, false",
            " 1,  1, true",
            "-1, -1, true"
    })
    @Name("Relational-SME-Integers")
    void relationalLessOrEquals(Integer subTop, Integer top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, top)));
        insList.add(new Instruction(Instruction.Mnemonic.SME, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }
    @ParameterizedTest(name = "{0} < {1} = {2}")
    @CsvSource({
//            S   T  ExpectedResult
            " 1,  2, true",
            " 2,  1, false",
            "-1,  1, true",
            " 1, -1, false",
            " 1,  1, false",
            "-1, -1, false"
    })
    @Name("Relational-SME-Integers")
    void relationalLess(Integer subTop, Integer top, Boolean expectedResult) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, subTop)));
        insList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, top)));
        insList.add(new Instruction(Instruction.Mnemonic.SMR, new DataFrame(DataType.NONE, null)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(1, stack.size());
        var stackElement = stack.peek();
        assertEquals(DataType.BOOLEAN, stackElement.type);
        assertEquals(expectedResult, stackElement.content);
    }

    @ParameterizedTest(name = "SPos0:{0} SPos1:{1} SPos2:{2} ADDR:{3} => SPos0:{4} SPos1:{5}")
    @CsvSource({
            "a, b, c, 1, a, c",
            "a, b, c, 2, c, c",
    })
    @Name("Memory-STC")
    void stackCopyToPositions(String sPos0, String sPos1, String sPos2, Integer nPositions,
                             String expectedSPos0, String expectedSPos1) {
        var insList = new ArrayList<Instruction>();
        insList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, sPos0)));
        insList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, sPos1)));
        insList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, sPos2)));
        insList.add(new Instruction(Instruction.Mnemonic.STC, new DataFrame(DataType.INTEGER, nPositions)));
        insList.add(new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.NONE, null)));
        var vm = new VirtualMachine(insList);
        vm.executeAll();
        var stack = vm.getStack();
        assertEquals(2, stack.size());
        // we start at 1 here because it's a stack, so LIFO ordering
        var stackElement1 = stack.get(1);
        assertEquals(DataType.LITERAL, stackElement1.type);
        assertEquals(expectedSPos1, stackElement1.content);
        var stackElement0 = stack.get(0);
        assertEquals(DataType.LITERAL, stackElement0.type);
        assertEquals(expectedSPos0, stackElement0.content);
    }
}