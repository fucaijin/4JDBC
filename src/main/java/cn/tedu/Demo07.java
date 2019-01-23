package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo07 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("�����������ҳ��:");
		int page = scan.nextInt();
		System.out.println("��������������:");
		int row = scan.nextInt();
		try (Connection conn = DBUtils.getConn();) {
			PreparedStatement prepStat = conn.prepareStatement("select name,age from person limit ?,?;"); // limit ����������,��ѯ������
			prepStat.setInt(1, (page-1>0?page-1:0)*row);// �����-1,�ͻ�����ʱ����,�������ľ����,�����ѯ��0ҳ��ǿ�Ƹ�Ϊ1
			prepStat.setInt(2, row);
			ResultSet rs = prepStat.executeQuery();
			while (rs.next()) System.out.println(rs.getString(1)+" | " + rs.getString(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scan.close();
	}

}
