package com.jay.visualgithub.github.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GithubController {

    @Autowired
    RestTemplate restTemplate;

    private String github_uri = "https://api.github.com/users/";

    @RequestMapping("/{githubId}")
    List<Object> getRepoList(@PathVariable("githubId") String githubId){
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(github_uri + githubId + "/repos", Object[].class);
        System.out.println(responseEntity.toString());
        Object[] resultData = responseEntity.getBody();

        return Arrays.stream(resultData).collect(Collectors.toList());
    }
}
