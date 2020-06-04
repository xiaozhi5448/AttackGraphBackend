package com.loop0day.security.db.bean;

import java.util.ArrayList;
import java.util.List;

public class AttackPathExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public AttackPathExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
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
     * This method corresponds to the database table attack_path
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
     * This method corresponds to the database table attack_path
     *
     * @mbg.generated Wed Jun 03 17:43:21 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attack_path
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
     * This class corresponds to the database table attack_path
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSrcIsNull() {
            addCriterion("src is null");
            return (Criteria) this;
        }

        public Criteria andSrcIsNotNull() {
            addCriterion("src is not null");
            return (Criteria) this;
        }

        public Criteria andSrcEqualTo(String value) {
            addCriterion("src =", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcNotEqualTo(String value) {
            addCriterion("src <>", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcGreaterThan(String value) {
            addCriterion("src >", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcGreaterThanOrEqualTo(String value) {
            addCriterion("src >=", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcLessThan(String value) {
            addCriterion("src <", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcLessThanOrEqualTo(String value) {
            addCriterion("src <=", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcLike(String value) {
            addCriterion("src like", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcNotLike(String value) {
            addCriterion("src not like", value, "src");
            return (Criteria) this;
        }

        public Criteria andSrcIn(List<String> values) {
            addCriterion("src in", values, "src");
            return (Criteria) this;
        }

        public Criteria andSrcNotIn(List<String> values) {
            addCriterion("src not in", values, "src");
            return (Criteria) this;
        }

        public Criteria andSrcBetween(String value1, String value2) {
            addCriterion("src between", value1, value2, "src");
            return (Criteria) this;
        }

        public Criteria andSrcNotBetween(String value1, String value2) {
            addCriterion("src not between", value1, value2, "src");
            return (Criteria) this;
        }

        public Criteria andSportIsNull() {
            addCriterion("sport is null");
            return (Criteria) this;
        }

        public Criteria andSportIsNotNull() {
            addCriterion("sport is not null");
            return (Criteria) this;
        }

        public Criteria andSportEqualTo(String value) {
            addCriterion("sport =", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportNotEqualTo(String value) {
            addCriterion("sport <>", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportGreaterThan(String value) {
            addCriterion("sport >", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportGreaterThanOrEqualTo(String value) {
            addCriterion("sport >=", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportLessThan(String value) {
            addCriterion("sport <", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportLessThanOrEqualTo(String value) {
            addCriterion("sport <=", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportLike(String value) {
            addCriterion("sport like", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportNotLike(String value) {
            addCriterion("sport not like", value, "sport");
            return (Criteria) this;
        }

        public Criteria andSportIn(List<String> values) {
            addCriterion("sport in", values, "sport");
            return (Criteria) this;
        }

        public Criteria andSportNotIn(List<String> values) {
            addCriterion("sport not in", values, "sport");
            return (Criteria) this;
        }

        public Criteria andSportBetween(String value1, String value2) {
            addCriterion("sport between", value1, value2, "sport");
            return (Criteria) this;
        }

        public Criteria andSportNotBetween(String value1, String value2) {
            addCriterion("sport not between", value1, value2, "sport");
            return (Criteria) this;
        }

        public Criteria andDstIsNull() {
            addCriterion("dst is null");
            return (Criteria) this;
        }

        public Criteria andDstIsNotNull() {
            addCriterion("dst is not null");
            return (Criteria) this;
        }

        public Criteria andDstEqualTo(String value) {
            addCriterion("dst =", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstNotEqualTo(String value) {
            addCriterion("dst <>", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstGreaterThan(String value) {
            addCriterion("dst >", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstGreaterThanOrEqualTo(String value) {
            addCriterion("dst >=", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstLessThan(String value) {
            addCriterion("dst <", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstLessThanOrEqualTo(String value) {
            addCriterion("dst <=", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstLike(String value) {
            addCriterion("dst like", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstNotLike(String value) {
            addCriterion("dst not like", value, "dst");
            return (Criteria) this;
        }

        public Criteria andDstIn(List<String> values) {
            addCriterion("dst in", values, "dst");
            return (Criteria) this;
        }

        public Criteria andDstNotIn(List<String> values) {
            addCriterion("dst not in", values, "dst");
            return (Criteria) this;
        }

        public Criteria andDstBetween(String value1, String value2) {
            addCriterion("dst between", value1, value2, "dst");
            return (Criteria) this;
        }

        public Criteria andDstNotBetween(String value1, String value2) {
            addCriterion("dst not between", value1, value2, "dst");
            return (Criteria) this;
        }

        public Criteria andDportIsNull() {
            addCriterion("dport is null");
            return (Criteria) this;
        }

        public Criteria andDportIsNotNull() {
            addCriterion("dport is not null");
            return (Criteria) this;
        }

        public Criteria andDportEqualTo(String value) {
            addCriterion("dport =", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportNotEqualTo(String value) {
            addCriterion("dport <>", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportGreaterThan(String value) {
            addCriterion("dport >", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportGreaterThanOrEqualTo(String value) {
            addCriterion("dport >=", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportLessThan(String value) {
            addCriterion("dport <", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportLessThanOrEqualTo(String value) {
            addCriterion("dport <=", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportLike(String value) {
            addCriterion("dport like", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportNotLike(String value) {
            addCriterion("dport not like", value, "dport");
            return (Criteria) this;
        }

        public Criteria andDportIn(List<String> values) {
            addCriterion("dport in", values, "dport");
            return (Criteria) this;
        }

        public Criteria andDportNotIn(List<String> values) {
            addCriterion("dport not in", values, "dport");
            return (Criteria) this;
        }

        public Criteria andDportBetween(String value1, String value2) {
            addCriterion("dport between", value1, value2, "dport");
            return (Criteria) this;
        }

        public Criteria andDportNotBetween(String value1, String value2) {
            addCriterion("dport not between", value1, value2, "dport");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNull() {
            addCriterion("protocol is null");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNotNull() {
            addCriterion("protocol is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolEqualTo(String value) {
            addCriterion("protocol =", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotEqualTo(String value) {
            addCriterion("protocol <>", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThan(String value) {
            addCriterion("protocol >", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThanOrEqualTo(String value) {
            addCriterion("protocol >=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThan(String value) {
            addCriterion("protocol <", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThanOrEqualTo(String value) {
            addCriterion("protocol <=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLike(String value) {
            addCriterion("protocol like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotLike(String value) {
            addCriterion("protocol not like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolIn(List<String> values) {
            addCriterion("protocol in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotIn(List<String> values) {
            addCriterion("protocol not in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolBetween(String value1, String value2) {
            addCriterion("protocol between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotBetween(String value1, String value2) {
            addCriterion("protocol not between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIsNull() {
            addCriterion("privilege is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIsNotNull() {
            addCriterion("privilege is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeEqualTo(String value) {
            addCriterion("privilege =", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotEqualTo(String value) {
            addCriterion("privilege <>", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeGreaterThan(String value) {
            addCriterion("privilege >", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeGreaterThanOrEqualTo(String value) {
            addCriterion("privilege >=", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLessThan(String value) {
            addCriterion("privilege <", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLessThanOrEqualTo(String value) {
            addCriterion("privilege <=", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLike(String value) {
            addCriterion("privilege like", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotLike(String value) {
            addCriterion("privilege not like", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIn(List<String> values) {
            addCriterion("privilege in", values, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotIn(List<String> values) {
            addCriterion("privilege not in", values, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeBetween(String value1, String value2) {
            addCriterion("privilege between", value1, value2, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotBetween(String value1, String value2) {
            addCriterion("privilege not between", value1, value2, "privilege");
            return (Criteria) this;
        }

        public Criteria andVulnIdIsNull() {
            addCriterion("vuln_id is null");
            return (Criteria) this;
        }

        public Criteria andVulnIdIsNotNull() {
            addCriterion("vuln_id is not null");
            return (Criteria) this;
        }

        public Criteria andVulnIdEqualTo(Integer value) {
            addCriterion("vuln_id =", value, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdNotEqualTo(Integer value) {
            addCriterion("vuln_id <>", value, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdGreaterThan(Integer value) {
            addCriterion("vuln_id >", value, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vuln_id >=", value, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdLessThan(Integer value) {
            addCriterion("vuln_id <", value, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdLessThanOrEqualTo(Integer value) {
            addCriterion("vuln_id <=", value, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdIn(List<Integer> values) {
            addCriterion("vuln_id in", values, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdNotIn(List<Integer> values) {
            addCriterion("vuln_id not in", values, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdBetween(Integer value1, Integer value2) {
            addCriterion("vuln_id between", value1, value2, "vulnId");
            return (Criteria) this;
        }

        public Criteria andVulnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vuln_id not between", value1, value2, "vulnId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table attack_path
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
     * This class corresponds to the database table attack_path
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