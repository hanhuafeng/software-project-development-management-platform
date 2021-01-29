package com.example.software.project.development.management.platform.service;

import com.example.software.project.development.management.platform.ro.CirculationRo;
import com.github.pagehelper.PageInfo;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/25
 */
public interface SubscriptionService {
    /**
     * 获取调配审阅信息
     * @param page
     * @param limit
     * @param createPersonName
     * @return
     */
    PageInfo<CirculationRo> getCirculationInfoByPage(Integer page, Integer limit, String createPersonName);
}
