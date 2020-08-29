package com.agileengine.imagegallery.controller;

import com.agileengine.imagegallery.cache.PictureCache;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
@AllArgsConstructor
public class Search {

    private final PictureCache cache;

    @GetMapping("/{search}")
    public ResponseEntity<?> search(@PathVariable String search) {
        return ResponseEntity.ok(cache.search(search));
    }
}
