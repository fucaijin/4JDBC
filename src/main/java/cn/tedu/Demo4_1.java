package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo4_1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û���");
		String username = scanner.nextLine();
		System.out.println("����������");
		String password = scanner.nextLine();
		scanner.close();// �ͷ���Դ
		
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			String sql = "select count(*) from user where username='"+username+"' and password='"+password+"'";
			System.out.println(sql);
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				int count = rs.getInt(1);
				if(count>0){
					System.out.println("��¼�ɹ�!");
				}else{
					System.out.println("��¼ʧ��!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
