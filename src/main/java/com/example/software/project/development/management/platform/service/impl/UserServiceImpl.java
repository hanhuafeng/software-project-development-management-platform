package com.example.software.project.development.management.platform.service.impl;

import com.example.software.project.development.management.platform.dao.MenuMapper;
import com.example.software.project.development.management.platform.dao.ProjectMapper;
import com.example.software.project.development.management.platform.dao.UserMapper;
import com.example.software.project.development.management.platform.po.*;
import com.example.software.project.development.management.platform.po.ext.MenuExt;
import com.example.software.project.development.management.platform.response.GlobalVariable;
import com.example.software.project.development.management.platform.ro.MenuResult;
import com.example.software.project.development.management.platform.ro.UserPermissionRo;
import com.example.software.project.development.management.platform.service.UserService;
import com.example.software.project.development.management.platform.util.IdCardUtils;
import com.example.software.project.development.management.platform.util.TokenUtils;
import com.example.software.project.development.management.platform.util.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    @Override
    public String userLogin(User user) {
        String token = null;
        UserExample example = new UserExample();
        example.createCriteria()
                .andUserNameEqualTo(user.getUserName())
                .andPasswordEqualTo(user.getPassword())
                .andTypeEqualTo(user.getType());
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() != 0) {
            token = TokenUtils.getToken(String.valueOf(user.getId()));
            GlobalVariable.userInfoMap.put(token, users.get(0));
        }
        return token;
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @Override
    public UserPermissionRo getUserInfo() {
        UserPermissionRo userPermissionRo = null;
        User user = UserUtil.gerUserInfo();

        if (user != null) {
            userPermissionRo = new UserPermissionRo();
            userPermissionRo.setUser(user);
            userPermissionRo.setMenu(getMenu(user.getType()));
        }
        return userPermissionRo;
    }

    /**
     * @param
     * @return
     * @Description: 根据用户类型获取menu列表
     */
    @Override
    public List<MenuResult> getMenu(Integer userType) {
        List<MenuExt> menuExts = menuMapper.selectAllByPid(userType);
        Map<Integer, MenuResult> map = new HashMap<>();
        for (MenuExt menuExt : menuExts) {
            Integer id = menuExt.getId();
            Integer pid = menuExt.getPid();
            Menu menu = new Menu();
            if (pid == 0) {
                menu.setId(menuExt.getId2());
                menu.setIcon(menuExt.getIcon2());
                menu.setName(menuExt.getName2());
                menu.setPid(menuExt.getPid2());
                menu.setUrl(menuExt.getUrl2());
                if (map.get(menuExt.getId()) != null) {
                    map.get(menuExt.getId()).getChildren().add(menu);
                } else {
                    MenuResult menuResult = new MenuResult();
                    menuResult.setId(menuExt.getId());
                    menuResult.setIcon(menuExt.getIcon());
                    menuResult.setName(menuExt.getName());
                    menuResult.setPid(menuExt.getPid());
                    menuResult.setUrl(menuExt.getUrl());
                    menuResult.getChildren().add(menu);
                    map.put(menuExt.getId(), menuResult);
                }
            }
        }
        List<MenuResult> list = new LinkedList<>();
        for (Map.Entry<Integer, MenuResult> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * 分页获取用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> gerUserListByPage(Integer pageNum, Integer pageSize,String userName) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(new LinkedList<>());
        UserExample example = new UserExample();
        if (StringUtils.isNotEmpty(userName)){
            example.createCriteria()
                    .andTypeNotEqualTo(0)
                    .andUserNameLike("%"+userName+"%");
        }else{
            example.createCriteria()
                    .andTypeNotEqualTo(0);
        }

        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() != 0) {
            pageInfo = new PageInfo<>(users);
        }
        return pageInfo;
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    public void addNewUser(User user) {
        user.setId(null);
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);
    }

    /**
     * 检测用户是否存在
     *
     * @param user
     * @return true用户存在，false用户不存在
     */
    @Override
    public boolean checkUserExist(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUserNameEqualTo(user.getUserName())
                .andTypeEqualTo(user.getType());
        List<User> users = userMapper.selectByExample(example);
        return users != null && users.size() != 0;
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserInfoByUserId(User user) {
        String userName = user.getTrueName();
        ProjectExample example = new ProjectExample();
        example.createCriteria()
                .andDutyPersonIdEqualTo(user.getId());
        Project project = new Project();
        project.setDutyPersonName(userName);
        projectMapper.updateByExampleSelective(project,example);
        if(StringUtils.isNotEmpty(user.getIdcard())){
            String gender = IdCardUtils.judgeGender(user.getIdcard());
            int age = IdCardUtils.countAge(user.getIdcard());
            user.setAge(age);
            user.setSex(gender);
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据用户类型获取用户
     * @param userType
     * @return
     */
    @Override
    public List<User> getUsersByUserType(Integer userType) {
        User user = UserUtil.gerUserInfo();
        if(user == null){
            return null;
        }
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdNotEqualTo(user.getId())
                .andTypeEqualTo(userType);
        List<User> users = userMapper.selectByExample(example);
        return users!=null?users:new LinkedList<>();
    }
}
