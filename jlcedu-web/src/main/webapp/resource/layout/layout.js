$(document).ready(function() {
	$("#switchLanguageSelect").val($("#locale").val());
	$("#switchLanguageSelect").change(function() {
		$(location).attr("href", "/"+$(this).children("option:selected").val()+$("#uri").val());
	});
	$("#testBtn").click(function() {
		var data = {"arg":"xxx"};
		$.ajax({
			url: "/"+$("#locale").val()+"/website/test",
			type: "POST",
			dataType: "JSON",
			data: data,
			complete: function(xhr, status) {
				//alert("complete---"+xhr+"-"+status);
			},
			success: function(data) {
				alert(data.status+": "+data.data);
			},
			error: function(xhr, status) {
				alert("error---"+xhr+"-"+status);
			}
		});
	});
	$("#testIndexBtn").click(function() {
		$.ajax({
			url: "/"+$("#locale").val()+"/index",
			type: "POST",
			dataType: "JSON",
			complete: function(xhr, status) {
				//alert("complete---"+xhr+"-"+status);
			},
			success: function(data) {
				if(data.status>0) {
					for(var i=0;i<data.data.banners.length;i++) {
						var banner = data.data.banners[i];
						alert(banner.img+"---"+banner.desc);
					}
					alert("---"+data.data.superSlide.link);
					for(var i=0;i<data.data.superSlide.img.length;i++) {
						var img = data.data.superSlide.img[i];
						alert("---"+img);
					}
				}
			},
			error: function(xhr, status) {
				alert("error---"+xhr+"-"+status);
			}
		});
	});
});