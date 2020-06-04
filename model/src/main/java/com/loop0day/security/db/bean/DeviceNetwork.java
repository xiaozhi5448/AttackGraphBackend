package com.loop0day.security.db.bean;

public class DeviceNetwork extends DeviceNetworkKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_network.address
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_network.netmask
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String netmask;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_network
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceNetwork(Integer deviceId, Integer interfaceId, String address, String netmask) {
        super(deviceId, interfaceId);
        this.address = address;
        this.netmask = netmask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_network
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceNetwork() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_network.address
     *
     * @return the value of device_network.address
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_network.address
     *
     * @param address the value for device_network.address
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_network.netmask
     *
     * @return the value of device_network.netmask
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getNetmask() {
        return netmask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_network.netmask
     *
     * @param netmask the value for device_network.netmask
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setNetmask(String netmask) {
        this.netmask = netmask == null ? null : netmask.trim();
    }
}