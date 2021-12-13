package com.alenvieira.comics.controller;

import com.alenvieira.comics.controller.dto.UserDTO;
import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.model.User;
import com.alenvieira.comics.repository.UserRepository;
import com.alenvieira.comics.service.ComicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.alenvieira.comics.TestUtil.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ComicService comicService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void shouldAddUserAndReturnCreated() throws Exception {
        String userJson = userDTOToJsonString(userDTOSample1());
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(userJson));
    }

    @Test
    public void shouldFinUserAndReturnOk() throws Exception {
        when(userRepository.findById(any())).thenReturn(Optional.of(userSample1()));
        this.mvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(userDTOToJsonString(userDTOSample1())));
    }

    @Test
    public void shouldFinUserAndReturnNotFound() throws Exception {
        String userJson = userDTOToJsonString(userDTOSample1());
        this.mvc.perform(get("/api/v1/user/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotCreateUserWhenUserIsBlank() throws Exception {
        UserDTO user = new UserDTO();
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(containsString("birthDate")));

    }

    @Test
    public void shouldNotCreateUserWhenNameIsInvalid() throws Exception {
        User user = userSample2();
        user.setName("");
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setName(null);
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));
    }

    @Test
    public void shouldNotCreateUserWhenCPFIsInvalid() throws Exception {
        User user = userSample2();
        user.setCpf("");
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setCpf(null);
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setCpf("168.812.550-20");
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

    }


    @Test
    public void shouldNotCreateUserWhenEmailIsInvalid() throws Exception {
        User user = userSample2();
        user.setEmail("");
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setEmail(null);
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setEmail("emailinvalid");
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(not(containsString("birthDate"))));

    }

    @Test
    public void shouldNotCreateUserWhenBirthDateIsInvalid() throws Exception {
        User user = userSample2();
        user.setBirthDate(null);
        this.mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON).content(userDTOToJsonString(new UserDTO(user))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(containsString("birthDate")));
    }

    @Test
    public void shouldAddComicToUserWhenComicExists() throws Exception {
        User user = userSample1();
        user.setComics(new HashSet<>());
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(comicService.getComic(any())).thenReturn(Optional.of(comicSample1()));
        this.mvc.perform(post("/api/v1/users/1/comic/1")).andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotAddComicToUserWhenComicNotExists() throws Exception {
        User user = userSample1();
        user.setComics(new HashSet<>());
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(comicService.getComic(any())).thenReturn(Optional.empty());
        this.mvc.perform(post("/api/v1/users/1/comic/1")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotAddComicToUserWhenUserNotExists() throws Exception {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(comicService.getComic(any())).thenReturn(Optional.of(comicSample1()));
        this.mvc.perform(post("/api/v1/users/1/comic/1")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnListEmptyWhenUsersNotExists() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        this.mvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk()).andExpect(content().string(containsString("[]")));
    }

    @Test
    public void shouldReturnListWhenUsersExists() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<>(){{ add(userSample1()); add(userSample2());}});
        this.mvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk()).andExpect(content().string(not(containsString("[]"))));
    }
}