package com.sondeos.jamrunner.Runner;

import java.io.*;

public class JsRunner {
    public static final String REPO_NOT_EXISTS = "Repo not exists";
    private String repo;
    private String name;
    private String workspace;

    public JsRunner(String repo, String name) {
        this.repo = repo;
        this.name = name;
        workspace = "/tmp/";
    }

    public String run() {
        cloneReponsitory();
        installDependencies();
        String result = runTest();
        result += countLines();
        cleanRepository();
        return result;
    }

    private String countLines() {
        if (repoExists()){
            Long result = countLineNumberReader("index.js");
            return "   " + result + " lines";
        }
        return "";
    }

    private void cleanRepository() {
        if (repoExists())
            runCommand("rm -rf " + name, null);
    }

    private String runTest() {
        return runCommand("npm test", this.name);
    }

    private String installDependencies() {
        return runCommand("npm install", this.name);
    }

    private void cloneReponsitory() {
        runCommand("git clone " + this.repo + " " + this.name, null);

        if(!repoExists())
            throw new RuntimeException(REPO_NOT_EXISTS);
    }

    private boolean repoExists() {
        return new File(workspace + name).exists();
    }

    public String runCommand(String cmd, String folder) {
        Process p;
        String path = workspace + (folder != null ? folder : "");
        try {
            p = Runtime.getRuntime().exec(cmd, null, new File(path));
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private long countLineNumberReader(String fileName) {
        String fullPath = this.workspace + this.name + "/" + fileName;
        File file = new File(fullPath);

        long lines = 0;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null) ;

            lines = lnr.getLineNumber();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }
}
