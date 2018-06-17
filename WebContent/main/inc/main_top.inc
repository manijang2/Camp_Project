<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String memid = (String)session.getAttribute("idKey");
String gradeKey = (String)session.getAttribute("gradeKey");

String log = "";
String mem1 = "";
String mem2 = "";
String mem3 = "";
String mem4 = "";

String m[] = {"텐트/타프","침낭/매트리스/전기요","캠핑퍼니처","랜턴/조명/버너/난로","그릴/화로/오븐"};
String m1[] = {"텐트","타프","그늘막/파라솔","액세서리"};
String m2[] = {"침낭","매트리스","배게/방석","레저시트/카페트","전기요","침낭수납가방/압축쌕"};
String m3[] = {"테이블","체어","수납가구","야전침대","해먹/해먹스탠드"};
String m4[] = {"랜턴/조명","버너","난로"};
String m5[] = {"그릴","화로대","오븐","소품"};

if(memid == null){
	log = "<a href='/Camp_Project/member/member_login.jsp'>로그인</a>";
	mem1 = "<a href='/Camp_Project/member/member_register.jsp'>회원가입</a>";
}else{
	if(gradeKey != null && gradeKey.equals("admin")){
		//admin 로그인 시 관리자 메뉴 보이기
		log = "<a href='/Camp_Project/member/member_logout.jsp'>로그아웃</a>";
		mem1 = "<br><a href='/Camp_Project/admin/main.do'>&#9776;관리자 메뉴</a>";
	}else{
		log = "<a href='/Camp_Project/member/member_logout.jsp'>로그아웃</a>";
		mem2 = "<a href='/web_project/cart/cart.jsp'>장바구니</a>";
		mem3 = "<a href='/web_project/order/order.jsp'>주문조회</a>";
		mem4 = "<a href='javascript:delevery()'>배송조회</a>";
		mem1 = "<a href='/Camp_Project/member/member_update_load.jsp'>회원정보 수정</a>";
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8">
<script src="/Camp_Project/js/main_script.js"></script>
<script src="/Camp_Project/js/main_suggest.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<link href="/Camp_Project/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/Camp_Project/css/main_menutop.css" rel="stylesheet" type="text/css">
<link href="/Camp_Project/css/main_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnok").onclick = function(){
		
		location.href="../product/p_search.jsp?p_name="+frm.sel.value;
		
		if(frm.sel.value == ""){
			frm.sel.focus();
			alert("검색어를 입력하시오");
			return;
		}
	}
}
function searchData(p_name){
	if(frm.sel.value == ""){
		frm.sel.focus();
		alert("검색어를 입력하시오");
		return;
	}
	//frm.submit();
}
</script>
</head>
<body>
	<div class="div">
		<table>
			<tr>
				<TD class="left">
					<a href="/Camp_Project/main/main.jsp"><img src="/Camp_Project/image/route_logo.png"></a>
				</TD>
				<td class="right top" style="text-align: right;">
					<div>
						<nav id="topMenu">
						<ul>
							<li><%=log %></li>
							<li><%=mem1 %></li>
							<li><%=mem2 %></li>
							<li><%=mem3 %></li>
							<li><%=mem4 %></li>
						</ul>
						</nav>
						<br> <br> <br> <br>
					</div>

				</td>
			</tr>
		</table>
		<br> <br>
		<!-- 네비게이션 -->
		<nav class="navbar navbar-default" style="z-index: 10000">
		<div class="collapse navbar-collapse left" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle" role="button" aria-expanded="true" href="#" data-toggle="dropdown"> >> 전체 카테고리 << <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/Camp_Project/p_cate1.do?ct_name1=<%=m[0]%>"><%=m[0]%></a>
							<ul>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[0]%>&ct_name2=<%=m1[0]%>"><%=m1[0]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[0]%>&ct_name2=<%=m1[1]%>"><%=m1[1]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[0]%>&ct_name2=<%=m1[2]%>"><%=m1[2]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[0]%>&ct_name2=<%=m1[3]%>"><%=m1[3]%></a></li>
							</ul></li>
						<li class="divider"></li>
						<li><a href="/Camp_Project/p_cate1.do?ct_name1=<%=m[1]%>"><%=m[1]%></a>
							<ul>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[1]%>&ct_name2=<%=m2[0]%>"><%=m2[0]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[1]%>&ct_name2=<%=m2[1]%>"><%=m2[1]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[1]%>&ct_name2=<%=m2[2]%>"><%=m2[2]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[1]%>&ct_name2=<%=m2[3]%>"><%=m2[3]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[1]%>&ct_name2=<%=m2[4]%>"><%=m2[4]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[1]%>&ct_name2=<%=m2[5]%>"><%=m2[5]%></a></li>
							</ul></li>
						<li class="divider"></li>

						<li><a href="/Camp_Project/p_cate1.do?ct_name1=<%=m[2]%>"><%=m[2]%></a>
							<ul>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[2]%>&ct_name2=<%=m3[0]%>"><%=m3[0]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[2]%>&ct_name2=<%=m3[1]%>"><%=m3[1]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[2]%>&ct_name2=<%=m3[2]%>"><%=m3[2]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[2]%>&ct_name2=<%=m3[3]%>"><%=m3[3]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[2]%>&ct_name2=<%=m3[4]%>"><%=m3[4]%></a></li>
							</ul></li>

						<li class="divider"></li>

						<li><a href="/Camp_Project/p_cate1.do?ct_name1=<%=m[3]%>"><%=m[3]%></a>
							<ul>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[3]%>&ct_name2=<%=m4[0]%>"><%=m4[0]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[3]%>&ct_name2=<%=m4[1]%>"><%=m4[1]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[3]%>&ct_name2=<%=m4[2]%>"><%=m4[2]%></a></li>
							</ul></li>
						<li class="divider"></li>
						<li><a href="/Camp_Project/p_cate1.do?ct_name1=<%=m[4]%>"><%=m[4]%></a>
							<ul>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[4]%>&ct_name2=<%=m5[0]%>"><%=m5[0]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[4]%>&ct_name2=<%=m5[1]%>"><%=m5[1]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[4]%>&ct_name2=<%=m5[2]%>"><%=m5[2]%></a></li>
								<li><a href="/Camp_Project/p_cate2.do?ct_name1=<%=m[4]%>&ct_name2=<%=m5[3]%>"><%=m5[3]%></a></li>
							</ul></li>
					</ul></li>
				<li><a href="/Camp_Project/p_bestlist.do">best</a></li>
				<li><a href="/Camp_Project/p_newlist.do">신상품</a></li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="/Camp_Project/BoardList.do">공지사항</a></li>
				<li><a href="/web_project/board/BoardQna.jsp">Q & A</a></li>
				<li><a href="/web_project/board/reviewList.jsp">상품후기</a></li>
			</ul>

			<form class="navbar-form navbar-right" role="search" name="frm">
				<div class="form-group">
					<input class="form-control" type="text" id="keyword" placeholder="검색어 입력" onkeydown="sijak()" name="sel">
				</div>
				<input class="btn btn-default" type="button" value="검색" id="btnok" name="btnok">
				<div id="suggest" style="display:; position: absolute; left: 932px; top: 40px; background-color: NAVY; color: white;" class="left td1">
					<div id="suggestList" class="left"></div>
					</li>
			</form>
		</div>
		</nav>
		<br>

	</div>
</body>
</html>