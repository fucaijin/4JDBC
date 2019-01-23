package cn.tedu;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Demo11 {

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			// 得到数据库的元数据,元数据保存数据库或表的相关信息
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("数据库版本:" + dbmd.getDriverVersion());
			System.out.println("用户名:" + dbmd.getUserName());
			System.out.println("数据库地址:" + dbmd.getURL());
			System.out.println("数据库厂商:" + dbmd.getDatabaseProductName());
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from emp;");
			// 得到表相关的元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 得到表的字段数量
			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				System.out.println("字段名:" + rsmd.getColumnName(i+1));
				System.out.println("字段类型:" + rsmd.getColumnTypeName(i+1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
