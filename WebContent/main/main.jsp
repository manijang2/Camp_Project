<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="product.ProductDto"%>
<%@page import="java.util.*"%>
<jsp:useBean id="productDao" class="product.ProductDao" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>★★ 캠핑루트 ★★</title>

</head>
<body>
	<%@include file="/main/inc/main_top.inc"%>
	<%@include file="/main/inc/banner.inc"%>
	<div class="div">
		<br>
		<div class="div"></div>
		<br>
		<table>
			<tr>
				<td>
					<!-- 배너 리스트 -->
					<!-- 메인 상품 리스트 -->
					<table class="product_main">
						<tr>
							<c:forEach var="l" items="${list}" varStatus="status">
								<td>
									<figure class="snip1384"> <img src="/Camp_Project/image/product/${l.p_image1}" /> <figcaption>
									<h3>${l.p_name}</h3>
									<p>
										<fmt:formatNumber value="${l.p_price}" type="currency" currencySymbol="&#92; " />
									</p>
									<c:if test="${! empty l.p_brand}">
										<p>브랜드 : ${l.p_brand}</p>
									</c:if> <c:if test="${! empty l.p_origin}">
										<p>원산지 : ${l.p_origin}</p>
									</c:if> </figcaption> <a href="../product/p_read.jsp?p_code=${l.p_code}"></a> </figure>
								</td>
								<c:if test="${status.count%4=='0'}">
						</tr>
						<tr>
							</c:if>
							</c:forEach>
						</tr>
					</table>
					<!-- 메인 상품 리스트  끝-->

				</td>
				<td></td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	</div>
	<%@include file="/main/inc/main_bottom.inc"%>

</body>
</html>