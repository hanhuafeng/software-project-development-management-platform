package com.example.software.project.development.management.platform.util;

import com.example.software.project.development.management.platform.dao.ProjectMapper;
import com.example.software.project.development.management.platform.po.Project;
import com.example.software.project.development.management.platform.po.ProjectExample;
import com.example.software.project.development.management.platform.po.User;
import com.example.software.project.development.management.platform.response.GlobalVariable;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/21
 */
@Component
public class UserUtil {
    @Autowired
    private ProjectMapper projectMapper;
    public static User gerUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = CookieUtil.getCookie("token", request);
        if (StringUtils.isNotEmpty(token)) {
            return GlobalVariable.userInfoMap.get(token);
        }
        return null;
    }

    /**
     * 获取人员负责的项目
     * @return
     */
    public List<Project> getUserDutyProjectList(Integer userId){
        if(userId == null){
            return null;
        }
        ProjectExample example = new ProjectExample();
        example.createCriteria()
                .andDutyPersonIdEqualTo(userId);
        List<Project> projects = projectMapper.selectByExample(example);
        return projects!=null?projects:new LinkedList<>();
    }
}
