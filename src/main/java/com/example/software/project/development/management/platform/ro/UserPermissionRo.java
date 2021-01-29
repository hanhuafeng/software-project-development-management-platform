package com.example.software.project.development.management.platform.ro;

import com.example.software.project.development.management.platform.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionRo {
    private User user;
    private List<MenuResult> menu;
}
