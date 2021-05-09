package br.univali.comp.cli;

import br.univali.comp.gui.EditorFile;
import br.univali.comp.javacc.gen.ParseException;
import br.univali.comp.javacc.gen.Sintatico;
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
        Sintatico s = new Sintatico(targetStream);
        String output = s.analyze(args, editorFile.getFileContents());
        System.out.println(output);
    }
}
