package com.sofast.system.controller;


import com.sofast.common.result.Result;
import com.sofast.system.entity.Log;
import com.sofast.system.service.ILogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统日志  前端控制器
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
@RestController
@RequestMapping("/system/log")
public class LogController {

    @Autowired
    private ILogService logService;

    @ApiOperation(value = "日志列表",httpMethod = "GET")
    @GetMapping
    @RequestMapping("list")
    public Result<List<Log>> list(){
        return Result.ok(logService.list(null));
    }

}
