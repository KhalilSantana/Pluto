package br.univali.comp.virtualmachine;

public class InstructionParameter {
    public final ParameterType type;
    public final Object content;

    public InstructionParameter(ParameterType type, Object parameter) {
        this.type = type;
        this.content = parameter;
    }
}
