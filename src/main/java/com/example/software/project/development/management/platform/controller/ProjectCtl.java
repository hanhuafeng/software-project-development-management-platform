package com.example.software.project.development.management.platform.controller;

import com.example.software.project.development.management.platform.po.Project;
import com.example.software.project.development.management.platform.ro.SubscriptionToProject;
import com.example.software.project.development.management.platform.po.Subscription;
import com.example.software.project.development.management.platform.response.Result;
import com.example.software.project.development.management.platform.response.ResultCode;
import com.example.software.project.development.management.platform.service.ProjectService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/24
 */
@RestController
@RequestMapping("/project")
public class ProjectCtl {
    @Autowired
    private ProjectService projectService;

    /**
     * 分页获取父子项目列表
     *
     * @param page
     * @param limit
     * @param projectName
     * @return
     */
    @GetMapping("/get/page")
    public Result<PageInfo<Project>> getProjectByPage(Integer page, Integer limit, String projectName, Integer fatherId) {
        if (page == null || page < 0) {
            page = 1;
        }
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (StringUtils.isEmpty(projectName)) {
            projectName = "";
        }
        return new Result<>(ResultCode.SUCCESS, projectService.getProjectListByPage(page, limit, projectName, fatherId));
    }

    @DeleteMapping("/delete")
    public Result<T> deleteProjectByProjectId(Integer id) {
        if (id == null || id < 0) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        projectService.deleteProjectByProjectId(id);
        return new Result<T>(ResultCode.SUCCESS);
    }

    @PutMapping("/update")
    public Result<T> updateProjectByProjectId(@RequestBody Project project) {
        if (project.getId() == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        projectService.updateProjectByProjectId(project);
        return new Result<T>(ResultCode.SUCCESS);
    }

    @PostMapping("/add")
    public Result<T> addNewProject(@RequestBody Project project) {
        projectService.addNewProject(project);
        return new Result<T>(ResultCode.SUCCESS);
    }

    /**
     * 根据项目编号获取项目信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public Result<Project> getProjectById(Integer id) {
        if (id == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, projectService.getProjectById(id));
    }

    /**
     * 更新项目状态
     *
     * @param project
     * @return
     */
    @PutMapping("/updateStatus")
    public Result<Project> updateStatus(@RequestBody Project project) {
        if (project.getId() == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        projectService.updateStatus(project);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 项目流转申请提交
     *
     * @param relationSubscriptionToProject
     * @return
     */
    @PostMapping("/subscriptionToOtherPerson")
    public Result<T> subscriptionToOtherPerson(@RequestBody SubscriptionToProject relationSubscriptionToProject) {
        if (relationSubscriptionToProject.getProjectId() == null
                || relationSubscriptionToProject.getToPersonId() == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        projectService.subscriptionToOtherPerson(relationSubscriptionToProject);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 更新审批状态
     *
     * @param subscription
     * @return
     */
    @PutMapping("/updateSubscriptionStatus")
    public Result<T> updateSubscriptionStatus(@RequestBody Subscription subscription) {
        if (subscription.getStatus() == null
                || subscription.getId() == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        projectService.updateSubscriptionStatus(subscription);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 获取项目图表
     * @param projectId
     * @param type
     * @return
     * @throws ParseException
     */
    @GetMapping("/getProjectCharts")
    public Result<Map<String, List<Object>>> getProjectCharts(Integer projectId, Integer type) throws ParseException {
        if(projectId == null || type == null){
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS,projectService.getProjectCharts(projectId,type));
    }

    /**
     * 获取状态数量
     * @param projectId
     * @return
     */
    @GetMapping("/getProjectStatusNum")
    public Result<Map<String, Integer>> getProjectStatusNum(Integer projectId){
        if(projectId == null){
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS,projectService.getProjectStatusNum(projectId));
    }
}
