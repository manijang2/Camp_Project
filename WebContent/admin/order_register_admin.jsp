<%@ page import="product.ProductDto"%>
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
<link href="../css/style.css" rel="stylesheet" type="text/css"></head>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("newName").focus(); 
}
</script>
<body>
<form name="pForm" id="newForm" action="../order/order_registerOk_admin.jsp" method="post">
<table class="table table-striped table-hover member_table">	
	<% int newNum = adminDao.getNewOrderNum();%>
	<tr>
	 	<td class="td1 align_Center" rowspan="2"><input type="checkbox" name="check" value="${o.o_num}"/></td>	
		<td class="td2"><input type="text" name="o_num" value="<%=newNum%>" size="10" class="form-control input-sm" readonly/></td>
		<td class="td3"><input type="text" id="newName" name="o_pcode" size="10" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_name" size="50" class="form-control input-sm" readonly/></td>
		<td class="td4"><input type="text" name="p_price" size="40" class="form-control input-sm"readonly/></td>
		<td class="td4"><input type="text" name="o_quantity" size="25" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="o_usemileage" size="40" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="o_shippingfee" size="40" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="o_pay" size="40" class="form-control input-sm"/></td>
		<td class="td5" rowspan="2">
			<input type="button" value="등록" id="btnRSubmit" onclick="checkRegister()" class="btn btn-info btn-xs" />
			<input type="reset" value="리셋" class="btn btn-primary btn-xs" />
			<input type="button" value="삭제" onclick="../order/order_update_admin.jsp" class="btn btn-danger btn-xs"/>
		</td>
	</tr>
	<tr>
		<td class="td4"><input type="text" name="os_status" size="40" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_date" size="60" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_id" size="40" class="form-control input-sm"/></td>		
		<td class="td4"><input type="text" name="o_mname" size="50" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_phone" size="50" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_zipcode" size="60" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_address" size=50 class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="o_message" size="30" class="form-control input-sm"/></td>
	</tr>	
</table>
</form>
</body>
</html>