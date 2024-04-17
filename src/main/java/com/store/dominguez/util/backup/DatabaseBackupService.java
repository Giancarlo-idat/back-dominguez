package com.store.dominguez.util.backup;

import com.smattme.MysqlExportService;
import com.smattme.MysqlImportService;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DatabaseBackupService {

    private final String dbName = "dominguez";
    private final String dbUsername = "root";
    private final String dbPassword = "root";
    private final String backupPath = "C:/Users/a1510732/Desktop/dominguez/dominguez/src/main/java/com/store/dominguez/backup";

    public void backupDatabase() {
        try {
            Properties properties = new Properties();
            properties.setProperty(MysqlExportService.DB_NAME, dbName);
            properties.setProperty(MysqlExportService.DB_USERNAME, dbUsername);
            properties.setProperty(MysqlExportService.DB_PASSWORD, dbPassword);
            properties.setProperty(MysqlExportService.TEMP_DIR, backupPath);

            MysqlExportService mysqlExportService = new MysqlExportService(properties);
            mysqlExportService.export();

            System.out.println("Backup realizado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoreDatabase(String sqlDumpFilePath) {
        try {
            String sql = new String(Files.readAllBytes(Paths.get(sqlDumpFilePath)));

            boolean result = MysqlImportService.builder()
                    .setDatabase(dbName)
                    .setSqlString(sql)
                    .setUsername(dbUsername)
                    .setPassword(dbPassword)
                    .setDeleteExisting(true)
                    .setDropExisting(true)
                    .importDatabase();

            if (result) {
                System.out.println("Restauración de la base de datos completada con éxito.");
            } else {
                System.out.println("Error al restaurar la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
