<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.ProductDto"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Camp_Project/css/admin_style.css" rel="stylesheet" type="text/css">
<link href="/Camp_Project/css/style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/Camp_Project/js/admin_script.js"></script>
<script src="/Camp_Project/js/product_script.js"></script>

</head>
<body>
<%@include file="/admin/inc/admin_menu.inc"%>  
<div id="main">
<%@include file="/main/inc/main_top.inc"%>
<div class="div">
<br><br>
<div class="innerdiv">
<h2>상품관리</h2>
<hr>
<br><Br><br>
<form name="productForm">
<table class="table table-striped table-hover member_table">
	<tr>
		<td colspan="14" style="text-align: right; background-color:white">
			<input type="button" value="상품등록" onclick="location.href='../product/p_update_admin.jsp?newInput=y'" class="btn btn-primary"/> 
		</td>
	</tr>
	<tr class="align_Center active">
		<th class="th1"><input name="allCheck" type="checkbox" onclick="javascript:selectCheckBoxAll()"/></th>
		<th class="th2">코드</th>
		<th class="th3">상품명</th>
		<th class="th4">상품가격</th>
		<th class="th4">브랜드</th>
		<th class="th4">원산지</th>
		<th class="th2">재고량</th>
		<th class="th2">판매량</th>
		<th class="th2">적립율</th>
		<th class="th4">배송료</th>
		<th class="th4">등록일</th>
		<th class="th4">카테고리</th>
		<th class="th5">수정취소삭제</th>
		<th class="th5">이미지편집</th>
	</tr>
	<c:set var="list" value="${list}"></c:set>
	<c:if test="${empty list}">
		<tr>
			<td colspan="4">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>	
	<c:forEach var="p" items="${list}" varStatus="s">
	<form name="pForm" action="../product/p_updateOk_admin.jsp" method="post">
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
			<td class="td5"colspan = "3">
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
	</form>
	</c:forEach>
	<c:set var="n" value="${param.newInput}"/>
	<c:if  test="${!empty n}">
		<jsp:include page="/admin/inc/p_register_admin.jsp"></jsp:include>
	</c:if>
	<tr>	
		<td colspan="5">
			<input type="button" value="취소" onclick="location.href='../admin/admin_productMgr.jsp'"  class="btn btn-danger"/> &nbsp;&nbsp;
			<input type="button" value="삭제" onclick="javascript:confirmDelete()" class="btn btn-danger"/> &nbsp;&nbsp;
			<input type="button" value="상품등록" onclick="location.href='../product/p_update_admin.jsp?newInput=y'" class="btn btn-primary"/> 
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