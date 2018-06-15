package com.mrbt.oa.mvc.mapper.company;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepart;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrbtCompanyDepartMapper extends BaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int countByExample(MrbtCompanyDepartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int deleteByExample(MrbtCompanyDepartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int insert(MrbtCompanyDepart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int insertSelective(MrbtCompanyDepart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    List<MrbtCompanyDepart> selectByExample(MrbtCompanyDepartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    MrbtCompanyDepart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") MrbtCompanyDepart record, @Param("example") MrbtCompanyDepartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByExample(@Param("record") MrbtCompanyDepart record, @Param("example") MrbtCompanyDepartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByPrimaryKeySelective(MrbtCompanyDepart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MRBT_COMPANY_DEPART
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByPrimaryKey(MrbtCompanyDepart record);
}