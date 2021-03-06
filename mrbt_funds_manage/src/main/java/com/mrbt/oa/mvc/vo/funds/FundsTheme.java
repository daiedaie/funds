package com.mrbt.oa.mvc.vo.funds;

import com.mrbt.oa.mvc.vo.BaseVo;
import java.math.BigDecimal;

public class FundsTheme extends BaseVo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FUNDS_THEME.ID
     *
     * @mbggenerated Thu Jul 07 13:45:45 CST 2016
     */
    private BigDecimal id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FUNDS_THEME.NAME
     *
     * @mbggenerated Thu Jul 07 13:45:45 CST 2016
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FUNDS_THEME.ID
     *
     * @return the value of FUNDS_THEME.ID
     *
     * @mbggenerated Thu Jul 07 13:45:45 CST 2016
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FUNDS_THEME.ID
     *
     * @param id the value for FUNDS_THEME.ID
     *
     * @mbggenerated Thu Jul 07 13:45:45 CST 2016
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FUNDS_THEME.NAME
     *
     * @return the value of FUNDS_THEME.NAME
     *
     * @mbggenerated Thu Jul 07 13:45:45 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FUNDS_THEME.NAME
     *
     * @param name the value for FUNDS_THEME.NAME
     *
     * @mbggenerated Thu Jul 07 13:45:45 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }
}