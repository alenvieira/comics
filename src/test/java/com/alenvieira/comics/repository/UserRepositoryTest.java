package com.alenvieira.comics.repository;

import com.alenvieira.comics.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static com.alenvieira.comics.TestUtil.userSample1;
import static com.alenvieira.comics.TestUtil.userSample2;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveWhenUserValid(){
        User user = userSample1();
        userRepository.save(user);
        assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    public void shouldDontSaveWhenUserDuplicate(){
        User user1 = userSample2();
        userRepository.save(user1);
        User user2 = userSample2();
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));
    }

    @Test
    public void shouldDontSaveWhenUserBlank(){
        User user = new User();
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void shouldDontSaveWhenUserWithoutNameValid(){
        User user = new User();
        user.setEmail("fulano@silvinha.com");
        user.setCpf("768.812.550-20");
        user.setBirthDate(LocalDate.of(1980, 10, 10));
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void shouldDontSaveWhenUserWithoutEmailValid(){
        User user = new User();
        user.setName("Fulando da Silvinha");
        user.setEmail("fulanosilvinha.com");
        user.setCpf("768.812.550-20");
        user.setBirthDate(LocalDate.of(1980, 10, 10));
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void shouldDontSaveWhenUserWithoutCpfValid(){
        User user = new User();
        user.setName("Fulando da Silvinha");
        user.setEmail("fulanosilvinha.com");
        user.setCpf("068.812.550-20");
        user.setBirthDate(LocalDate.of(1980, 10, 10));
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void shouldDontSaveWhenUserWithoutBirthDateValid(){
        User user = new User();
        user.setName("Fulando da Silvinha");
        user.setEmail("fulanosilvinha.com");
        user.setCpf("768.812.550-20");
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

}
