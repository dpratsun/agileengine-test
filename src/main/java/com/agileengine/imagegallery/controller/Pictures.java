package com.agileengine.imagegallery.controller;

import com.agileengine.imagegallery.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class Pictures {
    private final PictureService service;

    @GetMapping
    public ResponseEntity<?> getImages(@RequestParam("page") int page) {
        return ResponseEntity.ok(service.getPicturesByPage(Math.max(page, 1)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable String id) {
        return ResponseEntity.ok(service.getPictureById(id));
    }
}
