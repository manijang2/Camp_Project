<%@page import="product.db.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productDao" class="product.db.ProductDao" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>신상품 모음전</title>
<script type="text/javascript" src="/Camp_Project/js/main_script.js"></script>
<link href="/Camp_Project/css/main_style.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/main/inc/main_top.inc" %>
<div class="div">

 	<br><br>
 	<h1>신상품 모음전</h1><hr>
 	<h3>따끈따끈한 <b>신상 입고</b> </h3>
 	<br><br>
<table class="product_main">
	<tr>
	<% ArrayList<ProductDto> list = (ArrayList)productDao.selectNewProduct(); %>
	<c:set value="<%=list %>" var="list"/>
	<c:forEach var="l" items="${list}" varStatus="status">
		<td>
			<figure class="snip1384">
				  <img src="/Camp_Project/image/product/${l.p_image1}"/>
				  <figcaption>
				    <h3>${l.p_name}</h3>
				    <p><fmt:formatNumber value="${l.p_price}" type="currency" /></p><br>
					<c:if test="${! empty l.p_brand}">
							<p>브랜드 : ${l.p_brand}</p>
					</c:if>
					<c:if test="${! empty l.p_origin}">
							<p>원산지 : ${l.p_origin}</p>
					</c:if>
				  </figcaption>
				  <a href="/Camp_Project/p_read.do?p_code=${l.p_code}"></a>
			</figure>
		</td>
	 <c:if test="${status.count%4=='0'}">
	 </tr><tr>
     </c:if>
	</c:forEach>
	</tr>
</table>
<br><Br><br>

</div>
<%@include file="/main/inc/main_bottom.inc"%>

</body>
</html>