<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartDao"  class="cart.CartDao"/>
<jsp:useBean id="cartDto"  class="cart.CartDto"/>
<jsp:setProperty property="*" name="cartDto"/> 
<%
request.setCharacterEncoding("utf-8"); 
String c_id = (String)session.getAttribute("idKey");
int c_quantity = Integer.parseInt(request.getParameter("c_quantity"));

//boolean b = cartDao.updateCart(cartDto);
if(c_id==null){
	response.sendRedirect("/Camp_Project/member/member_login.jsp");
}else{
	cartDto.setC_id(c_id);
	cartDto.setC_quantity(c_quantity);
	cartDao.updateCart(cartDto);
	response.sendRedirect("/Camp_Project/cart/cart.jsp");
}
%>