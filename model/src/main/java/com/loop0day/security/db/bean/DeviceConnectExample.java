package com.loop0day.security.db.bean;

import java.util.ArrayList;
import java.util.List;

public class DeviceConnectExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public DeviceConnectExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(Integer value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(Integer value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(Integer value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(Integer value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<Integer> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<Integer> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(Integer value1, Integer value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdIsNull() {
            addCriterion("interface_id is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdIsNotNull() {
            addCriterion("interface_id is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdEqualTo(Integer value) {
            addCriterion("interface_id =", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdNotEqualTo(Integer value) {
            addCriterion("interface_id <>", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdGreaterThan(Integer value) {
            addCriterion("interface_id >", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("interface_id >=", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdLessThan(Integer value) {
            addCriterion("interface_id <", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("interface_id <=", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdIn(List<Integer> values) {
            addCriterion("interface_id in", values, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdNotIn(List<Integer> values) {
            addCriterion("interface_id not in", values, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdBetween(Integer value1, Integer value2) {
            addCriterion("interface_id between", value1, value2, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("interface_id not between", value1, value2, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdIsNull() {
            addCriterion("peer_device_id is null");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdIsNotNull() {
            addCriterion("peer_device_id is not null");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdEqualTo(Integer value) {
            addCriterion("peer_device_id =", value, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdNotEqualTo(Integer value) {
            addCriterion("peer_device_id <>", value, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdGreaterThan(Integer value) {
            addCriterion("peer_device_id >", value, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("peer_device_id >=", value, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdLessThan(Integer value) {
            addCriterion("peer_device_id <", value, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdLessThanOrEqualTo(Integer value) {
            addCriterion("peer_device_id <=", value, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdIn(List<Integer> values) {
            addCriterion("peer_device_id in", values, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdNotIn(List<Integer> values) {
            addCriterion("peer_device_id not in", values, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdBetween(Integer value1, Integer value2) {
            addCriterion("peer_device_id between", value1, value2, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerDeviceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("peer_device_id not between", value1, value2, "peerDeviceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdIsNull() {
            addCriterion("peer_interface_id is null");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdIsNotNull() {
            addCriterion("peer_interface_id is not null");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdEqualTo(Integer value) {
            addCriterion("peer_interface_id =", value, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdNotEqualTo(Integer value) {
            addCriterion("peer_interface_id <>", value, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdGreaterThan(Integer value) {
            addCriterion("peer_interface_id >", value, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("peer_interface_id >=", value, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdLessThan(Integer value) {
            addCriterion("peer_interface_id <", value, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("peer_interface_id <=", value, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdIn(List<Integer> values) {
            addCriterion("peer_interface_id in", values, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdNotIn(List<Integer> values) {
            addCriterion("peer_interface_id not in", values, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdBetween(Integer value1, Integer value2) {
            addCriterion("peer_interface_id between", value1, value2, "peerInterfaceId");
            return (Criteria) this;
        }

        public Criteria andPeerInterfaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("peer_interface_id not between", value1, value2, "peerInterfaceId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table device_connect
     *
     * @mbg.generated do_not_delete_during_merge Wed Jun 03 17:43:21 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table device_connect
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}