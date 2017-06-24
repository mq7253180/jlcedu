package com.jlcedu.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
	private final static int id = 4;

	public void session1() throws Exception {
		Connection conn = null;
		PreparedStatement stat1 = null;
//		PreparedStatement stat2 = null;
//		ResultSet rs = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			stat1 = conn.prepareStatement("UPDATE t_core_i18n_locale SET locale=? WHERE id=?");
//			stat2 = conn.prepareStatement("SELECT locale FROM t_core_i18n_locale WHERE id=?");
			stat1.setString(1, "www");
			stat1.setInt(2, id);
//			stat2.setInt(1, id);
			stat1.executeUpdate();
//			rs = stat2.executeQuery();
//			if(rs.next()) {
//				System.out.println(rs.getString("locale"));
//			}
			Thread.sleep(5000);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
//			if(rs!=null)
//				rs.close();
			if(stat1!=null)
				stat1.close();
//			if(stat2!=null)
//				stat2.close();
			if(conn!=null)
				conn.close();
		}
	}

	public void session2() throws Exception {
		Connection conn = null;
//		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		ResultSet rs = null;
		try {
			Thread.sleep(1000);
			conn = this.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//			stat1 = conn.prepareStatement("UPDATE t_core_i18n_locale SET locale=? WHERE id=?");
			stat2 = conn.prepareStatement("SELECT locale FROM t_core_i18n_locale WHERE id=?");
//			stat1.setString(1, "www");
//			stat1.setInt(2, id);
			stat2.setInt(1, id);
//			stat1.executeUpdate();
			rs = stat2.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("locale"));
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(rs!=null)
				rs.close();
//			if(stat1!=null)
//				stat1.close();
			if(stat2!=null)
				stat2.close();
			if(conn!=null)
				conn.close();
		}
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://47.93.89.0:3306/jlcedu?zeroDateTimeBehavior=convertToNull", "jlcedu", "mq62455878");
	}

	public static void main(String[] args) throws Exception {
		JdbcTest test = new JdbcTest();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test.session1();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test.session2();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		t1.start();
		t2.start();
	}

}
