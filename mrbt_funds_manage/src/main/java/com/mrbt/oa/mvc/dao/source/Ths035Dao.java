package com.mrbt.oa.mvc.dao.source;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.source.Ths035Mapper;
import com.mrbt.oa.mvc.vo.source.Ths035;
import com.mrbt.oa.mvc.vo.source.Ths035Example;
import com.mrbt.utils.GridPage;

@Repository
public class Ths035Dao extends BaseDao<Ths035, Ths035Mapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.source.Ths035Mapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	public List<Ths035> listCombo(Ths035Example example, RowBounds page) {
		// 如果page为空，则取全部
		if (page == null) {
			return this.getSqlSession().selectList(
					this.getMapper_preffix() + "selectAll", example);
		}
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "selectAll", example, page);
	}
	public int listCountCombo(Ths035Example example) {
		return this.getMapper().countByExampleAll(example);
	}
	public GridPage<Ths035> listGridCombo(Ths035Example example, RowBounds page) {
		GridPage<Ths035> result = new GridPage<Ths035>();
		result.setRows(listCombo(example, page));
		result.setTotal(listCountCombo(example));
		return result;
	}
}
