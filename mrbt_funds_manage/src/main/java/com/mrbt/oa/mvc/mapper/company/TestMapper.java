package com.mrbt.oa.mvc.mapper.company;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.company.Test;
import com.mrbt.oa.mvc.vo.company.TestExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestMapper extends BaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int countByExample(TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int deleteByExample(TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int insert(Test record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int insertSelective(Test record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    List<Test> selectByExample(TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    Test selectByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") Test record, @Param("example") TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByExample(@Param("record") Test record, @Param("example") TestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByPrimaryKeySelective(Test record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST
     *
     * @mbggenerated Thu May 12 15:09:23 CST 2016
     */
    int updateByPrimaryKey(Test record);
}