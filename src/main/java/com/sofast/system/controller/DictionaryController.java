package com.sofast.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofast.common.controller.BaseController;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;
import com.sofast.system.entity.Dictionary;
import com.sofast.system.service.IDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@RestController
@Api(tags = "字典表Controller")
@RequestMapping("/system/dictionary")
public class DictionaryController implements BaseController<IDictionaryService,Dictionary> {

       @Autowired
       private IDictionaryService dictionaryService;

       /**
        * 字典表列表
        * @param  dictionary 字典表
        * @return
        */
       @ApiOperation(value = "字典表列表")
       @PostMapping("list")
       public Result<List<Dictionary>> list(@RequestBody Dictionary  dictionary){
        return list( dictionaryService, dictionary);
       }

       /**
        * 字典表列表(分页)
        * @param pageQuery 分页参数
        * @return
        */
       @ApiOperation(value = "字典表列表(分页)")
       @PostMapping("page")
       public Result<IPage<Dictionary>> page(@RequestBody PageQuery<Dictionary> pageQuery){
        return page(dictionaryService,pageQuery);
       }

        /**
         * 字典表新增 OR 更新
         * @param dictionary 字典表
         * @return
         */
        @ApiOperation(value = "新增 OR 更新")
        @PostMapping("saveOrUpdate")
        public Result<Boolean> saveOrUpdate(@RequestBody Dictionary  dictionary){
        return saveOrUpdate( dictionaryService, dictionary);
        }

        /**
         * 字典表详情
         * @param id
         * @return
         */
        @ApiOperation(value = "详情")
        @GetMapping("get/{id}")
        public Result<Dictionary> get(@PathVariable("id") Serializable id){
        return get(dictionaryService,id);
        }

         /**
          * 字典表删除
          * @param  id
          * @return
          */
         @ApiOperation(value = "删除")
         @GetMapping("remove/{id}")
         public Result<Boolean> remove(@PathVariable("id") Serializable id){
          return remove( dictionaryService,id);
         }

}
