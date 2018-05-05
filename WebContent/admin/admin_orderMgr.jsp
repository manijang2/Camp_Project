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
	<c:set var="orderNums" value="${orderNums }"></c:set>
	<c:if test="${empty orderNums}">
		<tr>
			<td colspan="14">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>	
	<c:forEach var="num" items="${list}" >

	<c:set var="o" value="${num }"/>
<form name="oForm${o.o_num}" id="oForm${o.o_num}" action="../order/order_updateOk_admin.jsp" method="post">
<table class="table table-striped table-hover member_table">	
	<tr>
	 	<td class="td1 align_Center" rowspan="2"><input type="checkbox" name="check" value="${o.o_num}"/></td>	
		<td class="td2"><input type="text" name="o_num" value="${o.o_num}" size="40" class="form-control input-sm" readonly/></td>
		<td class="td3"><input type="text" name="o_pcode" value="${o.o_pcode}" size="40" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_name" value="${o.p_name}" size="50" class="form-control input-sm" readonly/></td>
		<td class="td4"><input type="text" name="p_price" value="${o.p_price}" size="40" class="form-control input-sm"readonly/></td>
		<td class="td4"><input type="text" name="o_quantity" value="${o.o_quantity}" size="25" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="o_usemileage" value="${o.o_usemileage}" size="40" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="o_shippingfee" value="${o.o_shippingfee}" size="40" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="o_pay" value="${o.o_pay}" size="40" class="form-control input-sm"/></td>
		<td class="td5" rowspan="2">
			<input type="button" value="수정" onclick="javascript:confirmUpdateEach('oForm${o.o_num}')" class="btn btn-info btn-xs" />
			<input type="reset" value="취소" class="btn btn-primary btn-xs" />
			<input type="button" value="삭제" onclick="javascript:confirmDeleteEach('${o.o_num}')" class="btn btn-danger btn-xs"/>
		</td>
	</tr>
	<tr>
		<td class="td4"><select class="select form-control input-sm" name="o_status" class="form-control input-sm">
								<option value="${o.o_status}">${o.os_status}</option>
								<option value="10">결제전</option>
								<option value="20">결제완료</option>
								<option value="30">배송준비</option>
								<option value="40">배송중</option>
								<option value="50">배송완료</option>
								<option value="100">주문취소</option>
								<option value="200">반품신청</option>
								<option value="300">반품완료</option>
						</select> 
		</td>
		<td class="td4"><input type="text" name="o_date" value="${fn:substring(o.o_date,0,10)}" size="60" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_id" value="${o.o_id}"  size="40" class="form-control input-sm"/></td>		
		<td class="td4"><input type="text" name="o_mname" value="${o.o_mname}"  size="50" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_phone" value="${o.o_phone}"  size="50" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_zipcode" value="${o.o_zipcode}"  size="60" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_address" value="${o.o_address}"  size=50 class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_message" value="${o.o_message}"  size="30" class="form-control input-sm"/></td>

	</tr>
</table>
</form>

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