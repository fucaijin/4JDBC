package cn.tedu;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.Util;

public class Demo02 {
	public static void main(String[] args) {
//		try {
//			Connection c1 = DBUtils.getConn();
//			System.out.println("�õ�����1:" + c1);
//			Connection c2 = DBUtils.getConn();
//			System.out.println("�õ�����2:" + c2);
//			Connection c3 = DBUtils.getConn();
//			System.out.println("�õ�����3:" + c3);
//			c1.close(); // ��������Ǵ����ӳ�����õ���,��ôclose�����ǶϿ�����,���ǰ����ӹ黹�����ӳ�
//			Connection c4 = DBUtils.getConn();
//			System.out.println("�õ�����4:" + c4);
			
			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		for (int i = 0; i < 10; i++) {
			new DemoThread().start();
		}
		
	}
}

class DemoThread extends Thread{
	@Override
	public void run() {
		try {
			Connection conn = DBUtils.getConn();
			System.out.println(this.currentThread().getName()+"�õ�����");
			Thread.sleep(5000);
			conn.close();
			System.out.println(this.currentThread().getName()+"�黹����");
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}