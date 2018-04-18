function selectDelete() { //선택 삭제 
	if(confirm("삭제하시겠습니까?")) {
		location.href = '/cart_select_del.do';
		form.action = "/Camp_Project/cart_select_del.do";
		form.method = "post";
		form.submit();
	}
}


//카트 선택 항목 삭제
function selectDelete1() { //선택 삭제 
	if(confirm("삭제하시겠습니까?")) {
		form.action = "/Camp_Project/cart_select_del.do";
		form.method = "post";
		form.submit();
	}
}
