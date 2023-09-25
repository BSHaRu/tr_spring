<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>result param</title>
</head>
<body>
	<h3>@RequestParam 어노테이션 결과</h3>
	<a href="/Ch04/index">index</a>

	<h4>param1 결과</h4>
	<p>
		uid : ${uid}
	</p>
	<hr/>

	<h4>param2 결과</h4>
	<p>
		uid : ${uid}<br/>
		name : ${name}<br/>
	</p>
	<hr/>

	<h4>param3 결과</h4>
	<p>
		uid : ${uid}<br/>
		name : ${name}<br/>
		hp : ${hp}<br/>
		age : ${age}<br/>
	</p>
	<hr/>
	
	
	
</body>
</html>