package com.mrbt.user.model;

public class UsresDegree implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4217757350408732633L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usres_degree.id
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usres_degree.name
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usres_degree.min_amount
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    private Integer minAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usres_degree.max_amount
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    private Integer maxAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usres_degree.picture
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    private String picture;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usres_degree.id
     *
     * @return the value of usres_degree.id
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usres_degree.id
     *
     * @param id the value for usres_degree.id
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usres_degree.name
     *
     * @return the value of usres_degree.name
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usres_degree.name
     *
     * @param name the value for usres_degree.name
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usres_degree.min_amount
     *
     * @return the value of usres_degree.min_amount
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public Integer getMinAmount() {
        return minAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usres_degree.min_amount
     *
     * @param minAmount the value for usres_degree.min_amount
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usres_degree.max_amount
     *
     * @return the value of usres_degree.max_amount
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public Integer getMaxAmount() {
        return maxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usres_degree.max_amount
     *
     * @param maxAmount the value for usres_degree.max_amount
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usres_degree.picture
     *
     * @return the value of usres_degree.picture
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usres_degree.picture
     *
     * @param picture the value for usres_degree.picture
     *
     * @mbggenerated Thu Jun 18 12:05:34 CST 2015
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}