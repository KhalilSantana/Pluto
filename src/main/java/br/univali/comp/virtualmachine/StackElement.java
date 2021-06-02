package br.univali.comp.virtualmachine;

public class StackElement {
    public final Object content;
    public final DataType dataType;

    public StackElement(Object content, DataType dataType) {
        this.content = content;
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        String contentStr = null;
        switch (dataType) {
            case FLOAT -> {
                contentStr = ((Float) content).toString();
            }
            case INTEGER -> {
                contentStr = ((Integer) content).toString();
            }
            case LOGIC -> {
                contentStr = ((Boolean) content).toString();
            }

            case LITERAL -> {
                contentStr = (String) content;
            }
        }

        return String.format("C: %s; T: %s", contentStr, dataType);
    }
}