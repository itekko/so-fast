package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofast.common.controller.BaseController;
import com.sofast.common.query.PageQuery;
import com.sofast.common.result.Result;
import com.sofast.system.entity.${entity};
import com.sofast.system.service.I${entity}Service;
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if swagger2>
@Api(tags = "${table.comment!}Controller")
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} implements ${superControllerClass}<I${entity}Service,${entity}> {
<#else>
public class ${table.controllerName} {
</#if>

       @Autowired
       private I${entity}Service ${entity?uncap_first}Service;

       /**
        * ${table.comment!}列表
        * @param  ${entity?uncap_first} ${table.comment!}
        * @return
        */
       <#if swagger2>
       @ApiOperation(value = "${table.comment!}列表")
       </#if>
       @PostMapping("list")
       public Result<List<${entity}>> list(@RequestBody ${entity}  ${entity?uncap_first}){
        return list( ${entity?uncap_first}Service, ${entity?uncap_first});
       }

       /**
        * ${table.comment!}列表(分页)
        * @param pageQuery 分页参数
        * @return
        */
       <#if swagger2>
       @ApiOperation(value = "${table.comment!}列表(分页)")
       </#if>
       @PostMapping("page")
       public Result<IPage<${entity}>> page(@RequestBody PageQuery<${entity}> pageQuery){
        return page(${entity?uncap_first}Service,pageQuery);
       }

        /**
         * ${table.comment!}新增 OR 更新
         * @param ${entity?uncap_first} ${table.comment!}
         * @return
         */
        <#if swagger2>
        @ApiOperation(value = "新增 OR 更新")
        </#if>
        @PostMapping("saveOrUpdate")
        public Result<Boolean> saveOrUpdate(@RequestBody ${entity}  ${entity?uncap_first}){
        return saveOrUpdate( ${entity?uncap_first}Service, ${entity?uncap_first});
        }

        /**
         * ${table.comment!}详情
         * @param id
         * @return
         */
        @ApiOperation(value = "详情")
        @GetMapping("get/{id}")
        public Result<${entity}> get(@PathVariable("id") Serializable id){
        return get(${entity?uncap_first}Service,id);
        }

         /**
          * ${table.comment!}删除
          * @param  id
          * @return
          */
        <#if swagger2>
         @ApiOperation(value = "删除")
        </#if>
         @GetMapping("remove/{id}")
         public Result<Boolean> remove(@PathVariable("id") Serializable id){
          return remove( ${entity?uncap_first}Service,id);
         }

}
</#if>
