package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("����������:");
		String name = scanner.nextLine();
		System.out.println("����������:");
		String age = scanner.nextLine();
		try (Connection conn = DBUtils.getConn();){
//			Statement stat = conn.createStatement();
//			stat.executeUpdate("insert into person (name,age) values('"+name+"',"+age+")");
			
			// ����Ԥ�����sqlִ�ж���,���ڴ���Statement��ʱ��ͱ�����sql���,ִ�е�ʱ��Ͳ��ñ�����.������?��������
			PreparedStatement stat = conn.prepareStatement("insert into person (name,age) values(?,?)");
			// �滻?Ϊ�����ı���
			stat.setString(1, name);
			stat.setInt(2, Integer.parseInt(age));
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("д�����ݿ����!");
		scanner.close();
	}
}
