<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<jsp:useBean id="member" class="member.MemberDao"/>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>회원자료</title>
<script src="/Camp_Project/js/member_script.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<link href="/Camp_Project/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/Camp_Project/css/member_style.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="/main/inc/main_top.inc" %>

<div class="div">
<br><br>
<div class="innerdiv">
<h2>회원관리</h2>
<hr>
<form name="memberForm">
<table class="table table-striped table-hover member_table">
	<tr class="align_Center active">
		<th class="th1"><input name="allCheck" type="checkbox" onclick="javascript:selectCheckBoxAll()"/></th>
		<th class="th2">아이디</th>
		<th class="th3">이름</th>
		<th class="th4">회원등급</th>
		<th class="th5">수정/삭제</th>
	</tr>	
	<c:set var="list" value="<%=(ArrayList<MemberDto>)member.selectAll()%>"></c:set>
	<c:if test="${empty list}">
		<tr>
			<td colspan="4">등록된 회원이 없습니다</td>
		</tr>
	</c:if>	
	<c:forEach var="m" items="${list}">
	<tr>
		<td class="td1 align_Center"><input type="checkbox" name="check" value="${m.m_id}"/></td>
		<td class="td2"><a href='../member/member_update.jsp?m_id=${m.m_id}'>${m.m_id}</a></td>
		<td class="td3">>${m.m_name}</td>
		<td class="td4">
			<c:choose>
			<c:when test="${'admin' eq m.m_grade}">
				<b style="color:red;">${m.m_grade}</b>
			</c:when>
			<c:otherwise>
				${m.m_grade}
			</c:otherwise>
			</c:choose>
		</td>
		<td class="td5">
			<input type="button" value="수정" onclick="javascript:location.href='../member/member_update.jsp?m_id=${m.m_id}'" class="btn btn-info btn-xs" /> / 
			<input type="button" value="삭제" onclick="javascript:confirmDeleteEach('${m.m_id}')" class="btn btn-danger btn-xs"/>
		</td>	
	</tr>	
	</c:forEach>
	<tr>
		<td colspan="5">
			<input type="button" value="선택삭제" onclick="javascript:confirmDelete()" class="btn btn-danger"/>
			<input type="button" value="회원등록" onclick="javascript:location.href='../member/member_register.jsp'" class="btn btn-primary"/> 
		</td>

	</tr>
</table>
</form>
</div>
<br><br><br><br>
</div>
<%@include file="/main/inc/main_bottom.inc"%>

</body>
</html>