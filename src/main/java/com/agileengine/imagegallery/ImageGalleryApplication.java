package com.agileengine.imagegallery;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class ImageGalleryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ImageGalleryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
