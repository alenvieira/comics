package com.alenvieira.comics.controller.dto;

import com.alenvieira.comics.model.User;
import com.alenvieira.comics.validator.UniqueCPF;
import com.alenvieira.comics.validator.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserDTO {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    @UniqueEmail
    private String email;
    @CPF
    @NotBlank
    @UniqueCPF
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate birthDate;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.birthDate = user.getBirthDate();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public User convertToUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setCpf(this.cpf);
        user.setBirthDate(this.birthDate);
        return user;
    }

}
