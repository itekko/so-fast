package com.sofast.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofast.common.controller.BaseController;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;
import com.sofast.system.entity.Resource;
import com.sofast.system.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统资源表 前端控制器
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@RestController
@Api(tags = "系统资源表Controller")
@RequestMapping("/system/resource")
public class ResourceController implements BaseController<IResourceService,Resource> {

       @Autowired
       private IResourceService resourceService;

       /**
        * 系统资源表列表
        * @param  resource 系统资源表
        * @return
        */
       @ApiOperation(value = "系统资源表列表")
       @PostMapping("list")
       public Result<List<Resource>> list(@RequestBody Resource  resource){
        return list( resourceService, resource);
       }

       /**
        * 系统资源表列表(分页)
        * @param pageQuery 分页参数
        * @return
        */
       @ApiOperation(value = "系统资源表列表(分页)")
       @PostMapping("page")
       public Result<IPage<Resource>> page(@RequestBody PageQuery<Resource> pageQuery){
        return page(resourceService,pageQuery);
       }

        /**
         * 系统资源表新增 OR 更新
         * @param resource 系统资源表
         * @return
         */
        @ApiOperation(value = "新增 OR 更新")
        @PostMapping("saveOrUpdate")
        public Result<Boolean> saveOrUpdate(@RequestBody Resource  resource){
        return saveOrUpdate( resourceService, resource);
        }

        /**
         * 系统资源表详情
         * @param id
         * @return
         */
        @ApiOperation(value = "详情")
        @GetMapping("get/{id}")
        public Result<Resource> get(@PathVariable("id") Serializable id){
        return get(resourceService,id);
        }

         /**
          * 系统资源表删除
          * @param  id
          * @return
          */
         @ApiOperation(value = "删除")
         @GetMapping("remove/{id}")
         public Result<Boolean> remove(@PathVariable("id") Serializable id){
          return remove( resourceService,id);
         }

}
