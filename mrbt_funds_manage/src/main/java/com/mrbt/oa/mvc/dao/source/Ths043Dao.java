package com.mrbt.oa.mvc.dao.source;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.source.Ths043Mapper;
import com.mrbt.oa.mvc.vo.source.Ths043;

@Repository
public class Ths043Dao extends BaseDao<Ths043, Ths043Mapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.source.Ths043Mapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

}