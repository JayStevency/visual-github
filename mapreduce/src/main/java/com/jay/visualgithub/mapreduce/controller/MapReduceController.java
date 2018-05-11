package com.jay.visualgithub.mapreduce.controller;

import com.jay.visualgithub.mapreduce.service.DataRenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MapReduceController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataRenderService dataRenderService;

    @RequestMapping("/{githubId}")
    public Object[] getGitData(@PathVariable("githubId") String githubId){
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://sidecar/github/" + githubId, Object[].class);

        dataRenderService.setObjects(responseEntity.getBody());
        Object[] ret = new Object[3];
        ret[0] = dataRenderService.getUserData();
        ret[1] = dataRenderService.getRepoListData();
        ret[2] = dataRenderService.getPieChartData();
        return ret;
    }


}
