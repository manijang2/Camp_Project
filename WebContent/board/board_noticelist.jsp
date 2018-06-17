<%@ page import="board.db.*" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
request.setCharacterEncoding("utf-8");

List boardList = (List) request.getAttribute("boardlist");
int listcount = ((Integer)request.getAttribute("listcount")).intValue();
int nowpage = ((Integer)request.getAttribute("page")).intValue();
int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
int startpage = ((Integer)request.getAttribute("startpage")).intValue();
int endpage = ((Integer)request.getAttribute("endpage")).intValue();



%>


<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>공지사항리스트</title>
<script src="/Camp_Project/js/board_script.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="/Camp_Project/css/board.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/main/inc/main_top.inc"%>

<%String adminBtn = "";
if(gradeKey != null && gradeKey.equals("admin")){
	//admin 로그인 시 관리자 메뉴 보이기
	adminBtn = "<input type='button' value='글쓰기' onclick='noticeWrite()' class='btn btn-primary btn-sm'>";
} %>

<div class="div">
<br><br>
<h1>공지사항 게시판</h1>
<hr>

<table class="table table-striped table-hover notice_table">
	<tr class="active success">
		<th class="th1 rad_left" style="padding: 13px;background-color: #2c3e50">번호</th>
		<th class="th2" style="padding: 13px;background-color: #2c3e50">제목</th>
		<th class="th3" style="padding: 13px;background-color: #2c3e50">글쓴이</th>
		<th class="th4" style="padding: 13px;background-color: #2c3e50">날짜</th>
		<th class="th5 rad_right" style="padding: 13px;background-color: #2c3e50">조회</th>
	</tr>
	
	<c:forEach var="b" items="<%=boardList %>">
	<Tr>
		<td class="td1">${b.bn_num}</td>
		<td class="td2"><a href="./BoardDetailAction.do?num=${b.bn_title}>">${b.bn_title}</a></td>
		<td class="td3">${b.bn_id}</td>
		<td class="td4"><fmt:formatDate value="${b.bn_date}" /></td>
		<td class="td5">${b.bn_views}</td>
	</Tr>
</c:forEach>
</table>

<table>
	<tr>
		<td colspan="3" class="th2">
		<form action="boardnoticelist.jsp" name="frm" method="post" class="navbar-form" >
			<select class="form-control" id="select" name="seltype">
				<option value="title" selected="selected" class="form-control">글제목</option>											
			</select>
			<input type="text" name="selword" class="form-control"  placeholder="Search">
			<input type="button" value="검색" id="btnSearch" class="btn btn-primary" onclick="noticeSearch()">
		</form>
		</td>
	</tr>
	<Tr>
		<td class="tdbtn">
			<input type="button" value="목록" onclick="location.href='boardnoticelist.jsp?page=<%=nowpage%>" class="btn btn-deault btn-sm">
			&nbsp;<%=adminBtn %>
		</td>
		<td class="th2">
			<ul class="pagination">
			<!-- 페이지 앞으로 버튼 -->
			<%
			if(nowpage <=1){ %>
			<li class="disabled"><a href ="boardnoticelist.jsp?page=1">&laquo;</a></li>
			<% } else {		
			
			if(maxpage < 5) {
				if(nowpage==1) {%>
				<li class="disabled"><a href ="boardnoticelist.jsp?page=1">&laquo;</a></li>	
				<% }else{ %>
				<li><a href ="boardnoticelist.jsp?page=1">&laquo;</a></li>	
			<%  }}else{	%>
				<li><a href ="boardnoticelist.jsp?page=<%=nowpage-5 %>">&laquo;</a></li>
			<%	}
			}%>
			
			<!-- 페이지 숫자버튼 -->
			<%
			
			for(int i = 1; i <= maxpage; i++){
				if(i == nowpage) {%>
				<li class="active"><a href ="boardnoticelist.jsp?page=<%=i %>"><%=i %></a></li>	
			<%  }else{	%>
				<li><a href ="boardnoticelist.jsp?page=<%=i %>"><%=i %></a></li>
			<%	}
			}			
			%>
			
			<!-- 페이지 뒤로버튼 -->
			<%
			if(maxpage > 5) {%>
				<li><a href ="boardnoticelist.jsp?page=<%=nowpage+5 %>">&raquo;</a></li>	
			<%  }else{
					if(nowpage==maxpage){%>
					<li class="disabled"><a href ="boardnoticelist.jsp?page=<%=maxpage %>">&raquo;</a></li>
			<%		}else{  %>
					<li><a href ="boardnoticelist.jsp?page=<%=maxpage%>">&raquo;</a></li>
			<%}}%>
			
			   
			</ul>
		</td>
		<td class="tdbtn">
		</td>
	</Tr>
</table>

</div>

<br><br>
<%@ include file="/main/inc/main_bottom.inc" %>

</body>
</html>