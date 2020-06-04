package com.loop0day.security.db.bean;

public class SecurityVariant {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column security_variant.variant_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private Integer variantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column security_variant.variant_name
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    private String variantName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_variant
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public SecurityVariant(Integer variantId, String variantName) {
        this.variantId = variantId;
        this.variantName = variantName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_variant
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public SecurityVariant() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column security_variant.variant_id
     *
     * @return the value of security_variant.variant_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Integer getVariantId() {
        return variantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column security_variant.variant_id
     *
     * @param variantId the value for security_variant.variant_id
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column security_variant.variant_name
     *
     * @return the value of security_variant.variant_name
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getVariantName() {
        return variantName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column security_variant.variant_name
     *
     * @param variantName the value for security_variant.variant_name
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setVariantName(String variantName) {
        this.variantName = variantName == null ? null : variantName.trim();
    }
}