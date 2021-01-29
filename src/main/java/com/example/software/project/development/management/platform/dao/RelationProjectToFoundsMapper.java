package com.example.software.project.development.management.platform.dao;

import com.example.software.project.development.management.platform.po.RelationProjectToFounds;
import com.example.software.project.development.management.platform.po.RelationProjectToFoundsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationProjectToFoundsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int countByExample(RelationProjectToFoundsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int deleteByExample(RelationProjectToFoundsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int insert(RelationProjectToFounds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int insertSelective(RelationProjectToFounds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    List<RelationProjectToFounds> selectByExample(RelationProjectToFoundsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    RelationProjectToFounds selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int updateByExampleSelective(@Param("record") RelationProjectToFounds record, @Param("example") RelationProjectToFoundsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int updateByExample(@Param("record") RelationProjectToFounds record, @Param("example") RelationProjectToFoundsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int updateByPrimaryKeySelective(RelationProjectToFounds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relation_project_to_funds
     *
     * @mbggenerated Tue Jan 26 23:15:03 CST 2021
     */
    int updateByPrimaryKey(RelationProjectToFounds record);
}
