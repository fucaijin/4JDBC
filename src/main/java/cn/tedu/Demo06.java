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
			// 批量插入一百条数据
			for (int i = 1; i <= 100; i++) {
				prepStat.setString(1, "name"+i);
				prepStat.setInt(2, i);
				// 添加到批量操作
				prepStat.addBatch();
				System.out.println("添加"+i+"条数据");
				// 避免内存溢出 每隔20次就执行一次
				if(i%20==0){
					prepStat.executeBatch();// 执行批量操作
					System.out.println("刷出第"+i/20+"次");
				}
			}
			prepStat.executeBatch();// 执行批量操作
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
