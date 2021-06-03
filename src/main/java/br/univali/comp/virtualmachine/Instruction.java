package br.univali.comp.virtualmachine;

public class Instruction {
    public final InstructionMnemonic mnemonic;
    public final DataFrame parameter;

    public Instruction(InstructionMnemonic mnemonic, DataFrame parameter) {
        this.mnemonic = mnemonic;
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return String.format("Instruction %s - %s", mnemonic, parameter);
    }
}
