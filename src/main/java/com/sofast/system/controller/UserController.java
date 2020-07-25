package com.sofast.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofast.common.controller.BaseController;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;
import com.sofast.system.entity.User;
import com.sofast.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@RestController
@Api(tags = "系统用户表Controller")
@RequestMapping("/system/user")
public class UserController implements BaseController<IUserService,User> {

       @Autowired
       private IUserService userService;

       /**
        * 系统用户表列表
        * @param  user 系统用户表
        * @return
        */
       @ApiOperation(value = "系统用户表列表")
       @PostMapping("list")
       public Result<List<User>> list(@RequestBody User  user){
        return list( userService, user);
       }

       /**
        * 系统用户表列表(分页)
        * @param pageQuery 分页参数
        * @return
        */
       @ApiOperation(value = "系统用户表列表(分页)")
       @PostMapping("page")
       public Result<IPage<User>> page(@RequestBody PageQuery<User> pageQuery){
        return page(userService,pageQuery);
       }

        /**
         * 系统用户表新增 OR 更新
         * @param user 系统用户表
         * @return
         */
        @ApiOperation(value = "新增 OR 更新")
        @PostMapping("saveOrUpdate")
        public Result<Boolean> saveOrUpdate(@RequestBody User  user){
        return saveOrUpdate( userService, user);
        }

        /**
         * 系统用户表详情
         * @param id
         * @return
         */
        @ApiOperation(value = "详情")
        @GetMapping("get/{id}")
        public Result<User> get(@PathVariable("id") Serializable id){
        return get(userService,id);
        }

         /**
          * 系统用户表删除
          * @param  id
          * @return
          */
         @ApiOperation(value = "删除")
         @GetMapping("remove/{id}")
         public Result<Boolean> remove(@PathVariable("id") Serializable id){
          return remove( userService,id);
         }

}
