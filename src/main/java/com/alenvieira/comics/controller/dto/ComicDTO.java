package com.alenvieira.comics.controller.dto;

import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.model.Creator;
import com.alenvieira.comics.repository.ComicRepository;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ComicDTO {

    @NotBlank
    private String title;
    @NotNull
    private BigDecimal price;
    @NotNull
    @UniqueElements
    private Set<CreatorDTO> creators;
    @NotNull
    private String isbn;
    private String description;

    public ComicDTO() {
    }

    public ComicDTO(Comic comic) {
        this.title = comic.getTitle();
        this.price = comic.getPrice();
        this.creators = comic.getCreators().stream().map(creator -> {
            return new CreatorDTO(creator);
        }).collect(Collectors.toSet());
        this.description = comic.getDescription();
        this.isbn = comic.getIsbn();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<CreatorDTO> getCreators() {
        return creators;
    }

    public void setCreators(Set<CreatorDTO> creators) {
        this.creators = creators;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comic convertToComic() {
        Comic comic = new Comic();
        comic.setTitle(this.title);
        comic.setPrice(this.price);
        comic.setIsbn(this.getIsbn());
        comic.setDescription(getDescription());
        comic.setCreators(creators.stream().map(c -> {
            return new Creator(c.getName());
        }).collect(Collectors.toSet()));
        return comic;
    }

}
