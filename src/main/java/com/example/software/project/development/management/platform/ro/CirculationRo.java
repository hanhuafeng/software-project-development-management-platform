package com.example.software.project.development.management.platform.ro;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hanhuafeng
 */
@Data
public class CirculationRo {
    private Integer projectId;
    private Integer subscriptionId;
    private Integer dealStatus;
    private String toUserTrueName;
    private String createUserTrueName;
    private String projectName;
    private String reason;
    private Date createTime;
    private String dealPersonName;

    @AllArgsConstructor
    @Data
    public static class  Station{
        private String Guid;
        private String Name;
        public int check(String Name){
            if(Name!=null && (Name.contains(this.Name) || this.Name.contains(Name))) {
                return 1;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        List<Station> stations = new LinkedList<>();
        stations.add(new Station("0101","鼓楼"));
        stations.add(new Station("0202","城隍庙"));
        stations.add(new Station("0201","鼓楼站"));
        stations = stations.stream().sorted((o1, o2) -> o1.check(o2.Name)).collect(Collectors.toList());
        System.out.println(stations);
    }
}
