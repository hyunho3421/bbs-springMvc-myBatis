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
	<form role="form">
		<input type="hidden" name="msg" value="${msg}">
	</form>

	<table class="table table-striped table-bordered">
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
					<td><a href="/bbs/view${pageMaker.makeQuery(pageMaker.criteria.page)}&no=${board.no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.reg_date}" /></td>
					<td><span class="badge">${board.view_cnt}</span></td>
				</tr>
			</c:forEach>
		<c:if test="${empty list}">
			<tr>
				<td colspan="5" class="text-center">게시글이 없습니다.</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li><a href="/bbs/list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.criteria.page == idx?'class=active':''}" />>
					<a href="/bbs/list${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="/bbs/list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
	<div align="right">
		<button class="btn btn-primary" type="submit" id="btnRegister">등록</button>
	</div>
</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script src="/static/bbs/list.js"></script>
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