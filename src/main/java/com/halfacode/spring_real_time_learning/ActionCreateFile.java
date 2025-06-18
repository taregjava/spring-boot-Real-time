package com.halfacode.spring_real_time_learning;

import java.io.File;
import java.io.IOException;
public class ActionCreateFile {

    public static void main(String[] args) throws IOException {
        // Dynamic project path
        String projectDir = System.getProperty("user.dir");
        File targetFile = new File(projectDir + File.separator + "files" + File.separator + "hi1.txt");

        workingWithFile(targetFile);
    }

    private static void workingWithFile(File file) throws IOException {
        if (!file.exists()) {
            File parent = file.getParentFile();

            if (parent.mkdirs()) {
                System.out.println("✅ 'files' folder created successfully at: " + parent.getAbsolutePath());

                // Create 100,000 files
                for (int i = 0; i < 10; i++) {
                    File child = new File(parent, String.format("hi%d.txt", i));
                    if (child.createNewFile()) {
                        if (i % 10000 == 0) { // Log progress every 10,000 files
                            System.out.printf("✅ Created file: %s%n", child.getName());
                        }
                    } else {
                        System.out.printf("❌ Failed to create file: hi%d.txt%n", i);
                    }
                }
                System.out.println("✅ 100,000 files created successfully!");
            }
        }

        if (file.exists()) {
            System.out.println("\n📄 File Information:");
            System.out.println("Name: " + file.getName());
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is File: " + file.isFile());
            System.out.println("Is Directory: " + file.isDirectory());

            if (file.isFile()) {
                System.out.println("Last Modified: " + file.lastModified());
                System.out.println("Length: " + file.length() + " bytes");
                System.out.println("Is Hidden: " + file.isHidden());

                File parent = file.getParentFile();

                // Delete all files in the folder
                System.out.println("\n🗑️ Starting cleanup...");
                for (File child : parent.listFiles()) {
                    if (child.delete()) {
                        System.out.printf("✅ Deleted: %s%n", child.getName());
                    } else {
                        System.out.printf("❌ Failed to delete: %s%n", child.getName());
                    }
                }

                // Attempt to delete the folder after clearing files
                if (parent.delete()) {
                    System.out.println("✅ Folder 'files' deleted successfully!");
                } else {
                    System.out.println("❌ Failed to delete folder 'files'. It may not be empty.");
                }
            }
        }
    }
}


