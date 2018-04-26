<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="cartDao" class="cart.CartDao"/>
<c:set var="c_num" value="${param.c_num}"/>

<c:choose>
	<c:when test="${empty num}">
		<c:set var="numlist" value="${paramValues.check}"/>
		<c:if test="${empty numlist}"> 
			<c:redirect url="/Camp_Project/cart/cart.jsp"/> 
		</c:if>
	</c:when>
	<c:otherwise>
		<c:set var="numlist" value="<%=new String[]{(String)pageContext.getAttribute(\"c_num\")}%>"/>
	</c:otherwise>
</c:choose>

<c:if test="<%=cartDao.selectDelete((String[])pageContext.getAttribute(\"numlist\"))%>" var="isDeleted" />
<c:out value="${isDeleted}"/>
<c:if test="${isDeleted}">
	<c:redirect url="/Camp_Project/cart/cart.jsp"/> 
</c:if>
<c:if test="${not isDeleted}">
	<script>
		alert("삭제실패");
	</script>
	<c:redirect url="/Camp_Project/cart/cart.jsp"/> 
</c:if>