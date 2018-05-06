<%@page import="cart.CartDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<jsp:useBean id="cartDao" class="cart.CartDao"/>
<jsp:useBean id="cartDto" class="cart.CartDto"/>
<jsp:useBean id="orderDto" class="order.OrderDto"/>
<jsp:useBean id="orderDao" class="order.OrderDao"/>
<jsp:setProperty property="*" name="cartDto"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% 
String c_id = (String)session.getAttribute("idKey");
int c_pcode = Integer.parseInt(request.getParameter("c_pcode"));
int quantity = Integer.parseInt(request.getParameter("quantity"));

// 바로 구매 시 장바구니 담기
if(c_id==null){
	response.sendRedirect("/Camp_Project/member/member_login.jsp");
}else{
	cartDto.setC_id(c_id);
	cartDto.setC_pcode(c_pcode);
	cartDto.setC_quantity(quantity);
	cartDao.insertCart(cartDto);
}

CartDto dto = cartDao.orderOneRead(c_id);
CartDto mdto = cartDao.selectMember(c_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 주문하기</title>

<script type="text/javascript" src="/Camp_Project/js/main_script.js"></script>
<script type="text/javascript" src="/Camp_Project/js/member_script.js"></script>
<link href="/Camp_Project/css/main_style.css" rel="stylesheet" type="text/css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


</head>
<body>
<%@include file="/main/inc/main_top.inc" %>

<br><br>

<div class="div">
<!-- 주문상품 표기  -->
<br><br>
<form method="post" name="form" action="order_proc.jsp">
<table>
	<tr class="success center" style="background-color: #2c3e50">
		<th style="width: 100px;padding: 13px;">이미지</th>
		<th>주문 상품</th>
		<th style="width: 60px;">수량</th>
		<th style="width: 100px;">단가</th>
		<th style="width: 100px;">소계</th>
	</tr>
	
 	<c:set value="<%=dto %>" var="l" />
	<tr>
		<td style="text-align: center;padding: 13px"><img src="/Camp_Project/image/product/${l.p_image1 }" width="100"></td>
		<td><a href="/Camp_Project/product/p_read.jsp?p_code=${l.c_pcode}">${l.p_name}<input type="hidden" name="p_code" value="${l.c_pcode }"></a></td>
		<td style="text-align: center;"><input type="hidden" name="c_quantity" value="${l.c_quantity }">${l.c_quantity }</td>
		<td style="text-align: center;"><fmt:formatNumber value="${l.p_price}" type="currency" /></td>
		<td style="text-align: center;"><fmt:formatNumber value="${l.p_price * l.c_quantity}" type="currency" /></td>
	</tr>
	<c:set value="금액+payCount" var="payCount(총금액)" />	

</table>
<br><br><br><br>
<!-- 결제금액 표기 -->
<table>
	<tr>
		<td style="width: 30x;"></td>
		<td style="width: 120px; text-align: center;"><h3>상품금액</h3></td>
		<td style="width: 30x; text-align: center;"><img src="/Camp_Project/image/orderImage/dommy.png"></td>
		<td style="width: 120px; text-align: center;"><h3>배송비</h3></td>
		<td style="width: 30x; text-align: center;"><img src="/Camp_Project/image/orderImage/dommy.png"></td>
		<td style="width: 120px; text-align: center;"><h3>마일리지</h3></td>
		<td style="width: 30x; text-align: center;"><img src="/Camp_Project/image/orderImage/dommy.png"></td>
		<td style="width: 220px; text-align: center;"><h3>결제 예상 금액</h3></td>
		<td rowspan="2" style="text-align: center;">
			현재 사용가능한 마일리지 (<fmt:formatNumber value="${l.m_mileage}" type="currency" />)
			<br>
			<input type="text" name="o_usemileage"  id="u_mil" size="10" value="0">
			<input type="button" name="use_mil_Btn" onclick="javascript:usemilige()" value="적용">
			<c:set value="o_usemileage" var="um"/>
		</td>
	</tr>
	<tr>
		<td style="width: 30x;"><img src="/Camp_Project/image/orderImage/dommy.png"></td>
		<td style="width: 100px; text-align: center;">
			<h3><fmt:formatNumber value="${l.p_price * l.c_quantity}" type="currency"  /></h3></td>
		<td style="width: 30x; text-align: center;">
			<img src="/Camp_Project/image/orderImage/sign1.png">
		</td>
		<td style="width: 100px; text-align: center;">
			<h3><fmt:formatNumber value="${l.p_shippingfee}" type="currency" /></h3>
		</td>
		<td style="width: 30x; text-align: center;">
			<img src="/Camp_Project/image/orderImage/sign2.png">
		</td>
		<td style="width: 100px; text-align: center;">
			<c:set value="o_usemileage" var="1" />
			<h3><div id="use_mi">0</div></h3>
		</td>
		<td style="width: 30x; text-align: center;"><img src="/Camp_Project/image/orderImage/sign3.png"></td>
		<td style="width: 200px; text-align: center;">
			<input type="hidden" name="c_mil"  id="c_mil" value="${l.m_mileage}">
			<input type="hidden" id="order_pay" value="${l.p_price * l.c_quantity + l.p_shippingfee}">
			<input type="hidden" id="result_pay" name="result_pay">
			<input type="hidden" id="p_shippingfee" name="p_shippingfee" value="${l.p_shippingfee}">
			<h3><div id="use_mi2"><fmt:formatNumber value="${(l.p_price * l.c_quantity)+ l.p_shippingfee}" type="currency" /></div>
			</h3>
		</td>
	</tr>
</table>

 <br><br><br><br>
<!-- 수령지 정보, 결제버튼 테이블 -->
<c:set value="<%=mdto %>" var="m" />
<table class="table table-striped table-hover innerdiv2" style="height: 300px;" >
	<tr>
		<td class="success center" colspan="3" style="padding: 13px;background-color: #2c3e50"> <b>상품 주문하기</b></td>
	</tr>
	<tr>
		<td style="width: 200x;" class="center" >수령인</td>
		<td class="left px200"><input type="text" name="m_name" value="${m.m_name}" class="form-control input-sm" ></td>
		<td></td>
	</tr>
	<tr>
		<td class="center" style="vertical-align: middle; ">휴대폰</td>
		<td class="left px200"><input type="text" name="m_phone" value="${m.m_phone}" class="form-control input-sm" ></td>
		<td class="left"></td>
	</tr>
	<tr>
				
		
		<td class="center" style="vertical-align: middle;">우편번호</td>
		<td  class="left px200">
			<input type="text" name="m_zipcode" id="postcode" placeholder="우편번호" size="7" readonly class="form-control input-sm" value="${m.m_zipcode}">
		</td>
		<td class="left ">
			<input type="button" value="우편번호찾기" id="btnZip" onclick="javascript:execDaumPostcode()" class="btn btn-default btn-sm"> (우편번호 찾기 후 상세주소를 입력해주세요)
		</td>
	</tr>
	<tr>
		<td class="center" style="vertical-align: middle;" >수령지</td>
		<td class="left" colspan="2">
			<input type="text" name="m_address" id="roadAddress" placeholder="우편번호 찾기를 눌러주세요" size="60" onclick="javascript:checkAddress()" class="form-control input-sm"  value="${m.m_address}" ><span id="guide" style="color: #999"></span>
		</td>
	</tr>
	<tr>
		<td class="center">배송메세지</td>
		<td class="left px200" colspan="2"><input type="text" name="o_message" placeholder="부재 시 경비실에 맡겨주세요" class="form-control input-sm" ></td>
	</tr>
	<tr>
		<td style="text-align: center;" colspan="3"> <br>
			<input type="button" value="결제하기" class="btn btn-primary btn-sm" onclick="oneOrder()">
			<input type="button" value="장바구니 확인" onclick="JavaScript:location.href='/Camp_Project/cart/cart.jsp'" class="btn btn-default btn-sm">
			<input type="button" value="홈 으로 이동" onclick="JavaScript:location.href='/Camp_Project/index.jsp'" class="btn btn-default btn-sm">
			<input type="hidden" value="${l.c_num }" name="c_num"><br><br>
	    </td>
	</tr>
</table>


</form>
		
</div>
<%@include file="/main/inc/main_bottom.inc"%>




</body>
</html>