
function addMore() {
	var td = document.getElementById("more");
	var br = document.createElement("br");
	var input = document.createElement("input");
	var button = document.createElement("input");
	input.type = "file";
	input.name = "pictures";
	button.type = "button";
	button.value = "\u5220\u9664";
	button.className = "bt";
	button.onclick = function () {
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
	};
	td.appendChild(br);
	td.appendChild(input);
	td.appendChild(button);
}
function del(node) {
	var d = document.getElementById("d_" + node);
	var h = document.getElementById("h_" + node);
	d.style.display = "none";
	d.removeChild(h);
}
function addLabPic() {
	var td = document.getElementById("labPic");
	var br = document.createElement("br");
	var input = document.createElement("input");
	var button = document.createElement("input");
	input.type = "file";
	input.name = "labPics";
	button.type = "button";
	button.value = "\u5220\u9664";
	button.className = "bt";
	button.onclick = function () {
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
	};
	td.appendChild(br);
	td.appendChild(input);
	td.appendChild(button);
}
function addEquipPic() {
	var td = document.getElementById("equipPic");
	var br = document.createElement("br");
	var input = document.createElement("input");
	var button = document.createElement("input");
	input.type = "file";
	input.name = "equipPics";
	button.type = "button";
	button.value = "\u5220\u9664";
	button.className = "bt";
	button.onclick = function () {
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
	};
	td.appendChild(br);
	td.appendChild(input);
	td.appendChild(button);
}
function deleterole(divid) {
	var rolediv = "tr_" + divid;
	var rolehd = "hd_" + divid;
	var div = document.getElementById(rolediv);
	var hd = document.getElementById(rolehd);
	div.style.display = "none";
	div.removeChild(hd);
}
function jump() {
	var jumpFlag = document.getElementById("jumpFlag");
	if (jumpFlag.value == "edit") {
		window.alert("\u4fee\u6539\u64cd\u4f5c\u6210\u529f!");
	} else {
		if (jumpFlag.value == "delete") {
			window.alert("\u5220\u9664\u64cd\u4f5c\u6210\u529f!");
		} else {
			if (jumpFlag.value == "add") {
				window.alert("\u6dfb\u52a0\u64cd\u4f5c\u6210\u529f!");
			} else {
				if (jumpFlag.value == "audit") {
					window.alert("\u5ba1\u6838\u64cd\u4f5c\u6210\u529f!");
				} else {
					if (jumpFlag.value == "register") {
						window.alert("\u6ce8\u518c\u7ba1\u7406\u5458\u6210\u529f\uff0c\u8bf7\u7b49\u5f85\u5ba1\u6838!");
					}
				}
			}
		}
	}
	}
function registvalid() {
	var fm1 = document.getElementById("fm1");
	var loginName = document.getElementById("loginName").value;
	var password = document.getElementById("password").value;
	var repassword = document.getElementById("repassword").value;
	var adminType = document.getElementById("adminType").value;
	if ("" == loginName || null == loginName) {
		alert("\u5fc5\u987b\u586b\u5199\u767b\u5f55\u540d");
	} else {
		if ("" == password || null == password) {
			alert("\u5fc5\u987b\u586b\u5199\u5bc6\u7801");
		} else {
			if ("" == repassword || null == repassword) {
				alert("\u5fc5\u987b\u586b\u5199\u786e\u8ba4\u5bc6\u7801");
			} else {
				if (password != repassword) {
					alert("\u5bc6\u7801\u548c\u786e\u8ba4\u5bc6\u7801\u5fc5\u987b\u76f8\u540c");
				} else {
					if (0 == adminType) {
						alert("\u5fc5\u987b\u9009\u62e9\u7ba1\u7406\u5458\u7c7b\u522b");
					} else {
						fm1.submit();
					}
				}
			}
		}
	}
}

