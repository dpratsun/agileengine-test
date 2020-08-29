package com.agileengine.imagegallery.service.impl;

import com.agileengine.imagegallery.domain.Picture;
import com.agileengine.imagegallery.service.AgileEnginePictureService;
import com.agileengine.imagegallery.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RestTemplateAgileEnginePictureService implements AgileEnginePictureService {
    private final static String IMAGES_ENDPOINT = "/images";
    private final static String PAGE_REQUEST_PARAMETER = "?page=";

    private final RestTemplate restTemplate;

    private final TokenService tokenService;

    @Override
    public List<Picture> getPicturesByPage(int page) {
        String url = IMAGES_ENDPOINT + PAGE_REQUEST_PARAMETER + page;
        HttpHeaders headers = prepareHeaders();

        ResponseEntity<GetPicturesResponse> response;

        try {
            response = restTemplate
                    .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), GetPicturesResponse.class);
        } catch (Exception e) {
            headers.setBearerAuth(tokenService.renew());
            response = restTemplate
                    .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), GetPicturesResponse.class);
        }

        return Objects.requireNonNull(response.getBody()).pictures;
    }

    @Override
    public Picture getPictureById(String id) {
        String url = IMAGES_ENDPOINT + "/" + id;

        HttpHeaders headers = prepareHeaders();

        ResponseEntity<Picture> response;

        try {
            response = restTemplate
                    .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Picture.class);
        } catch (Exception e) {
            headers.setBearerAuth(tokenService.renew());
            response = restTemplate
                    .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Picture.class);
        }

        return response.getBody();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetPicturesResponse {
        private List<Picture> pictures;
        private int page;
        private int pageCount;
        private boolean hasMore;
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenService.get());

        return headers;
    }
}
