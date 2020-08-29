package com.agileengine.imagegallery.service.impl;

import com.agileengine.imagegallery.domain.Picture;
import com.agileengine.imagegallery.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public List<Picture> getPicturesByPage(int page) {
        return null;
    }

    @Override
    public Picture getPictureById(String id) {
        return null;
    }

    @Override
    public List<Picture> searchPictures(String searchTerm) {
        return null;
    }
}
