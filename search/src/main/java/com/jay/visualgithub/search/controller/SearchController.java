package com.jay.visualgithub.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
public class SearchController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{githubId}")
    public List<Object> searchGithubInfo(@PathVariable("githubId") String githubId){
        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange("http://github/" + githubId, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Object>>() {});

        List<Object> repoData = responseEntity.getBody();

        return repoData;
    }
}
