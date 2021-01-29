package com.example.software.project.development.management.platform.service.impl;

import com.example.software.project.development.management.platform.dao.*;
import com.example.software.project.development.management.platform.po.*;
import com.example.software.project.development.management.platform.ro.SubscriptionToProject;
import com.example.software.project.development.management.platform.service.ProjectService;
import com.example.software.project.development.management.platform.util.DateUtils;
import com.example.software.project.development.management.platform.util.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/24
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SubscriptionMapper subscriptionMapper;
    @Autowired
    private CirculationMapper circulationMapper;
    @Autowired
    private RelationProjectToCirculationMapper relationProjectToCirculationMapper;
    @Autowired
    private RelationSubscriptionToCirculationMapper relationSubscriptionToCirculationMapper;
    @Autowired
    private UserUtil userUtil;

    /**
     * 新增一个项目，可新增父项目，也可以新增子项目
     *
     * @param project
     */
    @Override
    public void addNewProject(Project project) {
        project.setCreateTime(new Date());
        project.setUpdateTime(new Date());
        project.setId(null);
        project.setStatus(0);
        Integer userId = project.getDutyPersonId();
        if (userId == null) {
            return;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        project.setDutyPersonName(user.getTrueName());
        projectMapper.insertSelective(project);
    }

    /**
     * 根据项目编号修改项目内容
     *
     * @param project
     */
    @Override
    public void updateProjectByProjectId(Project project) {
        project.setUpdateTime(new Date());
        project.setPid(null);
        int id = project.getId();
        ProjectExample example = new ProjectExample();
        // 根据父项目的时间，调整子项目的时间
        // 如果父项目的开始时间往后调整，子项目应该同时往后调整
        example.createCriteria()
                .andPidEqualTo(id)
                .andStartTimeLessThan(project.getStartTime());
        Project upProject = new Project();
        upProject.setStartTime(project.getStartTime());
        projectMapper.updateByExampleSelective(upProject, example);
        // 如果父项目的结束时间往前调整，子项目应该同时往前调整
        example.clear();
        example.createCriteria()
                .andPidEqualTo(id)
                .andEndTimeGreaterThan(project.getEndTime());
        upProject.setStartTime(null);
        upProject.setEndTime(project.getEndTime());
        projectMapper.updateByExampleSelective(upProject, example);
        Integer userId = project.getDutyPersonId();
        if (userId != null) {
            User user = userMapper.selectByPrimaryKey(userId);
            project.setDutyPersonName(user.getTrueName());
        }
        // 最后更新父项目
        projectMapper.updateByPrimaryKeySelective(project);
    }

    /**
     * 根据项目编号删除项目
     * 如果是父项目，还会删除子项目
     *
     * @param id 项目编号
     */
    @Override
    public void deleteProjectByProjectId(Integer id) {
        ProjectExample example = new ProjectExample();
        example.or().andIdEqualTo(id);
        example.or().andPidEqualTo(id);
        projectMapper.deleteByExample(example);
    }

    /**
     * 根据父项目编号，分页找子项目
     *
     * @param page
     * @param limit
     * @param projectName 项目名称
     * @param fatherId    父项目编号
     * @return
     */
    @Override
    public PageInfo<Project> getProjectListByPage(Integer page, Integer limit, String projectName, Integer fatherId) {
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return null;
        }
        Integer userType = user.getType();
        PageHelper.startPage(page, limit);
        PageInfo<Project> pageInfo = new PageInfo<>(new LinkedList<>());
        if (user.getType() == 2 && fatherId == 0) {
            //普通职员需要通过自己负责子项目查到父项目
            List<Project> dutyProjectList = userUtil.getUserDutyProjectList(user.getId());
            List<Integer> pids = dutyProjectList.stream().map(Project::getPid).collect(Collectors.toList());
            ProjectExample example1 = new ProjectExample();
            example1.createCriteria()
                    .andNameLike("%" + projectName + "%")
                    .andIdIn(pids);
            List<Project> projectList = projectMapper.selectByExample(example1);
            pageInfo = new PageInfo<>(projectList);
        }else{
            ProjectExample example = new ProjectExample();
            ProjectExample.Criteria criteria = example.createCriteria();
            criteria.andNameLike("%" + projectName + "%")
                    .andPidEqualTo(fatherId);
            if ((userType == 2 && fatherId != 0) || (userType == 1 && fatherId == 0)) {
                criteria.andDutyPersonIdEqualTo(user.getId());
            }
            List<Project> projects = projectMapper.selectAllProjectWithCount(example);
            if (projects != null && projects.size() != 0) {
                pageInfo = new PageInfo<>(projects);
            }
        }
        return pageInfo;
    }

    /**
     * 根据项目编号获取项目信息
     *
     * @param id
     * @return
     */
    @Override
    public Project getProjectById(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStatus(Project project) {
        Integer id = project.getId();
        // 1完成 0未完成
        Integer status = project.getStatus();
        if (status == 1) {
            ProjectExample example = new ProjectExample();
            example.createCriteria()
                    .andPidEqualTo(id);
            Project upProject = new Project();
            upProject.setStatus(1);
            projectMapper.updateByExampleSelective(upProject, example);
        }
        project.setEndTime(null);
        project.setStartTime(null);
        project.setPid(null);
        project.setDutyPersonName(null);
        project.setUpdateTime(null);
        project.setCreateTime(null);
        project.setDescribeInfo(null);
        project.setDutyPersonId(null);
        project.setName(null);
        project.setNeedTime(null);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    /**
     * 提交审批请求
     *
     * @param subscriptionToProject
     */
    @Override
    public void subscriptionToOtherPerson(SubscriptionToProject subscriptionToProject) {
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return;
        }
        Integer projectId = subscriptionToProject.getProjectId();
        Project project = new Project();
        project.setId(projectId);
        project.setStatus(3);
        project.setUpdateTime(new Date());
        projectMapper.updateByPrimaryKeySelective(project);
        Subscription subscription = new Subscription();
        subscription.setCreateTime(new Date());
        subscription.setUpdateTime(new Date());
        subscription.setStatus(0);
        subscriptionMapper.insertSelective(subscription);
        Integer subscriptionId = subscription.getId();
        Circulation circulation = new Circulation();
        BeanUtils.copyProperties(subscriptionToProject, circulation);
        circulation.setCreatePersonId(user.getId());
        circulationMapper.insertSelective(circulation);
        Integer circulationId = circulation.getId();
        RelationProjectToCirculation relationProjectToCirculation = new RelationProjectToCirculation();
        relationProjectToCirculation.setCirculationId(circulationId);
        relationProjectToCirculation.setProjectId(projectId);
        relationProjectToCirculationMapper.insertSelective(relationProjectToCirculation);
        RelationSubscriptionToCirculation relationSubscriptionToCirculation = new RelationSubscriptionToCirculation();
        relationSubscriptionToCirculation.setCirculation(circulationId);
        relationSubscriptionToCirculation.setSubscriptionId(subscriptionId);
        relationSubscriptionToCirculationMapper.insertSelective(relationSubscriptionToCirculation);
    }

    /**
     * 更新审批状态
     *
     * @param subscription
     */
    @Override
    public void updateSubscriptionStatus(Subscription subscription) {
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return;
        }
        subscription.setDealPersonId(user.getId());
        subscription.setUpdateTime(new Date());
        subscriptionMapper.updateByPrimaryKeySelective(subscription);
        RelationSubscriptionToCirculationExample example = new RelationSubscriptionToCirculationExample();
        example.createCriteria()
                .andSubscriptionIdEqualTo(subscription.getId());
        List<RelationSubscriptionToCirculation> subscriptionToCirculations = relationSubscriptionToCirculationMapper.selectByExample(example);
        RelationSubscriptionToCirculation subscriptionToCirculation = subscriptionToCirculations.get(0);
        RelationProjectToCirculationExample example1 = new RelationProjectToCirculationExample();
        example1.createCriteria()
                .andCirculationIdEqualTo(subscriptionToCirculation.getCirculation());
        List<RelationProjectToCirculation> relationProjectToCirculations = relationProjectToCirculationMapper.selectByExample(example1);
        RelationProjectToCirculation relationProjectToCirculation = relationProjectToCirculations.get(0);
        Integer projectId = relationProjectToCirculation.getProjectId();
        Project project = new Project();
        project.setId(projectId);
        project.setStatus(0);
        if (subscription.getStatus() == 1) {
            Circulation circulation = circulationMapper.selectByPrimaryKey(relationProjectToCirculation.getCirculationId());
            project.setDutyPersonId(circulation.getToPersonId());
        }
        projectMapper.updateByPrimaryKeySelective(project);
    }

    /**
     * 获取项目图表数据
     *
     * @param projectId
     * @param type      0 年，1 月
     * @return
     */
    @Override
    public Map<String, List<Object>> getProjectCharts(Integer projectId, Integer type) throws ParseException {
        Map<String, List<Object>> result = new LinkedHashMap<>();
        result.put("nameList", new LinkedList<>());
        result.put("successList", new LinkedList<>());
        result.put("errorList", new LinkedList<>());
        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria()
                .andPidEqualTo(projectId);
        Map<String, Map<String, Long>> nameToSuccessAndError = new LinkedHashMap<>();
        List<Project> projects = projectMapper.selectByExample(projectExample);
        for (Project project : projects) {
            User user = userMapper.selectByPrimaryKey(project.getDutyPersonId());
            if (user == null) {
                continue;
            }
            if (type == 0 && DateUtils.belongCalendar(project.getUpdateTime(), DateUtils.getThisYear())) {

            }
            if (nameToSuccessAndError.get(user.getTrueName()) == null) {
                nameToSuccessAndError.put(user.getTrueName(), new LinkedHashMap<>());
                nameToSuccessAndError.get(user.getTrueName()).put("success", 0L);
                nameToSuccessAndError.get(user.getTrueName()).put("error", 0L);
            }
            //超时未完成的情况：status = 1 更新时间超过最后时间
            if (project.getStatus() == 1 && DateUtils.belongCalendar(project.getUpdateTime(), project.getEndTime())) {
                // 异常情况
                long errorNum = nameToSuccessAndError.get(user.getTrueName()).get("error") + 1L;
                nameToSuccessAndError.get(user.getTrueName()).put("error", errorNum);
            }
            if (project.getStatus() == 1) {
                long dqcday = (long) ((project.getEndTime().getTime() - project.getStartTime().getTime()) / (1000 * 60 * 60 * 24) + 0.5);
                long successNum = nameToSuccessAndError.get(user.getTrueName()).get("success") + dqcday;
                nameToSuccessAndError.get(user.getTrueName()).put("success", successNum);
            }
        }
        for (String name : nameToSuccessAndError.keySet()) {
            result.get("nameList").add(name);
            result.get("successList").add(nameToSuccessAndError.get(name).get("success"));
            result.get("errorList").add(nameToSuccessAndError.get(name).get("error"));
        }
        return result;
    }

    @Override
    public Map<String, Integer> getProjectStatusNum(Integer projectId) {
        Map<String, Integer> result = new LinkedHashMap<>();
        result.put("success", 0);
        result.put("error", 0);
        ProjectExample example = new ProjectExample();
        example.createCriteria()
                .andPidEqualTo(projectId);
        List<Project> projects = projectMapper.selectByExample(example);
        if (projects != null) {
            for (Project project : projects) {
                if (project.getStatus() == 0) {
                    int num = result.get("error") + 1;
                    result.put("error", num);
                } else {
                    int num = result.get("success") + 1;
                    result.put("success", num);
                }
            }
        }
        return result;
    }
}
