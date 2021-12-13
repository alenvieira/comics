package com.alenvieira.comics.services;

import com.alenvieira.comics.controller.dto.ComicDTO;
import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.repository.ComicRepository;
import com.alenvieira.comics.service.ComicService;
import com.alenvieira.comics.service.MarvelClient;
import com.alenvieira.comics.service.dto.MarvelDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.alenvieira.comics.TestUtil.comicSample1;
import static com.alenvieira.comics.TestUtil.marvelDTOSample;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ComicServiceTest {

    @MockBean
    private ComicRepository comicRepository;
    @MockBean
    private MarvelClient marvelClient;
    @Autowired
    @InjectMocks
    private ComicService comicService;

    @Test
    public void shouldReturnDBComicWhenComicExistsInDB(){
        Comic comic = comicSample1();
        when(comicRepository.findById(any())).thenReturn(Optional.of(comicSample1()));
        Comic comicResult = comicService.getComic(1L).get();
        assertEquals(comic.getId(), comicResult.getId());
    }

    @Test
    public void shouldReturnAPIComicWhenComicNotExistsInDB(){
        MarvelDTO marvelDTO = marvelDTOSample();
        Comic comic = marvelDTO.convertToComicDTO().convertToComic();
        when(marvelClient.getMarvelData(any(),any(),any(),any())).thenReturn(marvelDTO);
        Comic comicResult = comicService.getComic(1L).get();
        assertEquals(comic.getIsbn(), comicResult.getIsbn());
    }

    @Test
    public void shouldReturnNothingWhenComicNotExists(){
        Comic comic = comicSample1();
        Optional<Comic> comicResult = comicService.getComic(1L);
        assertFalse(comicResult.isPresent());
    }

}
