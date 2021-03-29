package br.univali.comp.gui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class EditorFile {
    private final String FILE_EXT = ".txt";
    private File file = null;

    public EditorFile(File file) {
        this.file = file;
    }

    public String getFileContents() throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    // Get cannonical path / absolute path
    public Optional<String> getFilePath() {
        if (getFileStatus() == FileStatus.OK) {
            return Optional.of(file.getAbsolutePath());
        }
        return Optional.empty();
    }

    public void writeToFile(String contents) throws IOException {
        FileUtils.write(file, contents, StandardCharsets.UTF_8);
    }

    public boolean hasValidExtension() {
        boolean hasValidExt = FilenameUtils.isExtension(file.getName(), "txt");
        if (hasValidExt) {
            return true;
        }
        System.err.printf("File doesn't have a vaild extension, want %s, have %s\n", FILE_EXT, FilenameUtils.isExtension(file.getName(), "txt"));
        return false;
    }

    public FileStatus getFileStatus() {
        if (!hasValidExtension()) {
            return FileStatus.INVALID_EXTENSION;
        }
        return FileStatus.OK;
    }

    public boolean isFileStatusOK() {
        return this.getFileStatus() == FileStatus.OK;
    }

    public enum FileStatus {
        OK,
        IO_ERROR,
        INVALID_EXTENSION,
    }
}
