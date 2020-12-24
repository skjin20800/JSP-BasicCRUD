<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<!--
 로그인은 무조건 POST
의미상 GET도 POST도아닌데 POST를 사용한다.
특수한 경우이다.
 -->
<h1>Login Page</h1>
<form action="/hello/user?gubun=loginProc" method="post" >
<input  type="text" name="username" placeholder="username"/>
<input  type="password" name="password" placeholder="password"/>
<button>로그인</button>
</form>
</body>
</html>