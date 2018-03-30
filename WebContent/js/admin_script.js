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

