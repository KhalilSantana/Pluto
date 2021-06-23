package br.univali.comp.virtualmachine;

public class Instruction implements Comparable<Instruction> {
    public final Integer number;
    public final Mnemonic mnemonic;
    public final DataFrame parameter;

    public Instruction(Mnemonic mnemonic, DataFrame parameter) {
        this.number = 0;
        this.mnemonic = mnemonic;
        this.parameter = parameter;
    }

    public Instruction(Integer number, Mnemonic mnemonic, DataFrame parameter) {
        this.number = number;
        this.mnemonic = mnemonic;
        this.parameter = parameter;
    }

    // These are needed for TableView to bind and display the object
    public Integer getNumber() {
        return number;
    }

    public Mnemonic getMnemonic() {
        return mnemonic;
    }

    public DataFrame getParameter() {
        return parameter;
    }



    @Override
    public String toString() {
        return String.format("Instruction %s - %s", mnemonic, parameter);
    }

    @Override
    public int compareTo(Instruction o) {
        return this.number.compareTo(o.number);
    }

    public enum Mnemonic {
        ADD,
        DIV,
        MUL,
        SUB,
        ALB,
        ALI,
        ALR,
        ALS,
        LDB,
        LDI,
        LDR,
        LDS,
        LDV,
        STR,
        AND,
        NOT,
        OR,
        BGE,
        BGR,
        DIF,
        EQL,
        SME,
        SMR,
        JMF,
        JMP,
        JMT,
        STP,
        REA,
        WRT,
        STC
    }
}
