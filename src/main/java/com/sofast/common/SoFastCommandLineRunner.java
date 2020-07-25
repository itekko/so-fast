package com.sofast.common;

import org.apache.shardingsphere.core.strategy.route.ShardingStrategy;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SoFastCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;
    @Override
    public void run(String... args) throws Exception {
        ShardingDataSource shardingDataSource = context.getBean(ShardingDataSource.class);
        ShardingStrategy t_system_log = shardingDataSource.getRuntimeContext().getRule().getTableRule("t_system_log").getTableShardingStrategy();
    }
}
