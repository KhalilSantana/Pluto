package br.univali.comp.virtualmachine;

public class Instruction {
    public final InstructionMnemonic mnemonic;
    public final InstructionParameter parameter;

    public Instruction(InstructionMnemonic mnemonic, InstructionParameter parameter) {
        this.mnemonic = mnemonic;
        this.parameter = parameter;
    }
}
