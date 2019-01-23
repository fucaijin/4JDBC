package cn.tedu;

import java.sql.Connection;
import java.sql.Statement;

public class Demo05 {
	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			String sql1 = "insert into person values(null,'悟空',20)";
			String sql2 = "insert into person values(null,'八戒',30)";
			String sql3 = "insert into person values(null,'沙僧',40)";
			// Batch - 批量
			stat.addBatch(sql1);// 添加到批量操作
			stat.addBatch(sql2);// 添加到批量操作
			stat.addBatch(sql3);// 添加到批量操作
			stat.executeBatch();// 执行批量操作
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
