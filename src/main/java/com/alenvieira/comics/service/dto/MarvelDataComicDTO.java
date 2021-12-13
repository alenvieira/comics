package com.alenvieira.comics.service.dto;

import com.alenvieira.comics.controller.dto.ComicDTO;
import com.alenvieira.comics.controller.dto.CreatorDTO;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class MarvelDataComicDTO {

    private String title;
    private Set<MarvelDataComicPriceDTO> prices;
    private MarvelDataComicsCreatorInfoDTO creators;
    private String isbn;
    private String description;

    public MarvelDataComicDTO() {
    }

    public MarvelDataComicDTO(String title, Set<MarvelDataComicPriceDTO> prices,
                              MarvelDataComicsCreatorInfoDTO creators, String isbn, String description) {
        this.title = title;
        this.prices = prices;
        this.creators = creators;
        this.isbn = isbn;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Set<MarvelDataComicPriceDTO> getPrices() {
        return prices;
    }

    public MarvelDataComicsCreatorInfoDTO getCreators() {
        return creators;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    private BigDecimal getFirstPrice(){
        MarvelDataComicPriceDTO price = prices.stream().findFirst().get();
        return new BigDecimal(price.getPrice());
    }

    public ComicDTO getComicDTO(){
        ComicDTO comicDTO = new ComicDTO();
        comicDTO.setTitle(this.getTitle());
        comicDTO.setIsbn(this.getIsbn());
        comicDTO.setDescription(this.getDescription());
        comicDTO.setPrice(this.getFirstPrice());
        comicDTO.setCreators(this.creators.getItems());
        return comicDTO;
    }

}
