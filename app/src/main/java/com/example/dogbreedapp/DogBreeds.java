package com.example.dogbreedapp;

import java.util.List;

public class DogBreeds {
    String name;
    List<String> subbreed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubbreed() {
        return subbreed;
    }

    public void setSubbreed(List<String> subbreed) {
        this.subbreed = subbreed;
    }
}
