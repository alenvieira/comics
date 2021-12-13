package com.alenvieira.comics.controller;

import com.alenvieira.comics.controller.dto.UserDTO;
import com.alenvieira.comics.controller.dto.UserWithComicDTO;
import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.model.User;
import com.alenvieira.comics.repository.UserRepository;
import com.alenvieira.comics.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ComicService comicService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriComponentsBuilder) {
        User user = userDTO.convertToUser();
        userRepository.save(user);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
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

    @PostMapping("/{id}/comic/{comicId}")
    @Transactional
    public ResponseEntity<UserWithComicDTO> addComicToUser(@PathVariable Long id, @PathVariable Long comicId) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Comic> optionalComic = comicService.getComic(comicId);
        if (optionalUser.isPresent() && optionalComic.isPresent()) {
            User user = optionalUser.get();
            Comic comic = optionalComic.get();
            if (comic.getId() == null) {
                comic.setId(comicId);
                comicService.save(comic);
            }
            if (user.getComics().contains(comic)) {
                return ResponseEntity.unprocessableEntity().body(new UserWithComicDTO(user));
            }
            user.getComics().add(comic);
            return ResponseEntity.accepted().body(new UserWithComicDTO(user));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<Set<UserDTO>> getUsers() {
        Set<UserDTO> users = userRepository.findAll().stream().map(user -> {
            return new UserDTO(user);
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(users);
    }


}
