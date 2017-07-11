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

	<form role="form">
		<input type="hidden" id="page" value="${criteria.page}" >
		<input type="hidden" id="perPageNum" value="${criteria.perPageNum}">
	</form>

	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>no</th>
				<th width="60%">제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th class="text-center">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="board">
				<tr>
					<td>${board.no}</td>
					<td><a href="/bbs/view${pageMaker.makeSearchQuery(pageMaker.criteria.page)}&no=${board.no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.reg_date}" /></td>
					<td class="text-center"><span class="badge">${board.view_cnt}</span></td>
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
				<li><a href="/bbs/list${pageMaker.makeSearchQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.criteria.page == idx?'class=active':''}" />>
					<a href="/bbs/list${pageMaker.makeSearchQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="/bbs/list${pageMaker.makeSearchQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>

	<%--
		n: 검색 조건이 없음
		t: 제목으로 검색
		c: 내용으로 검색
		w: 작성자로 검색
		tc: 제목이나 내용으로 검색
		cw: 내용이나 작성자로 검색
		tcw: 제목이나 내용 혹은 작성자로 검색
	--%>

	<div class="row" style="position: relative; left: 30%;">
		<div class="form-group col-sm-2" style="padding-left: 5px; padding-right: 0px;">
			<select class="form-control" name="searchType">
				<option value="n" <c:out value="${criteria.searchType == null ? 'selected' : ''}"/> >
					-----
				</option>
				<option value="t" <c:out value="${criteria.searchType eq 't' ? 'selected' : ''}"/> >
					제목
				</option>
				<option value="c" <c:out value="${criteria.searchType eq 'c' ? 'selected ' : ''}"/> >
					내용
				</option>
				<option value="w" <c:out value="${criteria.searchType eq 'w' ? 'selected ' : ''}"/> >
					작성자
				</option>
				<option value="tc" <c:out value="${criteria.searchType eq 'tc' ? 'selected ' : ''}"/> >
					제목+내용
				</option>
				<option value="cw" <c:out value="${criteria.searchType eq 'cw' ? 'selected ' : ''}"/> >
					내용+작성자
				</option>
				<option value="tcw" <c:out value="${criteria.searchType eq 'tcw' ? 'selected ' : ''}"/> >
					제목+내용+작성자
				</option>
			</select>
		</div>
		<div class="col-sm-2" style="padding-left: 5px; padding-right: 2px;">
			<input class="form-control" type="text" name="keyword" id="keyword" value="${criteria.keyword}">
		</div>
		<button class="btn btn-default" id="btnSearch">검색</button>
		<button class="btn btn-primary" type="submit" id="btnRegister">등록</button>
	</div>
	<!-- -->
</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script src="/static/bbs/list.js"></script>
<script src="/static/common/common-js.js"></script>
</body>
</html>