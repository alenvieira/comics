package com.alenvieira.comics;

import com.alenvieira.comics.controller.dto.CreatorDTO;
import com.alenvieira.comics.controller.dto.UserDTO;
import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.model.Creator;
import com.alenvieira.comics.model.User;
import com.alenvieira.comics.service.dto.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TestUtil {

    public static String userDTOToJsonString(UserDTO user) throws JSONException {
        JSONObject userJson = new JSONObject();
        userJson.put("name", user.getName());
        userJson.put("email", user.getEmail());
        userJson.put("cpf", user.getCpf());
        if (user.getBirthDate() != null) {
            userJson.put("birthDate", user.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        return userJson.toString();
    }

    public static UserDTO userDTOSample1() {
        return new UserDTO(userSample1());
    }

    public static UserDTO userDTOSample2() {
        return new UserDTO(userSample2());
    }

    public static User userSample1() {
        User user = new User();
        user.setName("Fulano da Silvinha");
        user.setEmail("fulano@silvinha.com");
        user.setCpf("768.812.550-20");
        user.setBirthDate(LocalDate.of(1980, 10, 10));
        return user;
    }

    public static User userSample2() {
        User user = new User();
        user.setName("Sicrano Moura");
        user.setEmail("sicrano@moura.com");
        user.setCpf("404.901.650-87");
        user.setBirthDate(LocalDate.of(1985, 05, 15));
        return user;
    }

    public static Comic comicSample1() {
        Comic comic = new Comic();
        comic.setId(55L);
        comic.setTitle("Quadrinho 1");
        comic.setPrice(BigDecimal.valueOf(10.00));
        Creator creator = new Creator();
        creator.setName("Autor 1");
        Set<Creator> creators = new HashSet<Creator>();
        creators.add(creator);
        comic.setCreators(creators);
        comic.setIsbn("124231452351");
        return comic;
    }

    public static Comic comicSample2() {
        Comic comic = new Comic();
        comic.setId(56L);
        comic.setTitle("Quadrinho 2");
        comic.setPrice(BigDecimal.valueOf(12.00));
        Creator creator = new Creator();
        creator.setName("Autor 2");
        Set<Creator> creators = new HashSet<Creator>();
        creators.add(creator);
        comic.setCreators(creators);
        comic.setIsbn("adasdas");
        return comic;
    }

    public static MarvelDTO marvelDTOSample() {
        Creator creator = new Creator("Criador");
        CreatorDTO creatorDTO = new CreatorDTO(creator);
        MarvelDataComicPriceDTO marvelDataComicPriceDTO = new MarvelDataComicPriceDTO("10");
        Set<MarvelDataComicPriceDTO> prices = new HashSet<>(){{ add(marvelDataComicPriceDTO); }};
        Set<CreatorDTO> creators = new HashSet<>(){{ add(creatorDTO); }};
        MarvelDataComicsCreatorInfoDTO marvelDataComicsCreatorInfoDTO = new MarvelDataComicsCreatorInfoDTO(creators);
        MarvelDataComicDTO marvelDataComicDTO = new MarvelDataComicDTO("Quadrinho Legal",
                 prices, marvelDataComicsCreatorInfoDTO, "1g1f23hjt", "Muito bom!");
        Set<MarvelDataComicDTO> marvelDataComicDTOS = new HashSet<>(){{ add(marvelDataComicDTO); }};
        MarvelDataDTO marvelDataDTO = new MarvelDataDTO(marvelDataComicDTOS);
        MarvelDTO marvelDTO = new MarvelDTO(marvelDataDTO);
        return marvelDTO;
    }

}
