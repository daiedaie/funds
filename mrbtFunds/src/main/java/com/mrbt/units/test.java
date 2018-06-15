package com.mrbt.units;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class test {

	private static Logger LOGGER = Logger.getLogger(test.class);

	public static void main(String[] args) {

		try {
			// java驱动程序
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// url串
			String url = "jdbc:oracle:thin:@192.168.1.88:1521:orcl";
			// 连接数据库的url串,用户密码,用户名;
			Connection conn = DriverManager.getConnection(url, "oracletest", "qwe123");
			// 创建数据库通道
			Statement stmt = conn.createStatement();
			// 创建数据库结果集
			ResultSet rs = stmt.executeQuery("select * from test_user");
			while (rs.next()) {
				System.out.print("name:" + rs.getString(1));
			}
			rs.close();// 关闭结果集
			stmt.close();// 关闭结果集通道
			conn.close();// 关闭数据库连接
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("找不到服务!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
