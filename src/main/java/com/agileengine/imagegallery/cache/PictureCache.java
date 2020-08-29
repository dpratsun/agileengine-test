package com.agileengine.imagegallery.cache;

import com.agileengine.imagegallery.domain.Picture;

import java.util.List;

public interface PictureCache {
    void add(Picture picture);

    Picture getById(String id);

    List<Picture> getByPage(int page);

    void clear();
}
