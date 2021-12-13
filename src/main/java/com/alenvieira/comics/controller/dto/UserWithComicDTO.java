package com.alenvieira.comics.controller.dto;

import com.alenvieira.comics.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class UserWithComicDTO {

    private String name;
    private String email;
    private String cpf;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private Set<ComicDTO> comics;

    public UserWithComicDTO() {
    }

    public UserWithComicDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.birthDate = user.getBirthDate();

        if (user.getComics() != null) {
        this.comics = user.getComics().stream().map(comic -> {
            return new ComicDTO(comic);
        }).collect(Collectors.toSet());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<ComicDTO> getComics() {
        return comics;
    }

    public void setComics(Set<ComicDTO> comicSet) {
        this.comics = comics;
    }
}
