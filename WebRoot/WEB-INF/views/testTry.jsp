<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>test load websockifys</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<script src="resources/js/jquery.js"></script>
		<script type="text/javascript">
			<!--
			var ipAddress, port;
			$(document).ready(function() {
				$.ajax({ 
					url: "http://localhost:8081/apptryDB/apptry/try/1",
					astry		dataType: "json",
					success: function(data) {
						ipAddress = data.ipAddress;
						port = data.port;
						$(body).html("ip is " + ipAddress + "&port is " + port);
					},
					error: function(data) {}
				});
			});
			//-->
		</script>
	</head>

	<body>
		This is my JSP page.
		<br>
	</body>
</html>
