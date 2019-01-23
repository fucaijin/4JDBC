package cn.tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo3_1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入姓名:");
		String name = scanner.nextLine();
		System.out.println("请输入年龄:");
		String age = scanner.nextLine();
		try {
			Connection conn = DBUtils.getConn();
			Statement stat = conn.createStatement();
			stat.executeUpdate("insert into person (name,age) values('"+name+"',"+age+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("写入数据库完毕!");
		scanner.close();
	}
}
