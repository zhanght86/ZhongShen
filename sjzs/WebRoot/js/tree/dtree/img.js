var pimages = new Array("help.gif","icon_delete.gif","icon_draft.gif","icon_exit.gif","icon_folder.gif","icon_folderopen.gif","icon_inbox.gif","icon_newmail.gif","icon_sent.gif","icon_unctitle.gif","icon-address.gif","icon-calendar.gif","icon-chat.gif","icon-chatm.gif","icon-check.gif","icon-checkflow.gif","icon-depart.gif","icon-doc.gif","icon-doctype.gif","icon-download.gif","icon-dustbin.gif","icon-exit.gif","icon-forum.gif","icon-forumm.gif","icon-guestbook.gif","icon-guestbookm.gif","icon-internet.gif","icon-kaoqin.gif","icon-leave.gif","icon-memorabilia.gif","icon-notice.gif","icon-noticem.gif","icon-plan.gif","icon-receive.gif","icon-send.gif","icon-studydoc.gif","icon-tel.gif","icon-today.gif","icon-uploaddoc.gif","icon-user.gif","icon-userinfo.gif","icon-vehicle.gif","icon-work.gif","icon-writedoc.gif","icon-zipcode.gif","book.gif","folderclosed.gif","folderopen.gif");

/**
 * 当点击一个图片时返回此图片的src，并将它返回给父窗口
 * @param imgObj	img对象
 * @return 
 */
function selectOk(imgObj) {
	window.returnValue = imgObj.src;
	window.close();
}

/**
 * 生成图片列表
 * 每行10个图片，不到10个图片的行输出空白
 * @return
 */
function imgTable() {
	var i = 0;
	document.write("<ul>");
	while (i < pimages.length) {
		for (var j = 0; j < 10; j = j + 1) {
			if (pimages[i]) {
				var str = "<li><img src=\"js/tree/dtree/left/" + pimages[i] + "\" ";
				str = str + " onclick=\"selectOk(this)\" align=\"middle\" style=\"vertical-align: middle;\"></li>";
				document.write(str);
			} else {
				var str = "<li>&nbsp;</li>";
				document.write(str);
			}
			i = i + 1;
		}
	}
	document.write("</ul>");
}