<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀입력</title>
</head>
<body>
<div id="main">
<%@include file="/main/inc/main_top.inc"%>
<div class="div">
<br><br>
<div class="innerdiv">
<h2>상품관리</h2>
<hr>
<br><Br><br>
<form name="productForm">
<table class="table table-striped table-hover member_table">
	<tr>
		<td colspan="14" style="text-align: right; background-color:white">
			<input type="button" value="상품등록" onclick="location.href='../product/p_update_admin.jsp?newInput=y'" class="btn btn-primary"/> 
		</td>
	</tr>
	<tr class="align_Center active">
		<th class="th1"><input name="allCheck" type="checkbox" onclick="javascript:selectCheckBoxAll()"/></th>
		<th class="th2">코드</th>
		<th class="th3">상품명</th>
		<th class="th4">상품가격</th>
		<th class="th4">브랜드</th>
		<th class="th4">원산지</th>
		<th class="th2">재고량</th>
		<th class="th2">판매량</th>
		<th class="th2">적립율</th>
		<th class="th4">배송료</th>
		<th class="th4">등록일</th>
		<th class="th4">카테고리</th>
		<th class="th5">수정취소삭제</th>
		<th class="th5">이미지편집</th>
	</tr>
	<c:set var="list" value="<%=(ArrayList<ProductDto>)productDao.productAll()%>"></c:set>
	<c:if test="${empty list}">
		<tr>
			<td colspan="4">등록된 상품이 없습니다.</td>
		</tr>
	</c:if>	
	<c:forEach var="p" items="${list}" varStatus="s">
	<jsp:include page="/admin/inc/p_updateDetail_admin.jsp?p_code=${p.p_code}"></jsp:include>
	</c:forEach>
	<c:set var="n" value="${param.newInput}"/>
	<c:if  test="${!empty n}">
		<jsp:include page="/admin/inc/p_register_admin.jsp"></jsp:include>
	</c:if>
	<tr>	
		<td colspan="5">
			<input type="button" value="취소" onclick="location.href='../admin/admin_productMgr.jsp'"  class="btn btn-danger"/> &nbsp;&nbsp;
			<input type="button" value="삭제" onclick="javascript:confirmDelete()" class="btn btn-danger"/> &nbsp;&nbsp;
			<input type="button" value="상품등록" onclick="location.href='../product/p_update_admin.jsp?newInput=y'" class="btn btn-primary"/> 
		</td>
	</tr>
</table>
</form>
</div>
<br><br><br><br>
</div>
<%@include file="/main/inc/main_bottom.inc"%>
</div>
</body>
</html>