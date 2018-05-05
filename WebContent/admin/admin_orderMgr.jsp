<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="order.db.OrderDto"%>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="adminDao" class="admin.db.AdminDao" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Camp_Project/css/admin_style.css" rel="stylesheet" type="text/css">
<link href="/Camp_Project/css/style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/Camp_Project/js/admin_script.js"></script>
<script type="text/javascript" src="/Camp_Project/js/order_script.js"></script>
</head>
<body>
<%@include file="/admin/inc/admin_menu.inc"%>  
 
<div id="main">
<%@include file="/main/inc/main_top.inc"%>
<div class="div">
<br><br>
<div class="innerdiv">
<h2>주문 관리</h2>
<hr>
<br><Br><br>
<form name="orderForm">
<table class="table table-striped table-hover member_table">
	<tr class="align_Center active">
		<th class="th1" width="10" rowspan="2"><input name="allCheck" type="checkbox" onclick="javascript:selectCheckBoxAll()"/></th>
		<th class="th2" width="50">주문번호</th>
		<th class="th2" width="50">상품번호</th>
		<th class="th3" width="50">상품이름</th>
		<th class="th3" width="50">상품단가</th>
		<th class="th4" width="50">주문수량</th>
		<th class="th4" width="50">사용된마일리지</th>
		<th class="th4" width="50">배송료</th>
		<th class="th4" width="50">총 금액</th>
		<th class="th5" width="50"  rowspan="2"></th>
	</tr>
	<tr class="align_Center active">
		<th class="th4" width="50">주문상태</th>
		<th class="th4" width="50">주문날짜</th>
		<th class="th4" width="50">주문자 ID</th>
		<th class="th4" width="50">주문자 이름</th>
		<th class="th2" width="50">주문자 전화번호</th>
		<th class="th2" width="50">배송지 우편주소 </th>
		<th class="th2" width="50">배송지 주소 </th>
		<th class="th2" width="50">배송시 메세지 </th>
		 
	</tr>
	<c:set var="orderNums" value="<%=(ArrayList<Integer>)adminDao.selectOrderNum()%>"></c:set>
	<c:if test="${empty orderNums}">
		<tr>
			<td colspan="14">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>	
	<c:forEach var="num" items="${orderNums}" >
	<jsp:include page="/admin/order_updateDetail_admin.jsp?o_num=${num}"></jsp:include>
	</c:forEach>
	<c:set var="n" value="${param.newInput}"/>
	<c:if  test="${!empty n}">
		<jsp:include page="/admin/order_register_admin.jsp"></jsp:include>
	</c:if>
	<tr>	
		<td colspan="5">
			<input type="button" value="취소" onclick="location.href='../admin/admin_orderMgr.jsp'"  class="btn btn-danger"/> &nbsp;&nbsp;
			<input type="button" value="삭제" onclick="javascript:confirmDelete()" class="btn btn-danger"/> &nbsp;&nbsp;
			<input type="button" value="상품등록" onclick="location.href='../order/order_update_admin.jsp?newInput=y'" class="btn btn-primary"/> 
		</td>
	</tr>
</table>
</form>
</div>
<br><br><br><br>
</div>
<%@include file="/main/inc/main_bottom.inc"%>
</div>
</body>
</html>