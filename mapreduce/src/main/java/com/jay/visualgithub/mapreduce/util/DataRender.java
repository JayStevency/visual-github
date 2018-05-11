package com.jay.visualgithub.mapreduce.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jay.visualgithub.mapreduce.domain.*;
import com.jay.visualgithub.mapreduce.job.JobPool;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jay on 2017. 3. 29..
 */
public class DataRender {
    private static Gson gson = new GsonBuilder().create();

    private static Logger logger = LoggerFactory.getLogger(DataRender.class);

    public static Object[] getRepoList(Object[] input){
        int i = 0;
        Object[] ret = new Object[input.length];

        for(Object obj : input){
            JSONObject jsonObject = new JSONObject(gson.toJson(obj));
            ret[i++] = (Object) new RepoVO(jsonObject.getString("name"),
                    jsonObject.getInt("stargazers_count"),
                    jsonObject.getInt("forks_count"),
                    jsonObject.getInt("watchers_count"));
        }
        return ret;

    }


    public static Object[] getPieChart(Object[] input){
        int i = 0 ;
        Object[] ret = new Object[(input.length % JobPool.JOB_MAX == 0? input.length : JobPool.JOB_MAX * (input.length/JobPool.JOB_MAX + 1))];

        for(Object obj : input){
            JSONObject jsonObject = new JSONObject(gson.toJson(obj));
            String name = jsonObject.getString("name");
            String language = (jsonObject.has("language") ? jsonObject.getString("language") : "empty");
            ret[i++] = (Object) new LangVO(name, language);
        }

        /** Padding 부분
         */
        for(; i < ret.length; i++){
            ret[i] = (Object) new LangVO("empty","empty");
        }

        return ret;
    }

    public static Object getUserData(Object[] input){


        JSONObject jsonObject = new JSONObject(gson.toJson(input[0]));
        JSONObject user = jsonObject.getJSONObject("owner");

        String name = user.getString("login");
        String avatar = user.getString("avatar_url");
        return (Object) new UserVO(avatar,name);
    }

}
