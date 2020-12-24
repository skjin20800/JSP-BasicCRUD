<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String id = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null) { //쿠키검사
		for (int i = 0; i < cookies.length; i++) { //쿠키검사
			if (cookies[i].getName().equals("idKey")) { // 쿠키 키 확인
				id = cookies[i].getValue();  //확인된 키값 id로 적용
			}
		}
		if (id.equals("")) { // 아이디 ""일시 로그인 실패
%>
<script>
	alert("로그인 되지 않았습니다.");
	location.href = "cookieLogin.jsp";
</script>
<%
	}
}
%>
<html>
<head>
<title>Cookie 로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h2 align="center">Cookie 로그인</h2>
	<table width="300" border="1" align="center" bgcolor="#FFFF99">
		<tr bordercolor="#FFFF66">
			<td colspan="2" align="center"><b>Log On Page</b></td>
		</tr>
		<tr>
			<td align="center"><b><%=id%></b>님이 로그인 하셨습니다.</td>
			<td align="center"><a href="cookieLogout.jsp">로그아웃</a></td>
		</tr>
	</table>
</body>
</html>