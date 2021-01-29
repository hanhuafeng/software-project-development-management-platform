package com.example.software.project.development.management.platform.ro;

import lombok.Data;

import java.util.Date;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/26
 */
@Data
public class FundsRo {
    private int fundsId;
    private String reason;
    private String price;
    private int lastDealPersonType;
    private Date createTime;
    private Date updateTime;
    private int subscriptionId;
    private int status;
    private int createPersonId;
    private String projectName;
    private String trueName;
    private int projectId;
}
