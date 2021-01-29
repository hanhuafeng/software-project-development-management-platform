package com.example.software.project.development.management.platform.service.impl;

import com.example.software.project.development.management.platform.dao.*;
import com.example.software.project.development.management.platform.po.*;
import com.example.software.project.development.management.platform.po.ext.FundsSubscription;
import com.example.software.project.development.management.platform.ro.FundsRo;
import com.example.software.project.development.management.platform.ro.FundsSearchRoExample;
import com.example.software.project.development.management.platform.service.FundsService;
import com.example.software.project.development.management.platform.util.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/26
 */
@Service
public class FundsServiceImpl implements FundsService {
    @Autowired
    private FundsMapper fundsMapper;
    @Autowired
    private RelationSubscriptionToFoundsMapper relationSubscriptionToFoundsMapper;
    @Autowired
    private RelationProjectToFoundsMapper relationProjectToFoundsMapper;
    @Autowired
    private SubscriptionMapper subscriptionMapper;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 添加新的经费申请
     *
     * @param funds
     */
    @Override
    public void addNewFunds(Funds funds) {
        funds.setCreateTime(new Date());
        funds.setUpdateTime(new Date());
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return;
        }
        funds.setLastDealPersonType(user.getType());
        funds.setCreatePersonId(user.getId());
        fundsMapper.insertSelective(funds);
        Subscription subscription = new Subscription();
        subscription.setCreateTime(new Date());
        subscription.setUpdateTime(new Date());
        subscription.setStatus(0);
        subscriptionMapper.insertSelective(subscription);
        RelationSubscriptionToFounds relationSubscriptionToFounds = new RelationSubscriptionToFounds();
        relationSubscriptionToFounds.setFundsId(funds.getId());
        relationSubscriptionToFounds.setSubscriptionId(subscription.getId());
        relationSubscriptionToFoundsMapper.insertSelective(relationSubscriptionToFounds);
        RelationProjectToFounds relationProjectToFounds = new RelationProjectToFounds();
        relationProjectToFounds.setProjectId(funds.getProjectId());
        relationProjectToFounds.setFundsId(funds.getId());
        relationProjectToFoundsMapper.insertSelective(relationProjectToFounds);
    }

    /**
     * 分页获取预算申报
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<FundsRo> getFundsByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        PageInfo<FundsRo> pageInfo = new PageInfo<>(new LinkedList<>());
        FundsSearchRoExample example = new FundsSearchRoExample();
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return null;
        }
        FundsSearchRoExample.Criteria criteria = example.createCriteria();
        if (user.getType() == 1) {
            // 项目经理只要找跟自己有关的项目就可以了
            List<Integer> projectIds = userUtil.getUserDutyProjectList(user.getId()).stream().map(Project::getId).collect(Collectors.toList());
            criteria.andProjectIdIn(projectIds);
        }
        if (user.getType() == 2) {
            //普通职员需要通过自己负责子项目查到父项目
            List<Project> projects = userUtil.getUserDutyProjectList(user.getId());
            List<Integer> pids = projects.stream().map(Project::getPid).collect(Collectors.toList());
            ProjectExample example1 = new ProjectExample();
            example1.createCriteria()
                    .andIdIn(pids);
            List<Project> projectList = projectMapper.selectByExample(example1);
            if(projectList!=null){
                List<Integer> ids = projectList.stream().map(Project::getId).collect(Collectors.toList());
                criteria.andProjectIdIn(ids);
            }
            criteria.andCreatePersonIdEqualTo(user.getId());
        }
        List<FundsRo> fundsRos = fundsMapper.getFundsList(example);
        if (fundsRos != null) {
            pageInfo = new PageInfo<>(fundsRos);
        }
        return pageInfo;
    }

    /**
     * 更新审批状态
     *
     * @param fundsSubscription
     */
    @Override
    public void updateFundsSubscriptionStatus(FundsSubscription fundsSubscription) {
        User user = UserUtil.gerUserInfo();
        if(user == null){
            return ;
        }
        int subscriptionId = fundsSubscription.getSubscriptionId();
        int status = fundsSubscription.getStatus();
        int fundsId = fundsSubscription.getFundsId();
        Subscription subscription = new Subscription();
        subscription.setId(subscriptionId);
        subscription.setStatus(status);
        subscription.setDealPersonId(user.getId());
        subscription.setUpdateTime(new Date());
        subscriptionMapper.updateByPrimaryKeySelective(subscription);
        Funds funds = new Funds();
        funds.setId(fundsId);
        funds.setUpdateTime(new Date());
        funds.setLastDealPersonType(user.getType());
        fundsMapper.updateByPrimaryKeySelective(funds);
    }
}
