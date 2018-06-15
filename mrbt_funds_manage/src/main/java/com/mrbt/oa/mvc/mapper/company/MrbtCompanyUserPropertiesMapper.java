package com.mrbt.oa.mvc.mapper.company;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserProperties;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserPropertiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrbtCompanyUserPropertiesMapper extends BaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int countByExample(MrbtCompanyUserPropertiesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int deleteByExample(MrbtCompanyUserPropertiesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int insert(MrbtCompanyUserProperties record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int insertSelective(MrbtCompanyUserProperties record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    List<MrbtCompanyUserProperties> selectByExample(MrbtCompanyUserPropertiesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    MrbtCompanyUserProperties selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") MrbtCompanyUserProperties record, @Param("example") MrbtCompanyUserPropertiesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByExample(@Param("record") MrbtCompanyUserProperties record, @Param("example") MrbtCompanyUserPropertiesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByPrimaryKeySelective(MrbtCompanyUserProperties record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_USER_PROPERTIES
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByPrimaryKey(MrbtCompanyUserProperties record);
}