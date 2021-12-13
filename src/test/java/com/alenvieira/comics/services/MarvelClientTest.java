package com.alenvieira.comics.services;

import com.alenvieira.comics.service.MarvelClient;

import com.alenvieira.comics.service.dto.MarvelDTO;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class MarvelClientTest {

    private String urlService = "https://gateway.marvel.com/v1/public/";
    private MarvelClient client;

    @Test
    public void whenGetMarvelClientThenReturnOkAndDTONotNull(){
        String comicId = "1";
        String fakeTs = "441895";
        String fakePublicKey = "jh35k4kjuh56";
        String fakeHash = "ahj354yujg4h6";
        String urlResult = urlService+"comics/"+comicId+"?ts="+fakeTs+"&apikey="+fakePublicKey+"&hash="+fakeHash;
        client = Feign.builder()
                .client(new MockClient().ok(HttpMethod.GET, urlResult, JSON))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(MarvelClient.class, urlService);

        MarvelDTO marvelDTO = client.getMarvelData(Long.parseLong(fakeTs),
                fakePublicKey, fakeHash, Long.parseLong("1"));
        assertNotNull(marvelDTO);
    }

    private String JSON = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"© 2021 MARVEL\"," +
            "\"attributionText\":\"Data provided by Marvel. © 2021 MARVEL\",\"attributionHTML\"" +
            ":\"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>\",\"" +
            "etag\":\"d3e90e346052cc3129627737aba9d809dd1d3533\",\"data\":{\"offset\":0,\"limit\":" +
            "20,\"total\":1,\"count\":1,\"results\":[{\"id\":1309,\"digitalId\":0,\"title\":\"" +
            "Marvel Age Spider-Man Vol. 3: Swingtime (Digest)\",\"issueNumber\":0,\"variantDescription" +
            "\":\"\",\"description\":\"The fledgling wall-crawler reaches a crucial turning point " +
            "in his young life as the diabolical Doctor Octopus makes his long-awaited return! Plus:" +
            " the first appearances of the Enforcers and Mysterio! And learn why J. Jonah Jameson " +
            "really hates Spider-Man!\",\"modified\":\"2018-01-22T16:16:19-0500\",\"isbn\":\"0-7851-1548" +
            "-X\",\"upc\":\"5960611548-00111\",\"diamondCode\":\"\",\"ean\":\"\",\"issn\":\"\",\"format" +
            "\":\"Digest\",\"pageCount\":96,\"textObjects\":[{\"type\":\"issue_solicit_text\",\"language" +
            "\":\"en-us\",\"text\":\"The fledgling wall-crawler reaches a crucial turning point in his" +
            " young life as the diabolical Doctor Octopus makes his long-awaited return! Plus: the " +
            "first appearances of the Enforcers and Mysterio! And learn why J. Jonah Jameson really" +
            " hates Spider-Man!\"}],\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/1309" +
            "\",\"urls\":[{\"type\":\"detail\",\"url\":\"http://marvel.com/comics/collection/1309/marvel" +
            "_age_spider-man_vol_3_swingtime_digest?utm_campaign=apiRef&utm_source=e5376aa8e802f5497576" +
            "da484e24b1e8\"}],\"series\":{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/1315" +
            "\",\"name\":\"Marvel Age Spider-Man Vol. 3: Swingtime (2004)\"},\"variants\":[],\"collections" +
            "\":[],\"collectedIssues\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/819" +
            "\",\"name\":\"Marvel Age Spider-Man (2004) #12\"},{\"resourceURI\":\"http://gateway.marvel." +
            "com/v1/public/comics/793\",\"name\":\"Marvel Age Spider-Man (2004) #11\"},{\"resourceURI\"" +
            ":\"http://gateway.marvel.com/v1/public/comics/753\",\"name\":\"Marvel Age Spider-Man (2004)" +
            " #10\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/564\",\"name\":\"Marvel" +
            " Age Spider-Man (2004) #9\"}],\"dates\":[{\"type\":\"onsaleDate\",\"date\":\"2004-07-07T00:00" +
            ":00-0400\"},{\"type\":\"focDate\",\"date\":\"-0001-11-30T00:00:00-0500\"}],\"prices\":[{\"type" +
            "\":\"printPrice\",\"price\":5.99}],\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel" +
            "/i/mg/3/20/5a664d331236e\",\"extension\":\"jpg\"},\"images\":[{\"path\":\"http://i.annihil.us/" +
            "u/prod/marvel/i/mg/9/50/5a66546ed16ba\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us" +
            "/u/prod/marvel/i/mg/6/c0/5a6653bfc7942\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us" +
            "/u/prod/marvel/i/mg/3/60/5a664d4928a03\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us" +
            "/u/prod/marvel/i/mg/3/20/5a664d331236e\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us/" +
            "u/prod/marvel/i/mg/5/d0/4bc68d056311a\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us/" +
            "u/prod/marvel/i/mg/9/50/4bc67ee2265e0\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us/" +
            "u/prod/marvel/i/mg/f/90/4bc67baba88f6\",\"extension\":\"jpg\"},{\"path\":\"http://i.annihil.us/" +
            "u/prod/marvel/i/mg/a/10/4bc679e66ce80\",\"extension\":\"jpg\"}],\"creators\":{\"available\":14," +
            "\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/1309/creators\",\"items\":[{" +
            "\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/8571\",\"name\":\"Guru-eFX\"" +
            ",\"role\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/424\"" +
            ",\"name\":\"Udon Comics\",\"role\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com" +
            "/v1/public/creators/4207\",\"name\":\"Avalon Studio\",\"role\":\"colorist\"},{\"resourceURI\"" +
            ":\"http://gateway.marvel.com/v1/public/creators/697\",\"name\":\"Bright Anvil Studios\",\"rol" +
            "e\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/415\",\"nam" +
            "e\":\"Derec Aucoin\",\"role\":\"penciller\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/" +
            "public/creators/778\",\"name\":\"Valentine De Landro\",\"role\":\"penciller\"},{\"resourceURI" +
            "\":\"http://gateway.marvel.com/v1/public/creators/700\",\"name\":\"Logan Lubera\",\"role\":\"" +
            "penciller\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/690\",\"name\":\"" +
            "Patrick Scherberger\",\"role\":\"penciller\"},{\"resourceURI\":\"http://gateway.marvel.com/v1" +
            "/public/creators/682\",\"name\":\"Todd Dezago\",\"role\":\"writer\"},{\"resourceURI\":\"http://" +
            "gateway.marvel.com/v1/public/creators/684\",\"name\":\"Mike Raicht\",\"role\":\"writer\"},{\"res" +
            "ourceURI\":\"http://gateway.marvel.com/v1/public/creators/699\",\"name\":\"Norman Lee\",\"role\":" +
            "\"inker\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/469\",\"name\":\"Crai" +
            "g Yeung\",\"role\":\"inker\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/361" +
            "\",\"name\":\"Cory Petit\",\"role\":\"letterer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1" +
            "/public/creators/434\",\"name\":\"Michael Ryan\",\"role\":\"penciller (cover)\"}],\"returned\":14" +
            "},\"characters\":{\"available\":1,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/" +
            "1309/characters\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1" +
            "009610\",\"name\":\"Spider-Man (Peter Parker)\"}],\"returned\":1},\"stories\":{\"available\":9,\"" +
            "collectionURI\":\"http://gateway.marvel.com/v1/public/comics/1309/stories\",\"items\":[{\"resour" +
            "ceURI\":\"http://gateway.marvel.com/v1/public/stories/2761\",\"name\":\"Cover #2761\",\"type\":\"" +
            "cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/2762\",\"name\":\"Interi" +
            "or #2762\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/st" +
            "ories/2773\",\"name\":\"Cover #2773\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marv" +
            "el.com/v1/public/stories/2774\",\"name\":\"Interior #2774\",\"type\":\"interiorStory\"},{\"resou" +
            "rceURI\":\"http://gateway.marvel.com/v1/public/stories/2775\",\"name\":\"\\\"UNMASKED BY DOCTOR " +
            "OCTOPUS!\\\" Desperate to clear his name and rid himself of the accursed SPIDER-MAN, DOCTOR OCTO" +
            "PUS draws the w\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/sto" +
            "ries/2776\",\"name\":\"\\\"UNMASKED BY DOCTOR OCTOPUS!\\\" Desperate to clear his name and rid h" +
            "imself of the accursed SPIDER-MAN, DOCTOR OCTOPUS draws the w\",\"type\":\"interiorStory\"},{\"r" +
            "esourceURI\":\"http://gateway.marvel.com/v1/public/stories/2777\",\"name\":\"\\\"THE MENACE OF M" +
            "YSTERIO\\\" There's a new criminal in town and his name is... Spider-Man?! Is Spider-Man committ" +
            "ing crimes in his s\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public" +
            "/stories/2778\",\"name\":\"\\\"THE MENACE OF MYSTERIO\\\" There's a new criminal in town and his" +
            " name is... Spider-Man?! Is Spider-Man committing crimes in his s\",\"type\":\"interiorStory\"}," +
            "{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/65730\",\"name\":\"MARVEL AGE SPI" +
            "DER-MAN VOL. 3: SWINGTIME 0 cover\",\"type\":\"cover\"}],\"returned\":9},\"events\":{\"available" +
            "\":0,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/1309/events\",\"items\":[],\"" +
            "returned\":0}}]}}";

}
