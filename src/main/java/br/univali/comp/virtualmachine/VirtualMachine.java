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
//                    LDB,
            case LDI -> loadInteger(ins.parameter);
//                    LDR,
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

    private void loadInteger(InstructionParameter parameter) {
        System.out.println("LOAD");
        if (parameter.type != ParameterType.INTEGER_CONSTANT) {
            throw new RuntimeException(String.format("Invalid instruction, expected integer constant parameter, got: %s", parameter.type));
        }
        var content = (Integer) parameter.content;
        stack.push(new StackElement(content, DataType.INTEGER));
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
}
