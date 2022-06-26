package com.amelin.drivesync;

import com.amelin.drivesync.model.SyncFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class SyncDispatcher {
    private final GoogleCloudAdapter cloudAdapter;

    public SyncDispatcher(GoogleCloudAdapter cloudAdapter) {
        this.cloudAdapter = cloudAdapter;
    }

    public void createTasks(List<SyncFile> files) {
        try {
            for (SyncFile file: files) {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path path = Paths.get(file.getFilePath()).getParent();
                path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                boolean poll = true;
                while (poll) {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        System.out.println(
                                "Event kind:" + event.kind()
                                        + ". File affected: " + event.context() + ".");
                        cloudAdapter.updateFile(file);
                    }
                    poll = key.reset();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
