package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名");
		String username = scanner.nextLine();
		System.out.println("请输入密码");
		String password = scanner.nextLine();
		scanner.close();// 释放资源
		
		try (Connection conn = DBUtils.getConn();) {
			PreparedStatement stat = conn.prepareStatement("select count(*) from user where username=? and password=?");
			stat.setString(1, username);
			stat.setString(2, password);
			ResultSet rs = stat.executeQuery();
			
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
