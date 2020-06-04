package com.loop0day.security.db.bean;

public class AttackPath {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.src
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String src;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.sport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String sport;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.dst
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String dst;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.dport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String dport;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.protocol
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String protocol;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.privilege
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String privilege;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attack_path.vuln_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer vulnId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public AttackPath(Integer id, String src, String sport, String dst, String dport, String protocol, String privilege, Integer vulnId) {
        this.id = id;
        this.src = src;
        this.sport = sport;
        this.dst = dst;
        this.dport = dport;
        this.protocol = protocol;
        this.privilege = privilege;
        this.vulnId = vulnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public AttackPath() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.id
     *
     * @return the value of attack_path.id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.id
     *
     * @param id the value for attack_path.id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.src
     *
     * @return the value of attack_path.src
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getSrc() {
        return src;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.src
     *
     * @param src the value for attack_path.src
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.sport
     *
     * @return the value of attack_path.sport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getSport() {
        return sport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.sport
     *
     * @param sport the value for attack_path.sport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setSport(String sport) {
        this.sport = sport == null ? null : sport.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.dst
     *
     * @return the value of attack_path.dst
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getDst() {
        return dst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.dst
     *
     * @param dst the value for attack_path.dst
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDst(String dst) {
        this.dst = dst == null ? null : dst.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.dport
     *
     * @return the value of attack_path.dport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getDport() {
        return dport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.dport
     *
     * @param dport the value for attack_path.dport
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDport(String dport) {
        this.dport = dport == null ? null : dport.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.protocol
     *
     * @return the value of attack_path.protocol
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.protocol
     *
     * @param protocol the value for attack_path.protocol
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.privilege
     *
     * @return the value of attack_path.privilege
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.privilege
     *
     * @param privilege the value for attack_path.privilege
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attack_path.vuln_id
     *
     * @return the value of attack_path.vuln_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getVulnId() {
        return vulnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attack_path.vuln_id
     *
     * @param vulnId the value for attack_path.vuln_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setVulnId(Integer vulnId) {
        this.vulnId = vulnId;
    }
}