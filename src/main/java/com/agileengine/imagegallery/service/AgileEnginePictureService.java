package com.agileengine.imagegallery.service;

import com.agileengine.imagegallery.domain.Picture;

import java.util.List;

public interface AgileEnginePictureService {
    List<Picture> getPicturesByPage(int page);

    Picture getPictureById(String id);
}
