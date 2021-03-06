package com.loop0day.security.db.dao;

import com.loop0day.security.db.bean.SecurityFunction;
import com.loop0day.security.db.bean.SecurityFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SecurityFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    long countByExample(SecurityFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int deleteByExample(SecurityFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int deleteByPrimaryKey(Integer functionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int insert(SecurityFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int insertSelective(SecurityFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    List<SecurityFunction> selectByExample(SecurityFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    SecurityFunction selectByPrimaryKey(Integer functionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByExampleSelective(@Param("record") SecurityFunction record, @Param("example") SecurityFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByExample(@Param("record") SecurityFunction record, @Param("example") SecurityFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByPrimaryKeySelective(SecurityFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_function
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByPrimaryKey(SecurityFunction record);
}