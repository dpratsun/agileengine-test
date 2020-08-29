package com.agileengine.imagegallery.cache.impl;

import com.agileengine.imagegallery.cache.PictureCache;
import com.agileengine.imagegallery.domain.Picture;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryPictureCache implements PictureCache {
    private final Logger logger = LoggerFactory.getLogger(InMemoryPictureCache.class);

    private final HashMap<String, Picture> pictures = new HashMap<>();
    private final HashMap<Integer, List<Picture>> picturesByPage = new HashMap<>();
    private Node head = new Node('*');

    @Value("${itemsPerPage}")
    private int itemsPerPage;

    @Data
    private static class Node {
        private Character character;
        private boolean isWordEnd = false;
        private HashMap<Character, Node> children = new HashMap<>();
        private List<String> results = new ArrayList<>();

        Node(Character character) {
            this.character = character;
        }
    }

    @Override
    public void add(Picture picture) {
        logger.info("Adding new picture to cache: " + picture.toString());
        pictures.put(picture.getId(), picture);
        int currentPage = (int) Math.ceil((float)pictures.size()/itemsPerPage);
        if (picturesByPage.containsKey(currentPage)) {
            picturesByPage.get(currentPage).add(picture);
        } else {
            picturesByPage.put(currentPage, new ArrayList<>(List.of(picture)));
        }
        addToTree(picture.getAuthor(), picture.getId(), 0, head);
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
    public List<Picture> search(String searchTerm) {
        var result = new ArrayList<Picture>();
        var foundIds = findInTree(searchTerm, 0, head);
        foundIds.forEach(id -> result.add(getById(id)));
        return result;
    }

    @Override
    public void clear() {
        pictures.clear();
        picturesByPage.clear();
        head = new Node('*');
    }

    private void addToTree(String string, String imageId, int index, Node node) {
        if (index == string.length()) {
            node.isWordEnd = true;
            node.results.add(imageId);
            return;
        }
        if (!node.children.containsKey(string.charAt(index))) {
            node.children.put(string.charAt(index), new Node(string.charAt(index)));
        }
        Node nextNode = node.children.get(string.charAt(index));
        addToTree(string, imageId, index+1, nextNode);
    }

    private List<String> findInTree(String string, int index, Node node) {
        if (index == string.length()) {
            if (node.isWordEnd) return node.results;
            return Collections.emptyList();
        }
        if (!node.children.containsKey(string.charAt(index))) return Collections.emptyList();
        Node nextNode = node.children.get(string.charAt(index));
        return findInTree(string, index+1, nextNode);
    }
}
