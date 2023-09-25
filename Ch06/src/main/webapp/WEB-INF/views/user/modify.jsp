<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>user - modify</title>
</head>
<body>
	<h3>user 수정</h3>
	<a href="/Ch06/index">index</a>
	<a href="/Ch06/user/list">user 목록</a>
	<form action="/Ch06/user/modify" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="uid" value=${userDTO.uid } readonly />
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" value=${userDTO.name } />
				</td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>
					<input type="text" name="hp" value=${userDTO.hp } />
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>
					<input type="text" name="age" value=${userDTO.age } />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="등록하기" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>