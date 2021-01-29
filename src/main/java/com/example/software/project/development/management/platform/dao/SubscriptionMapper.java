package com.example.software.project.development.management.platform.dao;

import com.example.software.project.development.management.platform.po.Subscription;
import com.example.software.project.development.management.platform.po.SubscriptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubscriptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int countByExample(SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int deleteByExample(SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int insert(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int insertSelective(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    List<Subscription> selectByExample(SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    Subscription selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int updateByExampleSelective(@Param("record") Subscription record, @Param("example") SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int updateByExample(@Param("record") Subscription record, @Param("example") SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int updateByPrimaryKeySelective(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subscription
     *
     * @mbggenerated Mon Jan 25 16:43:41 CST 2021
     */
    int updateByPrimaryKey(Subscription record);
}