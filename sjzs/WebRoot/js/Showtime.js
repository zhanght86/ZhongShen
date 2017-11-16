var enabled = 0;
today = new Date();
var day;
var date;
if (today.getDay() == 0)
	day = "星期日 "
if (today.getDay() == 1)
	day = "星期一 "
if (today.getDay() == 2)
	day = "星期二 "
if (today.getDay() == 3)
	day = "星期三 "
if (today.getDay() == 4)
	day = "星期四 "
if (today.getDay() == 5)
	day = "星期五 "
if (today.getDay() == 6)
	day = "星期六 "
document.fgColor = " FF0072";
date1 = (today.getYear()) + "年" + (today.getMonth() + 1) + "月"
		+ today.getDate() + "日 " + day;
date2 = day;
document.write(date1);
document.write("<span id='clock'></span>");
var now, hours, minutes, seconds, timeValue;
function showtime() {
	now = new Date();
	hours = now.getHours();
	minutes = now.getMinutes();
	seconds = now.getSeconds();
	timeValue = hours + ":";
	timeValue += ((minutes < 10) ? "0" : "") + minutes + ":";
	timeValue += ((seconds < 10) ? "0" : "") + seconds;
	document.getElementById("clock").innerHTML = timeValue;
	setTimeout("showtime()", 100);
}
showtime();