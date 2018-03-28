var xhr;
var checkFirst = loopSend = false;
var lastKeyword = "";

function sijak() {
	// alert("a");
	if (checkFirst == false) {
		setTimeout("sendKeyword()", 100);
		loopSend = true;
	}
	/*
	 * if(frm.btnok.onclick == true) location.href = "../layout/layout.jsp";
	 * submit();
	 */
}

function sendKeyword() {
	if (loopSend == false)
		return;
	var keyWord = document.frm.keyword.value;
	// console.log(keyWord);
	if (keyWord == "") {
		lastKeyword = "";
		hide("suggest");
	} else if (keyWord != lastKeyword) {
		lastKeyword = keyWord;

		if (lastKeyword != "") {
			var para = "keyword=" + keyWord;
			xhr = new XMLHttpRequest();
			xhr.open("post", "../product/j_suggest.jsp", true);

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						process();
					} else {
						alert("요청 실패:" + xhr.status);
					}
				}
			}
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");

			xhr.send(para);
		} else {
			hide("suggest");
		}
	}
}

function process() {
	var resultText = xhr.responseText;
	// alert(resultText);
	var result = resultText.split("|");
	// alert(result[0] + " " + result[1]);
	var tot = result[0];
	if (tot > 0) {
		var datas = result[1].split(",");
		var imsi = "";
		for (var i = 0; i < datas.length; i++) {
			imsi += "<a href=\"javascript:func('" + datas[i] + "')\">"
					+ datas[i] + "</a><br>";
		}
		var listView = document.getElementById("suggestList");
		listView.innerHTML = imsi;
		show("suggest");
	}
}

function func(reData) {
	frm.sel.value = reData;
	loopSend = checkFirst = false;
	lastKeyworwd = "";
	hide("suggest");
}

function hide(ele) {
	var e = document.getElementById(ele);
	if (e)
		e.style.display = "none";
}
function show(ele) {
	var e = document.getElementById(ele);
	if (e)
		e.style.display = "";
}