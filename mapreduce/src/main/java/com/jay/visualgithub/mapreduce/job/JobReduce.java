package com.jay.visualgithub.mapreduce.job;

import java.util.*;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jay on 2017. 4. 1..
 */
public class JobReduce implements Callable<Map<String, Integer>> {

    private List<Map<String, Integer>> result_queue;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public JobReduce(List<Map<String, Integer>> result_queue) {
        this.result_queue = result_queue;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Map<String, Integer> call() throws Exception {
        Map<String, Integer> ret = new HashMap<>();
        for(Map<String, Integer> map: result_queue){
            Iterator<String> it = map.keySet().iterator();
            while(it.hasNext()){
                String key = it.next();
                int val = map.get(key);
                if(!ret.containsKey(key)){
                    ret.put(key,val);
                }else{
                    ret.compute(key, (k,v)->(Integer.sum(ret.get(k),v)));
                }
            }
        }

        Iterator<String> it = ret.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            int val = ret.get(key);
            logger.info("Thread{}| Key :{}, Val:{}",this.hashCode(), key, val);
        }
        return ret;
    }
}