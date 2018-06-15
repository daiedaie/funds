package com.mrbt.oa.mvc.service.funds;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsPackageRatioDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsBaseType;
import com.mrbt.oa.mvc.vo.funds.FundsPackageRatio;
import com.mrbt.oa.mvc.vo.funds.FundsPackageRatioExample;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.redis.RedisDao;

@Service
public class FundsPackageRatioService extends
		BaseService<FundsPackageRatio, FundsPackageRatioDao> {
	@Autowired
	public FundsBaseTypeService fundsBaseTypeService;
	@Autowired
	private RedisDao redisDao;

	@Override
	public FundsPackageRatioExample getExample(FundsPackageRatio vo) {
		FundsPackageRatioExample example = new FundsPackageRatioExample();
		FundsPackageRatioExample.Criteria cri = example.createCriteria();
		if (vo.getTypeId() != null) {
			cri.andTypeIdEqualTo(vo.getTypeId());
		}
		if (vo.getFundsTypeId() != null) {
			cri.andFundsTypeIdEqualTo(vo.getFundsTypeId());
		}
		example.setOrderByClause("TYPE_ID");
		return example;
	}

	/**
	 * 添加基金组合时判断某基金是否已经存在
	 * 
	 * @param map
	 * @return
	 */
	public List<FundsPackageRatio> findByFundsCodeAndTypeId(String fundsCode,
			BigDecimal typeId) {
		Map map = new HashMap();
		map.put("fundsCode", fundsCode);
		map.put("typeId", typeId);
		return generalDao.findByFundsCodeAndTypeId(map);
	}

	/**
	 * 修改基金组合时判断某基金是否已经存在
	 * 
	 * @param map
	 * @return
	 */
	public int findByFundsCodeNoId(String fundsCode, BigDecimal typeId,
			BigDecimal id) {
		Map map = new HashMap();
		map.put("fundsCode", fundsCode);
		map.put("typeId", typeId);
		map.put("id", id);
		return generalDao.findByFundsCodeNoId(map);
	}

	/**
	 * 根据基金组合类型id(typeId)查询该基金组合下所有的基金类型id(fundsTypeId)
	 * 
	 * @param typeId
	 * @return
	 */
	public List<String> findByDistinctFundsTypeId(BigDecimal typeId) {
		return generalDao.findByDistinctFundsTypeId(typeId);
	}

	/**
	 * 基金组合宝 基金类型所占比例 根据基金类型id(typeId)
	 * 
	 * @param id
	 * @return
	 */

	public Map<String, Integer> fundsPackageFundsTypeRatio(BigDecimal typeId) {
		// 定义map存放基金类型名称和个数
		Map<String, Integer> map = new HashMap<String, Integer>();
		/**
		 * if(typeId == 1) map.put(0,1) map.put(1,3) if(typeId == 2)
		 * map.put(0,1) map.put(1,1)
		 */
		// 根据基金组合类型id(typeId)查询该基金组合下所有的基金类型id(fundsTypeId)
		List<String> listFundsTypeId = this.findByDistinctFundsTypeId(typeId);// 0,1
		for (String fundsTypeId : listFundsTypeId) {
			// 根据基金类型id查询基金类型名称
			FundsBaseType fundsBaseType = fundsBaseTypeService
					.listById(fundsTypeId);
			if (fundsBaseType != null) {
				String fundsTypeName = fundsBaseType.getName();
				// 根据基金组合类型id和基金类型id,查询个数
				FundsPackageRatio fundsPackageRatio = new FundsPackageRatio();
				fundsPackageRatio.setTypeId(typeId);
				fundsPackageRatio.setFundsTypeId(fundsTypeId);
				int count = this.listCount(fundsPackageRatio);
				map.put(fundsTypeName, count);
			}
		}
		if (map.size() > 0) {
			String redisKey = AppCons.FUNDS_PACKAGE_FUNDS_TYPE_RATIO_ + typeId;
			redisDao.delete(redisKey);
			redisDao.set(redisKey, map);
			Map tempMap = (Map) redisDao.get(redisKey);
			System.out.println(tempMap.size());
			Iterator it = tempMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				System.out.println("key=" + key + " value=" + value);
			}
		}
		return map;
	}

}
