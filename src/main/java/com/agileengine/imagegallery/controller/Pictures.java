package com.agileengine.imagegallery.controller;

import com.agileengine.imagegallery.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class Pictures {
    private final PictureService service;

    @GetMapping
    public ResponseEntity<?> getImages(@RequestParam(required = false) Integer page) {
        return ResponseEntity.ok(service.getPicturesByPage(Objects.isNull(page) ? 1 : page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable String id) {
        return ResponseEntity.ok(service.getPictureById(id));
    }
}
