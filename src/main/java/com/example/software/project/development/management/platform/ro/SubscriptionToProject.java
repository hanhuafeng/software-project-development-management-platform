package com.example.software.project.development.management.platform.ro;

import lombok.Data;

@Data
public class SubscriptionToProject {
    private Integer subscriptionId;
    private Integer projectId;
    private Integer toPersonId;
    private String reason;
}
