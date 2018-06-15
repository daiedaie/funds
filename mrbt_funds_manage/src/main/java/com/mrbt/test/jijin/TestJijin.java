package com.mrbt.test.jijin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.mrbt.test.BaseTest;
import com.mrbt.utils.freemaker.generator.GeneratorHtml;

import freemarker.template.Configuration;

@TransactionConfiguration(defaultRollback = false)
public class TestJijin extends BaseTest {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	private Configuration config = null;

	public void insert(String joStr) {
		JSONArray root = JSONObject.fromObject(joStr).getJSONArray("d");
		JSONArray topicArr = root.getJSONArray(1);

		ArrayList<JijinVo> list = Crawl.getJijin(topicArr);
		JSONArray questAndAnswerArr = root.getJSONArray(2);
		Crawl.initMap(questAndAnswerArr);

		List<Object[]> args = new ArrayList<Object[]>();
		Object[] tmpObj = null;
		for (JijinVo vo : list) {
			tmpObj = new Object[9];
			tmpObj[0] = vo.getTopic();

			char index = 65;
			for (String tmp : vo.getQuestions()) {
				System.out.println(index + "，" + tmp);
				tmpObj[index - 64] = tmp;
				index++;
			}
			System.out.println("answer:" + vo.getABCD());
			System.out.println("answer_str:" + vo.getAnswer());
			tmpObj[5] = vo.getABCD();
			tmpObj[6] = vo.getAnaly();
			tmpObj[7] = "0";// 0为基础，1为道德
			tmpObj[8] = "2016-06-06";
			args.add(tmpObj);
		}
		jdbcTemplate
				.batchUpdate(
						"INSERT INTO `jijin` (`topic`, `A`, `B`, `C`, `D`, `answer`, `analy`, `type`, `dt`) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?)",
						args);
	}
	// @Test
	public void test() {
		for (int i = 1; i < 3; i++) {
			String joStr = Crawl.readFile("e:/jijin/jichu-06/jichu" + i
					+ ".txt");
			insert(joStr);
		}

	}

	private Configuration getFreeMarkerCFG(String templateFilePath)
			throws Exception {
		if (null == this.config) {

			this.config = new Configuration();
			try {
				this.config.setDirectoryForTemplateLoading(new File(
						templateFilePath));
			} catch (Exception ex) {
				throw ex;
			}
		}
		return this.config;
	}
	@Test
	public void generator() throws Exception {
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from jijin where type=0 and dt='2016-06-06' order by id");
		// @todo 装入新闻
		// NewsItem = loadNewsItem(1);
		GeneratorHtml test = new GeneratorHtml();

		Map root = new HashMap();
		root.put("grid", list);

		String sGeneFilePath = "e:/jijin";

		String sFileName = "jichu06.html";
		
		//D:/scoure_code/mrbt_funds_manage/src/main/java
		//E:/project/mrbt/mrbt_oa_html5/src
		test.geneHtmlFile(
				"a.ftl",
				"D:/scoure_code/mrbt_funds_manage/src/main/java/com/mrbt/utils/freemaker/jijin",
				root, sGeneFilePath, sFileName);
	}
}
