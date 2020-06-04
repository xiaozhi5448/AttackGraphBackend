package com.loop0day.security.db.bean;

public class DeviceThreat extends DeviceThreatKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_threat.threat_weight
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Double threatWeight;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_threat
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceThreat(Integer deviceId, Integer serviceId, Integer privilegeId, Integer threatId, Double threatWeight) {
        super(deviceId, serviceId, privilegeId, threatId);
        this.threatWeight = threatWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_threat
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceThreat() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_threat.threat_weight
     *
     * @return the value of device_threat.threat_weight
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Double getThreatWeight() {
        return threatWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_threat.threat_weight
     *
     * @param threatWeight the value for device_threat.threat_weight
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setThreatWeight(Double threatWeight) {
        this.threatWeight = threatWeight;
    }
}