package cn.tedu;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Demo11 {

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			// �õ����ݿ��Ԫ����,Ԫ���ݱ������ݿ���������Ϣ
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("���ݿ�汾:" + dbmd.getDriverVersion());
			System.out.println("�û���:" + dbmd.getUserName());
			System.out.println("���ݿ��ַ:" + dbmd.getURL());
			System.out.println("���ݿ⳧��:" + dbmd.getDatabaseProductName());
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from emp;");
			// �õ�����ص�Ԫ����
			ResultSetMetaData rsmd = rs.getMetaData();
			// �õ�����ֶ�����
			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				System.out.println("�ֶ���:" + rsmd.getColumnName(i+1));
				System.out.println("�ֶ�����:" + rsmd.getColumnTypeName(i+1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
