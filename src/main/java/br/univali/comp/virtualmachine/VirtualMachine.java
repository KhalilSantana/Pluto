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
        StringBuilder sb = new StringBuilder("[");
        stack.forEach(entry -> {
            sb.append(entry).append(", ");
        });
        sb.append("]");
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
//                    ALB,
//                    ALI,
//                    ALR,
//                    ALS,
            case LDB -> loadBoolean(ins.parameter);
            case LDI -> loadInteger(ins.parameter);
            case LDR -> loadFloat(ins.parameter);
//                    LDS,
//                    LDV,
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
            var x_val = (Double) x.content;
            var y_val = (Double) y.content;
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
        //TODO
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

    private static DataType checkType(StackElement x, StackElement y) {
        boolean sameType = x.dataType == y.dataType;
        DataType effectiveOutputDataType = null;
        if (sameType) {
            return x.dataType;
        }
        switch (x.dataType) {
            case FLOAT -> {
                if (y.dataType == DataType.INTEGER) {
                    effectiveOutputDataType = DataType.FLOAT;
                }
            }
            case INTEGER -> {
                if (y.dataType == DataType.FLOAT) {
                    effectiveOutputDataType = DataType.FLOAT;
                }
            }
        }
        if (effectiveOutputDataType == null) {
            throw new RuntimeException(String.format("Incompatible stack data types! Parameter x: %s, Parameter y: %s", x.dataType, y.dataType));
        }
        return effectiveOutputDataType;
    }

    private static RuntimeException invalidInstructionParameter(ParameterType expected, ParameterType got) {
        throw new RuntimeException(String.format("Invalid instruction, expected %s parameter, got: %s", expected, got));
    }
}
