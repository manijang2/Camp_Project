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
<script src="/Camp_Project/js/product_script.js"></script>
<link href="/Camp_Project/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="pForm" action="../product/p_updateOk_admin.jsp" method="post">
<table class="table table-striped table-hover member_table">
	<c:set var="p" value="<%= new ProductDto()/*(ProductDto)productDao.selectProductByCode(Integer.parseInt(request.getParameter(\"p_code\"))) */%>"/>	
	<tr>
		<td class="td1 align_Center"><input type="checkbox" name="check" value="${p.p_code}"/></td>
		<td class="td2"><input type="text" name="p_code" value="${p.p_code}" size="15" class="form-control input-sm" readonly/></td>
		<td class="td3"><input type="text" name="p_name" value="${p.p_name}" size="60" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_price" value="${p.p_price}" size="35" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_brand" value="${p.p_brand}" size="40" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_origin" value="${p.p_origin}" size="30" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="p_stock" value="${p.p_stock}" size="25" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="p_sales" value="${p.p_sales}" size="25" class="form-control input-sm"/></td>
		<td class="th2"><input type="text" name="p_mileagerate" value="${p.p_mileagerate}" size="25" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_shippingfee" value="${p.p_shippingfee}" size="35" class="form-control input-sm"/></td>
		<td class="td4"><input type="text" name="p_date" value="${fn:substring(p.p_date,0,10)}"  size="35" class="form-control input-sm"/></td>		
		<td class="td4"><input type="text" name="p_cnum" value="${p.p_cnum}"  size="25" class="form-control input-sm"/></td>
		<td class="td5">
			<input type="submit" value="수정" class="btn btn-info btn-xs" />
			<input type="reset" value="취소" class="btn btn-primary btn-xs" />
			<input type="button" value="삭제" onclick="javascript:confirmDeleteEach('${p.p_code}')" class="btn btn-danger btn-xs"/>
		</td>
		<td class="td5">	
			<input type="button" value="이미지 편집" class="btn btn-info btn-xs" onclick="javascript:editImages('${p.p_code}')" />
			<input type="hidden" name="p_image1" value="${p.p_image1}"/>
			<input type="hidden" name="p_image2" value="${p.p_image2}"/>
			<input type="hidden" name="p_image3" value="${p.p_image3}"/>
			<input type="hidden" name="p_image4" value="${p.p_image4}"/>
			<%-- <input type="hidden" name="p_info" value="${p.p_info}" > --%>	
		</td>
	</tr>	
</table>
</form>
</body>
</html>