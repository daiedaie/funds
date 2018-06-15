package com.mrbt.oa.mvc.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.vo.BaseVo;
import com.mrbt.utils.GridPage;

public abstract class BaseService<T extends BaseVo, D extends BaseDao<T, ?>> {
	@Autowired
	public D generalDao;

	/**
	 * 通用删除方法，根据id
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		generalDao.delete(id);
	}

	@Transactional
	public void delete(BigDecimal id) {
		generalDao.delete(id);
	}

	@Transactional
	public void delete(String id) {
		generalDao.delete(id);
	}

	@Transactional
	public void deleteByExample(T vo) {
		generalDao.deleteByExample(getExample(vo));
	}

	/**
	 * 查询总数
	 * 
	 * @param example
	 *            条件辅助类
	 * @return 总数
	 */
	public int listCount(T vo) {
		return generalDao.listCount(getExample(vo));
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
	public List<T> list(T vo, RowBounds page) {
		return generalDao.list(getExample(vo), page);
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
	public GridPage<T> listGrid(T vo, RowBounds page) {
		return generalDao.listGrid(getExample(vo), page);
	}

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	public T listById(Integer id) {
		return generalDao.listById(id);
	}

	public T listById(BigDecimal id) {
		return generalDao.listById(id);
	}

	public T listById(String id) {
		return generalDao.listById(id);
	}

	/**
	 * 保存实体bean
	 * 
	 * @param vo
	 *            实体bean
	 * @throws Exception
	 */
	@Transactional
	public void save(BaseVo vo) {
		generalDao.save(vo);
	}

	/**
	 * 保存实体bean
	 * 
	 * @param vo
	 *            实体bean
	 * @throws Exception
	 */
	@Transactional
	public void saveSelective(BaseVo vo) {
		generalDao.saveSelective(vo);
	}

	/**
	 * 更新实体类，根据bean
	 * 
	 * @param vo
	 *            实体bean
	 */
	@Transactional
	public void update(T vo) {
		generalDao.update(vo);
	}

	/**
	 * 更新实体类，根据bean
	 * 
	 * @param vo
	 *            实体bean
	 */
	@Transactional
	public void updateSelective(T vo) {
		generalDao.updateSelective(vo);
	}

	@Transactional
	public void updateByExampleSelective(T vo) {
		generalDao.updateByExampleSelective(vo, getExample(vo));
	}

	public D getGeneralDao() {
		return generalDao;
	}

	public void setGeneralDao(D generalDao) {
		this.generalDao = generalDao;
	}

	/**
	 * 获取example的虚拟类
	 * 
	 * @return
	 */
	public abstract Object getExample(T vo);

}
