package com.mvc.detail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.mapper.funds.FundDetailMapper;
import com.mrbt.mvc.mapper.funds.FundMarketMapper;
import com.mrbt.mvc.vo.FundDetailVo;
import com.mrbt.mvc.vo.FundMarketVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class FundDetailPrintTest {
	@Autowired
	private FundMarketMapper fundMarketMapper;
	@Autowired
	private FundDetailMapper fundDetailMapper;
	//@Test
	public void test(){
		//生成耗时:123058基金总数量:(1326)查询到基金详情数量:(660.0)未发现基金详情数量:(665.0)查询基金详情出现错误数量:(1.0)
		long start = System.currentTimeMillis();
		//查询全部基金代码
		List<FundMarketVo> funds = fundMarketMapper.selectFundMarketList();
		File errorfile = new File("f:/error.txt");
		File nofile = new File("f:/nofund.txt");
		File file = new File("f:/fund.txt");
		File resultfile = new File("f:/result.txt");
		double errorsize = 0,nosize = 0,findsize =0;
				
		for(FundMarketVo fund : funds){
			String code = fund.getMarket_fund_code();
			//查询全部基金的详情信息
			FundDetailVo detail = null;
			//查询的Detail可能是 不唯一的数据
			try{
				detail = fundDetailMapper.getDetailsFromId(code);
			}catch(Exception e){
				try {
					++errorsize;
					FileUtils.writeStringToFile(errorfile,code+e.toString()+"\n","UTF-8",true);
					FileUtils.writeStringToFile(errorfile,"\n"+"-","UTF-8",true);
					continue;
				} catch (IOException e1) {
					System.err.print("查询基金详情有错误");
					continue;
				}
			}
			if(detail == null){
				try {
					FileUtils.writeStringToFile(nofile,code.toString(),"UTF-8",true);
					FileUtils.writeStringToFile(nofile,"\n"+"-","UTF-8",true);
					++nosize;
				} catch (IOException e) {
					System.err.print("根据基金代码查不到详情的数据");
				}
			}
			else{
				try {
					FileUtils.writeStringToFile(file,code+"----"+detail.toString()+"\n","UTF-8",true);
					FileUtils.writeStringToFile(file,"\n"+"-","UTF-8",true);
					++findsize;
				} catch (IOException e) {
					System.err.print("查询到基金详情的数据保存到文件");
				}
			}
		}
		long end = System.currentTimeMillis();
		StringBuffer stringBuffer = new StringBuffer("生成耗时:"+(end-start));
		stringBuffer.append("基金总数量:("+funds.size()+")");
		stringBuffer.append("查询到基金详情数量:("+findsize+")");
		stringBuffer.append("未发现基金详情数量:("+nosize+")");
		stringBuffer.append("查询基金详情出现错误数量:("+errorsize+")");
		try {
			FileUtils.writeStringToFile(resultfile,stringBuffer.toString()+"\n","UTF-8",true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.print(stringBuffer.toString());
	}
	//@Test
	public void select(){
		File nofile = new File("f:/nofund.txt");
		try {
			String lists = FileUtils.readFileToString(nofile);
			System.err.println(lists.length()/6);
		} catch (IOException e) {
			System.err.print("文件读取错误");
		}
	}
}