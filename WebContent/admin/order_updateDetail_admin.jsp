<%@ page import="order.db.OrderDto"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="adminDao" class="admin.db.AdminDao" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀입력</title>
<script src="../js/script_order.js"></script>
<link href="../css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<c:set var="o" value="<%=(OrderDto)adminDao.selectOrder((String)request.getParameter(\"o_num\"))%>"/>
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
</body>
</html>