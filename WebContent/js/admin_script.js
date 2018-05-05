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
		alert("아이디 입력 양식에따라 작성하시오");
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



function confirmDelete() { //선택 삭제 
	if(confirm("선택삭제 하시겠습니까?")) {
		
		var $form = $('form[name=memberForm]');
		$form.attr('action', '/Camp_Project/admin/memberDelete.do');
		$form.attr('method', 'post');

	    $form.submit();
	}
}

function confirmDeleteEach(id) { //개별 삭제 
	if(confirm("탈퇴하시겠습니까?")) {
		
		var $form = $('<form></form>');
		$form.attr('action', '/Camp_Project/admin/memberDelete.do');
		$form.attr('method', 'post');
		$form.appendTo('body');
	     
	    var idx = $('<input type="hidden" value="' + id + '" name="m_id">');
	    $form.append(idx);

	    $form.submit();
	}
}

function clearMemberFiled() {
	$('input[name=m_name]').val('');
	$('input[name=m_email]').val('');
	$('input[name=m_phone]').val('');
	$('input[name=m_zipcode]').val('');
	$('input[name=m_address]').val('');
}



//회원ID중복확인 ----------------------------------------------------
function checkId(){	
	var pattern = /^[A-Za-z0-9]{4,12}$/; //영문 대문자 소문자 숫자   가능 4~12 글자수 제한
	if(regForm.m_id.value === ""){ 
		alert("id를 입력하시오")
		regForm.m_id.focus();
		return; 
		
	} else if(!regForm.m_id.value.match(pattern)){
		alert("아이디 입력 양식에따라 작성하시오");
		regForm.m_id.focus();
		return;
		
	} else { //중복검사 및 결과 표시를 위한 window open 
		url = "/Camp_Project/admin/member_id_check.do?m_id=" + regForm.m_id.value;  
		window.open(url,"id","toolbar=no,width=300,height=150," +
				"top=200,left=300,status=yes,scrollbars=yes,menubar=no");
	}
}

//회원등록 입력 체크 ----------------------------------------------------------
function checkRegisterIn(){
	
	if(checkInput(regForm)){
		regForm.action = "/Camp_Project/admin/member_register.do";	
		regForm.method="post";
		regForm.submit();
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


//삭제 확인 ------------------------------------------------------
function confirmProdectDeleteEach(code) { //개별 삭제 
	if(confirm("삭제하시겠습니까?")) {
		var $form = $('<form></form>');
		$form.attr('action', '/Camp_Project/admin/productDelete.do');
		$form.attr('method', 'post');
		$form.appendTo('body');
	     
	    var idx = $('<input type="hidden" value="' + code + '" name="p_code">');
	    $form.append(idx);

	    $form.submit();
	}
}

//상품 신규 등록 ----------------------------------------------------
function checkProductRegister(){
	var form = document.getElementById("newForm"); 
	var ptnNum = /^[0-9]{1,12}$/; // 숫자   가능 2~12 글자수 제한
	if(form.p_name.value === ""){ 
		alert("상품명을 입력하시오")
		form.p_name.focus();
		return; 
	} else if(form.p_price.value === ""){ 
		alert("상품 가격을 입력하시오")
		form.p_price.focus();
		return; 
	} else if(!form.p_price.value.match(ptnNum)){ 
		alert("가격을 숫자로 입력하시오")
		form.p_price.focus();
		return; 
	} else if(form.p_stock.value === ""){ 
		alert("재고량을 입력하시오")
		form.p_stock.focus();
		return; 
	} else if(!form.p_stock.value.match(ptnNum)){ 
		alert("재고량을 숫자로 입력하시오")
		form.p_stock.focus();
		return; 
	} else {
		form.submit(); 
	}
}

function confirmProductDelete() { //복수 삭제
	
	if(confirm("삭제하시겠습니까?")) {
		var $form = $('<form></form>');
		$form.attr('action', '/Camp_Project/admin/productDelete.do');
		$form.attr('method', 'post');
		$form.appendTo('body');
		
		$('input:checkbox[name="check"]').each(function() {
		      if(this.checked){//checked 처리된 항목의 값
		    	  var idx = $('<input type="check" value="' + this.value + '" name="check">');
		    	  $form.append(idx);
		      }
		});
	     
	    $form.submit();
	}
}

//이미지 편집 ------------------------------------------
function editImages(code){ 
	alert("기능구현중")
	return false;
	
	url = "../product/p_updateImages_admin.jsp?p_code=" + code;  
	window.open(url,"id","toolbar=no,width=300,height=300," +
			"top=200,left=300,status=yes,scrollbars=yes,menubar=no");
}

function confirmOrderUpdateEach(formName) { //개별 수정
	var frm = document.getElementById(formName);
	frm.action = "/Camp_Project/admin/orderUpdate.do";
	frm.method = "post"; 
	frm.submit(); 
}