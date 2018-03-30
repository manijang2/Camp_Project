<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="product.db.ProductDto" %>

<jsp:useBean id="productDao" class="product.db.ProductDao" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/main/inc/main_top.inc" %>
<%
//String memid = (String)session.getAttribute("idKey");
String adkey = (String)session.getAttribute("gradeKey");

int code = Integer.parseInt(request.getParameter("p_code"));
ProductDto dto = productDao.selectProductData(code);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세정보 확인</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script src="../js/script_product.js"></script>
<script src="../js/script.js"></script>
<link href="../css/style.css" rel="stylesheet" type="text/css">

</head>
<body>


<c:set value="<%=dto %>" var="list"/>
<br><br><br>
<div class="leftdiv">
<a href = "p_list.jsp">전체상품</a> > <a href="p_cate1.jsp?ct_name1=${list.ct_name1}">${list.ct_name1}</a> > 
<!-- 카테고리 2가 없을경우 하이드 -->
<c:choose>
	<c:when test="${empty list.ct_name2 }">
	</c:when>
	<c:otherwise>
		<a href="p_cate2.jsp?ct_name1=${list.ct_name1 }&ct_name2=${list.ct_name2 }">${list.ct_name2 }</a>
	</c:otherwise>
</c:choose> 
</div>
<br><br><br>
<div class="div">
<form method="post" name="form" id="frm">

<!-- 관리자 확인 -->



<!-- 상품 상단정보 시작 -->
<table class="product_main">
	<tr>
		<td width="50%"><img src="image/${list.p_image1}" width="500"> 
		</td>
		<td>
			<table class="table table-striped table-hover">
				<tr>
					<td>상품명</td>
					<td class="left">${list.p_name}</td>
				</tr>
				<tr>
					<td width="30%">상품번호</td>
					<td class="left" >${list.p_code}</td>
				</tr>
				<tr>
					<td>상품가격</td>
					<td class="left"><fmt:formatNumber type="currency" value="${list.p_price }" /></td>
				</tr>
				<tr>
					<td>마일리지 적립</td>
					<td class="left"><fmt:formatNumber type="currency" value="${list.p_price *list.p_mileagerate}" /></td>
				</tr>
				<tr>
					<td style="vertical-align : middle;">수량
					</td>
					<td class="left"> 
					<!-- 상품 수량 체크부분 -->
						<table style="width: 0px ;text-align: center; border: 0px;">
							<tr>
						       <td rowspan="2" align="right">
									<input type="text" name="quantity" value="1" size="3" style="text-align:center"> 
									<input type="hidden" name="c_pcode" value="${list.p_code}">
							    </td>
						        <td width="20"><a href="JavaScript:CountChange('up');"><img src="../image/up.jpg" width="11" height="9" border="0"></a><br><a href="JavaScript:CountChange('down');"><img src="../image/dw.jpg" width="11" height="9" border="0"></a></td>
						     </tr>
						</table>
					<!-- 상품 수량 체크부분끝 -->
					</td>
				<tr>
					<td>브랜드</td>
					<td class="left">${list.p_brand}</td>
				</tr>
				<tr>
					<td>원산지</td>
					<td class="left">${list.p_origin }</td>
				</tr>
			</table>
			<br>
			<!-- 버튼모음 -->
			<input type="button" name="submitOrder" onclick="goOrder()" value="바로 결제하기" class="btn btn-primary btn-sm" >
			<input type="button" name="submitCart" onclick="goCart()" value="장바구니 추가" class="btn btn-default btn-sm" >
		</td>
	</tr>
</table>
</form>
<!-- 상품 상단정보 끝 -->

<!-- 상품 상세정보 시작 -->
<br><br>
<table>
	<tr>
		<td style="height: 20px; text-align: center;"></td>
	</tr>
	
	<tr>
		<td style="text-align: center; vertical-align: top">${list.p_info}</td>
	</tr>
</table>
<!-- 상품 상세정보 끝 -->

	 <table class="table table-striped table-hover notice_table">
		<tr class="active success">
			<th class="th1 rad_left" style="padding: 13px;background-color: #2c3e50">
			<a href="../notice/boardnoticelist.jsp">공지사항</a></th>
			<th class="th2 " style="padding: 13px;background-color: #2c3e50">
			<a href="../board/BoardQna.jsp">상품Q/A</a></th>
			<th class="th3 rad_right" style="padding: 13px;background-color: #2c3e50">
			<a href="javascript:startXhr(${list.p_code })" id="review">상품후기</a></th>
		</tr>
		</table>
		
		
		<div id="showProduct">	
		</div>
</div>
<%@include file="/main/inc/main_bottom.inc"%>

</body>
</html>