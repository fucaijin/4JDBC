package cn.tedu;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.Util;

public class Demo02 {
	public static void main(String[] args) {
//		try {
//			Connection c1 = DBUtils.getConn();
//			System.out.println("得到连接1:" + c1);
//			Connection c2 = DBUtils.getConn();
//			System.out.println("得到连接2:" + c2);
//			Connection c3 = DBUtils.getConn();
//			System.out.println("得到连接3:" + c3);
//			c1.close(); // 如果连接是从连接池里面得到的,那么close并不是断开连接,而是把连接归还到连接池
//			Connection c4 = DBUtils.getConn();
//			System.out.println("得到连接4:" + c4);
			
			
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
			System.out.println(this.currentThread().getName()+"得到连接");
			Thread.sleep(5000);
			conn.close();
			System.out.println(this.currentThread().getName()+"归还连接");
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}