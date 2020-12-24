<%@ page contentType="text/html; charset=UTF-8" %>
<%
	Cookie[] cookies = request.getCookies(); // 모든 쿠키를 가져옴
	if(cookies!=null){ 
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("idKey")){ // idkey찾고
				cookies[i].setMaxAge(0); //유효시간 바로 0으로 때려버림
				response.addCookie(cookies[i]);
			}
		}
	}
%>
<script>
    alert("로그아웃 되었습니다.");
	location.href = "cookieLogin.jsp";
</script>