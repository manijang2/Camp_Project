<%@page import="cart.CartDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="orderDao" class="order.OrderDao" scope="session"/>
<jsp:useBean id="orderDto" class="order.OrderDto" />
<jsp:setProperty property="*" name="orderDto"/>
<jsp:useBean id="cartDao" class="cart.CartDao" scope="session"/>
<jsp:useBean id="cartDto" class="cart.CartDto" />
<jsp:setProperty property="*" name="cartDto"/>
<%
String c_id = (String)session.getAttribute("idKey");
int o_num =  Integer.parseInt(request.getParameter("c_num"));
int c_quantity = Integer.parseInt(request.getParameter("c_quantity"));
int o_pcode =  Integer.parseInt(request.getParameter("p_code"));
int o_pay = Integer.parseInt(request.getParameter("result_pay"));
int o_shippingfee = Integer.parseInt(request.getParameter("p_shippingfee")); 
int o_usemileage = Integer.parseInt(request.getParameter("o_usemileage"));
String p_image1 = request.getParameter("p_image1");
String o_mname = request.getParameter("m_name");
String o_phone = request.getParameter("m_phone");
String o_zipcode = request.getParameter("m_zipcode");
String o_address = request.getParameter("m_address");
String o_message = request.getParameter("o_message");


if(c_id==null){
	response.sendRedirect("/Camp_Project/member/member_login.jsp");
}else{

 	orderDto.setO_num(o_num);
	orderDto.setO_pcode(o_pcode);
	orderDto.setO_quantity(c_quantity);
	orderDto.setO_id(c_id);
	orderDto.setO_usemileage(o_usemileage);
	orderDto.setO_mname(o_mname);
	orderDto.setO_phone(o_phone);
	orderDto.setO_zipcode(o_zipcode);
	orderDto.setO_address(o_address);
	orderDto.setO_pay(o_pay);
	orderDto.setO_shippingfee(o_shippingfee);
	orderDto.setO_message(o_message);
	orderDto.setP_image1(p_image1);
	
	
 	orderDao.updateMileage(orderDto);
 	orderDao.updateProduct(orderDto);
 	orderDao.orderOne(orderDto);
 	cartDao.deleteCart(o_num);  
 	
	response.sendRedirect("/Camp_Project/order/order.jsp");
}
%>