package com.halfacode.spring_real_time_learning;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaFileDemo {

    public static void main(String[] args) {

        // Dynamically get the current project directory
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + File.separator + "files" + File.separator + "hi1.txt";

        System.out.println("Project Directory: " + projectDir);
        System.out.println("Target File Path: " + filePath);
        System.out.println("------------------------------------------------");

        // File examples using the project files folder
        File file1 = new File(filePath);
        System.out.println("File 1 (Direct Path): " + file1.getAbsolutePath());

        File file2 = new File(projectDir + File.separator + "files", "hi1.txt");
        System.out.println("File 2 (Folder + File Name): " + file2.getAbsolutePath());

        File folder = new File(projectDir + File.separator + "files");
        File file3 = new File(folder, "hi1.txt");
        System.out.println("File 3 (Using Folder Object): " + file3.getAbsolutePath());

        File file4 = new File(URI.create("file:///" + projectDir.replace("\\", "/") + "/files/hi1.txt"));
        System.out.println("File 4 (Using URI): " + file4.getAbsolutePath());

        System.out.println("------------------------------------------------");

        // Path examples using the project files folder
        Path path1 = Paths.get(filePath);
        System.out.println("Path 1 (Paths.get Direct Path): " + path1.toAbsolutePath());

        Path path2 = Paths.get(projectDir, "files", "hi1.txt");
        System.out.println("Path 2 (Paths.get with Folder Arguments): " + path2.toAbsolutePath());

        Path path3 = Paths.get(URI.create("file:///" + projectDir.replace("\\", "/") + "/files/hi1.txt"));
        System.out.println("Path 3 (Paths.get Using URI): " + path3.toAbsolutePath());

        Path path4 = FileSystems.getDefault().getPath(filePath);
        System.out.println("Path 4 (FileSystems.getDefault Direct Path): " + path4.toAbsolutePath());

        Path path5 = FileSystems.getDefault().getPath(projectDir, "files", "hi1.txt");
        System.out.println("Path 5 (FileSystems.getDefault with Folder Arguments): " + path5.toAbsolutePath());

        Path path6 = Path.of(filePath);
        System.out.println("Path 6 (Path.of Direct Path): " + path6.toAbsolutePath());

        Path path7 = Path.of(projectDir, "files", "hi1.txt");
        System.out.println("Path 7 (Path.of with Folder Arguments): " + path7.toAbsolutePath());

        Path path8 = Path.of(URI.create("file:///" + projectDir.replace("\\", "/") + "/files/hi1.txt"));
        System.out.println("Path 8 (Path.of Using URI): " + path8.toAbsolutePath());

        System.out.println("------------------------------------------------");
        System.out.println("✅ All file and path examples processed successfully.");
    }
}
