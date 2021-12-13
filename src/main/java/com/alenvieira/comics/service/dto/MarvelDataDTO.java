package com.alenvieira.comics.service.dto;

import java.util.Set;

public class MarvelDataDTO {
    private Set<MarvelDataComicDTO> results;

    public MarvelDataDTO() {
    }

    public MarvelDataDTO(Set<MarvelDataComicDTO> results) {
        this.results = results;
    }

    public Set<MarvelDataComicDTO> getResults() {
        return results;
    }

}
