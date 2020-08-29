package com.agileengine.imagegallery.service;

import com.agileengine.imagegallery.domain.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> getPicturesByPage(int page);

    Picture getPictureById(String id);

    List<Picture> searchPictures(String searchTerm);
}
