var locale = $("#locale").val();
var resourcePrefix = $("#resourcePrefix").val();
var uri = $("#uri").val();
(function($) {
	String.prototype.startWith = function(s) {
		if(s==null||s==undefined) {
			return false;
		} else {
			var _s = $.trim(s);
			var _this = $.trim(this);
			if(_this.length==0||_s.length>_this.length) {
				return false;
			} else {
				return _this.substr(0, _s.length)==_s?true:false;
			}
		}
	}
	$.i18n.properties({ 
		name: "quincy",
		path: resourcePrefix+"/i18n",
		mode: "map",//模式：变量或 Map
		language: locale,
		cache: false,
		encoding: "UTF-8",
		callback: function() {
			
		}
	});
	if(navigator.appName=="Microsoft Internet Explorer") {
		var regExp = new RegExp("^.+MSIE\\s*[1-9]\\.+.+$", "g");
		if(regExp.test(navigator.appVersion)) {
			alert($.i18n.prop("msg.ie"));
		}
	}
	$.ajaxProxy = function(s) {
		$.ajax({
			url: s.url,
			type: s.type,
			dataType: s.dataType,
			data: s.data,
			complete: function(xhr, status) {
				//alert("complete---"+xhr+"-"+status);
			},
			success: function(data) {
				if(data.status==1) {
					s.handle(data.data);
				} else if(data.status==0) {
					alert("跳登录页");
				} else {
					alert("系统繁忙");
				}
			},
			error: function(xhr, status) {
				alert("error---"+xhr+"-"+status);
			}
		});
	};
	$.fn.ajaxUploadFiles = function(s) {
		var formData = new FormData();
		var validationErrorMsg = "";
		var retVal = 1;
		var path = $(this).val();
		if(path==null||path.length==0) {
			validationErrorMsg = $.i18n.prop("upload.error.null");
			retVal = 0;
		} else {
			var files = $(this).prop("files");
			var separator = locale.startWith("zh")||locale.startWith("ZH")?"、":", ";
			for(var i=0;i<files.length;i++) {
				var file = files[i];
				var validFileType = false;
				var acceptableTypes = "";
				for(var j=0;j<s.acceptableTypes.length;j++) {
					var type = s.acceptableTypes[j];
					var regExp = new RegExp(".+\."+s.acceptableTypes[j]+"$", "i");
					if(regExp.test(file.name)) {
						validFileType = true;
					}
					acceptableTypes += type;
					acceptableTypes += separator;
				}
				if(!validFileType) {
					validationErrorMsg = $.i18n.prop("upload.error.types", acceptableTypes.substring(0, acceptableTypes.length-1));
					retVal = -1;
					break;
				}
				if(file.size>s.maxSize) {
					var maxSize = s.maxSize/1024/1024;
					validationErrorMsg = $.i18n.prop("upload.error.max_size", maxSize+"m");
					retVal = -2;
					break;
				}
				formData.append("file"+i, file);
			}
		}
		if(retVal<1) {
			s.validationFailed(validationErrorMsg);
		} else {
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
		}
		return retVal;
	};
})(jQuery);
var popLayer;
var popLayerParams = {offset: ["250px"], content: $.i18n.prop("msg.handling")};