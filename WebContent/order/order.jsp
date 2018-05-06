<%@page import="order.OrderDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="orderDao" class="order.OrderDao"/>
<jsp:useBean id="orderDto" class="order.OrderDto"/>
<% String o_id = (String)session.getAttribute("idKey"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 주문정보</title>
<script type="text/javascript" src="/Camp_Project/js/main_script.js"></script>
<link href="/Camp_Project/css/main_style.css" rel="stylesheet" type="text/css">

</head>

<body>
<%@include file="/main/inc/main_top.inc" %>
<div class="div">

<br><br>
<h1> <%=o_id %> 님의 주문내역 </h1>
<hr>

<b> 상품을 클릭 하시면, 상품의 정보를 확인하실 수 있습니다.</b>
<br><br>
<form method="post" name="form">
<table  class="table table-striped table-hover notice_table">
	<tr class="active success">
		<th class="th3 rad_left"  style="padding: 16px;background-color: #2c3e50;vertical-align: middle;" >주문번호<br>(결제일)</th>
		<th class="th3"  style="padding: 16px;background-color: #2c3e50; vertical-align: middle;">이미지</th>
		<th style="padding: 16px;background-color: #2c3e50; vertical-align: middle;">상품명</th>
		<th class="th1"  style="padding: 16px;background-color: #2c3e50; vertical-align: middle;">수량</th>
		<th class="th5"  style="padding: 16px;background-color: #2c3e50; vertical-align: middle;">결제금액</th>
		<th class="th5"  style="padding: 16px;background-color: #2c3e50; vertical-align: middle;">상태</th>
		<th class="th3 rad_right" style="padding: 16px;background-color: #2c3e50; vertical-align: middle;">수령지 변경</th>
	</tr>
	<% ArrayList<OrderDto> list = (ArrayList)orderDao.orderAll(o_id); %>
	<c:set value="<%=list %>" var="list" />

	<!-- 주문 내역 출력 -->
	<c:forEach var="l" items="${list}" varStatus="status">
	<tr align="center" >
		<td style="vertical-align: middle;">${l.o_num}<br>
			${l.o_date}
		</td>
		<td style="vertical-align: middle;"><img src="/Camp_Project/image/product/${l.p_image1 }" style="width:100px"></td>
		<td style="vertical-align: middle;"><a href="/Camp_Project/product/p_read.jsp?p_code=${l.o_pcode}">${l.p_name}</a></td>
		<td style="vertical-align: middle;">${l.o_quantity}</td>
		<td style="vertical-align: middle;"><fmt:formatNumber type="currency"  value="${l.o_quantity*l.o_pay}" /></td>
		<td style="vertical-align: middle;">${l.os_status}</td>
		<td style="vertical-align: middle;">
		<c:choose>
		    <c:when test="${l.os_num eq 10}">

		    </c:when>
		    <c:when test="${l.os_num eq 20 }">

		    </c:when>
		    <c:when test="${l.os_num eq 30}">
				배송 준비중
		    </c:when>
		    <c:when test="${l.os_num eq 40}">
				<a href="javascript:delevery()'">운송장 조회</a>
		    </c:when>
		    <c:when test="${l.os_num eq 50}">
				<button type="button"  onclick="location.href='../board/reviewWrite.jsp?o_pcode=${l.o_pcode}'">버튼</button>
		    </c:when>
		    <c:when test="${l.os_num eq 100}">
				주문취소 완료
		    </c:when>
		    <c:when test="${l.os_num eq 200}">
				반품 접수
		    </c:when>
		    <c:when test="${l.os_num eq 300}">
				반품완료
		    </c:when>
		    
		</c:choose>
		</td>
	</tr>

	<tr>
		<td colspan="7"  style="vertical-align: middle; height: 50px">
		<!-- 마일리지 내역 -->
		<fmt:formatNumber type="currency"  value="${l.p_mileagerate*l.p_price*l.o_quantity-l.o_usemileage}" />마일리지 적립<br>
		<a href="javascript:alert('삭제할수 없습니다')">주문내역삭제</a>(주문내역 삭제는 주문취소, 반품완료 항목만 가능합니다)
		</td>
		<c:set value="${total + (l.o_pay)}" var="total"/>
	</tr>
	</c:forEach>
</table>

<br><br>

<!-- 상품 주문하기 및 전체금액 표기 -->
<fmt:formatNumber type="currency"  value="${total}" /> 총 주문금액
<br><br>
<br><br>

	
</form>
		
</div>
<%@include file="/main/inc/main_bottom.inc"%>


</body>
</html>