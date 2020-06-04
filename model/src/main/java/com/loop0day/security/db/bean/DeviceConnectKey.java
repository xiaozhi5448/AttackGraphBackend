package com.loop0day.security.db.bean;

public class DeviceConnectKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_connect.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_connect.interface_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer interfaceId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceConnectKey(Integer deviceId, Integer interfaceId) {
        this.deviceId = deviceId;
        this.interfaceId = interfaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceConnectKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_connect.device_id
     *
     * @return the value of device_connect.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_connect.device_id
     *
     * @param deviceId the value for device_connect.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_connect.interface_id
     *
     * @return the value of device_connect.interface_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getInterfaceId() {
        return interfaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_connect.interface_id
     *
     * @param interfaceId the value for device_connect.interface_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }
}