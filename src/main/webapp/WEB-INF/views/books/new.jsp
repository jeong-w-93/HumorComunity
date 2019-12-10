<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%-- <%@ page pageEncoding="utf-8" session="false"%> --%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../includes/header.jsp"%>

<title>Books NEW</title>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Books NEW</h1>
            <p>views/books/new.jsp</p>
        </div>
        <form action="<c:url value="/books" />" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
            <div class="form-group form-group-lg">
                <label class="control-label">도서 제목</label>
                <input name="title" type="text" class="form-control">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">저자</label>
                <input name="author" type="text" class="form-control">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">이미지</label>
                <input name="file" type="file">
            </div>
            <button type="submit" class="btn btn-lg btn-primary">전송</button>
        </form>
    </div>
</body>
</html>
<%@include file="../includes/footer.jsp"%>