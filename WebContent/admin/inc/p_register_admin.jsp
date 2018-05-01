<%@ page import="product.ProductDto"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="productDao" class="product.ProductDao" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀입력</title>
<script src="../js/script_product.js"></script>
<link href="../css/style.css" rel="stylesheet" type="text/css"></head>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("newName").focus(); 
}
</script>
<body>
<form name="pForm" id="newForm" action="../product/p_registerOk_admin.jsp" method="post">
<table class="table table-striped table-hover member_table">	
	<tr>
		<% int newCode = 4; //productDao.getNewCode(); %>
		<td class="td1 align_Center"><input type="checkbox" name="check"/></td>
		<td class="td2"><input type="text" name="p_code" value="<%=newCode%>" size="15" class="form-control input-sm" readonly/></td>
		<td class="td3"><input type="text" id="newName" name="p_name" size="60" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_price" size="35" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_brand" size="40" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_origin" size="30" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="p_stock" size="25" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="p_sales" size="25" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="p_mileagerate" size="25" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_shippingfee" size="35" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_date" size="35" class="form-control input-sm"/></td>		
		<td class="td4"><input type="text" name="p_cnum" size="25" class="form-control input-sm"/></td>
		<td class="td5">
			<input type="button" value="등록" id="btnRSubmit" onclick="checkRegister()" class="btn btn-info btn-xs" />
			<input type="reset" value="리셋" class="btn btn-primary btn-xs" />
			<input type="button" value="삭제" onclick="../product/p_update_admin.jsp" class="btn btn-danger btn-xs"/>
		</td>
		<td class="td5">	
			<input type="button" value="이미지 편집" onclick="#" class="btn btn-info btn-xs"/>
			<input type="hidden" name="p_image1" />
			<input type="hidden" name="p_image2" />
			<input type="hidden" name="p_image3" />
			<input type="hidden" name="p_image4" />
			<input type="hidden" name="p_info" />	
		</td>
	</tr>	
</table>
</form>
</body>
</html>