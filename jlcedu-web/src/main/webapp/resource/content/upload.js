(function($) {
	$.fn.ajaxUploadOneFile = function(s) {
		var filePath = $(this).val();
		if(filePath==null||filePath.length==0) {
			alert(s.errorMsg.notSelected);
			return 0;
		}
		var validFileType = false;
		for(var i=0;i<s.acceptFileTypes.length;i++) {
			var regExp = new RegExp(".+\."+s.acceptFileTypes[i]+"$", "i");
			if(regExp.test(filePath)) {
				validFileType = true;
				break;
			}
		}
		if(!validFileType) {
			alert(s.errorMsg.acceptFileTypes);
			return -1;
		}
		var files0 = $(this).prop("files")[0];
		if(files0.size>s.maxSize) {
			alert(s.errorMsg.maxSize);
			return -2;
		}
		var formData = new FormData();
		formData.append(s.id, files0);
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