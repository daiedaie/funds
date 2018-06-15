package com.mrbt.oa.mvc.vo.funds;

import com.mrbt.oa.mvc.vo.BaseExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundsKnowledgeExample extends BaseExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public FundsKnowledgeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
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

		public Criteria andTitleIsNull() {
			addCriterion("TITLE is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("TITLE is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("TITLE =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("TITLE <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("TITLE >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("TITLE >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("TITLE <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("TITLE <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("TITLE like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("TITLE not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("TITLE in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("TITLE not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("TITLE between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("TITLE not between", value1, value2, "title");
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

		public Criteria andAuthorIsNull() {
			addCriterion("AUTHOR is null");
			return (Criteria) this;
		}

		public Criteria andAuthorIsNotNull() {
			addCriterion("AUTHOR is not null");
			return (Criteria) this;
		}

		public Criteria andAuthorEqualTo(String value) {
			addCriterion("AUTHOR =", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotEqualTo(String value) {
			addCriterion("AUTHOR <>", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorGreaterThan(String value) {
			addCriterion("AUTHOR >", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorGreaterThanOrEqualTo(String value) {
			addCriterion("AUTHOR >=", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLessThan(String value) {
			addCriterion("AUTHOR <", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLessThanOrEqualTo(String value) {
			addCriterion("AUTHOR <=", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLike(String value) {
			addCriterion("AUTHOR like", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotLike(String value) {
			addCriterion("AUTHOR not like", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorIn(List<String> values) {
			addCriterion("AUTHOR in", values, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotIn(List<String> values) {
			addCriterion("AUTHOR not in", values, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorBetween(String value1, String value2) {
			addCriterion("AUTHOR between", value1, value2, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotBetween(String value1, String value2) {
			addCriterion("AUTHOR not between", value1, value2, "author");
			return (Criteria) this;
		}

		public Criteria andOnLineIsNull() {
			addCriterion("ON_LINE is null");
			return (Criteria) this;
		}

		public Criteria andOnLineIsNotNull() {
			addCriterion("ON_LINE is not null");
			return (Criteria) this;
		}

		public Criteria andOnLineEqualTo(Short value) {
			addCriterion("ON_LINE =", value, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineNotEqualTo(Short value) {
			addCriterion("ON_LINE <>", value, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineGreaterThan(Short value) {
			addCriterion("ON_LINE >", value, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineGreaterThanOrEqualTo(Short value) {
			addCriterion("ON_LINE >=", value, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineLessThan(Short value) {
			addCriterion("ON_LINE <", value, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineLessThanOrEqualTo(Short value) {
			addCriterion("ON_LINE <=", value, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineIn(List<Short> values) {
			addCriterion("ON_LINE in", values, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineNotIn(List<Short> values) {
			addCriterion("ON_LINE not in", values, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineBetween(Short value1, Short value2) {
			addCriterion("ON_LINE between", value1, value2, "onLine");
			return (Criteria) this;
		}

		public Criteria andOnLineNotBetween(Short value1, Short value2) {
			addCriterion("ON_LINE not between", value1, value2, "onLine");
			return (Criteria) this;
		}

		public Criteria andTextIsNull() {
			addCriterion("TEXT is null");
			return (Criteria) this;
		}

		public Criteria andTextIsNotNull() {
			addCriterion("TEXT is not null");
			return (Criteria) this;
		}

		public Criteria andTextEqualTo(String value) {
			addCriterion("TEXT =", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextNotEqualTo(String value) {
			addCriterion("TEXT <>", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextGreaterThan(String value) {
			addCriterion("TEXT >", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextGreaterThanOrEqualTo(String value) {
			addCriterion("TEXT >=", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextLessThan(String value) {
			addCriterion("TEXT <", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextLessThanOrEqualTo(String value) {
			addCriterion("TEXT <=", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextLike(String value) {
			addCriterion("TEXT like", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextNotLike(String value) {
			addCriterion("TEXT not like", value, "text");
			return (Criteria) this;
		}

		public Criteria andTextIn(List<String> values) {
			addCriterion("TEXT in", values, "text");
			return (Criteria) this;
		}

		public Criteria andTextNotIn(List<String> values) {
			addCriterion("TEXT not in", values, "text");
			return (Criteria) this;
		}

		public Criteria andTextBetween(String value1, String value2) {
			addCriterion("TEXT between", value1, value2, "text");
			return (Criteria) this;
		}

		public Criteria andTextNotBetween(String value1, String value2) {
			addCriterion("TEXT not between", value1, value2, "text");
			return (Criteria) this;
		}

		public Criteria andPictureIsNull() {
			addCriterion("PICTURE is null");
			return (Criteria) this;
		}

		public Criteria andPictureIsNotNull() {
			addCriterion("PICTURE is not null");
			return (Criteria) this;
		}

		public Criteria andPictureEqualTo(String value) {
			addCriterion("PICTURE =", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureNotEqualTo(String value) {
			addCriterion("PICTURE <>", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureGreaterThan(String value) {
			addCriterion("PICTURE >", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureGreaterThanOrEqualTo(String value) {
			addCriterion("PICTURE >=", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureLessThan(String value) {
			addCriterion("PICTURE <", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureLessThanOrEqualTo(String value) {
			addCriterion("PICTURE <=", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureLike(String value) {
			addCriterion("PICTURE like", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureNotLike(String value) {
			addCriterion("PICTURE not like", value, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureIn(List<String> values) {
			addCriterion("PICTURE in", values, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureNotIn(List<String> values) {
			addCriterion("PICTURE not in", values, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureBetween(String value1, String value2) {
			addCriterion("PICTURE between", value1, value2, "picture");
			return (Criteria) this;
		}

		public Criteria andPictureNotBetween(String value1, String value2) {
			addCriterion("PICTURE not between", value1, value2, "picture");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FUNDS_KNOWLEDGE
	 * @mbggenerated  Mon Jul 11 18:09:22 CST 2016
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
     * This class corresponds to the database table FUNDS_KNOWLEDGE
     *
     * @mbggenerated do_not_delete_during_merge Thu Jun 23 11:21:14 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}