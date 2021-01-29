package com.example.software.project.development.management.platform.controller;

import com.example.software.project.development.management.platform.po.User;
import com.example.software.project.development.management.platform.response.Result;
import com.example.software.project.development.management.platform.response.ResultCode;
import com.example.software.project.development.management.platform.service.UserService;
import com.example.software.project.development.management.platform.util.CookieUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/21
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserCtl {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result userLogin(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getUserName())
                || user.getType() == null) {
            return new Result(ResultCode.INVALIDPARAM);
        }
        String cookie = userService.userLogin(user);
        if (StringUtils.isNotEmpty(cookie)) {
            CookieUtil.setCookie("token", response, cookie, 100000);
        }
        return new Result(ResultCode.SUCCESS, cookie);
    }

    /**
     * 获取用户权限
     *
     * @return
     */
    @GetMapping("/getUserPermission")
    public Result<T> getUserPermission() {
        return new Result(ResultCode.SUCCESS, userService.getUserInfo());
    }

    /**
     * 分页获取用户列表
     *
     * @return
     */
    @GetMapping("/getUserListByPage")
    public Result<PageInfo<User>> getUserListByPage(@RequestParam Integer page, @RequestParam Integer limit, String userName) {
        if (page == null || limit == null || page < 0 || limit < 0) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, userService.gerUserListByPage(page, limit, userName));
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result<T> addNewUser(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName())
                || StringUtils.isEmpty(user.getPassword())
                || user.getType() == null
                || (user.getType() != 1 && user.getType() != 2)) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        if (userService.checkUserExist(user)) {
            return new Result<>(ResultCode.CERTIFICATE_IS_OVER);
        }
        userService.addNewUser(user);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result<T> deleteUser(Integer id) {
        if (id == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        userService.deleteUser(id);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 通过用户编号获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserInfoById")
    public Result<User> getUserInfoById(Integer userId) {
        if (userId == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, userService.getUserInfoById(userId));
    }

    /**
     * 根据用户编号更新用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result<T> updateUserInfoByUserId(@RequestBody User user) {
        if (user.getId() == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        userService.updateUserInfoByUserId(user);
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 根据用户类型获取用户
     *
     * @param userType
     * @return
     */
    @GetMapping("/getUsersByUserType")
    public Result<List<User>> getUsersByUserType(Integer userType) {
        if (userType == null) {
            return new Result<>(ResultCode.INVALIDPARAM);
        }
        return new Result<>(ResultCode.SUCCESS, userService.getUsersByUserType(userType));
    }
}
