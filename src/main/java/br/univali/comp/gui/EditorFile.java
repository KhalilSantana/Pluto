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

    // Get canonical path / absolute path
    public Optional<String> getFilePath() {
        if (getFileStatus() == FileStatus.OK) {
            return Optional.of(file.getAbsolutePath());
        }
        return Optional.empty();
    }

    public FileStatus save(String contents) {
        return saveAs(contents, this.file);
    }

    public FileStatus saveAs(String contents, File target) {
        try {
            FileUtils.write(target, contents, StandardCharsets.UTF_8);
            this.file = target;
        } catch (IOException e) {
            e.printStackTrace();
            return FileStatus.IO_ERROR;
        }
        return FileStatus.OK;
    }

    public boolean hasValidExtension() {
        boolean hasValidExt = FilenameUtils.isExtension(file.getName(), "txt");
        if (hasValidExt) {
            return true;
        }
        System.err.printf("File doesn't have a valid extension, want %s, have %s\n", FILE_EXT, FilenameUtils.isExtension(file.getName(), "txt"));
        return false;
    }

    public FileStatus getFileStatus() {
        if (file == null) {
            return FileStatus.NO_OPEN_FILE;
        }
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
        NO_OPEN_FILE,
    }
}
