<%@ page contentType="text/html;charset=UTF-8"%>
<%
		String protocol = request.getProtocol(); //프로토콜 반환
		String serverName = request.getServerName(); // 서버이름 반환
		int serverPort = request.getServerPort(); // 서버 포트 반환
		String remoteAddr = request.getRemoteAddr(); //서버 주소 반환
		String remoteHost = request.getRemoteHost(); // 서버 컴퓨터 이름 반환
		String method = request.getMethod(); // 사용 메서드반환 GET ,POST 등
		StringBuffer requestURL = request.getRequestURL(); // 요청 경로 URL
		String requestURI = request.getRequestURI(); // 요청경로 URI
		String useBrowser = request.getHeader("User-Agent"); // 현재 사용하는 브라우저
		String fileType = request.getHeader("Accept"); // 브라우저가 지원하는 file 타입
%>
<h1>Request Example2</h1>
프로토콜 : <%=protocol%><p/>
서버의 이름 : <%=serverName%><p/>
서버의 포트 번호 :<%=serverPort%><p/>
사용자 컴퓨터의 주소 : <%=remoteAddr%><p/>
사용자 컴퓨터의 이름 : <%=remoteHost%><p/>
사용 method : <%=method%><p/>
요청 경로(URL) : <%=requestURL%><p/>
요청 경로(URI) : <%=requestURI%><p/>
현재 사용하는 브라우저 : <%=useBrowser%><p/>
브라우저가 지원하는 file의 type : <%=fileType%><p/>