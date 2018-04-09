<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/css_admin.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/script_admin.js"></script>
</head>
<body>
<%@include file="inc/admin_menu.inc"%>  
<div id="main">
<div>
<%@include file="/member/inc/member_list.inc"%> 
</div>
</body>
</html>