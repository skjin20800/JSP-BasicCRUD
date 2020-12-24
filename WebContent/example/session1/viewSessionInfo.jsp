<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션사용예제(세션확인)</title>
</head>
<body>
<%
	Enumeration en = session.getAttributeNames(); // 열거타입으로 세션의 이름을 저장하겠다.
	while(en.hasMoreElements()){ //Enumeration안에 요소들이 있는만큼 반복문 수행
		String name = (String)en.nextElement(); //nextElement : 요소들을 하나씩 순차적으로 추출함
																			//요소를 String형태로 캐스팅해서 출력한다
																			
		String value = (String)session.getAttribute(name); 
		// getAttribute(name) : 현재 할당된(name) 세션의 속성을 읽어온다는뜻이다
		// String으로 다운캐스팅해주어야 value에 담을수 있으니 다운캐스팅한다.
		
		out.println("session name : " + name + "<br/>");
		out.println("session value " + value + "<br/>");
	}
%>
</body>
</html>