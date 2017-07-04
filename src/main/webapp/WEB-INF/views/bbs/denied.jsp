<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<jsp:include page="../header.jsp" />

	<br />
	<div class="jumbotron">
		<h1>권한이 없습니다.</h1>
	</div>

</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script src="/static/bbs/list.js"></script>
<script src="/static/common/common-js.js"></script>
</body>
</html>