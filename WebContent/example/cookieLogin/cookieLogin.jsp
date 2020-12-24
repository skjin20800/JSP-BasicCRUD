<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String cookieName = ""; // 내가 준 쿠키가 있다면 여기 넣을 키값
	String id = ""; //내가 준 쿠키가 있다면 여기 넣을 값
	
	Cookie[] cookies = request.getCookies();
	if (cookies != null) { // 쿠키가 Null이 아닐때 동작
		for (int i = 0; i < cookies.length; i++) {  //쿠키의 크기만큼 for문
					// 반복문 주는이유: 1. 사용자가 들고있는 무수히 많은 쿠키들중 내 쿠키를 찾기위해서
					//							2. 내가준 쿠키의 고유이름과 그에 해당하는 값을 찾기위해서
			if (cookies[i].getName().equals("idKey")) { // 내가 준 쿠키 idKey,를 찾아 idKey와 그 값들을 얻는것
				cookieName = cookies[i].getName();
				id = cookies[i].getValue();
			}
		}
		if (!id.equals("")) {
%>
<script>
	alert("로그인 되었습니다.");
	location.href = "cookieLoginOK.jsp";
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
	<form method="post" action="cookieLoginProc.jsp">
		<table width="300" border="1" align="center" bgcolor="#FFFF99">
			<tr bordercolor="#FFFF66">
				<td colspan="2" align="center"><b>Log in</b></td>
			</tr>
			<tr>
				<td width="47%" align="center">ID</td>
				<td width="53%" align="center"><input name="id"></td>
			</tr>
			<tr>
				<td align="center">PWD</td>
				<td align="center"><input name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				   <input type="submit" value="login">
			       <input type="reset" value="reset">
			     </td>
			</tr>
		</table>
	</form>
</body>
</html>