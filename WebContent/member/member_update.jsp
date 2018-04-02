<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>


<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>회원정보수정</title>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="/Camp_Project/css/member_style.css" rel="stylesheet" type="text/css">




</head>
<body>
	<%@include file="/member/inc/member_top.inc"%>

	<br>
	<br>
	<div class="div">
		<br> <br>
		<div class="inner_div2">
			<form name="upForm" action="./MemberUpdateAction.me" method="post" onsubmit="">
				<table class="table table-striped table-hover reg_table">
					<tr class="active">
						<th class="th6" colspan="3"><b style="color: #FFFFFF"> user님의 회원정보 수정</b></th>
					</tr>
					<tr>
						<th class="th6">아이디</th>
						<td class="td7"> &nbsp;  ${dto.m_id}<input type="hidden" name="m_id" value="user_id"/></td>
						<td class="td8"></td>
						
					</tr>
					<tr>
						<th class="th6" style="vertical-align: middle;">패스워드 확인</th>
						<td class="td7"><input type="text" name="m_pwd" size="15" value="user_pwd"  class="form-control input-sm" ></td>
						<td class="td8"></td>
					</tr>
					<tr>
						<th class="th6" style="vertical-align: middle;">이름</th>
						<td class="td7"><input type="text" name="m_name" size="15" value="user_name"  class="form-control input-sm" ></td>
						<td class="td8"></td>
					</tr>
					<tr>
						<th class="th6" style="vertical-align: middle;">이메일</th>
						<td class="td7"><input type="text" name="m_email" size="27" value="user_email"  class="form-control input-sm" ></td>
						<td class="td8"></td>
					</tr>
					<tr>
						<th class="th6" style="vertical-align: middle;">전화번호</th>
						<td class="td7"><input type="text" name="m_phone" size="20" value="010123456" onkeyup="javascript:editPhone(upForm)"  class="form-control input-sm" ></td>
						<td class="td8"></td>
					</tr>
					<tr>
						<th class="th6" style="vertical-align: middle;">우편번호</th>
						<td class="td7"><input type="text" name="m_zipcode" id="postcode" placeholder="우편번호" size="7" value="123456" readonly  class="form-control input-sm" ></td>
						<td class="td8"><input type="button" value="우편번호찾기" id="btnZip" onclick="javascript:execDaumPostcode()" class="btn btn-default btn-sm"></td>
					</tr>
					<tr>
						<th class="th6" style="vertical-align: middle;">주소</th>
						<td class="td7" colspan="2"><input type="text" name="m_address" id="roadAddress" placeholder="우편번호 찾기를 눌러주세요" size="60" value="user_address"  class="form-control input-sm" >
							<span id="guide" style="color: #999"></span></td>
					</tr>
					<!-- 관리자만 회원 등급/마일리지 조정 가능
					★관리자가 이 화면에서 조정할 필요가 있나??? -->
					 <!--<c:choose>
						<c:when test="${'admin' eq sessionScope.gradeKey}">
							<tr>
								<th class="th6" style="vertical-align: middle;">회원등급</th>
								<td class="td7"><select class="select form-control input-sm" name="m_grade" id="m_grade">
										<option value="${dto.m_grade}" selected>${dto.m_grade}</option>
										<option value="일반회원">일반회원</option>
										<option value="우수회원">우수회원</option>
										<option value="VIP">VIP</option>
										<option value="admin">관리자</option>
								</select></td>
								<td class="td8"></td>
							</tr>
							<tr>
								<th class="th6" style="vertical-align: middle;">마일리지</th>
								<td class="td7"><input type="text" name="m_mileage" size="15" value="${dto.m_mileage}"  class="form-control input-sm" ></td>
								<td class="td8"></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th class="th6">회원등급</th>
								<td class="td7"> &nbsp;  ${dto.m_grade}<input type="hidden" name="m_grade" value="${dto.m_grade}" ></td>
								<td class="td8"></td>
							</tr>
							<tr>
								<th class="th6">마일리지</th>
								<td class="td7"> &nbsp;  ${dto.m_mileage}<input type="hidden" name="m_mileage" value="${dto.m_mileage}"></td>
								<td class="td8"></td>
							</tr>
						</c:otherwise>
					</c:choose>
					끝 -------------------->
					<tr>
						<td colspan="3" class="th6">
						 <input type="reset" value="수정 취소" onclick="history.back()" class="btn btn-default btn-sm"/>
						 &nbsp;&nbsp;
						 <input type="reset" value="다시쓰기" class="btn btn-default btn-sm"/>
						 &nbsp;&nbsp;
						 <input type="button" value="회원수정" id="btnUp" onclick="javascript:checkUpdateIn()" class="btn btn-primary btn-sm"/>
						 &nbsp;&nbsp;
						 <input type="button" value="회원탈퇴" onclick="javascript:confirmDeleteEach('${dto.m_id}')" class="btn btn-danger btn-sm"/>
						 </td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<br>
		<br>
		<br>
	</div>
	<br>
	<br>
<%@include file="/main/inc/main_bottom.inc" %>
	
</body>
</html>