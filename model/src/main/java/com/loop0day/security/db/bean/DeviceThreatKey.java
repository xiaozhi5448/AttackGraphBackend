package com.loop0day.security.db.bean;

public class DeviceThreatKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_threat.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_threat.service_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_threat.privilege_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer privilegeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_threat.threat_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer threatId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_threat
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceThreatKey(Integer deviceId, Integer serviceId, Integer privilegeId, Integer threatId) {
        this.deviceId = deviceId;
        this.serviceId = serviceId;
        this.privilegeId = privilegeId;
        this.threatId = threatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_threat
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceThreatKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_threat.device_id
     *
     * @return the value of device_threat.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_threat.device_id
     *
     * @param deviceId the value for device_threat.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_threat.service_id
     *
     * @return the value of device_threat.service_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_threat.service_id
     *
     * @param serviceId the value for device_threat.service_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_threat.privilege_id
     *
     * @return the value of device_threat.privilege_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getPrivilegeId() {
        return privilegeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_threat.privilege_id
     *
     * @param privilegeId the value for device_threat.privilege_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_threat.threat_id
     *
     * @return the value of device_threat.threat_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getThreatId() {
        return threatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_threat.threat_id
     *
     * @param threatId the value for device_threat.threat_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setThreatId(Integer threatId) {
        this.threatId = threatId;
    }
}