<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/bootstrap/css/bootstrap-theme.min.css">
<title></title>
</head>
<body>
	<h2>stable这是一个新页面</h2>
	<form action="<%=path%>/cookie" method="post" class="row ">
		<div class="caption text-center lead" style="margin-top: 100px;">
			cookie:${token}<br/>
			<label>商户serial:</label>
			<input type="text" name="cookie" class="input" />
			<button class="btn-primary">提交</button>
		</div>

	</form>
</body>
