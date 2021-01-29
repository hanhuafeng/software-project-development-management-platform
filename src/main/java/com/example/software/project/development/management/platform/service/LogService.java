package com.example.software.project.development.management.platform.service;

import com.example.software.project.development.management.platform.po.Log;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/24
 */
public interface LogService {
    /**
     * 获取指定项目的全部日志
     *
     * @param projectId
     * @return
     */
    List<Log> getProjectLogListByProjectId(Integer projectId, String trueName);

    /**
     * 新增一个日志
     * @param log
     */
    void addNewLog(Log log);

    /**
     * 导出
     * @param projectId
     * @return
     */
    ServletOutputStream export(Integer projectId);
}
