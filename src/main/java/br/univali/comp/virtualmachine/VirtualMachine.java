package br.univali.comp.virtualmachine;

import java.util.*;

public class VirtualMachine {
    private final List<Instruction> instructions;
    private final Stack<DataFrame> stack = new Stack<>();
    private int instructionPointer = 0;
    private VMStatus status = VMStatus.NOT_STARTED;
    private DataType syscallDataType = null;
    private Object syscallData = null;

    public VirtualMachine(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Stack<DataFrame> getStack() {
        return stack;
    }

    public VMStatus getStatus() {
        return status;
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

    public void resumeExecution() {
        if (status == VMStatus.SYSCALL_IO_READ) {
            stack.push(new DataFrame(syscallDataType, syscallData));
        }
        this.syscallData = null;
        this.syscallDataType = null;
    }

    public DataType getSyscallDataType() {
        return syscallDataType;
    }

    public void setSyscallDataType(DataType syscallDataType) {
        this.syscallDataType = syscallDataType;
    }

    public Object getSyscallData() {
        return syscallData;
    }

    public void setSyscallData(Object syscallData) {
        this.syscallData = syscallData;
    }

    public void executeAll() {
        while (this.status != VMStatus.HALTED) {
            // IF we returned from a syscall/IO operation
            if (status == VMStatus.SYSCALL_IO_READ || status == VMStatus.SYSCALL_IO_WRITE) {
                resumeExecution();
            }
            executeStep();
            // If the last instruction was a syscall, pause execution until it completes
            if (status == VMStatus.SYSCALL_IO_READ || status == VMStatus.SYSCALL_IO_WRITE) {
                break;
            }
        }
    }

    public void executeStep() {
        // TODO
        status = VMStatus.RUNNING;
        Instruction ins = instructions.get(instructionPointer);
        System.out.printf("Instruction Pointer: %d\n", instructionPointer+1);
        System.out.println(ins);
        switch (ins.mnemonic) {
            case ADD -> add(ins);
            case DIV -> divide(ins);
            case MUL -> multiply(ins);
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
            case STR -> storeValueAt(ins);
            case AND -> logicalAnd(ins);
            case NOT -> logicalNOT(ins);
            case OR -> logicalOr(ins);
            case BGE -> relationalGreaterOrEquals(ins);
            case BGR -> relationalGreater(ins);
            case DIF -> relationalDifferent(ins);
            case EQL -> relationalEquals(ins);
            case SME -> relationalLessOrEquals(ins);
            case SMR -> relationalLess(ins);
            case JMF -> jumpFalseToAddress(ins);
            case JMP -> jumpToAddress(ins);
            case JMT -> jumpTrueToAddress(ins);
            case STP -> {
                this.status = VMStatus.HALTED;
                return;
            }
            case REA -> read(ins);
            case WRT -> write(ins);
//                    STC
        }
        System.out.println(this.printStack());
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
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            float result = x_val + y_val;
            stack.push(new DataFrame(type, result));
        }
    }

    private void divide(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = y_val / x_val;
            stack.push(new DataFrame(type, x_val));
        } else {
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            float result = y_val / x_val;
            stack.push(new DataFrame(type, result));
        }
    }

