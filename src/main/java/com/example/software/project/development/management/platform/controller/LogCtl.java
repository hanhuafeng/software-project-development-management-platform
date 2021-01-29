package com.example.software.project.development.management.platform.controller;

import com.example.software.project.development.management.platform.po.Log;
import com.example.software.project.development.management.platform.response.Result;
import com.example.software.project.development.management.platform.response.ResultCode;
import com.example.software.project.development.management.platform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/24
 */
@RestController
@RequestMapping("/log")
public class LogCtl {
    @Autowired
    private LogService logService;

    /**
     * 获取指定项目的全部日志
     *
     * @param id 项目编号
     * @return
     */
    @GetMapping("/getProjectLog")
    public Result<List<Log>> getProjectLogListByProjectId(Integer id, String trueName) {
        if (id == null || trueName == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, logService.getProjectLogListByProjectId(id, trueName));
    }

    /**
     * 添加新的日志
     * @param log
     * @return
     */
    @PostMapping("/addNewLog")
    public Result addNewLog(@RequestBody Log log){
        logService.addNewLog(log);
        return new Result(ResultCode.SUCCESS);
    }
}
