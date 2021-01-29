package com.example.software.project.development.management.platform.service.impl;

import com.example.software.project.development.management.platform.dao.LogMapper;
import com.example.software.project.development.management.platform.dao.ProjectMapper;
import com.example.software.project.development.management.platform.dao.RelationProjectToLogMapper;
import com.example.software.project.development.management.platform.dao.RelationUserToLogMapper;
import com.example.software.project.development.management.platform.po.*;
import com.example.software.project.development.management.platform.service.LogService;
import com.example.software.project.development.management.platform.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/24
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RelationProjectToLogMapper relationProjectToLogMapper;
    @Autowired
    private RelationUserToLogMapper relationUserToLogMapper;

    /**
     * 获取指定项目的全部日志
     *
     * @param projectId 项目编号
     * @return
     */
    @Override
    public List<Log> getProjectLogListByProjectId(Integer projectId, String trueName) {
        List<Log> logs = logMapper.getAllProjectLogs(projectId, trueName);
        return logs == null ? new LinkedList<>() : logs;
    }

    /**
     * 添加新的日志
     *
     * @param log
     */
    @Override
    public void addNewLog(Log log) {
        Integer projectId = log.getProjectId();
        User user = UserUtil.gerUserInfo();
        if (user == null) {
            return;
        }
        Project project = projectMapper.selectByPrimaryKey(projectId);
        String title = user.getTrueName() + "的项目日报(" + project.getName()+")";
        log.setTitle(title);
        log.setCreateTime(new Date());
        logMapper.insertSelective(log);
        RelationProjectToLog relationProjectToLog = new RelationProjectToLog();
        relationProjectToLog.setLogId(log.getId());
        relationProjectToLog.setProjectId(projectId);
        relationProjectToLogMapper.insertSelective(relationProjectToLog);
        RelationUserToLog relationUserToLog = new RelationUserToLog();
        relationUserToLog.setLogId(log.getId());
        relationUserToLog.setUserId(user.getId());
        relationUserToLogMapper.insertSelective(relationUserToLog);
    }

    /**
     *
     * @param projectId
     * @return
     */
    @Override
    public ServletOutputStream export(Integer projectId) {
        ServletOutputStream out = null;

        return null;
    }
}
