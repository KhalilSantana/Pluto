package br.univali.comp.virtualmachine;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class VirtualMachine {
    private final List<Instruction> instructions;
    private final Stack<DataFrame> stack = new Stack<>();
    private int instructionPointer = 0;


    public VirtualMachine(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Stack<DataFrame> getStack() {
        return stack;
    }

    public String printStack() {
        int stackPos = 0;
        StringBuilder sb = new StringBuilder("-- BOTTOM --\n");
        for (DataFrame se : stack) {
            sb.append(stackPos).append(" - ").append(se).append("\n");
            stackPos++;
        }
        sb.append("-- STACK TOP --");
        return sb.toString();
    }

    public void executeAll() {
        while (instructions.get(instructionPointer).mnemonic != Instruction.Mnemonic.STP) {
            executeStep();
        }
    }

    public void executeStep() {
        // TODO
        Instruction ins = instructions.get(instructionPointer);
        System.out.printf("Instruction Pointer: %d\n", instructionPointer);
        System.out.println(ins);
        System.out.println(this.printStack());
        switch (ins.mnemonic) {
            case ADD -> add(ins);
            case DIV -> divide(ins);
            case MUL -> multply(ins);
            case SUB -> subtract(ins);
            case ALB -> allocateBoolean(ins);
            case ALI -> allocateInteger(ins);
            case ALR -> allocateFloat(ins);
            case ALS -> allocateLiteralValue(ins);
            case LDB -> loadBoolean(ins);
            case LDI -> loadInteger(ins);
            case LDR -> loadFloat(ins);
            case LDS -> loadLiteral(ins);
            case LDV -> loadValueAt(ins);
//                    STR,
            case AND -> logicalAnd(ins);
            case NOT -> logicalNOT(ins);
//                    OR,
//                    BGE,
//                    BGR,
//                    DIF,
//                    EQL,
//                    SME,
//                    SMR,
//                    JMF,
//                    JMP,
//                    JMT,
//                    STP,
//                    REA,
//                    WRT,
//                    STC
        }
        instructionPointer++;
    }

    // VM Instructions
    private void add(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = x_val + y_val;
            stack.push(new DataFrame(type, x_val));
        } else {
            var x_val = (Float) x.content;
            var y_val = (Float) y.content;
            x_val = x_val + y_val;
            stack.push(new DataFrame(type, x_val));
        }
    }

    private void divide(Instruction ins) {
        //TODO
    }

    private void multply(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = x_val * y_val;
            stack.push(new DataFrame(type, x_val));
        } else {
            var x_val = (Float) x.content;
            var y_val = (Float) y.content;
            x_val = x_val * y_val;
            stack.push(new DataFrame(type, x_val));
        }
    }

    private void subtract(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = y_val - x_val;
            stack.push(new DataFrame(type, x_val));
        } else {
            var x_val = (Float) x.content;
            var y_val = (Float) y.content;
            x_val = y_val - x_val;
            stack.push(new DataFrame(type, x_val));
        }
    }

    private void allocateBoolean(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(DataType.INTEGER, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.BOOLEAN, false));
        }
    }

    private void allocateInteger(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(DataType.INTEGER, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.INTEGER, 0));
        }
    }

    private void allocateFloat(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(DataType.INTEGER, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.FLOAT, 0f));
        }
    }

    private void allocateLiteralValue(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(DataType.INTEGER, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.LITERAL, ""));
        }
    }

    private void loadBoolean(Instruction ins) {
        if (ins.parameter.type != DataType.BOOLEAN) {
            invalidInstructionParameter(DataType.BOOLEAN, ins.parameter.type);
        }
        var content = (Boolean) ins.parameter.content;
        stack.push(new DataFrame(DataType.BOOLEAN, content));
    }

    private void loadInteger(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(DataType.INTEGER, ins.parameter.type);
        }
        var content = (Integer) ins.parameter.content;
        stack.push(new DataFrame(DataType.INTEGER, content));
    }

    private void loadFloat(Instruction ins) {
        if (ins.parameter.type != DataType.FLOAT) {
            invalidInstructionParameter(DataType.FLOAT, ins.parameter.type);
        }
        var content = (Float) ins.parameter.content;
        stack.push(new DataFrame(DataType.FLOAT, content));
    }

    private void loadLiteral(Instruction ins) {
        if (ins.parameter.type != DataType.LITERAL) {
            invalidInstructionParameter(DataType.LITERAL, ins.parameter.type);
        }
        var content = (String) ins.parameter.content;
        stack.push(new DataFrame(DataType.LITERAL, content));
    }

    private void loadValueAt(Instruction ins) {
        if (ins.parameter.type != DataType.ADDRESS) {
            invalidInstructionParameter(DataType.ADDRESS, ins.parameter.type);
        }
        var stackElement = stack.get((Integer) ins.parameter.content);
        stack.push(new DataFrame(stackElement.type, stackElement.content));
    }

    private void logicalAnd(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(Arrays.asList(DataType.BOOLEAN), ins.mnemonic, x, y);
        var x_val = (Boolean) x.content;
        var y_val = (Boolean) y.content;
        x_val = x_val & y_val;
        stack.push(new DataFrame(type, x_val));
    }

    private void logicalNOT(Instruction ins) {
        DataFrame x = stack.pop();
        var type = checkType(Arrays.asList(DataType.BOOLEAN), ins.mnemonic, x, x);
        var x_val = !(Boolean) x.content;
        stack.push(new DataFrame(type, x_val));
    }

    private static DataType checkType(List<DataType> compatibleTypes, Instruction.Mnemonic mnemonic, DataFrame x, DataFrame y) {
        DataType effectiveOutputDataType = null;
        boolean compatibleTypesFlag = !(compatibleTypes.contains(x.type)) && !(compatibleTypes.contains(y.type));
        if (!compatibleTypesFlag) {
            switch (x.type) {
                case FLOAT -> {
                    switch (y.type) {
                        case FLOAT, INTEGER -> effectiveOutputDataType = DataType.FLOAT;
                    }
                }
                case INTEGER -> {
                    switch (y.type) {
                        case FLOAT -> effectiveOutputDataType = DataType.FLOAT;
                        case INTEGER -> effectiveOutputDataType = DataType.INTEGER;
                    }
                }
                case BOOLEAN -> {
                    switch (y.type) {
                        case BOOLEAN -> effectiveOutputDataType = DataType.BOOLEAN;
                    }
                }
            }
        } else {
            throw new RuntimeException(String.format("Incompatible stack data types for instruction %s! Parameter x: %s, Parameter y: %s", mnemonic, x.type, y.type));
        }
        if (effectiveOutputDataType == null) {
            throw new RuntimeException(String.format("Incompatible stack data types! Parameter x: %s, Parameter y: %s", x.type, y.type));
        }
        return effectiveOutputDataType;
    }

    private static void invalidInstructionParameter(DataType expected, DataType got) {
        throw new RuntimeException(String.format("Invalid instruction, expected %s parameter, got: %s", expected, got));
    }
}
