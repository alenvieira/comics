package com.alenvieira.comics.service;

import com.alenvieira.comics.controller.dto.ComicDTO;
import com.alenvieira.comics.model.Comic;
import com.alenvieira.comics.repository.ComicRepository;
import com.alenvieira.comics.service.dto.MarvelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Optional;

@Service
public class ComicService {

    @Autowired
    private MarvelClient client;
    @Value("${keyprivate.marvelapi}")
    private String privateKey;
    @Value("${keypublic.marvelapi}")
    private String publicKey;
    @Autowired
    private ComicRepository comicRepository;

    public Comic save(Comic comic) {
        return comicRepository.save(comic);
    }

    public Optional<Comic> getComic(Long comicId) {
        Optional<Comic> optionalComic = comicRepository.findById(comicId);
        if (optionalComic.isPresent()) {
            return optionalComic;
        } else {
            Long timeStamp = new Date().getTime();
            String hash = getHashAPI(timeStamp);
            Optional<MarvelDTO> optionalMarvelDTO = Optional.
                    ofNullable(client.getMarvelData(timeStamp, this.publicKey, hash, comicId));
            if (optionalMarvelDTO.isPresent()){
                return Optional.of(optionalMarvelDTO.get().convertToComicDTO().convertToComic());
            }
        }
        return Optional.empty();
    }

    public String getHashAPI(Long timeStamp){
        String message = timeStamp.toString() + this.privateKey + this.publicKey;
        String hash = DigestUtils.md5DigestAsHex(message.getBytes());
        return hash;
    }

}