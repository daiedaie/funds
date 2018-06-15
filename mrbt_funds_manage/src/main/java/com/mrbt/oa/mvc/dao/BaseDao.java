package com.mrbt.oa.mvc.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.BaseVo;
import com.mrbt.utils.GridPage;

/**
 * 通用dao
 * 
 * @author airgilbert
 *
 */

public abstract class BaseDao<T extends BaseVo, D extends BaseMapper> extends
		SqlSessionDaoSupport {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public D mapper;

	public String mapper_preffix;

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void delete(BigDecimal id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void delete(String id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void deleteByExample(Object example) {
		mapper.deleteByExample(example);
	}

	/**
	 * 查询总数
	 * 
	 * @param example
	 *            条件辅助类
	 * @return 总数
	 */
	public int listCount(Object example) {
		return mapper.countByExample(example);
	}

	/**
	 * 查找用户，根据vo
	 * 
	 * @param example
	 *            条件辅助类
	 * @param page
	 *            翻页
	 * @return AdminUser集合
	 */
	public List<T> list(Object example, RowBounds page) {
		// 如果page为空，则取全部
		if (page == null) {
			return this.getSqlSession().selectList(
					this.getMapper_preffix() + "selectByExample", example);
		}
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "selectByExample", example, page);
	}

	/**
	 * 查询，根据id
	 * 
	 * @param id
	 * @return
	 */
	public T listById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	public T listById(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	public T listById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询表格的结果，包含总数和rows
	 * 
	 * @param example
	 *            AdminUser条件辅助类
	 * @param page
	 *            翻页信息
	 * @return GridPage<AdminUser> grid结果信息
	 */
	public GridPage<T> listGrid(Object example, RowBounds page) {
		GridPage<T> result = new GridPage<T>();
		result.setRows(list(example, page));
		result.setTotal(listCount(example));
		return result;
	}

	/**
	 * 保存实体bean
	 * 
	 * @param vo
	 *            实体bean
	 * @throws Exception
	 */
	public void save(BaseVo vo) {
		mapper.insert(vo);
	}

	/**
	 * 保存实体bean
	 * 
	 * @param vo
	 *            实体bean
	 * @throws Exception
	 */
	public void saveSelective(BaseVo vo) {
		mapper.insertSelective(vo);
	}

	/**
	 * 更新实体类，根据bean
	 * 
	 * @param vo
	 *            实体bean
	 */
	public void update(T vo) {
		mapper.updateByPrimaryKey(vo);
	}

	/**
	 * 更新实体类，根据bean
	 * 
	 * @param vo
	 *            实体bean
	 */
	public void updateSelective(T vo) {
		mapper.updateByPrimaryKeySelective(vo);
	}

	public void updateByExampleSelective(T vo, Object example) {
		mapper.updateByExampleSelective(vo, example);
	}

	public String getMapper_preffix() {
		return mapper_preffix;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 需要覆盖此方法，实现mapper_preffix的赋值
	 * 
	 * @param mapper_preffix
	 */
	public abstract void setMapper_preffix(String mapper_preffix);

	public D getMapper() {
		return mapper;
	}

	public void setMapper(D mapper) {
		this.mapper = mapper;
	}

}
