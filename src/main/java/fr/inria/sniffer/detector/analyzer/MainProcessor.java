package fr.inria.sniffer.detector.analyzer;

import fr.inria.sniffer.detector.analyzer.spoon.FilteredFileSystemFolder;
import fr.inria.sniffer.detector.entities.PaprikaApp;
import fr.inria.sniffer.detector.entities.PaprikaClass;
import fr.inria.sniffer.detector.entities.PaprikaMethod;
import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sarra on 21/02/17.
 */
public class MainProcessor {

    static PaprikaApp currentApp;
    static PaprikaClass currentClass;
    static PaprikaMethod currentMethod;
    static ArrayList<URL> paths = new ArrayList<>();
    String appPath;
    private String jarsPath;
    private String sdkPath;
    private List<String> excluded;

    public MainProcessor(String appName, int appVersion, int commitNumber, String status, String appKey, String appPath, String sdkPath, String jarsPath, int sdkVersion, String module, List<String> excluded) {
        if (excluded == null) {
            this.excluded = Collections.emptyList();
        } else {
            this.excluded = excluded;
        }
        this.currentApp = PaprikaApp.createPaprikaApp(appName, appVersion, commitNumber, status, appKey, appPath, sdkVersion, module);
        currentClass = null;
        currentMethod = null;
        this.appPath = appPath;
        this.jarsPath = jarsPath;
        this.sdkPath = sdkPath;
    }

    public void process() {
        Launcher launcher = new Launcher();

        FilteredFileSystemFolder filteredPath = new FilteredFileSystemFolder(appPath);
        filteredPath.removeAllThatMatch(excluded);

        launcher.addInputResource(filteredPath);
        launcher.getEnvironment().setNoClasspath(true);
        launcher.buildModel();

        AbstractProcessor<CtClass> classProcessor = new ClassProcessor();
        AbstractProcessor<CtInterface> interfaceProcessor = new InterfaceProcessor();
        launcher.addProcessor(classProcessor);
        launcher.addProcessor(interfaceProcessor);
        launcher.process();
    }
}
