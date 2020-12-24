package exam;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterMgr{
	
	public RegisterMgr(){
		try{
//			pool = DBConnection.getInstance();
		}catch(Exception e){
			System.out.println("Error : 커넥션 연결 실패");			
		}
	}
	public boolean loginRegister(String id, String pwd) {
		Connection con = DBConnection.getInstance();	
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginCon = false;
        try {
			String query = "SELECT count(*) FROM member WHERE username = 'ssar' AND password = '1234'";
            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, "ssar");
//            pstmt.setString(2, "1234");
            rs = pstmt.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
            
            if(rs.next()&&rs.getInt(1)>0) 
            	loginCon =true;
        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally{
//             con.freeConnection(con,pstmt,rs); 데이터베이스를 끊는것
        }
        return loginCon;
    }
}
