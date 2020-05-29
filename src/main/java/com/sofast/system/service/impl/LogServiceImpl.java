package com.sofast.system.service.impl;

import com.sofast.system.entity.Log;
import com.sofast.system.mapper.LogMapper;
import com.sofast.system.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志  服务实现类
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
