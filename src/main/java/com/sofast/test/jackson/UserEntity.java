package com.sofast.test.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class UserEntity {

    private Map<String,Object> others = Maps.newHashMap();

    private String userId;

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    private void setOthers(String key,Object value){
        others.put(key,value);
    }

    @JsonAnyGetter
    public Map<String,Object> getOthers(){
        return others;
    }


    public static void main(String[] args) {
        int size = 5000000;
        List<String> uuisList = new ArrayList<>(size);

        System.out.println("开始生成 = " + new Date());
        //生成500万个uuid
        for (int i = 0; i< size; i++){
            uuisList.add(UUID.randomUUID().toString());
        }
        System.out.println("生成结束 = " + new Date());
/*
        System.out.println("开始串行排序");
        //long starttime = System.currentTimeMillis();//毫秒
        long startTime = System.nanoTime();//纳秒，更为精确
        uuisList.stream().sorted().count();//串行排序
        long endTime = System.nanoTime();//纳秒，更为精确
        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("结束串行排序 耗时为 " + distanceTime);*/
        /**
         * 开始生成 = Tue Jul 21 18:25:14 CST 2020
         * 生成结束 = Tue Jul 21 18:25:22 CST 2020
         * 开始串行排序
         * 结束串行排序 耗时为 4615
         */

        System.out.println("开始并行排序");
        //long starttime = System.currentTimeMillis();//毫秒
        long startTime = System.nanoTime();//纳秒，更为精确

        uuisList.parallelStream().sorted().count();//并行排序

        long endTime = System.nanoTime();//纳秒，更为精确

        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("结束并行排序 耗时为 " + distanceTime);

    }

}
