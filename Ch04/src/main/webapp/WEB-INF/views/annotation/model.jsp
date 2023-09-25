<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>annotation model</title>
</head>
<body>
	<h3>@ModelAttribute 어노테이션 실습</h3>
	<a href="/Ch04/index">index</a>
	
	<form action="/Ch04/annotation/model1" method="post">
		<input type="text" name="uid" />
		<input type="submit" value="전송" />
	</form>
	
	<form action="/Ch04/annotation/model2" method="post">
		<input type="text" name="uid" />
		<input type="text" name="name" />
		<input type="submit" value="전송" />
	</form>
	
	<form action="/Ch04/annotation/model3" method="post">
		<input type="text" name="uid" />
		<input type="text" name="name" />
		<input type="text" name="hp" />
		<input type="text" name="age" />
		<input type="submit" value="전송" />
	</form>
	
</body>
</html>