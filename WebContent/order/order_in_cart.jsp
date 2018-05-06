<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartDao" class="cart.CartDao" scope="session"/>
<jsp:useBean id="cartDto" class="cart.CartDto" /> 
<jsp:setProperty property="*" name="cartDto"/>
<%
String c_id = (String)session.getAttribute("idKey");
int c_pcode = Integer.parseInt(request.getParameter("p_code"));
int quantity = Integer.parseInt(request.getParameter("quantity"));


if(c_id==null){
	response.sendRedirect("/Camp_Project/member/member_login.jsp");
}else{
	cartDto.setC_id(c_id);
	cartDto.setC_pcode(c_pcode);
	cartDto.setC_quantity(quantity);
	
	cartDao.insertCart(cartDto);
	response.sendRedirect("order_proc.jsp");
}
%>