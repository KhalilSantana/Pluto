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
                contentStr = (content).toString();
            }
            case INTEGER, ADDRESS -> {
                contentStr = ((Integer) content).toString();
            }
            case BOOLEAN -> {
                contentStr = ((Boolean) content).toString();
            }
            case LITERAL -> {
                contentStr = String.valueOf(content);
            }
        }
        if (contentStr == null) {
            return "0";
        }
        return String.format("%s", contentStr);
    }

    public String toDebugString() {
        return String.format("%s T: %s", this.toString(), type);
    }
}
