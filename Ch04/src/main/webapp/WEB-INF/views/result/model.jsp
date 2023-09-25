<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>result model</title>
</head>
<body>
	<h3>@Requestmodel 어노테이션 결과</h3>
	<a href="/Ch04/index">index</a>

	<h4>model1 결과</h4>
	<p>
		uid : ${modelDTO.uid}
	</p>
	<hr/>

	<h4>model2 결과</h4>
	<p>
		uid : ${user.uid}<br/>
		name : ${user.name}<br/>
	</p>
	<hr/>

	<h4>model3 결과</h4>
	<p>
		uid : ${modelDTO.uid}<br/>
		name : ${modelDTO.name}<br/>
		hp : ${modelDTO.hp}<br/>
		age : ${modelDTO.age}<br/>
	</p>
	<hr/>
	
	
	
</body>
</html>