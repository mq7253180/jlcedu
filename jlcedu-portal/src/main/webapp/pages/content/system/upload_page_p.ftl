<#import "/spring.ftl" as spring>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Upload</title>
	</head>
	<body>
		<input type="file" id="file1" name="files1" multiple/>
		<input type="button" id="importExcel" name="importExcel" value="Import" onclick="upload('file1', '/<@attr key="locale" />/system/upload/do')"/>
		<input type="button" id="test" name="test" value="Test"/>
	</body>
	<input type="hidden" id="locale" value="<@attr key="locale" />"/>
	<input type="hidden" id="uri" value="<@attr key="uri_without_first" />"/>
	<input type="hidden" id="resourcePrefix" value="<@property key="prefix.resource"/>"/>
	<script type="text/javascript" src="<@property key="prefix.resource"/>/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="<@property key="prefix.resource"/>/content/upload.js"></script>
	<script type="text/javascript">
		$("#test").click(function() {
			$("#file1").ajaxUploadFiles({
				url: "/<@attr key="locale" />/system/upload/do",
				maxSize: 8000,
				acceptFileTypes: ["xlsx"],
				errorMsg: {
					notSelected: "Please select an xlsx file.",
					maxSize: "Size of the file must be less than 8000 bit.",
					acceptFileTypes: "Only xlsx is accepted."
				},
				complete: function(status, responseText) {
					alert("complete---"+status+": "+responseText);
				},
				success: function(json) {
					alert("success---"+json.data);
				},
				failure: function(status, responseText) {
					alert("failure---"+status+": "+responseText);
				}
			});
		});
	</script>
</html>