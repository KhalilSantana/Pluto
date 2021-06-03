package br.univali.comp.virtualmachine;

public class InstructionParameter {
    public final ParameterType type;
    public final Object content;

    public InstructionParameter(ParameterType type, Object parameter) {
        this.type = type;
        this.content = parameter;
    }

    @Override
    public String toString() {
        String contentStr = null;
        switch (type) {
            case FLOAT_CONSTANT -> {
                contentStr = ((Float) content).toString();
            }
            case INTEGER_CONSTANT -> {
                contentStr = ((Integer) content).toString();
            }
            case BOOLEAN_CONSTANT -> {
                contentStr = ((Boolean) content).toString();
            }
            case LITERAL_CONSTANT -> {
                contentStr = (String) content;
            }
        }

        return String.format("C: %s; T: %s", contentStr, type);
    }
}
