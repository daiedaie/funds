package com.mrbt.mvc.service.sender;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mrbt.utils.DateUtil;

@Component("senderServiceHandler")
public class SenderServiceHandler {
	@Autowired
	@Qualifier(value="senderService")
	private SenderService senderService;
	public boolean suggest(final String html,final String email) {
		ExecutorService scheduledExecutorService = null;
		try {
			String reg = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
			Pattern r = Pattern.compile(reg);
		    Matcher m = r.matcher(email);
		    if(m.find()) {
		    	scheduledExecutorService = Executors.newCachedThreadPool();
		    	scheduledExecutorService.submit(new Runnable(){
					@Override
					public void run() {
						try {
							senderService.sendMail("Tips", html+DateUtil.getCurrentTimes(new Date()), true, email);
							System.out.println("return true");
						} catch (MessagingException e) {
							System.out.println(e);
						}
					}
		    	});
		    	scheduledExecutorService.awaitTermination(5,TimeUnit.SECONDS);
		    	return true;
		    }
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}finally{
			if(scheduledExecutorService!=null){
				scheduledExecutorService.shutdown();
			}
		}
	}
	public boolean suggest(String html) {
		try {
			senderService.sendMail("Tips", html+DateUtil.getCurrentTimes(new Date()), true,"912457694@qq.com");
			return true;
		} catch (MessagingException e) {
			System.out.println(e);
			return false;
		}
	}
}
