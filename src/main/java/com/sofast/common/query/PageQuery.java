package com.sofast.common.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ekko
 * 分页查询Query类
 * @param <T>
 */
@ApiModel(value = "分页查询对象")
public class PageQuery<T> {

    /**
     * 默认当前页
     */
    public static final long DEFAULT_CURRENT = 1;

    /**
     * 每页显示条数
     */
    public static final long DEFAULT_SIZE = 20;

    /**
     * 查询数据实体
     */
    @ApiModelProperty(value = "查询数据入参")
    private T data;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Long current = DEFAULT_CURRENT;

    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数")
    private Long size = DEFAULT_SIZE;

    /**
     * <p>
     * SQL 排序 ASC 数组
     * </p>
     */
    @ApiModelProperty(value = "排序 ASC 数组")
    private List<String> ascs;
    /**
     * <p>
     * SQL 排序 DESC 数组
     * </p>
     */
    @ApiModelProperty(value = "排序 DESC 数组")
    private List<String> descs;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<String> getAscs() {
        return ascs;
    }

    public void setAscs(List<String> ascs) {
        this.ascs = ascs;
    }

    public List<String> getDescs() {
        return descs;
    }

    public void setDescs(List<String> descs) {
        this.descs = descs;
    }
}
