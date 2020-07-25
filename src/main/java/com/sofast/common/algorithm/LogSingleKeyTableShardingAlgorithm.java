package com.sofast.common.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * 分片算法
 */
public class LogSingleKeyTableShardingAlgorithm implements PreciseShardingAlgorithm<Date>, RangeShardingAlgorithm<Date> {

    public static final String DEFAULT_TABLE_NAME = "t_system_log";
    /**
     * 精确分片算法
     * 对应PreciseShardingAlgorithm，用于处理使用单一键作为分片键的=与IN进行分片的场景。需要配合StandardShardingStrategy使用。
     * @param availableTargetNames
     * @param shardingValue
     * @return 返回表名
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        Date value = shardingValue.getValue();
        String date = new SimpleDateFormat("yyyyMM").format(value);
        for (String each : availableTargetNames) {
            if (each.endsWith(date)) {
                return each;
            }
        }
        return DEFAULT_TABLE_NAME;
    }

    /**
     * 范围分片算法
     * 对应RangeShardingAlgorithm，用于处理使用单一键作为分片键的BETWEEN AND、>、<、>=、<=进行分片的场景。需要配合StandardShardingStrategy使用。
     * @param availableTargetNames
     * @param shardingValue
     * @return 返回符号表名的列表
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Date> range = shardingValue.getValueRange();
        Date begin = range.lowerEndpoint();
        Date end = range.upperEndpoint();

        String beginStr = new SimpleDateFormat("yyyyMM").format(begin);
        String endStr =  new SimpleDateFormat("yyyyMM").format(end);
        Integer endInt = Integer.parseInt(endStr);
        Integer beginInt = Integer.parseInt(beginStr);
        //当前值是否有匹配
        boolean isMatch = false;
        //月份相同时
        if (Objects.equals(endInt,beginInt)) {

            for (String each : availableTargetNames) {
                if (each.endsWith(beginStr)) {
                    result.add(each);
                    isMatch = true;
                    break;
                }
            }
            //若没有，默认为主表
            if (!isMatch) {
                result.add(DEFAULT_TABLE_NAME);
            }

        } else {
            for (int i = beginInt; i <= endInt; i++) {
                String dateStr = i + "";
                for (String each : availableTargetNames) {
                    if (each.endsWith(dateStr)) {
                        result.add(each);
                        isMatch = true;
                        break;
                    }
                }
                //若没有，默认为主表
                if (!isMatch) {
                    result.add(DEFAULT_TABLE_NAME);
                }

                //跨年处理 201712->201801
                if (dateStr.endsWith("12")) {
                    i += 88;
                }
            }
        }

        return result;
    }
}
