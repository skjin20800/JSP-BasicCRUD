package com.cos.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;
import com.cos.util.Script;

public class UsersService {
	
	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		
//		UsersDao usersDao = new UsersDao(); //나중에 싱글턴 패턴으로 바꾸기
//		int result = usersDao.insert(user);
		
		int result = UsersDao.getInstance().insert(user);

		if(result ==1 ) {
			// 3번 INSERT가 정삭적으로 되었다면 index.jsp응답
			resp.sendRedirect("auth/login.jsp");		
		}else {
			resp.sendRedirect("auth/join.jsp");
		}
		
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// 1번 전달되는 값 받기
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// 2번 데이터베이스 값이 있는 select 해서 확인
		Users user = Users.builder().username(username).password(password).build();
		Users userEntity = UsersDao.getInstance().login(user);
		
		if(userEntity != null) {
			//3번
			// session에는 사용자 패스워드 절대 넣지 않기
			HttpSession session = req.getSession();
			 session.setAttribute("sessionUser", userEntity);    //key , value
			//4번 인덱스 페이지로 이동
			 // 1. 한글처리를 위해 resp 객체를 건드린다.
			 // 2. MIME 타입
			 // 3. HTTP Header에 Content-Type
			 Script.href(resp,"index.jsp", "로그인 성공");
		}else {
		Script.back(resp, "로그인 실패");
		}

	}

	public void 유저정보보기(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 인증이 필요한 페이지 
		String result;
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("sessionUser");
//		UsersDao usersDao = new UsersDao();
		if(user != null) {
			Users userEntity = UsersDao.getInstance().selectById(user.getId());
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		} else {
			resp.sendRedirect("auth/login.jsp");
		}
		
	}

	public void 유저정보수정페이지(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 인증이 필요한 페이지 
		String result;
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("sessionUser");
		
		
		if(user != null) {
			Users userEntity = UsersDao.getInstance().selectById(user.getId());
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/updateOne.jsp");
			dis.forward(req, resp);
		} else {
			resp.sendRedirect("auth/login.jsp");
		}
		
	}
	
	public void 유저정보수정(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 데이터 원형 username = ssar&password=1234&email=ssar@nate.com

		// 1번 form의 input태그에 있는 3가지 값 username, password, email 받기
		// getParameter함수는 get방식의 데이터와 post방식의 데이터를 다 받을수있다
		// 단 post방식에서는 데이터 타입이 x-www-form-urlencoded 방식만 받을 수 있음.
		int id = Integer.parseInt(req.getParameter("id")); // 파싱해준다 username의 ssar을 추출한다
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		Users user = Users.builder()
				.id(id)
				.password(password)
				.email(email)
				.build();
		
		
		
		int result = UsersDao.getInstance().update(user);
		
	
		if(result ==1 ) {
			// 3번 INSERT가 정삭적으로 되었다면 index.jsp응답
			resp.sendRedirect("user?gubun=selectOne");		
		}else {
			// 이전페이지로 이동
			resp.sendRedirect("user?gubun=updateOne");
		}
		
	}
	
	public void 유저정보삭제(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id")); // 파싱해준다 username의 ssar을 추출한다

		Users user = Users.builder()
				.id(id).build();
		
		int result = UsersDao.getInstance().delete(user);
	
		if(result ==1 ) {
			HttpSession session = req.getSession(); //세션무효화코드
			session.invalidate(); //세션무효화 코드
			
			resp.sendRedirect("index.jsp");		
		}else {
			// 이전페이지로 이동
			resp.sendRedirect("user?gubun=selectOne");
		}
	}
}
