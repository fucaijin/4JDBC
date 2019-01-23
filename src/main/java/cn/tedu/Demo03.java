package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入姓名:");
		String name = scanner.nextLine();
		System.out.println("请输入年龄:");
		String age = scanner.nextLine();
		try (Connection conn = DBUtils.getConn();){
//			Statement stat = conn.createStatement();
//			stat.executeUpdate("insert into person (name,age) values('"+name+"',"+age+")");
			
			// 创建预编译的sql执行对象,即在创建Statement的时候就编译了sql语句,执行的时候就不用编译了.变量用?号来代替
			PreparedStatement stat = conn.prepareStatement("insert into person (name,age) values(?,?)");
			// 替换?为真正的变量
			stat.setString(1, name);
			stat.setInt(2, Integer.parseInt(age));
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("写入数据库完毕!");
		scanner.close();
	}
}
