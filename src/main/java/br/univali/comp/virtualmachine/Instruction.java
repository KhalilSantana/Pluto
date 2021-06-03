package br.univali.comp.virtualmachine;

public class Instruction {
    public final Mnemonic mnemonic;
    public final DataFrame parameter;

    public Instruction(Mnemonic mnemonic, DataFrame parameter) {
        this.mnemonic = mnemonic;
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return String.format("Instruction %s - %s", mnemonic, parameter);
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
