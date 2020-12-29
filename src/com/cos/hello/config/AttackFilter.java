package com.cos.hello.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AttackFilter implements Filter {


	// 2번째 (마지막 순서)
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// post요청만 받아서 처리!!
		
//		String test = request.getParameter("username");
//
//		
//		HttpServletRequest req = (HttpServletRequest) request;
//		String method = req.getMethod();
//		System.out.println("method : "+method);
//		
//		if(method.equals("POST")) {
//			String username = request.getParameter("username");
//			username = username.replaceAll("<", "&lt11").replaceAll(">","&gt");
//					System.out.println("username2 : " + username);
//			
//					request.setAttribute("username", username);
//					req.setAttribute("username", username);
//					System.out.println("알이큐 : " + req.getParameter("username"));
//					
//
//					
//			
////					RequestDispatcher dis = request.getRequestDispatcher("/hello/auth/join.jsp");			
////					dis.forward(request, response);
//					
//			//리퀘스트 유저네임에 다시 저거 변경한걸 넣어주기
//			// username에 < > 꺽쇠 들어오는것을 방어
//			// 만약에 꺽쇠가 들어오면 전부 &It; &gt;
//			// 다시 필터 타게 할 예정
//		
//		}
		
		chain.doFilter(request, response);
//		BufferedReader br = request.getReader();
//		String input;
//		while((input = br.readLine())!= null) {
//			System.out.println(input);
//		}
		

	
	
	}

}
