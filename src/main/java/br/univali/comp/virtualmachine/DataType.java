package br.univali.comp.virtualmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DataType {
    INTEGER(1),
    FLOAT(2),
    LITERAL(3),
    BOOLEAN(4),
    ADDRESS(5),
    NONE(0);

    public final Integer id;

    private DataType(Integer value) {
        this.id = value;
    }

    public static DataType get(int id) {
        for (DataType d : values()) {
            if (d.id.equals(id)) {
                return d;
            }
        }
        return null;
    }


    public static List<DataType> getNumericDataTypes() {
        return new ArrayList<>(Arrays.asList(DataType.FLOAT, DataType.INTEGER));
    }
}
