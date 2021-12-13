package com.alenvieira.comics.controller.dto;

import com.alenvieira.comics.model.Comic;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ComicWithDiscountDTO {

    private String title;
    private BigDecimal price;
    private Set<CreatorDTO> creators;
    private String isbn;
    private String description;

    public ComicWithDiscountDTO() {
    }

    public ComicWithDiscountDTO(Comic comic) {
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

    public Set<CreatorDTO> getCreators() {
        return creators;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return getDiscount() ? price.multiply(BigDecimal.valueOf(0.9)) : this.price;
    }

    public boolean getDiscount(){
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        if (dayOfWeek > 6){
            return false;
        }
        try {
            int value = Integer.parseInt(this.isbn.substring(this.isbn.length() - 1));
            int min = (dayOfWeek - 1) * 2;
            int max = min + 1;
            return (value == min || value == max ) ? true : false;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
