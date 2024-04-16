package com.store.dominguez.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DatabaseBackupServiceImpl {

    private final String backupPath;

    public DatabaseBackupServiceImpl(@Value("${backup.path}") String backupPath) {
        this.backupPath = backupPath;
    }

    public boolean createBackup() {
        String backupFileName = "backup_" + System.currentTimeMillis() + ".sql";
        String backupFilePath = backupPath + File.separator + backupFileName;

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", "C:\\Program Files\\MySQL\\MySQL Server 8.3\\bin\\mysqldump.exe", "-u", "root", "-proot", "dominguez", ">", backupFilePath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean restoreBackup(String backupFilePath) {
        try {
            File sqlFile = new File(backupFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
            reader.close();

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", "C:\\Program Files\\MySQL\\MySQL Server 8.3\\bin\\mysql.exe", "-u", "root", "-proot", "dominguez");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            OutputStream outputStream = process.getOutputStream();
            outputStream.write(sql.toString().getBytes());
            outputStream.close();

            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}

