package com.mrbt.test.jijin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Crawl {
	public static HashMap<String, JijinVo> answerMap = new HashMap<String, JijinVo>();
	public static HashMap<String, JijinVo> questMap = new HashMap<String, JijinVo>();

	public static String readFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		InputStream in = null;
		StringBuffer data = new StringBuffer();
		try {
			in = new FileInputStream(new File(path));
			reader = new BufferedReader(new InputStreamReader(in, "gb2312"));

			String temp = null;
			while ((temp = reader.readLine()) != null) {
				data.append(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (reader != null) {
				try {
					in.close();
					reader.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data.toString();

	}

	public static ArrayList<JijinVo> getJijin(JSONArray topicArr) {
		ArrayList<JijinVo> list = new ArrayList<JijinVo>(100);
		for (int i = 0; i < topicArr.size(); i++) {
			JijinVo vo = new JijinVo();
			vo.setTopic(topicArr.getJSONObject(i).getJSONObject("Topic")
					.getString("FieldValue"));
			vo.setAnaly(topicArr.getJSONObject(i).getJSONObject("AnalyticDesc")
					.getString("FieldValue"));
			vo.setQuestionId(topicArr.getJSONObject(i)
					.getJSONObject("QuestionID").getString("FieldValue"));
			vo.setAnswerId(topicArr.getJSONObject(i).getJSONObject("AnswersID")
					.getString("FieldValue"));
			answerMap.put(vo.getAnswerId(), vo);
			questMap.put(vo.getQuestionId(), vo);
			list.add(vo);
		}
		return list;
	}

	public static void initMap(JSONArray questAndAnswerArr) {
		for (int i = 0; i < questAndAnswerArr.size(); i++) {
			String content = questAndAnswerArr.getJSONObject(i)
					.getJSONObject("AnswerContent").getString("FieldValue");
			String answerId = questAndAnswerArr.getJSONObject(i)
					.getJSONObject("AnswerID").getString("FieldValue");
			String questId = questAndAnswerArr.getJSONObject(i)
					.getJSONObject("QuestionID").getString("FieldValue");
			if (answerMap.containsKey(answerId)) {
				answerMap.get(answerId).setAnswer(content);
			}
			if (questMap.containsKey(questId)) {
				questMap.get(questId).getQuestions().add(content);
			}
		}
	}

	public static void main(String[] args) {
		String joStr = readFile("e:/jijin/daode/daode1.txt");
		JSONArray root = JSONObject.fromObject(joStr).getJSONArray("d");
		JSONArray topicArr = root.getJSONArray(1);

		ArrayList<JijinVo> list = getJijin(topicArr);
		System.out.println(list.size());
		JSONArray questAndAnswerArr = root.getJSONArray(2);
		initMap(questAndAnswerArr);
		for (JijinVo vo : list) {
			System.out.print("topic:" + vo.getTopic() + "\t");
			char index = 65;
			for (String tmp : vo.getQuestions()) {
				System.out.println(index + "，" + tmp);
				index++;
			}
			System.out.print("\t");
			System.out.println("answer:" + vo.getABCD());

		}
		// JSONObject jo = JSONObject.fromObject(joStr).getJSONArray("d")
		// .getJSONArray(2).getJSONObject(1);
		// System.out.println(jo.getString("Topic"));
	}
}
