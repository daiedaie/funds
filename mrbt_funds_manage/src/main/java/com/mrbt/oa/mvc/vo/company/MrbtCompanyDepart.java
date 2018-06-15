package com.mrbt.oa.mvc.vo.company;

import com.mrbt.oa.mvc.vo.BaseVo;

public class MrbtCompanyDepart extends BaseVo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MRBT_COMPANY_DEPART.ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MRBT_COMPANY_DEPART.DP_NAME
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    private String dpName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MRBT_COMPANY_DEPART.PARENT_ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MRBT_COMPANY_DEPART.ROUTE_ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    private String routeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MRBT_COMPANY_DEPART.DP_STATUS
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    private Integer dpStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MRBT_COMPANY_DEPART.ID
     *
     * @return the value of MRBT_COMPANY_DEPART.ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MRBT_COMPANY_DEPART.ID
     *
     * @param id the value for MRBT_COMPANY_DEPART.ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MRBT_COMPANY_DEPART.DP_NAME
     *
     * @return the value of MRBT_COMPANY_DEPART.DP_NAME
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public String getDpName() {
        return dpName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MRBT_COMPANY_DEPART.DP_NAME
     *
     * @param dpName the value for MRBT_COMPANY_DEPART.DP_NAME
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MRBT_COMPANY_DEPART.PARENT_ID
     *
     * @return the value of MRBT_COMPANY_DEPART.PARENT_ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MRBT_COMPANY_DEPART.PARENT_ID
     *
     * @param parentId the value for MRBT_COMPANY_DEPART.PARENT_ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MRBT_COMPANY_DEPART.ROUTE_ID
     *
     * @return the value of MRBT_COMPANY_DEPART.ROUTE_ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public String getRouteId() {
        return routeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MRBT_COMPANY_DEPART.ROUTE_ID
     *
     * @param routeId the value for MRBT_COMPANY_DEPART.ROUTE_ID
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MRBT_COMPANY_DEPART.DP_STATUS
     *
     * @return the value of MRBT_COMPANY_DEPART.DP_STATUS
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public Integer getDpStatus() {
        return dpStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MRBT_COMPANY_DEPART.DP_STATUS
     *
     * @param dpStatus the value for MRBT_COMPANY_DEPART.DP_STATUS
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    public void setDpStatus(Integer dpStatus) {
        this.dpStatus = dpStatus;
    }
    
	// =================zidingyi
	/**
	 * tree parent
	 */
	public Integer _parentId;
	/**
	 * icon
	 */
	public String iconCls = "icon-organ";
	/**
	 * 是否为部门领导
	 */
	public Integer isLeader;

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

}