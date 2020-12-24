<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 사용 예제(세션 생성)</title>
</head>
<body>
<%
String id = "rorod";
String pwd = "1234";

session.setAttribute("idKey", id); //첫번째 세션에 대표변수 idKey, 값 id순으로 저장
session.setAttribute("pwdKey", pwd); // 마찬가지
%>
세션이 생성되었습니다.<br/>
<a href="viewSessionInfo.jsp">세션정보를 확인하는 페이지로 이동
</a>
</body>
</html>