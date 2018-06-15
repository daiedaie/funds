package com.mvc.index;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mrbt.utils.HttpClientUtil;

public class SearchSuggestTest {
	@Test
	public void suggest(){
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/search/suggest";
		Map<String,String> map = new HashMap<String, String>();
		map.put("content", "东方");
		String result = HttpClientUtil.doPost(url,map, "UTF-8");
		System.err.println(result);
		
		
//		Map<String,String> map2 = new HashMap<String, String>();
//		Scanner input = new Scanner(System.in);
//		while(true){
//			System.out.println("input content(Fund code、name、pinyin)");
//			System.out.println("\n");
//			map2.put("content", input.nextLine());
//			String result2 = HttpClientUtil.doPost(url,map2, "UTF-8");
//			System.err.println(result2);
//			//input.close();
//		}
		
		
		
//		Map<String,String> map3 = new HashMap<String, String>();
//		map3.put("content", "gtgcqdl");
//		String result3 = HttpClientUtil.doPost(url,map3, "UTF-8");
//		System.err.println(result3);
	}

}
