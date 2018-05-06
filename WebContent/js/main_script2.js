
//상품 리스트 버튼 함수
function goOrder() {
	alert("결제 페이지로 이동하시겠습니까?");
	form.action = "/Camp_Project/order_one.do";
	form.submit();
}
function goCart() {
	//adminCheck();
	form.action = "/Camp_Project/cart_proc.do";
	form.submit();
}



