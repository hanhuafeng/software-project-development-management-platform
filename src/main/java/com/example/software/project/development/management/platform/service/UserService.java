package com.example.software.project.development.management.platform.service;

import com.example.software.project.development.management.platform.po.User;
import com.example.software.project.development.management.platform.ro.MenuResult;
import com.example.software.project.development.management.platform.ro.UserPermissionRo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/21
 */
public interface UserService {
    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    String userLogin(User user);

    /**
     * 获取当前用户信息
     *
     * @return
     */
    UserPermissionRo getUserInfo();

    /**
     * @param
     * @return
     * @Description: 根据用户类型获取menu列表
     */
    List<MenuResult> getMenu(Integer userType);

    /**
     * 分页获取用户列表
     *
     * @return
     */
    PageInfo<User> gerUserListByPage(Integer pageNum, Integer pageSize, String userName);

    /**
     * 新增用户
     *
     * @param user
     */
    void addNewUser(User user);

    /**
     * 检测用户是否存在
     *
     * @param user
     * @return
     */
    boolean checkUserExist(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 通过用户编号获取用户信息
     *
     * @param id
     * @return
     */
    User getUserInfoById(Integer id);

    /**
     * 根据用户编号更新用户信息
     *
     * @param user
     */
    void updateUserInfoByUserId(User user);

    /**
     * 根据用户类型获取用户
     * @param userType
     * @return
     */
    List<User> getUsersByUserType(Integer userType);
}
