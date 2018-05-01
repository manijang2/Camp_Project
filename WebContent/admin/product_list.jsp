<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.ProductDto"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="productDao" class="product.ProductDao" />
<jsp:useBean id="productDto" class="product.ProductDto" />


<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Camp_Project/css/admin_style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/Camp_Project/js/admin_script.js"></script>
<script src="../js/script_product.js"></script>
<link href="../css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/admin/inc/admin_menu.inc"%>  
<div id="main">
<%@include file="/admin/inc/p_update_admin.jsp"%>
</div>
</body>
</html>