package com.example.software.project.development.management.platform.controller;

import com.example.software.project.development.management.platform.po.Funds;
import com.example.software.project.development.management.platform.po.ext.FundsSubscription;
import com.example.software.project.development.management.platform.response.Result;
import com.example.software.project.development.management.platform.response.ResultCode;
import com.example.software.project.development.management.platform.ro.FundsRo;
import com.example.software.project.development.management.platform.service.FundsService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/26
 */
@RestController
@RequestMapping("/funds")
public class FundsCtl {
    @Autowired
    private FundsService fundsService;

    /**
     * 添加新的预算申报
     * @param funds
     * @return
     */
    @PostMapping("/add")
    public Result<T> addNewFunds(@RequestBody Funds funds){
        if(StringUtils.isEmpty(funds.getReason()) || funds.getPrice() == null){
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        fundsService.addNewFunds(funds);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 分页获取预算申报
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/get")
    public Result<PageInfo<FundsRo>> getFundsByPage(Integer page,Integer limit){
        if(page == null || limit == null){
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS,fundsService.getFundsByPage(page,limit));
    }

    /**
     * 处理资金审批
     * @param fundsSubscription
     * @return
     */
    @PutMapping("/deal")
    public Result<T> dealFundsSubscription(@RequestBody FundsSubscription fundsSubscription){
        if(fundsSubscription.getFundsId() == null
                || fundsSubscription.getStatus() == null
        || fundsSubscription.getSubscriptionId() == null){
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        fundsService.updateFundsSubscriptionStatus(fundsSubscription);
        return new Result<>(ResultCode.SUCCESS);
    }
}
