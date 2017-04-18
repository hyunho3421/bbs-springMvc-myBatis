<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
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
	<table class="table table-striped">
		<thead>
			<tr>
				<th>no</th>
				<th width="60%">제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="board">
				<tr>
					<td>${board.no}</td>
					<td><a href="/bbs/view?no=${board.no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.reg_date}" /></td>
					<td><span class="badge">${board.view_cnt}</span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script>
	var result = '${msg}';

	if(result == 'register_success') {
	    alert("등록되었습니다.");
	}

	if(result == 'remove_success') {
	    alert("삭제 되었습니다.")
	}

	if(result == 'success_modify') {
        alert("수정정되었습니다.")
	}
</script>
</body>
</html>