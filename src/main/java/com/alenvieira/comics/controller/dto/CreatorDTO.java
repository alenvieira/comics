package com.alenvieira.comics.controller.dto;

import com.alenvieira.comics.model.Creator;

public class CreatorDTO {

    private String name;

    public CreatorDTO() {
    }

    public CreatorDTO(Creator creator) {
        this.name = creator.getName();
    }

    public String getName() {
        return name;
    }

    public Creator convertToCreator(){
        Creator creator = new Creator();
        creator.setName(this.name);
        return creator;
    }

}
