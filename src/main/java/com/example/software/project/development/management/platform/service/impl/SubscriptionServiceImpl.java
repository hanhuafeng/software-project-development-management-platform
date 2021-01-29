package com.example.software.project.development.management.platform.service.impl;

import com.example.software.project.development.management.platform.dao.CirculationMapper;
import com.example.software.project.development.management.platform.dao.ProjectMapper;
import com.example.software.project.development.management.platform.po.*;
import com.example.software.project.development.management.platform.ro.CirculationRo;
import com.example.software.project.development.management.platform.service.SubscriptionService;
import com.example.software.project.development.management.platform.util.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/25
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private CirculationMapper circulationMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserUtil userUtil;

    /**
     * 获取调配审阅信息
     *
     * @param page
     * @param limit
     * @param createPersonName
     * @return
     */
    @Override
    public PageInfo<CirculationRo> getCirculationInfoByPage(Integer page, Integer limit, String createPersonName) {
        PageInfo<CirculationRo> pageInfo = new PageInfo<>(new LinkedList<>());
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return null;
        }
        Integer userId = user.getId();
        PageHelper.startPage(page, limit);
        MiddleSubscriptionExample example = new MiddleSubscriptionExample();
        example.setOrderByClause("create_time desc");
        MiddleSubscriptionExample.Criteria criteria = example.createCriteria();
        if (user.getType() == 2){
            criteria.andCreateUserTrueNameLike("%" + user.getTrueName() + "%");
        }else{
            criteria.andCreateUserTrueNameLike("%" + createPersonName + "%");
        }
        if (user.getType() != 0) {
            List<Integer> projectIds = userUtil.getUserDutyProjectList(userId).stream().map(Project::getId).collect(Collectors.toList());
            ProjectExample example1 = new ProjectExample();
            example1.createCriteria()
                    .andPidIn(projectIds);
            List<Integer> ids = projectMapper.selectByExample(example1).stream().map(Project::getId).collect(Collectors.toList());
            ids.addAll(projectIds);
            criteria.andProjectIdIn(ids);
        }
        List<CirculationRo> circulations = circulationMapper.selectAllCirculation(example);
        if (circulations != null && circulations.size() != 0) {
            pageInfo = new PageInfo<>(circulations);
        }
        return pageInfo;
    }
}
