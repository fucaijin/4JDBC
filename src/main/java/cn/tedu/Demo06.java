package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Demo06 {

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			String sql = "insert into person values(null,?,?)";
			PreparedStatement prepStat = conn.prepareStatement(sql);
			// ��������һ��������
			for (int i = 1; i <= 100; i++) {
				prepStat.setString(1, "name"+i);
				prepStat.setInt(2, i);
				// ��ӵ���������
				prepStat.addBatch();
				System.out.println("���"+i+"������");
				// �����ڴ���� ÿ��20�ξ�ִ��һ��
				if(i%20==0){
					prepStat.executeBatch();// ִ����������
					System.out.println("ˢ����"+i/20+"��");
				}
			}
			prepStat.executeBatch();// ִ����������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
