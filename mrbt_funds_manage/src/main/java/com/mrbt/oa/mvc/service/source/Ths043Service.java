package com.mrbt.oa.mvc.service.source;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.source.Ths043Dao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.source.Ths043;
import com.mrbt.oa.mvc.vo.source.Ths043Example;

@Service
public class Ths043Service extends BaseService<Ths043, Ths043Dao> {

	@Override
	public Ths043Example getExample(Ths043 vo) {
		Ths043Example example = new Ths043Example();
		Ths043Example.Criteria cri = example.createCriteria();
		// 基金代码(内部 KF...) F001_THS043 关联到F001_THS035
		if (StringUtils.isNotBlank(vo.getF001Ths043())) {
			cri.andF001Ths043EqualTo(vo.getF001Ths043());
		}
		// 截止日期 F002_THS043
		if (vo.getF002Ths043() != null) {
			cri.andF002Ths043EqualTo(vo.getF002Ths043());
		}
		// 单位净值(单位:元) F003_THS043
		if (vo.getF003Ths043() != null) {
			cri.andF003Ths043EqualTo(vo.getF003Ths043());
		}
		// 按截止日期排序
		example.setOrderByClause("F002_THS043");
		return example;
	}

	public Ths043Example getExamples(Ths043 vo) {
		Ths043Example example = new Ths043Example();
		Ths043Example.Criteria cri = example.createCriteria();
		// 基金代码(内部 KF...) F001_THS043 关联到F001_THS035
		if (StringUtils.isNotBlank(vo.getF001Ths043())) {
			cri.andF001Ths043EqualTo(vo.getF001Ths043());
		}
		// 截止日期 F002_THS043
		if (vo.getF002Ths043() != null) {
			cri.andF002Ths043LessThanOrEqualTo(vo.getF002Ths043());
		}
		// 单位净值(单位:元) F003_THS043
		if (vo.getF003Ths043() != null) {
			cri.andF003Ths043EqualTo(vo.getF003Ths043());
		}
		// 按截止日期排序
		example.setOrderByClause("F002_THS043 desc");
		return example;
	}

	public List<Ths043> lists(Ths043 vo, RowBounds page) {
		return generalDao.list(getExamples(vo), page);
	}

	public Ths043Example getExampleByDate(Ths043 vo, Date oldDate, Date newDate) {
		Ths043Example example = new Ths043Example();
		Ths043Example.Criteria cri = example.createCriteria();
		// 基金代码(内部 KF...) F001_THS043 关联到F001_THS035
		if (StringUtils.isNotBlank(vo.getF001Ths043())) {
			cri.andF001Ths043EqualTo(vo.getF001Ths043());
		}
		// 截止日期 F002_THS043
		if (oldDate != null && newDate != null) {
			cri.andF002Ths043Between(oldDate, newDate);
		}
		// 按截止日期排序
		example.setOrderByClause("F002_THS043");
		return example;
	}

	public List<Ths043> listByDate(Ths043 vo, Date oldDate, Date newDate,
			RowBounds page) {
		return generalDao.list(getExampleByDate(vo, oldDate, newDate), page);
	}
}
