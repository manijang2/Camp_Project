<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String id = (String)session.getAttribute("idKey");%>
	
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
window.onload=function (){

	
		id.action = "./MemberUpdateAction_pre.do";	
		id.method="post";
		id.submit(); 
	
};
</script>

<form name="id" id="update">
<input type="hidden" name="m_id" value= "<%=id %>" >
</form>

</body>
</html>