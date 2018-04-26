<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Camp_Project/css/admin_style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/Camp_Project/js/admin_script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<link href="/Camp_Project/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/Camp_Project/css/member_style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<%@include file="/admin/inc/admin_menu.inc" %>
	<div id="main">
		<div>
			<%@include file="/main/inc/main_top.inc"%>
<div class="div">
<br> <br>
	<form name="regForm">
			<table class="table table-striped table-hover reg_table">	
				<tr class="active">
					<th class="th6" colspan="3" style="padding: 20px"><b>회원	가입</b></th>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">아이디</th>
					<td class="td7">
					<input type="text" name="m_id" size="15" placeholder="아이디" class="form-control input-sm" /></td>
					<td class="td8"><input type="button" value="ID중복확인" onclick="checkId()" class="btn btn-default btn-sm" /> 
						<input type="hidden" name="isIdChecked" value="unchecked" /> <b>tip)영문,숫자 4~12자 이상</b></td>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">패스워드</th>
					<td class="td7"><input type="password" name="m_pwd" size="15" placeholder="비밀번호" class="form-control input-sm" /></td>
					<td class="td8"><b>tip)영문,숫자 혼합 및 특수문자 허용 6~16글자수 제한</b></td>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">패스워드 확인</th>
					<td class="td7">
						<input type="password" name="rem_pwd" size="15" placeholder="비밀번호 재확인" class="form-control input-sm" /></td>
					<td></td>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">이름</th>
					<td class="td7"><input type="text" name="m_name" size="15" placeholder="이름" class="form-control input-sm" /></td>
					<td></td>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">이메일</th>
					<td class="td7"><input type="text" id="m_email" name="m_email" size="25" placeholder="홍길동 @daum.net" class="form-control input-sm" /></td>
					<td><div style="width: 200px">
							<select class="select form-control input-sm" name="sel" id="sel"
								onclick="javascript:editEmail()">
								<option value="">직접입력</option>
								<option value="@daum.net">@daum.net</option>
								<option value="@naver.com">@naver.com</option>
								<option value="@google.com">@google.com</option>
								<option value="@nate.com">@nate.com</option>
								<option value="@yahoo.com">@yahoo.com</option>
							</select>
						</div></td>
				</tr>
				<tr>
					<th class="th6">전화번호</th>
					<td class="td7"><input type="text" name="m_phone" size="20" placeholder="010-1111-2222" onkeyup="javascript:editPhone(regForm)" class="form-control input-sm"></td>
					<td></td>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">우편번호</th>
					<td class="td7">
						<input type="text" name="m_zipcode" id="postcode" placeholder="우편번호" size="7" readonly class="form-control input-sm">
					</td>
					<td class="td8">
						<input type="button" value="우편번호찾기" id="btnZip" onclick="javascript:execDaumPostcode()" class="btn btn-default btn-sm">
					</td>
				</tr>
				<tr>
					<th class="th6" style="vertical-align: middle;">주소</th>
					<td class="td7" colspan="2"><input type="text" name="m_address" id="roadAddress" placeholder="우편번호 찾기를 눌러주세요" size="60" onclick="javascript:checkAddress()" class="form-control input-sm">
					<span id="guide" style="color: #999"></span></td>
				</tr>
				<!-- 관리자만 회원 등급 조정 가능 -->
				<c:if test="${'admin' eq sessionScope.gradeKey}">
					<tr>
						<th class="th6"  style="vertical-align: middle;">회원등급</th>
						<td class="td7"><select class="select form-control input-sm" name="m_grade" id="m_grade">
								<option value="일반회원">일반회원</option>
								<option value="우수회원">우수회원</option>
								<option value="VIP">VIP</option>
								<option value="admin">관리자</option>
						</select></td>
						<td></td>
					</tr>
				</c:if>
				<!-- 끝 -------------------->
				<tr>
					<td colspan="3" align="center">
						<input type="button" value="회원가입" id="btnSubmit" onclick="javascript:checkRegisterIn()" class="btn btn-primary btn-sm">
					 	&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시쓰기" class="btn btn-default btn-sm">
					</td>
				</tr>		
			</table>
		</form>
	</div>
	<br> <br>
<br>
<br>
<%@include file="/main/inc/main_bottom.inc"%>
		</div>
	</div>
</body>
</html>