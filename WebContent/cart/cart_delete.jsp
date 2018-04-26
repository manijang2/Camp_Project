<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartDao"  class="cart.CartDao"/>
<%
request.setCharacterEncoding("utf-8");
int c_num = Integer.parseInt(request.getParameter("c_num"));

cartDao.deleteCart(c_num);
%>
	 <script>
		location.href="/Camp_Project/cart/cart.jsp";
	</script> 
