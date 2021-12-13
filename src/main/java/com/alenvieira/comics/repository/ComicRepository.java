package com.alenvieira.comics.repository;

import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Long> {

}
