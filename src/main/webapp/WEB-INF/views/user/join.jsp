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
    <br />

    <div class="well">
        <form role="form">
            <div class="form-group">
                <label for="id">ID:</label>
                <input type="text" class="form-control" name="id" id="id">
            </div>

            <div class="form-group">
                <label for="password">PASSWORD:</label>
                <input type="password" class="form-control" name="password" id="password"></input>
            </div>

            <div class="form-group">
                <label for="name">NAME:</label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
        </form>

        <div align="right" class="list-group">
            <button class="btn btn-primary" type="submit" id="btnJoin">가입</button>
            <button class="btn btn-default" type="submit" id="btnList">List</button>
        </div>
    </div>
</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script src="/static/common/common-js.js"></script>
<script src="/static/login/join.js"></script>
<script>
</script>
</body>
</html>