    private void multiply(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            x_val = x_val * y_val;
            stack.push(new DataFrame(type, x_val));
        } else {
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            float result = x_val * y_val;
            stack.push(new DataFrame(type, result));
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
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            float result = y_val - x_val;
            stack.push(new DataFrame(type, result));
        }
    }

    private void allocateBoolean(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(Collections.singletonList(DataType.INTEGER), ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.BOOLEAN, false));
        }
    }

    private void allocateInteger(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(Collections.singletonList(DataType.INTEGER), ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.INTEGER, 0));
        }
    }

    private void allocateFloat(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(Collections.singletonList(DataType.INTEGER), ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.FLOAT, 0f));
        }
    }

    private void allocateLiteralValue(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(Collections.singletonList(DataType.INTEGER), ins.parameter.type);
        }
        for (int i = 0; i < (Integer) ins.parameter.content; i++) {
            stack.push(new DataFrame(DataType.LITERAL, ""));
        }
    }

    private void loadBoolean(Instruction ins) {
        if (ins.parameter.type != DataType.BOOLEAN) {
            invalidInstructionParameter(Collections.singletonList(DataType.BOOLEAN), ins.parameter.type);
        }
        var content = (Boolean) ins.parameter.content;
        stack.push(new DataFrame(DataType.BOOLEAN, content));
    }

    private void loadInteger(Instruction ins) {
        if (ins.parameter.type != DataType.INTEGER) {
            invalidInstructionParameter(Collections.singletonList(DataType.INTEGER), ins.parameter.type);
        }
        var content = (Integer) ins.parameter.content;
        stack.push(new DataFrame(DataType.INTEGER, content));
    }

    private void loadFloat(Instruction ins) {
        if (ins.parameter.type != DataType.FLOAT) {
            invalidInstructionParameter(Collections.singletonList(DataType.FLOAT), ins.parameter.type);
        }
        var content = (Float) ins.parameter.content;
        stack.push(new DataFrame(DataType.FLOAT, content));
    }

    private void loadLiteral(Instruction ins) {
        if (ins.parameter.type != DataType.LITERAL) {
            invalidInstructionParameter(Collections.singletonList(DataType.LITERAL), ins.parameter.type);
        }
        var content = (String) ins.parameter.content;
        stack.push(new DataFrame(DataType.LITERAL, content));
    }

    private void storeValueAt(Instruction ins) {
        if (ins.parameter.type != DataType.ADDRESS) {
            invalidInstructionParameter(Collections.singletonList(DataType.ADDRESS), ins.parameter.type);
        }
        // stack starts at 1 on the grammar level, so we must offset that with a -1.
        var stackElement = stack.pop();
        stack.set( (Integer)ins.parameter.content - 1,
                new DataFrame(stackElement.type, stackElement.content)
        );
    }

    private void loadValueAt(Instruction ins) {
        if (ins.parameter.type != DataType.ADDRESS) {
            invalidInstructionParameter(Collections.singletonList(DataType.ADDRESS), ins.parameter.type);
        }
        // stack starts at 1 on the grammar level, so we must offset that with a -1.
        var stackElement = stack.get((Integer) ins.parameter.content - 1);
        stack.push(new DataFrame(stackElement.type, stackElement.content));
    }

    private void logicalAnd(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(Collections.singletonList(DataType.BOOLEAN), ins.mnemonic, x, y);
        var x_val = (Boolean) x.content;
        var y_val = (Boolean) y.content;
        x_val = x_val & y_val;
        stack.push(new DataFrame(type, x_val));
    }

    private void logicalNOT(Instruction ins) {
        DataFrame x = stack.pop();
        var type = checkType(Collections.singletonList(DataType.BOOLEAN), ins.mnemonic, x, x);
        var x_val = !(Boolean) x.content;
        stack.push(new DataFrame(type, x_val));
    }

    private void logicalOr(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(Collections.singletonList(DataType.BOOLEAN), ins.mnemonic, x, y);
        var x_val = (Boolean) x.content;
        var y_val = (Boolean) y.content;
        x_val = x_val || y_val;
        stack.push(new DataFrame(type, x_val));
    }

    private void relationalGreaterOrEquals(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            var flag = y_val >= x_val;
            stack.push(new DataFrame(DataType.BOOLEAN, flag));
        } else {
            var x_val = (Float) x.content;
            var y_val = (Float) y.content;
            var flag = y_val >= x_val;
            stack.push(new DataFrame(DataType.BOOLEAN, flag));
        }
    }

    private void relationalGreater(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            var flag = y_val > x_val;
            stack.push(new DataFrame(DataType.BOOLEAN, flag));
        } else {
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            Boolean result = y_val > x_val;
            stack.push(new DataFrame(DataType.BOOLEAN, result));
        }
    }

    private void relationalDifferent(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        var result = false;
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            result = !y_val.equals(x_val);
        } else {
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            result = y_val != x_val;
        }
        stack.push(new DataFrame(DataType.BOOLEAN, result));
    }

    private void relationalEquals(Instruction ins) {
        DataFrame x = stack.pop();
        DataFrame y = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, x, y);
        var result = false;
        if (type == DataType.INTEGER) {
            var x_val = (Integer) x.content;
            var y_val = (Integer) y.content;
            result = y_val.equals(x_val);
        } else {
            var x_val = ((Number) x.content).floatValue();
            var y_val = ((Number) y.content).floatValue();
            result = y_val == x_val;
        }
        stack.push(new DataFrame(DataType.BOOLEAN, result));
    }

    private void relationalLessOrEquals(Instruction ins) {
        DataFrame top = stack.pop();
        DataFrame sub = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, top, sub);
        var result = false;
        if (type == DataType.INTEGER) {
            var top_val = (Integer) top.content;
            var sub_val = (Integer) sub.content;
            result = sub_val <= top_val;
        } else {
            var top_val = (Float) top.content;
            var sub_val = (Float) sub.content;
            result = sub_val <= top_val;
        }
        stack.push(new DataFrame(DataType.BOOLEAN, result));
    }

    private void relationalLess(Instruction ins) {
        DataFrame top = stack.pop();
        DataFrame sub = stack.pop();
        var type = checkType(DataType.getNumericDataTypes(), ins.mnemonic, top, sub);
        var result = false;
        if (type == DataType.INTEGER) {
            var top_val = (Integer) top.content;
            var sub_val = (Integer) sub.content;
            result = sub_val < top_val;
        } else {
            var top_val = (Float) top.content;
            var sub_val = (Float) sub.content;
            result = sub_val < top_val;
        }
        stack.push(new DataFrame(DataType.BOOLEAN, result));
    }


    private void jumpFalseToAddress(Instruction ins) {
        checkType(Collections.singletonList(DataType.ADDRESS), ins.mnemonic, ins.parameter, ins.parameter);
        var top = stack.pop();
        checkType(Collections.singletonList(DataType.BOOLEAN), ins.mnemonic, top, top);
        if (!(Boolean) top.content) {
            instructionPointer = (Integer) ins.parameter.content;
            instructionPointer--; // We always add +1 after each instruction, this will revert that
        }
    }

    private void jumpToAddress(Instruction ins) {
        checkType(Collections.singletonList(DataType.ADDRESS), ins.mnemonic, ins.parameter, ins.parameter);
        instructionPointer = (Integer) ins.parameter.content;
        instructionPointer--; // We always add +1 after each instruction, this will revert that
    }

    private void jumpTrueToAddress(Instruction ins) {
        checkType(Collections.singletonList(DataType.ADDRESS), ins.mnemonic, ins.parameter, ins.parameter);
        var top = stack.pop();
        checkType(Collections.singletonList(DataType.BOOLEAN), ins.mnemonic, top, top);
        if ((Boolean) top.content) {
            instructionPointer = (Integer) ins.parameter.content;
            instructionPointer--; // We always add +1 after each instruction, this will revert that
        }
    }

    private void read(Instruction ins) {
        var acceptedTypes = DataType.getNumericDataTypes();
        acceptedTypes.add(DataType.LITERAL);
        if (!acceptedTypes.contains(ins.parameter.type)) {
            invalidInstructionParameter(acceptedTypes, ins.parameter.type);
        }
        this.status = VMStatus.SYSCALL_IO_READ;
        this.syscallDataType = ins.parameter.type;
    }

    private void write(Instruction ins) {
        var stackElement = stack.pop();
        checkType(Arrays.asList(DataType.INTEGER, DataType.FLOAT, DataType.LITERAL),
                ins.mnemonic, stackElement, stackElement);
        this.status = VMStatus.SYSCALL_IO_WRITE;
        this.syscallDataType = stackElement.type;
        this.syscallData = stackElement.content;
    }

    private static DataType checkType(List<DataType> compatibleTypes, Instruction.Mnemonic mnemonic, DataFrame x, DataFrame y) {
        DataType effectiveOutputDataType = null;
        boolean compatibleTypesFlag = !(compatibleTypes.contains(x.type)) && !(compatibleTypes.contains(y.type));
        if (!compatibleTypesFlag) {
            switch (x.type) {
                // ADD, MUL, SUB, DIV
                case FLOAT -> {
                    switch (y.type) {
                        case FLOAT, INTEGER -> effectiveOutputDataType = DataType.FLOAT;
                    }
                }
                // ADD, MUL, SUB, DIV
                case INTEGER -> {
                    switch (y.type) {
                        case FLOAT -> effectiveOutputDataType = DataType.FLOAT;
                        case INTEGER -> effectiveOutputDataType = DataType.INTEGER;
                    }
                }
                // LDB
                case BOOLEAN -> {
                    if (y.type == DataType.BOOLEAN) {
                        effectiveOutputDataType = DataType.BOOLEAN;
                    }
                }
                // JMP
                case ADDRESS -> {
                    if (y.type == DataType.ADDRESS) {
                        effectiveOutputDataType = DataType.ADDRESS;
                    }
                }
                case LITERAL -> {
                    if (y.type == DataType.LITERAL) {
                        effectiveOutputDataType = DataType.LITERAL;
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

    private static void invalidInstructionParameter(List<DataType> expected, DataType got) {
        throw new RuntimeException(String.format("Invalid instruction, expected %s parameter, got: %s", expected, got));
    }
}
