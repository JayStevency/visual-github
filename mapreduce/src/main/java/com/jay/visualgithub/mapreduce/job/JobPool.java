package com.jay.visualgithub.mapreduce.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jay on 2017. 3. 29..
 */
public class JobPool {
    public final static int JOB_MAX = 5;
    public ExecutorService mapExcutorService;
    public ExecutorService reduceExcutorService;

    public JobPool() {
        mapExcutorService = Executors.newFixedThreadPool(JOB_MAX);
        reduceExcutorService = Executors.newSingleThreadExecutor();
    }
}