package com.example.software.project.development.management.platform.controller;

import com.example.software.project.development.management.platform.response.Result;
import com.example.software.project.development.management.platform.response.ResultCode;
import com.example.software.project.development.management.platform.ro.CirculationRo;
import com.example.software.project.development.management.platform.service.SubscriptionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/25
 */
@RestController
@RequestMapping("/subscription")
public class SubscriptionCtl {
    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * 分页获取调配审阅信息
     *
     * @param page
     * @param limit
     * @param createPersonName
     * @return
     */
    @GetMapping("/get")
    public Result<PageInfo<CirculationRo>> getCirculationRoByPage(Integer page, Integer limit, String createPersonName) {
        if (page == null || limit == null || createPersonName == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, subscriptionService.getCirculationInfoByPage(page, limit, createPersonName));
    }

    /**
     * 普通职员分页获取调配审阅信息
     *
     * @param page
     * @param limit
     * @param createPersonName
     * @return
     */
    @GetMapping("/get/normal")
    public Result<PageInfo<CirculationRo>> getNormalCirculationRoByPage(Integer page, Integer limit, String createPersonName) {
        if (page == null || limit == null || createPersonName == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, subscriptionService.getCirculationInfoByPage(page, limit, createPersonName));
    }
}
