package com.sofast;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

//@SpringBootTest
class SoFastApplicationTests {


    @Test
    public void test02() throws Exception{
        //创建 cache ，过期时间 2 s
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build();
        //向缓存中添加 数据 K V 形式
                cache.put("hello","where are you");
        // 获取 key = hello 的 值
                System.out.println(cache.getIfPresent("hello"));
        // 延迟3 秒
                Thread.sleep(1000 * 3);
                cache.put("world","深圳");
        // return null if not present
                System.out.println(cache.getIfPresent("hello"));
                System.out.println(cache.getIfPresent("world"));
    }

}
