package com.loop0day.security.db.bean;

public class DeviceRouteKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_route.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_route.destination
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String destination;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_route.genmask
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String genmask;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceRouteKey(Integer deviceId, String destination, String genmask) {
        this.deviceId = deviceId;
        this.destination = destination;
        this.genmask = genmask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_route
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceRouteKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_route.device_id
     *
     * @return the value of device_route.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_route.device_id
     *
     * @param deviceId the value for device_route.device_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_route.destination
     *
     * @return the value of device_route.destination
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getDestination() {
        return destination;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_route.destination
     *
     * @param destination the value for device_route.destination
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_route.genmask
     *
     * @return the value of device_route.genmask
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getGenmask() {
        return genmask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_route.genmask
     *
     * @param genmask the value for device_route.genmask
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setGenmask(String genmask) {
        this.genmask = genmask == null ? null : genmask.trim();
    }
}