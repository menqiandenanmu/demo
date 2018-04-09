package com.mall.butler.port.test;


public class ThreadTest {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
//			if (i == 30) {
//				Runnable myRunnable = new MyRunnable();
//				Thread thread = new MyThread(myRunnable);
//				thread.start();
//			}
			try {
			Runnable myRunnable = new MyRunnable();
			Thread thread = new MyThread(myRunnable);
			thread.start();
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Thread thread=new MyThread();
//			thread.start();
		}
	}

}
