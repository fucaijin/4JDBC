package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("������������ƣ�");
		String teamName = scan.nextLine();
		System.out.println("��������Ա���ƣ�");
		String playerName = scan.nextLine();
		scan.close();
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			// �Ȳ�ѯ�Ƿ����������
			String sql = "select id from team where name=?;";
			PreparedStatement prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, teamName);
			ResultSet rs = prepStat.executeQuery();
			int teamId = -1;
			while (rs.next()) {
				System.out.println("111111111");
				teamId = rs.getInt(1);
			}
			
			if(teamId==-1){// ˵��������������
				// ������ӵ����ݿ���
				String sql1 = "insert into team values(null,?);";
				PreparedStatement prepStat2 = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
				prepStat2.setString(1, teamName);
				prepStat2.executeUpdate();
				ResultSet rs1 = prepStat2.getGeneratedKeys();
				while (rs1.next()) {
					// �õ�������ӵ�id
					teamId = rs1.getInt(1);
				}
			}
			
			// �����Ա
			String sql2 = "insert into player values(null,?,?)";
			PreparedStatement prepStat3 = conn.prepareStatement(sql2);
			prepStat3.setString(1, playerName);
			prepStat3.setInt(2, teamId);
			prepStat3.executeUpdate();
			
			System.out.println("ִ�н���!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
