<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>회원 로그인</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="/Camp_Project/css/member_style.css" rel="stylesheet" type="text/css">

<!--script 생성해서 여기 들어가야함 -->
</head>
<body>
	<%@include file="/member/inc/member_top.inc"%>


<div class="div">
<br><br>
<div class="innerdiv3">
<hr>
<h1>회원 로그인</h1>


<!--회원로그인 view
로그인 액션 : MemberLoginAction.me -->

<form action="./MemberLoginAction.me" method=post name="loginForm"
onsubmit="">
		<table  class="table table-striped table-hover login_table">
			<tr>
				<th class="th3" style="padding: 20px">아이디</th>
				<td><input type="text" name="m_id" placeholder="아이디"  class="form-control" ></td>
			</tr>
			<tr>
				<th class="th3" style="padding: 20px">비밀번호</th>
				<td><input type="password" name="m_pwd" placeholder="비밀번호"  class="form-control" ></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="button" value="로그인" id="btnlogin" onclick="javascript:doLogin()" class="btn btn-primary btn-sm"/> &nbsp;&nbsp;
				<input type="button" value="회원가입" id="btnNewMember" onclick="javascript:location.href='member_register.jsp'" class="btn btn-danger btn-sm">			
				</td>
			</tr>
		</table>
		</form>
<!-- 회원 로그인 -->


</div>
<br><br><br><br>
</div>
<br><br>

<!--  member_bottom 인식못함 -->
</body>
</html>