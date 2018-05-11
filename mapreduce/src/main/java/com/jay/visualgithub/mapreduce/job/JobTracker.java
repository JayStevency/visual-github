package com.jay.visualgithub.mapreduce.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jay on 2017. 3. 29..
 */
public class JobTracker {

    private Object[] works;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public JobTracker(Object[] works) {
        this.works = works;
    }

    private void ArrayCopy(Object[] src, int srcIdx, Object[] dest){
        logger.info("Array Copy : {}", srcIdx);
        for(int i = 0; i < dest.length; i++){
            dest[i] = src[srcIdx++];
        }
    }

    public Map<String, Integer> getPieData() throws ExecutionException, InterruptedException{
        //쓰레드 풀로 스레드 관리
        JobPool jobPool = new JobPool();
        List<Map<String, Integer>> mapResult = Collections.synchronizedList(new ArrayList<>());
        int task_length = works.length/JobPool.JOB_MAX;
        for(int i = 0; i < JobPool.JOB_MAX; i++){
            Object[] task = new Object[task_length];
            ArrayCopy(works,i * task_length, task);
            Future<Map<String, Integer>> future = jobPool.mapExcutorService.submit(new JobMap(task));
            if(!future.isCancelled()) mapResult.add(future.get());
        }

        Future<Map<String, Integer>> future = jobPool.reduceExcutorService.submit(new JobReduce(mapResult));
        return (!future.isCancelled() ? future.get(): null);
    }

}