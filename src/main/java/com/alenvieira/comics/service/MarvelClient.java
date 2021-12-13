package com.alenvieira.comics.service;

import com.alenvieira.comics.service.dto.MarvelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "url", url = "${domain.marvelapi}/v1/public")
public interface MarvelClient {
    @GetMapping("/comics/{comicId}")
    public MarvelDTO getMarvelData(@RequestParam(value = "ts") Long timeStamp,
                                   @RequestParam(value = "apikey") String publicKey,
                                   @RequestParam(value = "hash") String hashMD5,
                                   @PathVariable Long comicId);
}
