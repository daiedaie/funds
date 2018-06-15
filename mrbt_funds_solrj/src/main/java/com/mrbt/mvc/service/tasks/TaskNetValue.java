//package com.mrbt.mvc.service.tasks;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.mrbt.cache.RedisDao;
//
//@Component
//public class TaskNetValue {
//	private static final Logger log = LogManager.getLogger(TaskNetValue.class);
//	public TaskNetValue() {
//	}
//	@Autowired
//	private RedisDao redisDao;	
//	// 间隔固定时间触发任务
//	// @Scheduled(fixedDelay = 5000)
//	// 每隔10秒触发一次任务
//	
//	boolean write = true;
//	//@Scheduled(fixedDelay = 15000)
//	@Scheduled(cron = "0/10000000 * * * * ?")
//	public void generate() throws Exception {
//		log.info("192.168.1.189 redis write data");
//		/*String key = "netvalue201600";
//		List<String> list = new ArrayList<String>();
//		list.add("2015/03/08&0.1876");
//		list.add("2016/06/05&1.3762");
//		list.add("2017/09/03&2.1520");
//		list.add("2018/02/01&3.9918");
//		list.add("2019/04/02&4.5726");
//		if(write){
//			redisDao.setList(key, list);
//			redisDao.expire(key, 30,TimeUnit.SECONDS);
//			write = false;
//		}
//		else{
//			List<Object> contentType = redisDao.getList(key);
//			for(Object obj : contentType){
//				System.out.println(obj.toString());
//			}
//			write = true;
//		}*/
//		log.info("192.168.1.189 redis write data finish test");
//	}
//}
