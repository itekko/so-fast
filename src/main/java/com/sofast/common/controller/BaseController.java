package com.sofast.common.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;

import java.io.Serializable;
import java.util.List;

/**
 * @author ekko
 * @desc 顶级Controller
 * @param <M>
 * @param <T>
 */
public interface BaseController<M extends IService, T>  {


    /**
     * 列表查询
     * @param m
     * @param t
     * @return
     */
    default  Result<List<T>> list(M m, T t){
        Wrapper<T> wrapper = new QueryWrapper<>(t);
        return Result.ok(m.list(wrapper));
    }

    /**
     * 列表查询(分页)
     * @param m
     * @param pageQuery
     * @return
     */
    default  Result<IPage<T>> page(M m, PageQuery<T> pageQuery){
        Wrapper<T> wrapper = new QueryWrapper<>(pageQuery.getData());

        Long current = pageQuery.getCurrent();
        Long size = pageQuery.getSize();
        Page<T> page =null;
        if(current == null || size == null){
            page = new Page<>(PageQuery.DEFAULT_CURRENT,PageQuery.DEFAULT_SIZE);
        }else{
            page = new Page<T>(current, size);
        }
        page.setDescs(pageQuery.getDescs());
        page.setAscs(pageQuery.getAscs());
        return Result.ok(m.page(page,wrapper));
    }

    /**
     * 新增 OR 修改
     * @param m
     * @param t
     * @return
     */
    default Result<Boolean> saveOrUpdate(M m, T t){

        return Result.ok(m.saveOrUpdate(t));
    }

    /**
     * 详情
     * @param m
     * @param id
     * @return
     */
    default Result<T> get(M m, Serializable id){

        return Result.ok((T)m.getById(id));
    }

    /**
     * 删除
     * @param m
     * @param id
     * @return
     */
    default Result<Boolean> remove(M m,Serializable id){
        return Result.ok(m.removeById(id));
    }

}
