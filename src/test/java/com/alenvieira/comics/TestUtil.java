package com.alenvieira.comics;

import com.alenvieira.comics.model.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestUtil {

    public static String userToJsonString(User user) throws JSONException {
        JSONObject userJson = new JSONObject();
        userJson.put("name", user.getName());
        userJson.put("email", user.getEmail());
        userJson.put("cpf", user.getCpf());
        if (user.getBirthDate() != null) {
            userJson.put("birthDate", user.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        return userJson.toString();
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

}
