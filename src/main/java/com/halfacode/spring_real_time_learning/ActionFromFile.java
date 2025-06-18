package com.halfacode.spring_real_time_learning;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ActionFromFile {

    public static void main(String[] args) {
        try {
            // Get current project directory dynamically
            String projectDir = System.getProperty("user.dir");
            System.out.println("Project Directory: " + projectDir);

            // Create 'files' folder inside project
            File folder = new File(projectDir + File.separator + "files");
            if (!folder.exists()) {
                boolean folderCreated = folder.mkdirs();
                if (folderCreated) {
                    System.out.println("Folder created: " + folder.getAbsolutePath());
                } else {
                    System.out.println("Failed to create folder.");
                    return;
                }
            } else {
                System.out.println("Folder already exists: " + folder.getAbsolutePath());
            }

            // Create a file inside 'files' folder
            File file = new File(folder, "hi1.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }

            // Example using Path
            Path path = Paths.get(folder.getAbsolutePath(), "hi1.txt");
            System.out.println("Path to the file: " + path.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
