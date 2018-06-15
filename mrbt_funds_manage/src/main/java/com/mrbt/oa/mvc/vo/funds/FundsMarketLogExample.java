package com.mrbt.oa.mvc.vo.funds;

import com.mrbt.oa.mvc.vo.BaseExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundsMarketLogExample extends BaseExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public FundsMarketLogExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(BigDecimal value) {
			addCriterion("ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(BigDecimal value) {
			addCriterion("ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(BigDecimal value) {
			addCriterion("ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(BigDecimal value) {
			addCriterion("ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
			addCriterion("ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<BigDecimal> values) {
			addCriterion("ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<BigDecimal> values) {
			addCriterion("ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andFundsCodeIsNull() {
			addCriterion("FUNDS_CODE is null");
			return (Criteria) this;
		}

		public Criteria andFundsCodeIsNotNull() {
			addCriterion("FUNDS_CODE is not null");
			return (Criteria) this;
		}

		public Criteria andFundsCodeEqualTo(String value) {
			addCriterion("FUNDS_CODE =", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeNotEqualTo(String value) {
			addCriterion("FUNDS_CODE <>", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeGreaterThan(String value) {
			addCriterion("FUNDS_CODE >", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeGreaterThanOrEqualTo(String value) {
			addCriterion("FUNDS_CODE >=", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeLessThan(String value) {
			addCriterion("FUNDS_CODE <", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeLessThanOrEqualTo(String value) {
			addCriterion("FUNDS_CODE <=", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeLike(String value) {
			addCriterion("FUNDS_CODE like", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeNotLike(String value) {
			addCriterion("FUNDS_CODE not like", value, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeIn(List<String> values) {
			addCriterion("FUNDS_CODE in", values, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeNotIn(List<String> values) {
			addCriterion("FUNDS_CODE not in", values, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeBetween(String value1, String value2) {
			addCriterion("FUNDS_CODE between", value1, value2, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andFundsCodeNotBetween(String value1, String value2) {
			addCriterion("FUNDS_CODE not between", value1, value2, "fundsCode");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("CREATE_TIME is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("CREATE_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("CREATE_TIME =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("CREATE_TIME <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("CREATE_TIME >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("CREATE_TIME >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("CREATE_TIME <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("CREATE_TIME <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("CREATE_TIME in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("CREATE_TIME not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("CREATE_TIME between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("CREATE_TIME not between", value1, value2,
					"createTime");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesIsNull() {
			addCriterion("OFFLINE_NOTES is null");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesIsNotNull() {
			addCriterion("OFFLINE_NOTES is not null");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesEqualTo(String value) {
			addCriterion("OFFLINE_NOTES =", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesNotEqualTo(String value) {
			addCriterion("OFFLINE_NOTES <>", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesGreaterThan(String value) {
			addCriterion("OFFLINE_NOTES >", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesGreaterThanOrEqualTo(String value) {
			addCriterion("OFFLINE_NOTES >=", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesLessThan(String value) {
			addCriterion("OFFLINE_NOTES <", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesLessThanOrEqualTo(String value) {
			addCriterion("OFFLINE_NOTES <=", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesLike(String value) {
			addCriterion("OFFLINE_NOTES like", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesNotLike(String value) {
			addCriterion("OFFLINE_NOTES not like", value, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesIn(List<String> values) {
			addCriterion("OFFLINE_NOTES in", values, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesNotIn(List<String> values) {
			addCriterion("OFFLINE_NOTES not in", values, "offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesBetween(String value1, String value2) {
			addCriterion("OFFLINE_NOTES between", value1, value2,
					"offlineNotes");
			return (Criteria) this;
		}

		public Criteria andOfflineNotesNotBetween(String value1, String value2) {
			addCriterion("OFFLINE_NOTES not between", value1, value2,
					"offlineNotes");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerIsNull() {
			addCriterion("FUNDS_CODE_INNER is null");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerIsNotNull() {
			addCriterion("FUNDS_CODE_INNER is not null");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerEqualTo(String value) {
			addCriterion("FUNDS_CODE_INNER =", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerNotEqualTo(String value) {
			addCriterion("FUNDS_CODE_INNER <>", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerGreaterThan(String value) {
			addCriterion("FUNDS_CODE_INNER >", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerGreaterThanOrEqualTo(String value) {
			addCriterion("FUNDS_CODE_INNER >=", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerLessThan(String value) {
			addCriterion("FUNDS_CODE_INNER <", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerLessThanOrEqualTo(String value) {
			addCriterion("FUNDS_CODE_INNER <=", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerLike(String value) {
			addCriterion("FUNDS_CODE_INNER like", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerNotLike(String value) {
			addCriterion("FUNDS_CODE_INNER not like", value, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerIn(List<String> values) {
			addCriterion("FUNDS_CODE_INNER in", values, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerNotIn(List<String> values) {
			addCriterion("FUNDS_CODE_INNER not in", values, "fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerBetween(String value1, String value2) {
			addCriterion("FUNDS_CODE_INNER between", value1, value2,
					"fundsCodeInner");
			return (Criteria) this;
		}

		public Criteria andFundsCodeInnerNotBetween(String value1, String value2) {
			addCriterion("FUNDS_CODE_INNER not between", value1, value2,
					"fundsCodeInner");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FUNDS_MARKET_LOG
	 * @mbggenerated  Wed Jun 29 11:28:13 CST 2016
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FUNDS_MARKET_LOG
     *
     * @mbggenerated do_not_delete_during_merge Tue Jun 21 09:31:16 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}