package exam;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getInstance() {
		Connection conn = null;
		//jdbc:oracle:기본형식
		
		//thin: java가 들고있는 함수로 연결한다 (99퍼 thin사용)
		// 		범용성이높고, oci보다 속도가 느리다
		
		//oci: 운영체제의 native함수를 사용
		//			os마다 다르기때문에 사용이 힘들다
		//		String url = "jdbc:oracle:oci:@";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "test"; // 접속할 DB 아이디
		String password = "1234"; // 접속할 DB 비밀번호

		// ojdbc6 라이브러리의 OracleDriver를 new해주는것
		// OracleDriver 클래스를 메모리에 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);//접속할 DB아이디, 비밀번호
			System.out.println("DB연결 성공~~!");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB연결 실패");
		return null;
	}
}
