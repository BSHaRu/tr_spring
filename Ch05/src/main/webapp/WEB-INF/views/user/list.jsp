<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>user - list</title>
</head>
<body>
	<h3>user 목록</h3>
	<a href="/Ch05/index">index</a>
	<a href="/Ch05/user/register">user 등록</a>
	<table border="1">
		<tr>
			<th>아이디</th>
	        <th>이름</th>
	        <th>연락처</th>
	        <th>나이</th>
	        <th>관리</th>
		</tr>
		<c:choose>
			<c:when test="${!empty users }">
				<c:forEach var="user" items="${users }" >
				<tr>
					<td>${user.uid }</td>
					<td>${user.name }</td>
					<td>${user.hp }</td>
					<td>${user.age }</td>
					<td>
						<a href="/Ch05/user/modify?uid=${user.uid }">수정</a>
						<a href="/Ch05/user/delete?uid=${user.uid }">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">등록된 user가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		
	</table>
</body>
</html>