package mrbt_funds_web;

import java.util.HashMap;
import java.util.Map;

import com.mrbt.units.CrawlerWebSousePost;

public class testy {
	public static void main(String[] args) {
		Map<String, String> pram = new HashMap<String, String>();
		//http://192.168.1.88:9999/mrbt_funds_solrj/detail/get	{order=market_fund_code:asc, param=广发, start=0, rows=10}
		pram.put("content", "00");
		String xml = CrawlerWebSousePost.post("http://192.168.1.88:9999/mrbt_funds_solrj/market/retrieve", pram);
		System.out.println(xml);
	}
}

