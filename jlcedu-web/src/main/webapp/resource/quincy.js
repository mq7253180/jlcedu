(function($) {
	if(navigator.appName=="Microsoft Internet Explorer") {
		var regExp = new RegExp("^.+MSIE\\s*[1-9]\\.+.+$", "g");
		if(regExp.test(navigator.appVersion)) {
			alert("IE浏览器请使用10及以上版本");
		}
	}
	$.fn.ajaxUploadFiles = function(s) {
		var path = $(this).val();
		if(path==null||path.length==0) {
			alert(s.errorMsg.notSelected);
			return 0;
		}
		var formData = new FormData();
		var files = $(this).prop("files");
		for(var i=0;i<files.length;i++) {
			var file = files[i];
			var validFileType = false;
			for(var j=0;j<s.acceptFileTypes.length;j++) {
				var regExp = new RegExp(".+\."+s.acceptFileTypes[j]+"$", "i");
				if(regExp.test(file.name)) {
					validFileType = true;
					break;
				}
			}
			if(!validFileType) {
				alert(s.errorMsg.acceptFileTypes);
				return -1;
			}
			if(file.size>s.maxSize) {
				alert(s.errorMsg.maxSize);
				return -2;
			}
			formData.append("file"+i, file);
		}
		var req = window.XMLHttpRequest?new XMLHttpRequest():new ActiveXObject("Microsoft.XMLHTTP");
		req.onreadystatechange = function() {
			if(req.readyState==4) {
				s.complete(req.status, req.responseText);
				if(req.status==200) {
					s.success(JSON.parse(req.responseText));
				} else {
					s.failure(req.status, req.responseText);
				}
			}
		};
		req.open("POST", s.url, true);
		req.setRequestHeader("x-requested-with", "XMLHttpRequest");
		req.send(formData);
		return 1;
	};
})(jQuery);