package com.halfacode.spring_real_time_learning.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halfacode.spring_real_time_learning.model.AuditLogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuditLogService {

    private static final String BASE_FOLDER = "audit-logs";

    @Autowired
    private ObjectMapper objectMapper;

    public synchronized void log(AuditLogEntry entry) {
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            File folder = new File(BASE_FOLDER + File.separator + today);
            if (!folder.exists()) folder.mkdirs();

            File logFile = new File(folder, "log.json");
            if (!logFile.exists()) logFile.createNewFile();

            String json = objectMapper.writeValueAsString(entry);

            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(json + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Failed to write audit log: " + e.getMessage());
        }
    }

    /* public synchronized void logEntry(AuditLogEntry entry) {
         try {
             String today = LocalDateTime.now().toLocalDate().toString();
             File folder = new File(BASE_FOLDER + File.separator + today);
             if (!folder.exists()) folder.mkdirs();

             File logFile = new File(folder, "log.json");
             if (!logFile.exists()) logFile.createNewFile();

             String json = objectMapper.writeValueAsString(entry);

             try (FileWriter writer = new FileWriter(logFile, true)) {
                 writer.write(json + System.lineSeparator());
             }
         } catch (IOException e) {
             System.err.println("Failed to write audit log: " + e.getMessage());
         }
     }*/
    public synchronized void logEntry(AuditLogEntry entry) {
        try {
            String today = LocalDateTime.now().toLocalDate().toString();
            File folder = new File(BASE_FOLDER + File.separator + today);
            if (!folder.exists()) folder.mkdirs();

            File logFile = new File(folder, "log.json");
            if (!logFile.exists()) logFile.createNewFile();

            // ✅ Pretty print the JSON
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(entry);

            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(json + System.lineSeparator());
                writer.write(System.lineSeparator()); // Add extra empty line for separation (optional)
            }
        } catch (IOException e) {
            System.err.println("Failed to write audit log: " + e.getMessage());
        }
    }
    // ✅ Runs every Monday at 2 AM
    // @Scheduled(cron = "0 0 2 ? * MON")
    // 🔁 Runs once per day at 2 AM
    //@Scheduled(cron = "0 0 2 * * ?")
    // ✅ Runs every hour
    @Scheduled(cron = "0 0 * * * ?")
    public void cleanOldLogs() {
        File baseDir = new File(BASE_FOLDER);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return;
        }

        File[] folders = baseDir.listFiles(File::isDirectory);
        if (folders == null) return;

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        for (File folder : folders) {
            try {
                LocalDate folderDate = LocalDate.parse(folder.getName(), formatter);
                if (folderDate.isBefore(today.minusDays(3))) {
                    deleteFolderRecursively(folder.toPath());
                    System.out.println("Deleted old audit log folder: " + folder.getName());
                }
            } catch (Exception e) {
                System.err.println("Skipping folder: " + folder.getName() + " - " + e.getMessage());
            }
        }
    }

    // Recursively delete a folder
    private void deleteFolderRecursively(Path path) throws Exception {
        Files.walk(path)
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2)) // delete files before folders
                .forEach(File::delete);
    }
}