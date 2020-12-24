package com.cos.hello.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
// javax로 시작하는 패키지는 톰켓이 들고 있는 라이브러리 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBConn;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;
import com.mysql.cj.xdevapi.SchemaImpl;

// 디스패쳐의 역할 = 분기 = 필요한 View를 응답해주는 것
public class UserController extends HttpServlet {
	// req와 resp는 톰켓이 만들어 줍니다. (사용자의 요청이 있을때 마다)
	// req는 BufferedReader 할 수 있는 ByteStream
	// resp는 BufferedWriter 할 수 있는 ByteStream

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("userController 실행됨");
		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		route(gubun, req, resp);
	}

	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// http://localhost:8000/hello/user?gubun=login
		// 아래코드는 라우터라고한다
		if (gubun.equals("login")) {
			resp.sendRedirect("auth/login.jsp"); // 한번더 request
		} else if (gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp");
		} else if (gubun.equals("selectOne")) {// 유저 정보 보기
			// 인증이 필요한 페이지 
			String result;
			HttpSession session = req.getSession();
	         if(session.getAttribute("sessionUser") != null) {
	             Users user = (Users)session.getAttribute("sessionUser");
	             result = "인증 되었습니다.";
	             System.out.println(user);
	          }else {
	             result = "인증되지 않았습니다";
	          }
	         req.setAttribute("result", result); // request에 result값 삽입
			RequestDispatcher dis = // 디스패치 뜻 : 보내다 ,즉 dis로 request를 보낸다
					req.getRequestDispatcher("user/selectOne.jsp"); // <- 여기내용과 함께전달
			dis.forward(req, resp); // 해당 내용을 전송
			
		} else if (gubun.equals("updateOne")) {
			resp.sendRedirect("user/updateOne.jsp");
		} else if (gubun.equals("joinProc")) {// 회원가입 수행해줘
			// 데이터 원형 username = ssar&password=1234&email=ssar@nate.com

			// 1번 form의 input태그에 있는 3가지 값 username, password, email 받기
			// getParameter함수는 get방식의 데이터와 post방식의 데이터를 다 받을수있다
			// 단 post방식에서는 데이터 타입이 x-www-form-urlencoded 방식만 받을 수 있음.
			String username = req.getParameter("username"); // 파싱해준다 username의 ssar을 추출한다
			String password = req.getParameter("password");
			String email = req.getParameter("email");

			
			Users user = Users.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
			
			UsersDao usersDao = new UsersDao(); //나중에 싱글턴 패턴으로 바꾸기
			int result = usersDao.insert(user);
			if(result ==1 ) {
				// 3번 INSERT가 정삭적으로 되었다면 index.jsp응답
				resp.sendRedirect("auth/login.jsp");		
			}else {
				resp.sendRedirect("auth/join.jsp");
			}
			
		
			// 3번 INSERT가 정상적으로 되었다면 index.jsp를 응답
		    
			HttpSession session = req.getSession();
	          session.setAttribute("sessionKey", "9990");
	          resp.setHeader("Set-Cookie", "sessionKey=9990");
	         
		} else if (gubun.equals("loginProc")) {
			// 1번 전달되는 값 받기
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			System.out.println("==========loginProc Start=========");
			System.out.println(username);
			System.out.println(password);
			System.out.println(email);
			System.out.println("==========loginProc End=========");
			
			// 2번 데이터베이스 값이 있는 select 해서 확인
			
			Users user = Users.builder().id(1).username(username).build();
			// 3번
			HttpSession session = req.getSession();
			 session.setAttribute("sessionUser", user);    //key , value
			// session에는 사용자 패스워드 절대 넣지 않기			
			session.setAttribute("sessionUser", user); // 세션의 heap영역에 임시키 저장
			// 모든 응답에는 jSessionId가 쿠기로 추가된다.
			resp.setHeader("Set-Cookie", "9998"); // 사용자한테 임시키 발급

			// 4번 인덱스 페이지로 이동
			resp.sendRedirect("index.jsp");
		}
	}
}
