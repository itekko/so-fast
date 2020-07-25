//package com.sofast.common.config;
//
//import com.sofast.common.algorithm.LogSingleKeyTableShardingAlgorithm;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
////@Configuration
////@AutoConfigureAfter({SpringBootConfiguration.class})
//public class DataSourceConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        return new HikariDataSource();
//    }
//
//    @Bean
//    LogSingleKeyTableShardingAlgorithm logSingleKeyTableShardingAlgorithm(){
//        return new LogSingleKeyTableShardingAlgorithm();
//    }
//
//    @Bean
//    DataSource shardingDataSource() throws SQLException {
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("ds0",dataSource());
//        // 配置第一个数据源
//        /*BasicDataSource dataSource1 = new BasicDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds0");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("");
//        dataSourceMap.put("ds0", dataSource1);*/
//
//        // 配置第二个数据源
//        /*BasicDataSource dataSource2 = new BasicDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds1");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("");
//        dataSourceMap.put("ds1", dataSource2);*/
//        //ShardingRuleConfiguration shardingRuleConfiguration =
//        // 配置Order表规则
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_system_log","ds0.t_system_log,ds0.t_system_log_202007");
//
//        // 配置分库
//        //orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        // 分表策略
//        orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("create_time",logSingleKeyTableShardingAlgorithm(),logSingleKeyTableShardingAlgorithm()));
//
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.setDefaultDataSourceName("ds0");
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        // 省略配置order_item表规则...
//        // ...
//        // 获取数据源对象
//        Properties properties = new Properties();
//        properties.setProperty("sql.show","true");
//        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, null);
//    }
//
//    /**
//     * 需要手动配置事务管理器
//     * @return
//     */
//    @Bean
//    public DataSourceTransactionManager transactitonManager() throws SQLException{
//        return new DataSourceTransactionManager(shardingDataSource());
//    }
//
//    @Bean(name = "db1SqlSessionFactory")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(shardingDataSource());
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*Mapper.xml"));
//        return bean.getObject();
//    }
//
//    @Bean(name = "db1SqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate()throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
//}
