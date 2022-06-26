package com.amelin.drivesync;

import com.amelin.drivesync.model.SyncFile;

import java.util.List;

public class DriveQuickstart {
    public static void main(String... args) {
        List<SyncFile> files = new SettingsReader().read();
        new SyncDispatcher(new GoogleCloudAdapter()).createTasks(files);
    }
}
