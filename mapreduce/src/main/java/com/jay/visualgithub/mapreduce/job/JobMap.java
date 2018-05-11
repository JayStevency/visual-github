package com.jay.visualgithub.mapreduce.job;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by jay on 2017. 4. 1..
 */
public class JobMap implements Callable<Map<String, Integer>> {
    private Object[] task;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public JobMap(Object[] task) {
        this.task = task;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Map<String, Integer> call() throws Exception {
        Map<String, Integer> result = new HashMap<>();
        Gson gson = new GsonBuilder().create();

        for(Object obj : task){
            JSONObject jsonObject = new JSONObject(gson.toJson(obj));
            String key = jsonObject.getString("language");
            if(key.equals("empty")) continue;

            if(!result.containsKey(key)){
                result.put(key, 1);
            }else{
                result.compute(key, (k,v)->
                        v = Integer.sum(v.intValue(), 1)
                );
            }
        }

        Iterator<String> it = result.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            int val = result.get(key);
            logger.info("Thread{}| Key :{}, Val:{}",this.hashCode(), key, val);
        }

        return result;
    }
}