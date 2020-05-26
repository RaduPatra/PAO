package pack.radu.services;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;

public class WriteService implements FileLocations {
    private static WriteService writeService = null;
    FileWriter logsfile;
    private Date date;
    private long time;
    private Timestamp timestamp;


    private WriteService() {
        try {
            date = new Date();
            time = date.getTime();
            timestamp = new Timestamp(time);
            logsfile = new FileWriter(LOGS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WriteService getInstance() {
        if (writeService == null)
            writeService = new WriteService();
        return writeService;
    }

    public void writeLogsToFile(String actionName) {
        date = new Date();
        time = date.getTime();
        timestamp = new Timestamp(time);
        try {
            logsfile.append(actionName).append(",").append(String.valueOf(timestamp)).append("\n");
            logsfile.flush();

        } catch (IOException e) {
            System.out.println("Could not write data to logsfile: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Successfully wrote " + actionName + " to logs logsfile!");
    }

    public void closeLogs() {
        date = new Date();
        time = date.getTime();
        timestamp = new Timestamp(time);
        try {
            logsfile.append("Logs closed, ").append(String.valueOf(timestamp));
            logsfile.flush();
            logsfile.close();
            System.out.println("Audit closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
