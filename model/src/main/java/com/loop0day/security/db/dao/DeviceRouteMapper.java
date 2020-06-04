package com.loop0day.security.db.dao;

import com.loop0day.security.db.bean.DeviceRoute;
import com.loop0day.security.db.bean.DeviceRouteExample;
import com.loop0day.security.db.bean.DeviceRouteKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceRouteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    long countByExample(DeviceRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int deleteByExample(DeviceRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int deleteByPrimaryKey(DeviceRouteKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int insert(DeviceRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int insertSelective(DeviceRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    List<DeviceRoute> selectByExample(DeviceRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    DeviceRoute selectByPrimaryKey(DeviceRouteKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByExampleSelective(@Param("record") DeviceRoute record, @Param("example") DeviceRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByExample(@Param("record") DeviceRoute record, @Param("example") DeviceRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByPrimaryKeySelective(DeviceRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    int updateByPrimaryKey(DeviceRoute record);
}