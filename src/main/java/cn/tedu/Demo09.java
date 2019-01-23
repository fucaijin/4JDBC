package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo09 {

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			String sql = "insert into hero values(null,?,?)";
			PreparedStatement prepStat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// ִ��sql����ʱ�򷵻�����������ֵ
			prepStat.setString(1, "������");
			prepStat.setInt(2, 20000);
			prepStat.executeUpdate();
			// �õ�����������ֵ
			ResultSet rs = prepStat.getGeneratedKeys();
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
