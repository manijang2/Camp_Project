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

//회원ID중복확인 ----------------------------------------------------
function checkId(){	
	var pattern = /^[A-Za-z0-9]{4,12}$/; //영문 대문자 소문자 숫자   가능 4~12 글자수 제한
	if(regForm.m_id.value === ""){ 
		alert("id를 입력하시오")
		regForm.m_id.focus();
		return; 
	} else if(!regForm.m_id.value.match(pattern)){
		alert("입력 양식에따라 작성하시오");
		regForm.m_id.focus();
		return;
	} else { //중복검사 및 결과 표시를 위한 window open 
		url = "../member/member_registerValidId.jsp?m_id=" + regForm.m_id.value;  
		window.open(url,"id","toolbar=no,width=300,height=150," +
				"top=200,left=300,status=yes,scrollbars=yes,menubar=no");
	}
}

//이메일 자동 편집 ------------------------------------------------
function editEmail(){
	var email = regForm.m_email.value + regForm.sel.value;
	var splits = email.split("@");
	if(splits.length > 2) {
		email = splits[0] + regForm.sel.value;
	}		
	regForm.m_email.value = email;
}


//전화번호 자동 편집 ------------------------------------------------
function editPhone(form){ //keypress event(누른 후 입력됨, 즉 입력 전 이벤트 콜) 
	var phoneNum = form.m_phone.value.replace(/-/g, ""); 
	var length = phoneNum.length; 
	if(phoneNum.charAt(1) > 1) { //일반전화(02~)
		if(length <= 9) {// case1: 02-555-5555 (9자리: 2-3-4)
			dash1 = 2; dash2 = 5;
		} else {// case2: 02-5555-5555 (10자리: 2-4-4)
			dash1 = 2; dash2 = 6;
		}
	} else { //핸드폰(01~)
		if(length <= 10) {// case1: 010-555-5555 (10자리: 3-3-4) 
			dash1 = 3; dash2 = 6;
		} else {// case2: 010-5555-5555 (11자리 : 3-4-4) 
			dash1 = 3; dash2 = 7;
		}  
	}
	
	if(dash1 < length && length <= dash2) { //대쉬가 1개인 경우 
		phoneNum = phoneNum.slice(0,dash1) + "-" + phoneNum.slice(dash1,length+1);
	} else if(length > dash2) { //대쉬가 2개인 경우 
		phoneNum = phoneNum.slice(0,dash1) + "-" + phoneNum.slice(dash1,dash2) + "-" + phoneNum.slice(dash2,length+1);
	}
	
	form.m_phone.value = phoneNum;
}


//우편번호 찾기 --------------------------------------------------
function execDaumPostcode() {
  new daum.Postcode({
      oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          var extraRoadAddr = ''; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if(data.buildingName !== '' && data.apartment === 'Y'){
             extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if(extraRoadAddr !== ''){
              extraRoadAddr = ' (' + extraRoadAddr + ')';
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if(fullRoadAddr !== ''){
              fullRoadAddr += extraRoadAddr;
          }
          
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
          document.getElementById('roadAddress').value = fullRoadAddr;
          //document.getElementById('roadAddress').value = data.jibunAddress;
          document.getElementById('guide').innerHTML = '(나머지 주소를 입력해주세요)';          
      }
  }).open();
}

//주소 입력 전 우편번호 연결 -------------------------------------------------
function checkAddress() {
	if(document.getElementById('postcode').value === ""){
		document.getElementById('btnZip').click();
	}
}

//회원등록 입력 체크 ----------------------------------------------------------
function checkRegisterIn(){
	if(checkInput(regForm)){
		regForm.action = "../member/member_registerOk.jsp";	
		regForm.method="post";
		regForm.submit(); 
	}
}