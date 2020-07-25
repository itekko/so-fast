package com.sofast;

import com.sofast.system.entity.Log;
import com.sofast.system.service.ILogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ShardingJdbcTest {

    @Autowired
    ILogService logService;

    /**
     * 新增
     */
    @Test
    public void test01(){
        Log log = new Log();
        log.setMethod("小龙");
        log.setParams("---");
        logService.save(log);
       /* List<Log> list = logService.list(null);
        System.out.println(list);*/
    }
}
