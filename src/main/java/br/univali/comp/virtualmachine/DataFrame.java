package br.univali.comp.virtualmachine;

public class DataFrame {
    public final Object content;
    public final DataType type;

    public DataFrame(DataType type, Object content) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        String contentStr = null;
        switch (type) {
            case FLOAT -> {
                contentStr = ((Float) content).toString();
            }
            case INTEGER -> {
                contentStr = ((Integer) content).toString();
            }
            case BOOLEAN -> {
                contentStr = ((Boolean) content).toString();
            }
            case LITERAL -> {
                contentStr = (String) content;
            }
        }

        return String.format("C: %s; T: %s", contentStr, type);
    }
}
