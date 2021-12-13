package com.alenvieira.comics.service.dto;

import com.alenvieira.comics.controller.dto.CreatorDTO;

import java.util.Set;

public class MarvelDataComicsCreatorInfoDTO {

    private Set<CreatorDTO> items;

    public MarvelDataComicsCreatorInfoDTO() {
    }

    public MarvelDataComicsCreatorInfoDTO(Set<CreatorDTO> items) {
        this.items = items;
    }

    public Set<CreatorDTO> getItems() {
        return items;
    }

}
