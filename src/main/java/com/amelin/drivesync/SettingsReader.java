package com.amelin.drivesync;

import com.amelin.drivesync.model.SyncFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsReader {
    private final static String SETTINGS_FILE_PATH =
            "C:\\Users\\acod10044\\IdeaProjects\\GoogleDriveTest_v.0.1\\settings.txt";
    public List<SyncFile> read() {
        List<SyncFile> syncFiles = new ArrayList<>();
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(SETTINGS_FILE_PATH))) {
            String str;
            while ((str = reader.readLine()) != null) {
                String filepath = str.substring(0, str.indexOf(" "));
                String type = str.substring(str.indexOf(" ") + 1);
                syncFiles.add(new SyncFile(filepath, type));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return syncFiles;
    }
}
