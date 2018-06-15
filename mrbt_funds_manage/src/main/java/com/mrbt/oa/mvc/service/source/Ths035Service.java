package com.mrbt.oa.mvc.service.source;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.source.Ths035Dao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.source.Ths035;
import com.mrbt.oa.mvc.vo.source.Ths035Example;
import com.mrbt.utils.GridPage;

@Service
public class Ths035Service extends BaseService<Ths035, Ths035Dao> {

	@Override
	public Ths035Example getExample(Ths035 vo) {
		Ths035Example example = new Ths035Example();
		Ths035Example.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getF002Ths001())) {
			cri.andF002Ths001Like("%" + vo.getF002Ths001() + "%");
		}
		if (StringUtils.isNotBlank(vo.getF003Ths001())) {
			cri.andF003Ths001Like("%" + vo.getF003Ths001() + "%");
		}
		if (StringUtils.isNotBlank(vo.getF005Ths001())) {
			cri.andF005Ths001Like("%" + vo.getF005Ths001() + "%");
		}
		if (StringUtils.isNotBlank(vo.getF001Ths001())) {
			cri.andF001Ths001Like("%" + vo.getF001Ths001() + "%");
		}
		if (vo.getIsRecomm() != null) {
			if (vo.getIsRecomm() == (short) 1) {// 未添加到基金超市
				cri.andIsRecommIsNull();
			}
			if (vo.getIsRecomm() == (short) 2) {// 已添加到基金超市
				cri.andIsRecommIsNotNull();
			}
		}
		example.setOrderByClause("tmp.F002_THS001");
		return example;
	}

	public GridPage<Ths035> listGridCombo(Ths035 vo, RowBounds page) {
		return this.generalDao.listGridCombo(getExample(vo), page);
	}

	public List<Ths035> listCombo(Ths035 vo) {
		return this.generalDao.listCombo(getExample(vo), null);
	}
}
