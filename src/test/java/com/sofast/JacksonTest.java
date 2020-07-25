package com.sofast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.sofast.test.jackson.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class JacksonTest {

    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void test02() throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("111");
        Map<String,Object> map = Maps.newHashMap();
        map.put("kek",1111);
        userEntity.setOthers(map);
        String result = objectMapper.writeValueAsString(userEntity);
        System.out.println("序列化以后:" + result);

        UserEntity userEntity1 = objectMapper.readValue(result, UserEntity.class);
        System.out.println("userId" + userEntity1.getUserId());
        System.out.println("map" + userEntity1.getOthers());

    }
}
