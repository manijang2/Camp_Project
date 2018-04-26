<%@page import="java.util.ArrayList"%>
<%@page import="cart.CartDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartDao" class="cart.CartDao" scope="session"/>
<jsp:useBean id="cartDto" class="cart.CartDto" /> 
<jsp:setProperty property="*" name="cartDto"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String c_id = (String)session.getAttribute("idKey");
int c_pcode = Integer.parseInt(request.getParameter("c_pcode"));
int c_quantity = Integer.parseInt(request.getParameter("quantity"));
//세션에 c_id 값이 비었을경우 member_login.jsp로 이동
if(c_id==null){
	response.sendRedirect("/Camp_Project/member/member_login.jsp");
}else{
		
		cartDto.setC_id(c_id);
		cartDto.setC_pcode(c_pcode);
		cartDto.setC_quantity(c_quantity);
		cartDao.insertCart(cartDto);
		%>
		<script>
			if(confirm("장바구니에 담았습니다. \n장바구니로 이동하시겠습니까?"))
				location.href="/Camp_Project/cart/cart.jsp";
			else
				history.back();
		</script>
		<%
	}
%>

