package com.cos.hello.controller;

import java.io.IOException;

import javax.servlet.ServletException;
// javax로 시작하는 패키지는 톰켓이 들고 있는 라이브러리 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController extends HttpServlet{
	
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
//		String gubun = req.getRequestURI(); //URL을 확인하는것
		String gubun = req.getParameter("gubun");
		
		System.out.println("boardController 실행됨");
		//http://localhost:8000/hello/user?gubun=login
		//아래코드는 라우터라고한다

		if(gubun.equals("deleteOne")) {
			resp.sendRedirect("board/deleteOne.jsp"); // 한번더 request	
		}
		else if(gubun.equals("selectAll")) {
			resp.sendRedirect("board/selectAll.jsp"); 	
		}
		else if(gubun.equals("updateOne")) {
			resp.sendRedirect("board/updateOne.jsp"); 	
		}
		else if(gubun.equals("insertOne")) {
			resp.sendRedirect("board/insertOne.jsp"); 	
		}
				
	}
}
