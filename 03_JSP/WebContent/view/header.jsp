<%@page import="com.parksh.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Member member = (Member)session.getAttribute("member");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if (member == null) {%>
	<form action="/view/login.jsp" method="post">
		<fieldset>
			<legend>로그인</legend>
			아이디: <input type="text" name="id" /><br /> 비밀번호: <input
				type="password" name="pw" /><br /> <input type="submit"
				value=" 로그인" /> <input type="reset" value="취소" />
		</fieldset>
	</form>
	<% }else{ %>
	<h2>[<%=member.getMemberName() %>] 님</h2>
	<% } %>
	 

</body>
</html>