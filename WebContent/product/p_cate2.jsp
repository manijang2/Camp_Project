<%@ page import="product.db.ProductDto" import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productDao" class="product.db.ProductDao" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String ct_name1  = request.getParameter("ct_name1");
String ct_name2  = request.getParameter("ct_name2");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소분류 카테고리 보기</title>
<script type="text/javascript" src="/Camp_Project/js/main_script.js"></script>
<link href="/Camp_Project/css/main_style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="/main/inc/main_top.inc" %>

<% ArrayList<ProductDto> list = (ArrayList)productDao.cate2Data(ct_name2); %>

<!-- 상단 카테고리 루트 이동 -->
<br><Br><br>
<div class="leftdiv">
<a href = "/Camp_Project/p_list.do">전체상품</a> >
<a href="/Camp_Project/p_cate1.do?ct_name1=<%=ct_name1 %>"><%=ct_name1 %></a> > 
<a href="/Camp_Project/p_cate2.do?ct_name1=<%=ct_name1 %>&ct_name2=<%=ct_name2 %>"><%=ct_name2 %></a>
<br>
</div>
<div class="div">
<!-- 메인 상품 출력 -->
<br><Br><br>
<c:set value="<%=list %>" var="list"/>
<c:forEach var="l" items="${list}" varStatus="status">
	<!-- 상품 롤 오버 스크립트 -->
		<figure class="snip1384">
			<img src="/Camp_Project/image/product/${l.p_image1}"/>
			<figcaption>
				<h3>${l.p_name}</h3>
				<p><fmt:formatNumber value="${l.p_price}" type="currency" currencySymbol="&#92;" /></p>
				<c:if test="${! empty l.p_brand}">
						<p>브랜드 : ${l.p_brand}</p>
				</c:if>
				<c:if test="${! empty l.p_origin}">
						<p>원산지 : ${l.p_origin}</p>
				</c:if>
			
			</figcaption>
			<a href="/Camp_Project/p_read.do?p_code=${l.p_code}"></a>
		</figure>
</c:forEach>
<br><Br><br>
</div>
<%@include file="/main/inc/main_bottom.inc"%>

</body>
</html>