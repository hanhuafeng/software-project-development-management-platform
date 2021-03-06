package com.example.software.project.development.management.platform;

import com.example.software.project.development.management.platform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoftwareProjectDevelopmentManagementPlatformApplication.class)
@Slf4j
class SoftwareProjectDevelopmentManagementPlatformApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        //userService.gerUserListByPage();
    }

}
