<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.min.css">
<title></title>
</head>
<body>
	<h2>stable这是一个新页面</h2>
	<form action="/postData" method="post" class="row ">
		<div class="caption text-center lead" style="margin-top: 100px;">
		<label>post数据:</label>
			<input type="text" name="postData" class="input"
				   value="<xml><AppId><![CDATA[wxfa31d2e4b3b580a4]]></AppId>
				<Encrypt><![CDATA[gn1lWvLw6iR34R017xKWkYVk7t
				s7LreKv9W4HBaW+gD0mTGyncU/BBiPDXQ04yIizPXQYj
				GbFYzmdH3yA+GOIQD4bvlmUkSmzCZFm1lBC/Uge8bEth
				dX6LhJxH29I/TcDMLh913SO1+uRyWlWxwdy+pjUj21We
				zBazrU5YxnNhRToROI7NSiC8qsScLcnYW59WSYyRzsgo
				Imzut0u+AexVtq0DLvz8HNJbBTQoZXmwfM0dNgJ2rk9h
				itxiuegzUj5RqRyp9UvfpZiYEpz2MffHF2PxRuVTYP/F
				1ggX5WTHSIqL8SxgoMuFBz8fyBFMN5FYRO7bWdhEOGxZ
				CciJycoIs2nTvIvdn+66nOK6DGvYjudj92qm7N9j/ZOg
				VmpmKU0N7Vmv05lkEmteQ3o9o1cjEQpOK2pGcl6yGN/v
				wEMo2VJpDgv5loWSNwNvsckswh3FsNbytj/XMCpvKnc+
				9X9Q==]]></Encrypt></xml>"/>
		<%--<input type="text" name="postData" class="input" value=""/>--%>
		<button class="btn-primary">提交</button>
		</div>

	</form>
</body>
