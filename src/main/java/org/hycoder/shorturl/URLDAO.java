package org.hycoder.shorturl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * 
 * */
public class URLDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// not used right now
	public boolean hasLongURL(String longURL) {
		String sql = "SELECT * FROM url_map WHERE long_url = ?";
		Connection conn = null;
		boolean existed = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, longURL);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				existed = true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return existed;
	}

	public int insert(URLMap urlMap) {
		String sql = "INSERT INTO url_map "
				+ "(tiny_url, long_url) VALUES (?, ?)";
		Connection conn = null;
		ResultSet rs = null;
		int autoRowId = -1;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, urlMap.getTinyURL());
			ps.setString(2, urlMap.getLongURL());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				autoRowId = rs.getInt(1);
				System.out.println("Atuo-Generated Key " + autoRowId);
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return autoRowId;
	}

	public URLMap findById(int urlId) {
		String sql = "SELECT * FROM url_map WHERE id = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, urlId);
			URLMap shortener = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				shortener = new URLMap(rs.getInt("id"),
						rs.getString("tiny_url"), rs.getString("long_url"));
			}
			rs.close();
			ps.close();
			return shortener;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
