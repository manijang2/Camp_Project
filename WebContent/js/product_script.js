window.onload = function(){
	document.getElementById("review").onclick = function(){
		//startXhr("br_pcode");
		
	}
}
var xhr ;
function startXhr(br_pcode){  // 사원 정보를 ajax로 읽어 오기 위한함수
	xhr = new XMLHttpRequest();
	var url = "../board/reviewOneList.jsp?br_pcode=" + br_pcode;
	xhr.open("get", url , true); // 누른 버튼이 어떤 것인지 넘겨준다.
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				process();
			} else {
				alert("요청 실패:" + xhr.status);
			}
		} 
	}
	xhr.send(null);
}
function process(){
	var data = xhr.responseXML;
	//xml데이터 읽어오기
	var products = data.getElementsByTagName("products");
	var br_num = data.getElementsByTagName("br_num");
	var br_title = data.getElementsByTagName("br_title");
	var br_id = data.getElementsByTagName("br_id");
	var br_date = data.getElementsByTagName("br_date");
	var br_pimage = data.getElementsByTagName("br_pimage");
	var br_content = data.getElementsByTagName("br_content");
	var br_bimage = data.getElementsByTagName("br_bimage");
	var br_scope = data.getElementsByTagName("br_scope");
	var br_views = data.getElementsByTagName("br_pcode");
	
	
	var str ="<br><br>";
		if(products.length == 0){
		str += "<table><tr>";
		str += "<td>작성된 후기글이 없습니다</td>"
			str += "</tr></table>";
		}
		
		
	for(var i=0; i< products.length; i++){
		str += "<table  class='product_main'>";
		str += "<tr>";
		str += "<td width='50%>";
		if(br_bimage[i].firstChild.nodeValue === "null"){
			str += "<img width='500' src='../product_image/" + br_pimage[i].childNodes[0].nodeValue + "'>";
		}else {
			str += "<img width='500' src='../board_image/" + br_bimage[i].childNodes[0].nodeValue + "'>";
		}
		str += "</td>";
		
		str += "<td>";
		str += "<table class='table table-striped table-hover'>";
		str += "<tr><td>";
		str += "글쓴이:</td>";
		str += "<td>" + br_id[i].childNodes[0].nodeValue + "</td>";
		str += "</tr>";
		
		str += "<tr><td>";
		if(br_scope[i].childNodes[0].nodeValue==2){
			str += "<img width='100' src='../image/별1.png'>" +"&nbsp;" ;
		}else if(br_scope[i].childNodes[0].nodeValue==4){
			str +=  "<img width='100' src='../image/별2.png'>" +"&nbsp; ";
		}else if(br_scope[i].childNodes[0].nodeValue==6){
			str +=  "<img width='100' src='../image/별3.png'>" +"&nbsp;&nbsp;" ;
		}else if(br_scope[i].childNodes[0].nodeValue==8){
			str += "<img width='100' src='../image/별4.png'>" +"&nbsp; ";
		}else if(br_scope[i].childNodes[0].nodeValue==10){
			str += "<img width='100' src='../image/별5.png'>" +"&nbsp; ";
		}
		str += "</td></tr>"
		str += br_date[i].childNodes[0].nodeValue.substring(0,10) + "</td>";
		str += "</tr>";
		
		str += "<tr>";
		str += "<td>" + br_title[i].childNodes[0].nodeValue + "</td>";
		str += "</tr>";
		
		str += "<tr><td>";
		if(br_bimage[i].firstChild.nodeValue === "null"){
			str += "<img width='100' height='100' src='../product_image/" + br_pimage[i].childNodes[0].nodeValue + "'>";
		}else {
			str += "<img width='100' height='100' src='../board_image/" + br_bimage[i].childNodes[0].nodeValue + "'>";
		}
		str += "</td></tr>";
		
		str += "<tr>";
		str += 	"<td align='center'>" + 
		"<textarea rows='10' style='width: 50%' border='0' readonly>" +
		br_content[i].childNodes[0].nodeValue.replace("\r\n", "<br>") +
		"</textarea>"
		+"</td>";
		str += "</tr>";

		str += "</table>";
		str += "<hr>";
	}		
	document.getElementById("showProduct").innerHTML = str;
}


//[admin] -----------------------------------------------------------------
//check 박스 전체 선택/해제 ------------------------------------------
function selectCheckBoxAll(){ 
	var checkBoxes = document.getElementsByName("check"); 
	for(i in checkBoxes) {
		checkBoxes[i].checked = (productForm.allCheck.checked) ? true : false;
  }
} 

//이미지 편집 ------------------------------------------
function editImages(code){ 
	url = "../product/p_updateImages_admin.jsp?p_code=" + code;  
	window.open(url,"id","toolbar=no,width=300,height=300," +
			"top=200,left=300,status=yes,scrollbars=yes,menubar=no");
} 

//삭제 확인 ------------------------------------------------------
function confirmDeleteEach(code) { //개별 삭제 
	if(confirm("삭제하시겠습니까?")) {
		var link = "../product/p_delete_admin.jsp?p_code=" + code; 
		location.href=link; 
	}
}

function confirmDelete() { //복수 삭제 
	if(confirm("삭제하시겠습니까?")) {
		var checkBoxes = document.getElementsByName("check"); 
		var codes = document.getElementsByName("p_code");
		var temp = "?"; 
		for(i in checkBoxes) {
			if(checkBoxes[i].checked) {
				temp += "p_code=" + codes[i].value + "&"; 
			}
		}
		temp = temp.slice(0,-1);
		var link = "../product/p_delete_admin.jsp" + temp; 
		location.href=link; 
	}
}

//상품 신규 등록 ----------------------------------------------------
function checkRegister(){
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