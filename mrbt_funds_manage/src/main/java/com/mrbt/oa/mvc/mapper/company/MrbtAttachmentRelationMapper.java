package com.mrbt.oa.mvc.mapper.company;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.company.MrbtAttachmentRelation;
import com.mrbt.oa.mvc.vo.company.MrbtAttachmentRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrbtAttachmentRelationMapper extends BaseMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int countByExample(MrbtAttachmentRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int deleteByExample(MrbtAttachmentRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int insert(MrbtAttachmentRelation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int insertSelective(MrbtAttachmentRelation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	List<MrbtAttachmentRelation> selectByExample(
			MrbtAttachmentRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	MrbtAttachmentRelation selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int updateByExampleSelective(
			@Param("record") MrbtAttachmentRelation record,
			@Param("example") MrbtAttachmentRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int updateByExample(@Param("record") MrbtAttachmentRelation record,
			@Param("example") MrbtAttachmentRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int updateByPrimaryKeySelective(MrbtAttachmentRelation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MRBT_ATTACHMENT_RELATION
	 * @mbggenerated  Thu May 26 17:48:51 CST 2016
	 */
	int updateByPrimaryKey(MrbtAttachmentRelation record);
}