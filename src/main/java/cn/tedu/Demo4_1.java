package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo4_1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名");
		String username = scanner.nextLine();
		System.out.println("请输入密码");
		String password = scanner.nextLine();
		scanner.close();// 释放资源
		
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			String sql = "select count(*) from user where username='"+username+"' and password='"+password+"'";
			System.out.println(sql);
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				int count = rs.getInt(1);
				if(count>0){
					System.out.println("登录成功!");
				}else{
					System.out.println("登录失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
