<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title></title>
	<link rel="stylesheet" href="/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<form role="form" method="post" action="/board/register">
		<div class="form-group">
			<label for="title">Title</label>
			<input type="text" class="form-control" name="title" id="title">
		</div>

		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control" name="content" id="content" rows="5"></textarea>
		</div>

		<div class="form-group">
			<label for="writer">Writer</label>
			<input type="text" class="form-control" name="writer" id="writer">
		</div>

		<button class="btn btn-primary" type="submit">등록</button>
	</form>
</div>

<script src="/resources/js/jquery-3.2.1.js" />
<script src="/resources/js/bootstrap.js" />
</body>
</html>