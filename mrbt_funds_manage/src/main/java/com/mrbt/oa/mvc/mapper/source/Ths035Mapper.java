package com.mrbt.oa.mvc.mapper.source;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.source.Ths035;
import com.mrbt.oa.mvc.vo.source.Ths035Example;
import com.mrbt.oa.mvc.vo.source.Ths035WithBLOBs;

public interface Ths035Mapper extends BaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int countByExample(Ths035Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int deleteByExample(Ths035Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int insert(Ths035WithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int insertSelective(Ths035WithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    List<Ths035WithBLOBs> selectByExampleWithBLOBs(Ths035Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    List<Ths035> selectByExample(Ths035Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int updateByExampleSelective(@Param("record") Ths035WithBLOBs record, @Param("example") Ths035Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Ths035WithBLOBs record, @Param("example") Ths035Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table THS035
     *
     * @mbggenerated Mon Jun 20 14:18:02 CST 2016
     */
    int updateByExample(@Param("record") Ths035 record, @Param("example") Ths035Example example);
    
    int countByExampleAll(Ths035Example example);
}