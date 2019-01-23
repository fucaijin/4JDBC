package cn.tedu;

import java.sql.Connection;
import java.sql.Statement;

public class Demo05 {
	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			String sql1 = "insert into person values(null,'���',20)";
			String sql2 = "insert into person values(null,'�˽�',30)";
			String sql3 = "insert into person values(null,'ɳɮ',40)";
			// Batch - ����
			stat.addBatch(sql1);// ��ӵ���������
			stat.addBatch(sql2);// ��ӵ���������
			stat.addBatch(sql3);// ��ӵ���������
			stat.executeBatch();// ִ����������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
