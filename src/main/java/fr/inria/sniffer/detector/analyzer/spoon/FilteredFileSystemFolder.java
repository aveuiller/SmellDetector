package fr.inria.sniffer.detector.analyzer.spoon;

import spoon.compiler.SpoonFile;
import spoon.support.compiler.FileSystemFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Add exclusion on pattern for a {@link FileSystemFolder}.
 * TODO: This class should be re-distributed to https://github.com/inria/spoon
 */
public class FilteredFileSystemFolder extends FileSystemFolder {
    private List<String> regexes;

    public FilteredFileSystemFolder(File file) {
        super(file);
        regexes = new ArrayList<>();
    }

    public FilteredFileSystemFolder(String path) {
        super(path);
        regexes = new ArrayList<>();
    }

    @Override
    public List<SpoonFile> getAllFiles() {
        return filterExcluded(super.getAllFiles());
    }

    @Override
    public List<SpoonFile> getAllJavaFiles() {
        return filterExcluded(super.getAllJavaFiles());
    }

    /**
     * Filter out the excluded patterns from all the found files.
     *
     * @param allFiles The initially found files in the {@link FileSystemFolder}.
     * @return the filtered list of remaining {@link SpoonFile}.
     */
    private List<SpoonFile> filterExcluded(List<SpoonFile> allFiles) {
        List<SpoonFile> filtered = new ArrayList<>();
        for (SpoonFile file : allFiles) {
            if (!file.getPath().matches(this.getRegex())) {
                filtered.add(file);
            }
        }
        return filtered;
    }

    private String getRegex() {
        return "(" + String.join("|", regexes) + ")";
    }

    public FilteredFileSystemFolder removeAllThatMatch(String regex) {
        this.regexes.add(regex);
        return this;
    }

    public FilteredFileSystemFolder removeAllThatMatch(List<String> regex) {
        this.regexes.addAll(regex);
        return this;
    }

}
