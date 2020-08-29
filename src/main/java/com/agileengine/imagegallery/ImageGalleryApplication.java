package com.agileengine.imagegallery;

import com.agileengine.imagegallery.task.Task;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class ImageGalleryApplication implements CommandLineRunner {

    private final Task cacheReloadTask;

    public static void main(String[] args) {
        SpringApplication.run(ImageGalleryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cacheReloadTask.perform();
    }
}
