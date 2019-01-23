package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û���");
		String username = scanner.nextLine();
		System.out.println("����������");
		String password = scanner.nextLine();
		scanner.close();// �ͷ���Դ
		
		try (Connection conn = DBUtils.getConn();) {
			PreparedStatement stat = conn.prepareStatement("select count(*) from user where username=? and password=?");
			stat.setString(1, username);
			stat.setString(2, password);
			ResultSet rs = stat.executeQuery();
			
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
