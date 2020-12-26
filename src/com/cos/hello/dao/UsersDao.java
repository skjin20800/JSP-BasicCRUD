package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;

public class UsersDao {
	
		private static UsersDao instance = new UsersDao();

		public static UsersDao getInstance() {
			return instance;
		}	

	
public int insert(Users user) {
	
	System.out.println("==========joinProc Start=========");
	System.out.println(user.getUsername());
	System.out.println(user.getPassword());
	System.out.println(user.getEmail());
	System.out.println("==========joinProc End=========");
	// 2번 DB에 연결해서 3가지의 값을 INSERT 하기
	// 2번 데이터베이스 값이 있는 select 해서 확인
	StringBuffer sb = new StringBuffer(); // String전용 컬렉션(수집)이다, 동기화되어있음,
	sb.append("INSERT INTO users(username, password, email) ");
	sb.append("VALUES(?,?,?)");
	String sql = sb.toString();
	Connection conn = DBConn.getInstance();
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		
		int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}

public Users login(Users users) {
	StringBuffer sb = new StringBuffer(); // String전용 컬렉션(수집)이다, 동기화되어있음,
	sb.append("SELECT id, password, username, email ");
	sb.append("FROM users ");
	sb.append("WHERE username = ? AND password = ?");
	String sql = sb.toString();
	Connection conn = DBConn.getInstance();
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, users.getUsername());
		pstmt.setString(2, users.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Users userEntity = Users.builder()
					.id(rs.getInt("id"))
					.username(rs.getString("username"))
					.email(rs.getString("email"))
					.build();
			return userEntity;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public Users selectById(int id) {
	StringBuffer sb = new StringBuffer(); // String전용 컬렉션(수집)이다, 동기화되어있음,
	sb.append("SELECT id, password, username, email ");
	sb.append("FROM users ");
	sb.append("WHERE id = ?");
	String sql = sb.toString();
	Connection conn = DBConn.getInstance();
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Users userEntity = Users.builder()
					.id(rs.getInt("id"))
					.username(rs.getString("username"))
					.password(rs.getString("password"))
					.email(rs.getString("email"))
					.build();
			return userEntity;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public int update(Users user) {
	
	StringBuffer sb = new StringBuffer(); // String전용 컬렉션(수집)이다, 동기화되어있음,
	sb.append("UPDATE users ");
	sb.append("SET password = ?, email = ? ");
	sb.append("WHERE id = ?");
	String sql = sb.toString();
	Connection conn = DBConn.getInstance();
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getEmail());		
		pstmt.setInt(3, user.getId());
		int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
		
		return 1;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}

public int delete(Users user) {
	
	StringBuffer sb = new StringBuffer(); // String전용 컬렉션(수집)이다, 동기화되어있음
	sb.append("DELETE FROM users WHERE id = ?");
	String sql = sb.toString();
	Connection conn = DBConn.getInstance();
	try {
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user.getId());
		int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
		System.out.println("리썰트값" + result);
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}

}
