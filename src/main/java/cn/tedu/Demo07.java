package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo07 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入请求的页数:");
		int page = scan.nextInt();
		System.out.println("请输入请求条数:");
		int row = scan.nextInt();
		try (Connection conn = DBUtils.getConn();) {
			PreparedStatement prepStat = conn.prepareStatement("select name,age from person limit ?,?;"); // limit 跳过多少条,查询多少条
			prepStat.setInt(1, (page-1>0?page-1:0)*row);// 如果是-1,就会运行时错误,因此用三木运算,如果查询第0页就强制改为1
			prepStat.setInt(2, row);
			ResultSet rs = prepStat.executeQuery();
			while (rs.next()) System.out.println(rs.getString(1)+" | " + rs.getString(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scan.close();
	}

}
