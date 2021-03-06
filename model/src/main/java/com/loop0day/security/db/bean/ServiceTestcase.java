package com.loop0day.security.db.bean;

public class ServiceTestcase {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.src
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String src;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.sport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String sport;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.dst
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String dst;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.dport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String dport;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.protocol
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String protocol;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.privileges
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String privileges;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.variant_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer variantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.threat_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer threatId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.record
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Double record;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.detect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Double detect;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.alarm
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Double alarm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_testcase.block
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Double block;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table service_testcase
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public ServiceTestcase(Integer id, String src, String sport, String dst, String dport, String protocol, String privileges, Integer variantId, Integer threatId, Double record, Double detect, Double alarm, Double block) {
        this.id = id;
        this.src = src;
        this.sport = sport;
        this.dst = dst;
        this.dport = dport;
        this.protocol = protocol;
        this.privileges = privileges;
        this.variantId = variantId;
        this.threatId = threatId;
        this.record = record;
        this.detect = detect;
        this.alarm = alarm;
        this.block = block;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table service_testcase
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public ServiceTestcase() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.id
     *
     * @return the value of service_testcase.id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.id
     *
     * @param id the value for service_testcase.id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.src
     *
     * @return the value of service_testcase.src
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getSrc() {
        return src;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.src
     *
     * @param src the value for service_testcase.src
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.sport
     *
     * @return the value of service_testcase.sport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getSport() {
        return sport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.sport
     *
     * @param sport the value for service_testcase.sport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setSport(String sport) {
        this.sport = sport == null ? null : sport.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.dst
     *
     * @return the value of service_testcase.dst
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getDst() {
        return dst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.dst
     *
     * @param dst the value for service_testcase.dst
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDst(String dst) {
        this.dst = dst == null ? null : dst.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.dport
     *
     * @return the value of service_testcase.dport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getDport() {
        return dport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.dport
     *
     * @param dport the value for service_testcase.dport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDport(String dport) {
        this.dport = dport == null ? null : dport.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.protocol
     *
     * @return the value of service_testcase.protocol
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.protocol
     *
     * @param protocol the value for service_testcase.protocol
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.privileges
     *
     * @return the value of service_testcase.privileges
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getPrivileges() {
        return privileges;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.privileges
     *
     * @param privileges the value for service_testcase.privileges
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setPrivileges(String privileges) {
        this.privileges = privileges == null ? null : privileges.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.variant_id
     *
     * @return the value of service_testcase.variant_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getVariantId() {
        return variantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.variant_id
     *
     * @param variantId the value for service_testcase.variant_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.threat_id
     *
     * @return the value of service_testcase.threat_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getThreatId() {
        return threatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.threat_id
     *
     * @param threatId the value for service_testcase.threat_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setThreatId(Integer threatId) {
        this.threatId = threatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.record
     *
     * @return the value of service_testcase.record
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Double getRecord() {
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.record
     *
     * @param record the value for service_testcase.record
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setRecord(Double record) {
        this.record = record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.detect
     *
     * @return the value of service_testcase.detect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Double getDetect() {
        return detect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.detect
     *
     * @param detect the value for service_testcase.detect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDetect(Double detect) {
        this.detect = detect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.alarm
     *
     * @return the value of service_testcase.alarm
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Double getAlarm() {
        return alarm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.alarm
     *
     * @param alarm the value for service_testcase.alarm
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setAlarm(Double alarm) {
        this.alarm = alarm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_testcase.block
     *
     * @return the value of service_testcase.block
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Double getBlock() {
        return block;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_testcase.block
     *
     * @param block the value for service_testcase.block
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setBlock(Double block) {
        this.block = block;
    }
}