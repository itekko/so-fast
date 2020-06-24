package com.sofast.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofast.common.controller.BaseController;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;
import com.sofast.system.entity.Role;
import com.sofast.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@RestController
@Api(tags = "系统角色表Controller")
@RequestMapping("/system/role")
public class RoleController implements BaseController<IRoleService,Role> {

       @Autowired
       private IRoleService roleService;

       /**
        * 系统角色表列表
        * @param  role 系统角色表
        * @return
        */
       @ApiOperation(value = "系统角色表列表")
       @PostMapping("list")
       public Result<List<Role>> list(@RequestBody Role  role){
        return list( roleService, role);
       }

       /**
        * 系统角色表列表(分页)
        * @param pageQuery 分页参数
        * @return
        */
       @ApiOperation(value = "系统角色表列表(分页)")
       @PostMapping("page")
       public Result<IPage<Role>> page(@RequestBody PageQuery<Role> pageQuery){
        return page(roleService,pageQuery);
       }

        /**
         * 系统角色表新增 OR 更新
         * @param role 系统角色表
         * @return
         */
        @ApiOperation(value = "新增 OR 更新")
        @PostMapping("saveOrUpdate")
        public Result<Boolean> saveOrUpdate(@RequestBody Role  role){
        return saveOrUpdate( roleService, role);
        }

        /**
         * 系统角色表详情
         * @param id
         * @return
         */
        @ApiOperation(value = "详情")
        @GetMapping("get/{id}")
        public Result<Role> get(@PathVariable("id") Serializable id){
        return get(roleService,id);
        }

         /**
          * 系统角色表删除
          * @param  id
          * @return
          */
         @ApiOperation(value = "删除")
         @GetMapping("remove/{id}")
         public Result<Boolean> remove(@PathVariable("id") Serializable id){
          return remove( roleService,id);
         }

}
