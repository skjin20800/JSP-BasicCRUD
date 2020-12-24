<%@page import="exam.RegisterMgr"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="regMgr" class="exam.RegisterMgr" />
<%
    String id = ""; //로그인에 적은 아이디 담기
	String pwd = ""; //로그인에 적은 비밀번호 담기
	if(request.getParameter("id") != null)  
		id  = request.getParameter("id");// 아이디넣고
	if(request.getParameter("pwd") != null)  
		pwd  = request.getParameter("pwd");// 패스워드 넣고
	if(regMgr.loginRegister(id, pwd)){// DB에 해당 정보 맞는지 체크
		out.println("통과");
		Cookie cookie = new Cookie("idKey", id); //맞으면 새로운 쿠키 생성해서 
		response.addCookie(cookie);					//사용자에게 전달
	
%>
	<script> 
	  alert("로그인 되었습니다.");
      location.href="cookieLoginOK.jsp";
	</script>
<%	}else{ %>
	<script>
	  alert("로그인 되지 않았습니다.");
      location.href="cookieLogin.jsp";
      
	</script>
<%	}	%>