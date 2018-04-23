// Admin Menu ------------------------------------------------/ 
$(document).ready(function() {
	openNav();
});

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}

//Admin MemeberMgr ------------------------------------------------/ 

//function changeMain(file) {
//	//main div에 로딩 파일 변경하기 
//	document.getElementById("main").innerHTML = "<%@ include file = '" + file + "'%>";
//	//location.href = "admin_main.jsp"; 
//}


//회원수정 입력 체크 ----------------------------------------------------------
function checkUpdateIn(){
	if(checkInput(upForm)){
		upForm.action = "/Camp_Project/admin/memberUpdate.do";	
		upForm.method="post";
		upForm.submit(); 
	}
}
//입력 체크 -------------------------------------------------------------
function checkInput(form){
	// ID : 영문 대문자 소문자 숫자   가능 4~12 글자수 제한
	var ptnId = /^[A-Za-z0-9]{4,12}$/;
	//Password : 6~16자 영문대소문자, 숫자, 특수문자 혼합하여 사용
	var ptnPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-]|.*[0-9]).{4,24}$/;
	//Name : 한글10자, 영문20자, 한글,영문,숫자 사용가능
	var ptnName =/^[0-9a-zA-Z가-힣]{2,20}$/; 
	//Email : 숫자 영문 대소문자 특수문자 -_\.ㅎ
	var ptnEmail = /^[A-Za-z0-9]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/; 
	//Phone : 010-1010-1010 
	var ptnPhone = /^\d{3}-\d{3,4}-\d{4}$/;
	
	if(form.m_id.value === ""){
		alert("id 입력해주세요")
		form.m_id.focus();
	}else if(form.isIdChecked != null && form.isIdChecked.value != "checked"){
		alert("중복체크해주세요");
	}else if(!form.m_id.value.match(ptnId)){
		alert("입력 양식에따라 작성하시오");
		form.m_id.focus();
	}else if(form.m_pwd.value ==""){
		alert("비밀번호를 입력하세요");
		form.m_pwd.focus();
	}else if(!form.m_pwd.value.match(ptnPwd)){
		alert("비번 입력양식에 맞게 작성해주세요");
		form.m_pwd.focus();
	}else if(form.rem_pwd != null && form.m_pwd.value != form.rem_pwd.value){
		alert("비밀번호가 틀립니다");
		form.rem_pwd.value = ""; 
		form.rem_pwd.focus();
	}else if(form.m_name.value ==""){
		alert("이름을 입력하세요");
		form.m_name.focus();
	}else if(!form.m_name.value.match(ptnName)){
		alert("이름 입력양식에 맞게 작성해주세요");
		form.m_name.focus();
	}else if(form.m_email.value ==""){
		alert("이메일을 입력하세요");
		form.m_email.focus();
	}else if(!form.m_email.value.match(ptnEmail)){
		alert("메일 형식이 맞지않습니다");
		form.m_email.focus();
	}else if(form.m_phone.value ==""){
		alert("핸드폰 번호를 입력하세요");
		form.m_phone.focus();
	}else if(!form.m_phone.value.match(ptnPhone)){
		alert("핸드폰 번호양식에 맞지않습니다");
		form.m_phone.focus();
	}else if(form.m_zipcode.value ==""){
		alert("우편번호를 입력하세요");
		form.m_zipcode.focus();
	}else if(form.m_address.value ==""){
		alert("주소를 입력하세요");
		form.m_address.focus();
	}else { 
		return true;	
	}
	return false;
}
