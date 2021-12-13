package com.alenvieira.comics.service.dto;

import com.alenvieira.comics.controller.dto.ComicDTO;

public class MarvelDTO {
    private MarvelDataDTO data;

    public MarvelDTO() {
    }

    public MarvelDTO(MarvelDataDTO data) {
        this.data = data;
    }

    public MarvelDataDTO getData() {
        return data;
    }

    public ComicDTO convertToComicDTO() {
        MarvelDataComicDTO dataComicDTO = data.getResults().stream().findFirst().get();
        return dataComicDTO.getComicDTO();
    }
}
