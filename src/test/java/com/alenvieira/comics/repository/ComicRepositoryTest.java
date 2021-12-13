package com.alenvieira.comics.repository;

import com.alenvieira.comics.model.Comic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import static com.alenvieira.comics.TestUtil.comicSample1;
import static com.alenvieira.comics.TestUtil.comicSample2;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ComicRepositoryTest {

    @Autowired
    ComicRepository comicRepository;

    @Test
    public void shouldSaveWhenComicValid() {
        Comic comic = comicSample1();
        comicRepository.save(comic);
        assertTrue(comicRepository.existsById(comic.getId()));
    }

    @Test
    public void shouldDontSaveWhenComicBlank(){
        Comic comic = new Comic();
        assertThrows(JpaSystemException.class, () -> comicRepository.save(comic));
    }

    @Test
    public void shouldDontSaveWhenComicWithoutTitle(){
        Comic comic = comicSample2();
        comic.setTitle(null);
        assertThrows(DataIntegrityViolationException.class, () -> comicRepository.save(comic));
    }

    @Test
    public void shouldDontSaveWhenComicWithoutPrice(){
        Comic comic = comicSample2();
        comic.setPrice(null);
        assertThrows(DataIntegrityViolationException.class, () -> comicRepository.save(comic));
    }

    @Test
    public void shouldDontSaveWhenComicWithoutIBSN(){
        Comic comic = comicSample2();
        comic.setIsbn(null);
        assertThrows(DataIntegrityViolationException.class, () -> comicRepository.save(comic));
    }

}
