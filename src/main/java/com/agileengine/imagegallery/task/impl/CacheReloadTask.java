package com.agileengine.imagegallery.task.impl;

import com.agileengine.imagegallery.cache.PictureCache;
import com.agileengine.imagegallery.service.AgileEnginePictureService;
import com.agileengine.imagegallery.task.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class CacheReloadTask implements Task {
    private final PictureCache cache;

    private final AgileEnginePictureService service;

    @Override
    public void perform() {
        cache.clear();
        int page = 1;
        var pictures = service.getPicturesByPage(page);
        while (pictures.size() > 0 && page < 2) {
            pictures.forEach(picture -> cache.add(service.getPictureById(picture.getId())));
            pictures = service.getPicturesByPage(++page);
        }
    }
}
