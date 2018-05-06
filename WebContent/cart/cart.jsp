<%@page import="cart.CartDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartDao" class="cart.CartDao"/>
<jsp:useBean id="cartDto" class="cart.CartDto"/>
<jsp:setProperty property="*" name="cartDto"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String c_id = (String)session.getAttribute("idKey");
ArrayList<CartDto> list = (ArrayList)cartDao.cartRead(c_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니</title>
<script type="text/javascript" src="/Camp_Project/js/main_script.js"></script>
<link href="/Camp_Project/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="div">
<%@include file="/main/inc/main_top.inc" %>
	<div class="div">
	
<br><br>
<h1>나의 장바구니</h1>
<hr>
<!-- 주문상품 개별표기  -->
<b> 상품을 클릭 하시면, 상품의 정보를 확인하실 수 있습니다.</b>
<br><br>
<form method="post" name="form">
<table  class="table table-striped table-hover notice_table">
	<tr class="active success">
		<th  class="th1 rad_left"  style="padding: 16px;background-color: #2c3e50" ><input name="allCheck" type="checkbox" onclick="allChk()"/></th>
		<th  style="background-color: #2c3e50;vertical-align: middle;">이미지</th>
		<th  style="background-color: #2c3e50;vertical-align: middle;">상품명</th>
		<th  style="background-color: #2c3e50;vertical-align: middle;">수량</th>
		<th  style="background-color: #2c3e50;vertical-align: middle;">단가</th>
		<th  style="background-color: #2c3e50;vertical-align: middle;">소계</th>
		<th  style="background-color: #2c3e50;vertical-align: middle; width: 120px">마일리지</th>
		<th  class="th1 rad_right" style="background-color: #2c3e50;vertical-align: middle; width: 120px">주문삭제</th>
	</tr>
	<c:set value="<%=list %>" var="list" />
	<c:forEach var="l" items="${list}" varStatus="status">
		<!-- 장바구니 내용 출력 -->
		<tr style="text-align: center;">
			<td width="80" style="vertical-align: middle;"><input type="checkbox" name="check" value="${l.c_num}"></td>
			<td width="100" style="vertical-align: middle;"><img src="/Camp_Project/image/product/${l.p_image1}" width="90"></td>
			<td style="vertical-align: middle;"><a href = "/Camp_Project/p_read.do?p_code=${l.c_pcode}">${l.p_name }</a></td>
			
			<!-- 상품 수량체크 -->
			<td width="150" height="20"  style="vertical-align: middle;">
				<table >
					<tr>
						<td><input type="text" id="${l.c_num}" name="${l.c_num}" value="${l.c_quantity}" size="1" style="text-align:right" ></td>
						<td width="10"><div>
									<div>
										<a href="JavaScript:cartCount('${l.c_num}','up');"><img src="/Camp_Project/image/product/up.jpg" border="0"></a>
									</div>
										<div><a href="JavaScript:cartCount('${l.c_num}','down');"><img src="/Camp_Project/image/product/dw.jpg" border="0"></a>
									</div>
								</div>
						</td>
						<td><input type="button" value="변경" onclick="JavaScript:cartUpdate('${l.c_num}')" class="btn btn-primary btn-xs"></td>
					</tr>
				</table>
			</td>
			<td width="80"  style="vertical-align: middle;">
				<fmt:formatNumber type="currency"  value="${l.p_price}" />
			</td>
			<td width="120"  style="vertical-align: middle;">
				<b><fmt:formatNumber type="currency"  value="${l.c_quantity * l.p_price}" /></b>
			</td>
			<td width="80"  style="vertical-align: middle;">
				<fmt:formatNumber type="currency"  value="${l.p_mileagerate * l.c_quantity * l.p_price}" /> </td>
			<td  width="80"  style="vertical-align: middle;">
				<!-- 주문 삭제버튼 -->
				<input type="button" value="삭제" onclick="JavaScript:cartDelete('${l.c_num}')"  class="btn btn-danger btn-xs">
			</td>
		</tr>
		<input type="hidden" name="c_num" value="${l.c_num }">
		<input type="hidden" name="m_id" value="memid">
		<input type="hidden" name="c_pcode" value="${l.c_pcode}">
		<!-- 주문 총금액 표기 -->		
		<c:set value="${total + (l.c_quantity * l.p_price)}" var="total"/>
	</c:forEach>

</table>
</form>
		
<br><br>
<!-- 상품 주문하기 및 전체금액 표기 -->
<table>
	<tr>
		<td><br>결제예정 총 금액  : ${total}<br><br></td>
	</tr>
</table>
<div class="div">
 <input type="button" onclick="javasctipt:selectOrder()" value="선택상품 주문" class="btn btn-info btn-sm">
 <input type="button" onclick="javasctipt:selectDelete()" value="선택상품 삭제" class="btn btn-danger btn-sm">
</div>
<br><br><br><br>
<!-- 상품 주문하기 및 전체금액 표기 끝-->

	

</div>
<%@include file="/main/inc/main_bottom.inc"%>

</div>
</body>
</html>