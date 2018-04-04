
//상품 리스트 버튼 함수
function goOrder() {
	alert("asb");
	form.action = "/Camp_Project/order_one.do";
	form.submit();
}
function goCart() {
	//adminCheck();
	form.action = "/Camp_Project/cart_proc.do";
	form.submit();
}



