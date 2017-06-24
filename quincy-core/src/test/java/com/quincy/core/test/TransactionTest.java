package com.quincy.core.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionTest {
	public void opt1() throws Exception {
		String suffix = "111";
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = this.createConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			stat = conn.prepareStatement("UPDATE singer SET name=CONCAT(name,?) WHERE id=?");
			stat.setString(1, suffix);
			stat.setLong(2, 1);
			stat.executeUpdate();
			
			Thread.sleep(500);

			stat.setString(1, suffix);
			stat.setLong(2, 2);
			stat.executeUpdate();

//			Thread.sleep(10000);
			conn.commit();
			
//			Thread.sleep(1000);
//			conn.rollback();
			System.out.println(suffix+" Finished");
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(stat!=null) {
				stat.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public void opt2() throws Exception {
		String suffix = "222";
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = this.createConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			stat = conn.prepareStatement("UPDATE singer SET name=CONCAT(name,?) WHERE id=?");
			Thread.sleep(100);
			stat.setString(1, suffix);
			stat.setLong(2, 1);
			stat.executeUpdate();
			
			Thread.sleep(200);

			stat.setString(1, suffix);
			stat.setLong(2, 2);
			stat.executeUpdate();

			conn.commit();
			System.out.println(suffix+" Finished");
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(stat!=null) {
				stat.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public void opt3() throws Exception {
		String suffix = "333";
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = this.createConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			stat = conn.prepareStatement("UPDATE singer SET name=CONCAT(name,?) WHERE id=?");
			stat.setString(1, suffix);
			stat.setLong(2, 2);
			stat.executeUpdate();
			Thread.sleep(15000);
//			conn.commit();
			conn.rollback();
			System.out.println(suffix+" Finished");
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(stat!=null) {
				stat.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public void opt4() throws Exception {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			Thread.sleep(1000);
			conn = this.createConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			stat = conn.prepareStatement("SELECT * FROM singer FOR UPDATE");
			rs = stat.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if(stat!=null) {
				stat.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public void opt5() throws Exception {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			Thread.sleep(1000);
			conn = this.createConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			stat = conn.prepareStatement("SELECT * FROM singer");
			rs = stat.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if(stat!=null) {
				stat.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/quincy1", "quincy", "ycniuq");
	}

	public static void main(String[] args) throws Exception {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		fixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("xxx");
			}
		});
		fixedThreadPool.shutdown();
		/*final TransactionTest test = new TransactionTest();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test.opt3();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test.opt4();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();*/
	}

}
