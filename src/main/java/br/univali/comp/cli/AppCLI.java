package br.univali.comp.cli;

import br.univali.comp.gui.EditorFile;
import br.univali.comp.javacc.gen.ParseException;
import br.univali.comp.javacc.gen.Linguagem2021_1;
import br.univali.comp.recovery.*;
import br.univali.comp.recovery.ParseEOFException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AppCLI {
    // Dummy class for manual testing

    public static void main(String[] args) throws IOException, ParseException, ParseEOFException {
        // This will analyze the file in Pluto/tests/hardcoded.txt
        EditorFile editorFile = new EditorFile(new File("tests/hardcoded.txt"), false);
        String fileContents = editorFile.getFileContents();
        InputStream targetStream = new java.io.ByteArrayInputStream(fileContents.getBytes());
        Linguagem2021_1 s = new Linguagem2021_1(targetStream);
        s.compile(fileContents);
    }
}
