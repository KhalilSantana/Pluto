package br.univali.comp.util;

import java.util.List;

public class AppMetadataHelper {
    // TODO: Maybe use java propreties here?
    public static List<String> getAuthors() {
        return List.of(
                "Khalil Santana",
                "Aream Luersen",
                "Guilherme Martins"
        );
    }

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }
}
