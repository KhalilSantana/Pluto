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
        switch (ins.mnemonic) {
            case ADD -> add(ins.parameter);
            case DIV -> divide(ins.parameter);
            case MUL -> multply(ins.parameter);
            case SUB -> subtract(ins.parameter);
            case ALB -> allocateBoolean(ins.parameter);
            case ALI -> allocateInteger(ins.parameter);
            case ALR -> allocateFloat(ins.parameter);
            case ALS -> allocateLiteralValue(ins.parameter);
            case LDB -> loadBoolean(ins.parameter);
            case LDI -> loadInteger(ins.parameter);
            case LDR -> loadFloat(ins.parameter);
            case LDS -> loadLiteral(ins.parameter);
            case LDV -> loadValueAt(ins.parameter);
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
        System.out.println(this.printStack());
        System.out.println(instructionPointer);
        instructionPointer++;
    }

    // VM Instructions
    private void add(InstructionParameter parameter) {
        System.out.println("ADD");
        StackElement x = stack.pop();
        StackElement y = stack.pop();
        var type = checkType(x, y);
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

    private void divide(InstructionParameter parameter) {
        //TODO
    }

    private void multply(InstructionParameter parameter) {
        //TODO
    }

    private void subtract(InstructionParameter parameter) {
        System.out.println("SUB");
        StackElement x = stack.pop();
        StackElement y = stack.pop();
        var type = checkType(x, y);
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

    private void allocateBoolean(InstructionParameter parameter) {
        System.out.println("ALB");
        if (parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, parameter.type);
        }
        for (int i = 0; i < (Integer) parameter.content; i++) {
            stack.push(new StackElement(false, DataType.LOGIC));
        }
    }

    private void allocateInteger(InstructionParameter parameter) {
        System.out.println("ALI");
        if (parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, parameter.type);
        }
        for (int i = 0; i < (Integer) parameter.content; i++) {
            stack.push(new StackElement(0, DataType.INTEGER));
        }
    }

    private void allocateFloat(InstructionParameter parameter) {
        System.out.println("ALR");
        if (parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, parameter.type);
        }
        for (int i = 0; i < (Integer) parameter.content; i++) {
            stack.push(new StackElement(0f, DataType.FLOAT));
        }
    }

    private void allocateLiteralValue(InstructionParameter parameter) {
        System.out.println("ALS");
        if (parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, parameter.type);
        }
        for (int i = 0; i < (Integer) parameter.content; i++) {
            stack.push(new StackElement("", DataType.LITERAL));
        }
    }

    private void loadBoolean(InstructionParameter parameter) {
        System.out.println("LDB");
        if (parameter.type != ParameterType.BOOLEAN_CONSTANT) {
            invalidInstructionParameter(ParameterType.BOOLEAN_CONSTANT, parameter.type);
        }
        var content = (Boolean) parameter.content;
        stack.push(new StackElement(content, DataType.LOGIC));
    }
    private void loadInteger(InstructionParameter parameter) {
        System.out.println("LOAD");
        if (parameter.type != ParameterType.INTEGER_CONSTANT) {
            invalidInstructionParameter(ParameterType.INTEGER_CONSTANT, parameter.type);
        }
        var content = (Integer) parameter.content;
        stack.push(new StackElement(content, DataType.INTEGER));
    }

    private void loadFloat(InstructionParameter parameter) {
        System.out.println("LDR");
        if (parameter.type != ParameterType.FLOAT_CONSTANT) {
            invalidInstructionParameter(ParameterType.FLOAT_CONSTANT, parameter.type);
        }
        var content = (Float) parameter.content;
        stack.push(new StackElement(content, DataType.FLOAT));
    }

    private void loadLiteral(InstructionParameter parameter) {
        System.out.println("LDS");
        if (parameter.type != ParameterType.LITERAL_CONSTANT) {
            invalidInstructionParameter(ParameterType.LITERAL_CONSTANT, parameter.type);
        }
        var content = (String) parameter.content;
        stack.push(new StackElement(content, DataType.LITERAL));
    }

    private void loadValueAt(InstructionParameter parameter) {
        System.out.println("LDV");
        if (parameter.type != ParameterType.ADDRESS) {
            invalidInstructionParameter(ParameterType.ADDRESS, parameter.type);
        }
        var stackElement = stack.get((Integer) parameter.content);
        stack.push(new StackElement(stackElement.content, stackElement.dataType));
    }

    private static DataType checkType(StackElement x, StackElement y) {
        DataType effectiveOutputDataType = null;
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
        if (effectiveOutputDataType == null) {
            throw new RuntimeException(String.format("Incompatible stack data types! Parameter x: %s, Parameter y: %s", x.dataType, y.dataType));
        }
        return effectiveOutputDataType;
    }

    private static void invalidInstructionParameter(ParameterType expected, ParameterType got) {
        throw new RuntimeException(String.format("Invalid instruction, expected %s parameter, got: %s", expected, got));
    }
}
