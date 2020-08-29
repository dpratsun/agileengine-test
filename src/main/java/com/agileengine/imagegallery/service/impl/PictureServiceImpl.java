package com.agileengine.imagegallery.service.impl;

import com.agileengine.imagegallery.cache.PictureCache;
import com.agileengine.imagegallery.domain.Picture;
import com.agileengine.imagegallery.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureCache cache;

    @Override
    public List<Picture> getPicturesByPage(int page) {
        return cache.getByPage(page);
    }

    @Override
    public Picture getPictureById(String id) {
        return cache.getById(id);
    }

    @Override
    public List<Picture> searchPictures(String searchTerm) {
        return null;
    }
}
