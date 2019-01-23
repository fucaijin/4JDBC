package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo08 {

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConn();) {
			Statement stat = conn.createStatement();
			// 开启事务 关闭自动提交
			conn.setAutoCommit(false);
			String sql1 = "update hero set money=money-2000 where id=2;";
			String sql2 = "update hero set money=money+2000 where id=1;";
			stat.executeUpdate(sql1);
			stat.executeUpdate(sql2);
			// 查询剩余的钱是否大于等于0
			String sql3 = "select money from hero where id=2;";
			ResultSet rs = stat.executeQuery(sql3);
			while (rs.next()) {
				int money = rs.getInt("money");
				if(money>=0){ // 说明钱够转账
					conn.commit(); // 提交事务
					System.out.println("转账成功");
				}else{// 钱不够转账
					conn.rollback(); // 回滚
					System.out.println("转账失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
