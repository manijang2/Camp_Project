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
<title>베스트 상품 모음전</title>
<script src="/web_project/js/script.js"></script>
<link href="/web_project/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/main/inc/main_top.inc" %>
<div class="div">

  	<br><br>
 	<h1>베스트 상품 모음전</h1><hr>
 	<h3>고객님들께 사랑받는 <b>킬러 아이템 대방출!!</b> 품절되기 전에 구매하세요!!</h3>
 	
 		<br><br>
<table class="product_main">
	<tr>
	<% ArrayList<ProductDto> list = (ArrayList)productDao.productBest(); %>
	<c:set value="<%=list %>" var="list"/>
	<c:forEach var="l" items="${list}" varStatus="status">
		<td>
			<figure class="snip1384">
				  <img src="/Camp_Project/image/product/${l.p_image1}"/>
				  <figcaption>
				    <h3>${l.p_name}</h3>
				    <p><fmt:formatNumber value="${l.p_price}" type="currency" currencySymbol="&#92; " /></p><br>
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