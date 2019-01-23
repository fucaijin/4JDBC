package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo08 {

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			// �������� �ر��Զ��ύ
			conn.setAutoCommit(false);
			String sql1 = "update hero set money=money-2000 where id=2;";
			String sql2 = "update hero set money=money+2000 where id=1;";
			stat.executeUpdate(sql1);
			stat.executeUpdate(sql2);
			// ��ѯʣ���Ǯ�Ƿ���ڵ���0
			String sql3 = "select money from hero where id=2;";
			ResultSet rs = stat.executeQuery(sql3);
			while (rs.next()) {
				int money = rs.getInt("money");
				if(money>=0){ // ˵��Ǯ��ת��
					conn.commit(); // �ύ����
					System.out.println("ת�˳ɹ�");
				}else{// Ǯ����ת��
					conn.rollback(); // �ع�
					System.out.println("ת��ʧ��");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
