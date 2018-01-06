function topDivOUT() { //v3.0
    var i, x, a = document.MM_sr;
    for(i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
    	x.src = x.oSrc;
}
function topDiv(divId, divName, zDivCount) {
    for (i = 0; i <= zDivCount; i++) {
        document.getElementById(divName + i).style.display = "none";
    }
    document.getElementById(divName + divId).style.display = "";
}
function pageLogin() {
    var $Name = $("#LoginName");
    var $Pass = $("#LoinPassWord");
    if ($Name.val() == "" || $Pass.val() == "") {
        alert("用户名密码不能为空！");
    } else {
        $.ajax({
            type: "get",
            url: "LoginXY.aspx",
            data: { type: "GetLoginXY", txt_Name: $Name.val(), txt_Pass: $Pass.val() },
            cache: false,
            success: function (msg) {
                var json = eval("(" + msg + ")");
                if (json.GetLoginXY == "0") {
                    alert("用户名密码错误！");
                } else if (json.GetLoginXY == "1") {
                    location.href = "xiaoyoulu/Index.aspx";
                } else {
                    alert("系统异常请联系系统管理员！");
                }
            }
        });
    }
}
lastScrollY = 0;
function heartBeat() {
    var diffY;
    if (document.documentElement && document.documentElement.scrollTop)
        diffY = document.documentElement.scrollTop;
    else if (document.body)
        diffY = document.body.scrollTop
    else {
    	/*Netscape stuff*/
    }
    percent = .1 * (diffY - lastScrollY);
    if (percent > 0) percent = Math.ceil(percent);
    else percent = Math.floor(percent);
    document.getElementById("LeftDiv").style.top = parseInt(document.getElementById("LeftDiv").style.top) + percent + "px";
    //document.getElementById("LeftDiv0").style.top = parseInt(document.getElementById("LeftDiv0").style.top) + percent + "px";
    document.getElementById("RightDiv").style.top = parseInt(document.getElementById("LeftDiv").style.top) + percent + "px";
    document.getElementById("RightDiv1").style.top = parseInt(document.getElementById("RightDiv1").style.top) + percent + "px";
    lastScrollY = lastScrollY + percent;
}
window.setInterval("heartBeat()", 1);
function hidead_L() {
    showad = false;
    document.getElementById("LeftDiv").style.display = "none";
}
function hidead_R() {
    showad = false;
    document.getElementById("RightDiv").style.display = "none";
}
function hidead_R1() {
    showad = false;
    document.getElementById("RightDiv1").style.display = "none";
}
var Url_L = "<a href='NewsList.aspx?ClassID=006007' target='_blank'><img src='"+resourcePrefix+"/layout/old/img/635525949185247500_1.jpg' width='100' height='275' border='0'></a>";
var Url_R = "<a href='NewsList.aspx?ClassID=009005' target='_blank'><img src='"+resourcePrefix+"/layout/old/img/635525949652747500_2.jpg' width='100' height='275' border='0'></a>";
suspendcode12 = "<DIV id=\"LeftDiv\" style='left:10px;POSITION:absolute;TOP:215px;'><div align='right' style='position: absolute;top:0px;right:0px;margin:2px;padding:2px;z-index:2000;'><a href='javascript:;' onclick='hidead_L()' style='color:red;text-decoration:none;font-size:12px;'>关闭</a></div>" + Url_L + "</div>"
suspendcode14 = "<DIV id=\"RightDiv\" style='right:10px;POSITION:absolute;TOP:215px;'><div align='right' style='position: absolute;top:0px;right:0px;margin:2px;padding:2px;z-index:2000;'><a href='javascript:;' onclick='hidead_R()' style='color:red;text-decoration:none;font-size:12px;'>关闭</a></div>" + Url_R + "</div>"
suspendcode15 = "<DIV id=\"RightDiv1\" style='right:10px;POSITION:absolute;TOP:500px;'><div align='right' style='position: absolute;top:0px;right:0px;margin:2px;padding:2px;z-index:2000;'><a href='javascript:;' onclick='hidead_R1()' style='color:red;text-decoration:none;font-size:12px;'>关闭</a></div><a href='http://www.jlyzzjb.com/sy.asp' target='_blank'><img src='"+resourcePrefix+"/layout/old/img/logo200.png' width='150' height='165' border='0'></a></div>";
//实验
//suspendcode16 = "<DIV id=\"RightDiv0\" style='right:10px;POSITION:absolute;TOP:10px;'><div align='right' style='position: absolute;top:0px;right:0px;margin:2px;padding:2px;z-index:2000;'></div><a href='javascript:;' onclick='logSY();' ><img src='images/2016fenban.png' width='150' height='165' border='0'></a></div>";
//分班
//suspendcode17 = "<DIV id=\"LeftDiv0\" style='left:10px;POSITION:absolute;TOP:10px;'><div align='right' style='position: absolute;top:0px;right:0px;margin:2px;padding:2px;z-index:2000;'></div><a href='javascript:;' onclick='logFB();' ><img src='images/2016shiyan.png' width='150' height='165' border='0'></a></div>";
document.write(suspendcode12);
document.write(suspendcode14);
document.write(suspendcode15);
//document.write(suspendcode16);
//document.write(suspendcode17);
window.onload = function() {
	showBox();
	setTimeout("closeBox()", 5000);
}
function showBox(o) {
	if (o == undefined) 
		o = document.getElementById("rbbox");
		o.style.height = o.clientHeight + 2 + "px";
		if (o.clientHeight < 200)
			setTimeout(function () { showBox(o) }, 5);
}
function closeBox() {
	document.getElementById("rbbox").style.display = "none";
}
$("#topDiv_0").css('display',''); 