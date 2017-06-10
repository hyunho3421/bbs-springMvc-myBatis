<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/bbs/list">KHH</a>
		</div>
		<ul class="nav navbar-nav">
			<li <c:out value="${root eq 'bbs' ? 'class=active' : ''}" />><a href="#">BBS</a></li>
			<%--<li><a href="#">Page 2</a></li>--%>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>
			<c:if test="${login.id ne null }" >
				<li><a href="#" onclick="return false;" style="cursor: default;">${login.id}</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span></a></li>
			</c:if>
			<c:if test="${login.id eq null }" >
				<li><a href="/user/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</c:if>
		</ul>
	</div>
</nav>