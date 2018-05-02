<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="/Camp_Project/main.do">홈으로</a>
  <a href="/Camp_Project/admin/memberList.do">회원관리</a>	
  		<a href="/Camp_Project/admin/member_register.do" class="subMenu">회원등록</a> 
		<a href="/Camp_Project/admin/memberList.do" class="subMenu">회원수정/삭제</a> 	
  <a href="/Camp_Project/admin/productList.do">상품관리</a>
  		<a href="admin_productReg.jsp" class="subMenu">상품등록</a> 
		<a href="admin_productMgr.jsp" class="subMenu">상품수정/삭제</a> 
  <a href="admin_orderMgr.jsp">주문관리</a>
  		<a href="admin_orderReg.jsp" class="subMenu">주문등록</a>  
		<a href="admin_orderMgr.jsp" class="subMenu">주문수정/삭제</a>
		<a href="admin_orderGraph.jsp" class="subMenu">매출통계</a>
  <a href="/member/member_logout.jsp">로그아웃</a>
</div>