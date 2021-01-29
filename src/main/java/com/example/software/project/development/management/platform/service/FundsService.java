package com.example.software.project.development.management.platform.service;

import com.example.software.project.development.management.platform.po.Funds;
import com.example.software.project.development.management.platform.po.ext.FundsSubscription;
import com.example.software.project.development.management.platform.ro.FundsRo;
import com.github.pagehelper.PageInfo;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/26
 */
public interface FundsService {
    /**
     * 添加新的经费申请
     *
     * @param funds
     */
    void addNewFunds(Funds funds);

    /**
     * 分页获取预算申报
     *
     * @param page
     * @param limit
     * @return
     */
    PageInfo<FundsRo> getFundsByPage(Integer page, Integer limit);

    /**
     * 更新订阅状态
     *
     * @param fundsSubscription
     */
    void updateFundsSubscriptionStatus(FundsSubscription fundsSubscription);
}
