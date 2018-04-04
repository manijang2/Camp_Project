//로그인 ---------------------------------

function doLogin(){
	if(loginForm.m_id.value === ""){
		alert("id를 입력하시오")
		loginForm.m_id.focus();
	}else if(loginForm.m_pwd.value ==""){
		alert("비밀번호를 입력하세요");
		loginForm.m_pwd.focus();
	}else{
		loginForm.action = "./MemberLoginAction.do";
		loginForm.method = "post";
		loginForm.submit();
	}
}