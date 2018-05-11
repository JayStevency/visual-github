package com.jay.visualgithub.mapreduce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jay.visualgithub.mapreduce.util.DataRender;
import com.jay.visualgithub.mapreduce.job.*;
import com.jay.visualgithub.mapreduce.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DataRenderService {

    private Object[] objects;

    private JobTracker jobTracker;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public DataRenderService(){

    }

    public DataRenderService(Object[] objects) {
        this.objects = objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }


    public Object[] getRepoListData(){
        return DataRender.getRepoList(objects);
    }

    public Object[] getPieChartData(){
        /* TODO JobTracker로 구현
         */

        Object[] result = DataRender.getPieChart(objects);


        jobTracker = new JobTracker(result);

        Map<String, Integer> list = null;
        try {
            list = jobTracker.getPieData();
        }catch (ExecutionException ee){
            logger.error("Execution Error : {}", ee.getMessage());
        }catch (InterruptedException ie){
            logger.error("Interrupted Error : {}", ie.getMessage());
        }

        /* TODO 최종 List 반환 후 PieVO Array 형태로 만드는 부분
         */

        List<PieVO> resList = new ArrayList<>();
        Object[] ret = new Object[list.size()];
        list.forEach((k,v)->{
            resList.add(new PieVO(k,v));
        });
        int i = 0;
        for(PieVO pieVO : resList){
            ret[i++] = (Object) pieVO;
        }
        return ret;
    }

    public Object getUserData(){
        return DataRender.getUserData(objects);
    }
}
