package com.example.software.project.development.management.platform.service;

import com.example.software.project.development.management.platform.po.Project;
import com.example.software.project.development.management.platform.po.Subscription;
import com.example.software.project.development.management.platform.ro.SubscriptionToProject;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/24
 */
public interface ProjectService {

    /**
     * 新增一个项目，可新增父项目，也可以新增子项目
     *
     * @param project
     */
    void addNewProject(Project project);

    /**
     * 根据项目编号修改项目内容
     *
     * @param project
     */
    void updateProjectByProjectId(Project project);

    /**
     * 根据项目编号删除项目
     * 如果是父项目，还会删除子项目
     *
     * @param id 项目编号
     */
    void deleteProjectByProjectId(Integer id);

    /**
     * 根据父项目编号，分页找子项目
     *
     * @param page
     * @param limit
     * @param projectName 项目名称
     * @param fatherId    父项目编号
     * @return
     */
    PageInfo<Project> getProjectListByPage(Integer page, Integer limit, String projectName, Integer fatherId);

    /**
     * 根据项目编号获取项目信息
     *
     * @param id
     * @return
     */
    Project getProjectById(Integer id);

    /**
     * 更新项目状态
     *
     * @param project
     */
    void updateStatus(Project project);

    /**
     * 项目流转给其他人员
     *
     * @param relationSubscriptionToProject
     */
    void subscriptionToOtherPerson(SubscriptionToProject relationSubscriptionToProject);

    /**
     * 更新审批状态
     *
     * @param subscription
     */
    void updateSubscriptionStatus(Subscription subscription);

    /**
     * 获取项目图表数据
     * @param projectId
     * @param type 0 年，1 月
     * @return
     */
    Map<String, List<Object>> getProjectCharts(Integer projectId,Integer type) throws ParseException;

    /**
     * 获取项目的状态数量
     * @param projectId
     * @return
     */
    Map<String,Integer> getProjectStatusNum(Integer projectId);
}
