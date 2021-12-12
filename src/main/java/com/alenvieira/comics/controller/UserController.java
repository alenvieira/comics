package com.alenvieira.comics.controller;

import com.alenvieira.comics.controller.dto.UserDTO;
import com.alenvieira.comics.model.User;
import com.alenvieira.comics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriComponentsBuilder) {
        User user = userDTO.convertToUser();
        userRepository.save(user);
        URI uri = uriComponentsBuilder.path("/api/v1/user{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new UserDTO(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
