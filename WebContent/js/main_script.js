//공지사항 리스트 글쓰기 버튼
function noticeWrite(){
	location.href="boardnoticewrite.jsp";
}

//리스트 검색 - 버튼
function noticeSearch(){
	if (frm.selword.value == "") {
		frm.selword.focus();
		alert("검색어를 입력하시오");
		return;
	}
	frm.submit();
}

// 글쓰기 페이지 - 버튼
function noticeInCheck() {
	if (frm.bn_title.value == "") {
		alert("제목을 입력하세요");
		frm.bn_title.focus();
	} else if (frm.bn_content.value == "") {
		alert("내용을 입력하세요");
		frm.bn_content.focus();
	} else
		frm.submit();
}


// 오더(1개)
function oneOrder(){
	usemilige();
	form.action="/Camp_Project/order_proc.do";
	form.submit();
}


// 상품 상세페이지----------------
// 상품 주문수량변경
function CountChange(count) {
	if (count == 'up') {
		form.quantity.value++;
	} else if (count == 'down') {
		form.quantity.value--;
	}
	if (form.quantity.value < 1) {
		form.quantity.value = 1;
	}
}


//상품 리스트 버튼 함수
function goOrder() {
	alert("asb");
	form.action = "/Camp_Project/order_one.do";
	form.submit();
}
function goCart() {
	//adminCheck();
	form.action = "/Camp_Project/cart_proc.do?c_pcode="+c_pcode;
	form.submit();
}

function goCart2() {
	//adminCheck();
	alert("ss");
	form.action = "/cart_proc.do";
	form.submit();
}

////관리자 체크
//function adminCheck(){
//	var key = frm.AdminKey.value;
//	if(key=="admin"){
//		alert("관리자는 주문하실 수 없습니다.")
//		return;
//}

//마일리지 금액 출력버튼
function usemilige(){
	use_m = Number($("#u_mil").val());		// 마일리지 사용자 입력값
	mil = Number($("#c_mil").val());		// 원래 갖고있는 마일리지
	opay = Number($("#order_pay").val());	// 주문상품 소계

	if(use_m > mil){				// 마일리지 입력값 검사
		alert("마일리지 값이 초과되었습니다");
	}else{
		$("#use_mi").html("&#8361;" +use_m);
		result = opay - use_m ;
		$("#result_pay").val(result);
		$("#use_mi2").html("&#8361;" + commas(result));
	}
	return rpay = 0;
}
//금액출력에 콤마찍기
function commas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//카트 - 주문 수량 변경버튼
function cartUpdate(c_num) {
	ps = "p"+c_num;			// 상품 갯수를 갖고있는 폼의 아이디
	ps2 = $("#"+c_num).val();	// 상품 갯수를 갖고있는 폼의 변경값
	location.href = '/Camp_Project/cart_update.do?c_num=' + c_num + '&c_quantity=' + ps2;
}

//카트 - 수량 버튼
function cartCount(productNum,action) {
	quantity = document.getElementById(productNum);	// 상품 갯수를 갖고있는 폼의 변경값
	if (action == 'up') {
		quantity.value ++;
	} else if (action == 'down') {
		quantity.value --;
	}
	if (quantity.value < 1) {
		quantity.value = 1;
	}
}

//카트 선택 항목 삭제
function selectDelete() { //선택 삭제 
	if(confirm("삭제하시겠습니까?")) {
		form.action = "/cart_select_del.do";
		form.method = "post";
		form.submit();
	}
}

/*카트 수정 시 정규식*/

//카트삭제
function cartDelete(c_num) {
	deleteQ = confirm("삭제하시겠습니까");
	if (deleteQ) {
		location.href = '/Camp_Project/cart_delete.do?c_num='+c_num;
	} else {
		location.href = "/Camp_Project/cart/cart.jsp";
	}
}
// 클릭상품보기
function cartDetail(no) {
	form.no.value = no;
	form.submit();
}

//체크박스 전체체크/해제
function allChk() {
	var chk = form.check; // for로 돌린 체크박스
	var allchk = form.allCheck.checked; // 모두체크용 체크박스 값
	var tot = chk.length;
	alert(tot);
	alert(chk.length);
	for (var i = 0; i < tot; i++) {
		if (allchk)
			chk[i].checked = true;
		if (!allchk)
			chk[i].checked = false;
	}
}



function delevery(){
	window.open("http://d2d.ilogen.com/d2d/delivery/invoice_search_popup.jsp?invoiceNum=90318348201&viewType=type1", "", "width=730, height=550");
}


//우편번호 찾기
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 도로명 조합형 주소 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', '
						+ data.buildingName : data.buildingName);
			}
			// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
			if (fullRoadAddr !== '') {
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
