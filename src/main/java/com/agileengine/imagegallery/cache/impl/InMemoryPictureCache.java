package com.agileengine.imagegallery.cache.impl;

import com.agileengine.imagegallery.cache.PictureCache;
import com.agileengine.imagegallery.domain.Picture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryPictureCache implements PictureCache {
    private final HashMap<String, Picture> pictures = new HashMap<>();
    private final HashMap<Integer, List<Picture>> picturesByPage = new HashMap<>();

    @Value("${itemsPerPage}")
    private int itemsPerPage;

    @Override
    public void add(Picture picture) {
        pictures.put(picture.getId(), picture);
        int currentPage = (int) Math.ceil((float)pictures.size()/itemsPerPage);
        if (picturesByPage.containsKey(currentPage)) {
            picturesByPage.get(currentPage).add(picture);
        } else {
            picturesByPage.put(currentPage, new ArrayList<>(List.of(picture)));
        }
    }

    @Override
    public Picture getById(String id) {
        return pictures.get(id);
    }

    @Override
    public List<Picture> getByPage(int page) {
        return picturesByPage.get(page);
    }

    @Override
    public void clear() {
        pictures.clear();
        picturesByPage.clear();
    }
}
