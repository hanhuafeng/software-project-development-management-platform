package com.example.software.project.development.management.platform.ro;

import com.example.software.project.development.management.platform.po.Menu;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2020-02-23
 */
@Data
public class MenuResult {
    private int id;
    private String name;
    private String icon;
    private String url;
    private int pid;
    private List<Menu> children;
    public MenuResult(){
        children = new LinkedList<>();
    }
}
