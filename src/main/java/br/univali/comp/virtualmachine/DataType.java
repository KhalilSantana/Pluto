package br.univali.comp.virtualmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DataType {
    LOGIC,
    INTEGER,
    FLOAT,
    LITERAL;

    public static List<DataType> getNumericDataTypes() {
        return new ArrayList<>(Arrays.asList(DataType.FLOAT, DataType.INTEGER));
    }
}
