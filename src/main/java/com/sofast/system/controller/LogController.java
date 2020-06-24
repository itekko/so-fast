package com.sofast.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofast.common.controller.BaseController;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;
import com.sofast.system.entity.Log;
import com.sofast.system.service.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sofast.common.controller.BaseController;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统日志  前端控制器
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@RestController
@Api(tags = "系统日志 Controller")
@RequestMapping("/system/log")
public class LogController implements BaseController<ILogService,Log> {

       @Autowired
       private ILogService logService;

       /**
        * 系统日志 列表
        * @param  log 系统日志 
        * @return
        */
       @ApiOperation(value = "系统日志 列表")
       @PostMapping("list")
       public Result<List<Log>> list(@RequestBody Log  log){
        return list( logService, log);
       }

       /**
        * 系统日志 列表(分页)
        * @param pageQuery 分页参数
        * @return
        */
       @ApiOperation(value = "系统日志 列表(分页)")
       @PostMapping("page")
       public Result<IPage<Log>> page(@RequestBody PageQuery<Log> pageQuery){
        return page(logService,pageQuery);
       }

        /**
         * 系统日志 新增 OR 更新
         * @param log 系统日志 
         * @return
         */
        @ApiOperation(value = "新增 OR 更新")
        @PostMapping("saveOrUpdate")
        public Result<Boolean> saveOrUpdate(@RequestBody Log  log){
        return saveOrUpdate( logService, log);
        }

        /**
         * 系统日志 详情
         * @param id
         * @return
         */
        @ApiOperation(value = "详情")
        @GetMapping("get/{id}")
        public Result<Log> get(@PathVariable("id") Serializable id){
        return get(logService,id);
        }

         /**
          * 系统日志 删除
          * @param  id
          * @return
          */
         @ApiOperation(value = "删除")
         @GetMapping("remove/{id}")
         public Result<Boolean> remove(@PathVariable("id") Serializable id){
          return remove( logService,id);
         }

}
