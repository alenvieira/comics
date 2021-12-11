package com.alenvieira.comics.controller;

import com.alenvieira.comics.model.User;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.alenvieira.comics.TestUtil.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void shouldAddUserAndReturnCreated() throws Exception {
        String userJson = userToJsonString(userSample1());
        this.mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON).content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(userJson));
    }

    @Test
    @Order(2)
    public void shouldFinUserAndReturnOk() throws Exception {
        String userJson = userToJsonString(userSample1());
        this.mvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));
    }

    @Test
    @Order(3)
    public void shouldFinUserAndReturnNotFound() throws Exception {
        String userJson = userToJsonString(userSample1());
        this.mvc.perform(get("/api/v1/user/2"))
                .andExpect(status().isNotFound());
    }

   @Test
   @Order(4)
    public void shouldAddDuplicateAndReturnUnprocessableEntity() throws Exception {
        String userJson = userToJsonString(userSample1());
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(not(containsString("birthDate"))));
    }

    @Test
    public void shouldNotCreateUserWhenUserIsBlank() throws Exception {
        User user = new User();
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
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
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setName(null);
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
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
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setCpf(null);
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(containsString("cpf")))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setCpf("168.812.550-20");
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
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
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setEmail(null);
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(not(containsString("birthDate"))));

        user.setEmail("emailinvalid");
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
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
        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userToJsonString(user)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(not(containsString("name"))))
                .andExpect(content().string(not(containsString("cpf"))))
                .andExpect(content().string(not(containsString("email"))))
                .andExpect(content().string(containsString("birthDate")));

        JSONObject userJson = new JSONObject();
        userJson.put("name", "Betrano Costa");
        userJson.put("email", "betrano@costa.com");
        userJson.put("cpf", "749.243.750-12");
        userJson.put("birthDate", "20-09-1990");

        this.mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON).content(userJson.toString()))
                .andExpect(status().isBadRequest());

    }

}


