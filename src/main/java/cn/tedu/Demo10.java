package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入球队名称：");
		String teamName = scan.nextLine();
		System.out.println("请输入球员名称：");
		String playerName = scan.nextLine();
		scan.close();
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			// 先查询是否存在这个球队
			String sql = "select id from team where name=?;";
			PreparedStatement prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, teamName);
			ResultSet rs = prepStat.executeQuery();
			int teamId = -1;
			while (rs.next()) {
				System.out.println("111111111");
				teamId = rs.getInt(1);
			}
			
			if(teamId==-1){// 说明不存在这个球队
				// 保存球队到数据库中
				String sql1 = "insert into team values(null,?);";
				PreparedStatement prepStat2 = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
				prepStat2.setString(1, teamName);
				prepStat2.executeUpdate();
				ResultSet rs1 = prepStat2.getGeneratedKeys();
				while (rs1.next()) {
					// 得到自增球队的id
					teamId = rs1.getInt(1);
				}
			}
			
			// 添加球员
			String sql2 = "insert into player values(null,?,?)";
			PreparedStatement prepStat3 = conn.prepareStatement(sql2);
			prepStat3.setString(1, playerName);
			prepStat3.setInt(2, teamId);
			prepStat3.executeUpdate();
			
			System.out.println("执行结束!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
