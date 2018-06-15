package com.mvc.thread;

import javax.mail.MessagingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.service.sender.SenderService;
import com.mrbt.mvc.service.sender.SenderServiceHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class EmailSenderTest {
	private static final Logger log = LogManager.getLogger(EmailSenderTest.class);
	@Autowired
	@Qualifier(value="senderService")
	private SenderService senderService;
	//@Test
	public void suggest() {
		String html = "<html><head><META http-equiv=Content-Type content='text/html; charset=UTF-8'><title>Mrbt_Funds_Solrj</title></head><body>欢迎使用，SolrJ。<br/><a href='www.baidu.com' >Mrbt_Funds_SolrJ</a><br>点击上面超链接 激活账户信息！</body><html>";
		try {
			senderService.sendMail("Tips", html, true, "912457694@qq.com");
			System.out.println("test finished");
		} catch (MessagingException e) {
			System.out.println("exception:"+e);
			e.printStackTrace();
		}
	}
	@Autowired
	@Qualifier(value = "senderServiceHandler")
	private SenderServiceHandler senderServiceHandler;
	@Test
	public void send(){
		long start = System.currentTimeMillis();
		for(int i = 0;i<100;i++){
			boolean result = senderServiceHandler.suggest("Exception in thread  java.lang.NullPointerException","912457694@qq.com");
			System.err.println(result);
			log.info(result);
		}
		long end = System.currentTimeMillis();
		log.info(end-start);
		
	}

}
