package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;

public class UsersDao {
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
}
