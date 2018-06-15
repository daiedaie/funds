package com.mrbt.test.jijin;

import java.util.ArrayList;
import java.util.List;

public class JijinVo {
	public String topic;
	public String answerId;
	public String questionId;
	public String analy;
	public List<String> questions = new ArrayList<String>();
	public String answer;

	public String getABCD() {
		char tmp = 65;

		for (int i = 0; i < questions.size(); i++) {
			if (answer.equals(questions.get(i))) {
				break;
			}
			tmp = (char) (tmp + 1);
		}
		return String.valueOf(tmp);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnaly() {
		return analy;
	}

	public void setAnaly(String analy) {
		this.analy = analy;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public static void main(String[] args) {
		char tmp = 65;
		System.out.println(tmp);
	}
}
