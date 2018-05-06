<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String id =(String)request.getAttribute("id");
System.out.println("id : " + id);
boolean check = (Boolean)request.getAttribute("check");
System.out.println("check : " + check);
%>

<% if(check==true){ %>	이미 사용중인 id 입니다.<p/>
	<a href ="#" onclick ="opener.document.regForm.m_id.focus(); window.close();">닫기</a>
<% }else{ %>

	
	사용 가능한 id 입니다.<p/>
	<a href ="#" onclick ="opener.document.regForm.isIdChecked.value='checked';window.close();">확인</a>
<%} %>




</body>
</html>