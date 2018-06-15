package com.mvc.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.mrbt.utils.FastJsonUtils;

public class ThreadPoolTest {
	
	@Test
	public void test(){
		ExecutorService executorService = Executors.newCachedThreadPool();
		final ReentrantLock lock = new ReentrantLock();
		for(int i = 0 ;i < 10 ;i++){
			Future<?> future = executorService.submit(new MyTask(lock));
			try {
				Object obj = future.get();
				System.err.println(FastJsonUtils.Obj2Json(obj));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
	private static class MyTask implements Runnable {
		private ReentrantLock lock;
		public MyTask(ReentrantLock lock){
			this.lock = lock;
		}
		@Override
		public void run() {
			lock.lock();
			try{
				System.err.println("线程run");
			}catch(Exception e){
				throw new RuntimeException(e);
			}finally{
				lock.unlock();
			}
			
		}
	}
}
