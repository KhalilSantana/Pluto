package br.univali.comp.virtualmachine;

import java.util.List;
import java.util.Stack;

public class VirtualMachine {
    private final List<Instruction> instructions;
    private final Stack<StackElement> stack = new Stack<>();
    private int instructionPointer = 0;


    public VirtualMachine(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Stack<StackElement> getStack() {
        return stack;
    }

    public String printStack() {
        int stackPos = 0;
        StringBuilder sb = new StringBuilder("-- BOTTOM --\n");
        for (StackElement se : stack) {
            sb.append(stackPos).append(" - ").append(se).append("\n");
            stackPos++;
        }
        sb.append("-- STACK TOP --");
        return sb.toString();
    }

    public void executeAll() {
        while (instructions.get(instructionPointer).mnemonic != InstructionMnemonic.STP) {
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
//                    AND,
//                    NOT,
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
        StackElement x = stack.pop();
        StackElement y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = x_val + y_val;
            stack.push(new StackElement(x_val, type));
        } else {
            var x_val = (Float) x.content;
            var y_val = (Float) y.content;
            x_val = x_val + y_val;
            stack.push(new StackElement(x_val, type));
        }
    }

    private void divide(Instruction ins) {
        //TODO
    }

    private void multply(Instruction ins) {
        //TODO
    }

    private void subtract(Instruction ins) {
        StackElement x = stack.pop();
        StackElement y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = y_val - x_val;
            stack.push(new StackElement(x_val, type));
        } else {
            var x_val = (Float) x.content;
            var y_val = (Float) y.content;
            x_val = y_val - x_val;
            stack.push(new StackElement(x_val, type));
        }
    }

    private void allocateBoolean(Instruction ins) {
        if (ins.parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new StackElement(false, DataType.LOGIC));
        }
    }

    private void allocateInteger(Instruction ins) {
        if (ins.parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new StackElement(0, DataType.INTEGER));
        }
    }

    private void allocateFloat(Instruction ins) {
        if (ins.parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new StackElement(0f, DataType.FLOAT));
        }
    }

    private void allocateLiteralValue(Instruction ins) {
        if (ins.parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new StackElement("", DataType.LITERAL));
        }
    }

    private void loadBoolean(Instruction ins) {
        if (ins.parameter.type != ParameterType.BOOLEAN_CONSTANT) {
            invalidInstructionParameter(ParameterType.BOOLEAN_CONSTANT, ins.parameter.type);
        }
        var content = (Boolean) ins.parameter.content;
        stack.push(new StackElement(content, DataType.LOGIC));
    }
    private void loadInteger(Instruction ins) {
        if (ins.parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, ins.parameter.type);
        }
        var content = (Integer) ins.parameter.content;
        stack.push(new StackElement(content, DataType.INTEGER));
    }

    private void loadFloat(Instruction ins) {
        if (ins.parameter.type != ParameterType.FLOAT_CONSTANT) {
            invalidInstructionParameter(ParameterType.FLOAT_CONSTANT, ins.parameter.type);
        }
        var content = (Float) ins.parameter.content;
        stack.push(new StackElement(content, DataType.FLOAT));
    }

    private void loadLiteral(Instruction ins) {
        if (ins.parameter.type != ParameterType.LITERAL_CONSTANT) {
            invalidInstructionParameter(ParameterType.LITERAL_CONSTANT, ins.parameter.type);
        }
        var content = (String) ins.parameter.content;
        stack.push(new StackElement(content, DataType.LITERAL));
    }

    private void loadValueAt(Instruction ins) {
        if (ins.parameter.type != ParameterType.ADDRESS) {
            invalidInstructionParameter(ParameterType.ADDRESS, ins.parameter.type);
        }
        var stackElement = stack.get((Integer) ins.parameter.content);
        stack.push(new StackElement(stackElement.content, stackElement.dataType));
    }

    private static DataType checkType(List<DataType> compatibleTypes, InstructionMnemonic mnemonic, StackElement x, StackElement y) {
        DataType effectiveOutputDataType = null;
        boolean compatibleTypesFlag = !(compatibleTypes.contains(x.dataType)) && !(compatibleTypes.contains(y.dataType));
        if (!compatibleTypesFlag) {
            switch (x.dataType) {
                case FLOAT -> {
                    switch (y.dataType) {
                        case FLOAT, INTEGER -> effectiveOutputDataType = DataType.FLOAT;
                    }
                }
                case INTEGER -> {
                    switch (y.dataType) {
                        case FLOAT -> effectiveOutputDataType = DataType.FLOAT;
                        case INTEGER -> effectiveOutputDataType = DataType.INTEGER;
                    }
                }
            }
        }
        else {
            throw new RuntimeException(String.format("Incompatible stack data types for instruction %s! Parameter x: %s, Parameter y: %s", mnemonic, x.dataType, y.dataType));
        }
        if (effectiveOutputDataType == null) {
            throw new RuntimeException(String.format("Incompatible stack data types! Parameter x: %s, Parameter y: %s", x.dataType, y.dataType));
        }
        return effectiveOutputDataType;
    }

    private static void invalidInstructionParameter(ParameterType expected, ParameterType got) {
        throw new RuntimeException(String.format("Invalid instruction, expected %s parameter, got: %s", expected, got));
    }
}
