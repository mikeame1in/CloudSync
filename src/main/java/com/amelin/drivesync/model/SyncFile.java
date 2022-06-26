package com.amelin.drivesync.model;

public class SyncFile {
    private String filePath;
    private String type;

    public SyncFile(String filePath, String type) {
        this.filePath = filePath;
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getType() {
        return type;
    }
}
