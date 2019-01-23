package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();){
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from emp;");
			while (rs.next()) {
				System.out.println(rs.getString("ename") + " - " + rs.getString("sal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
