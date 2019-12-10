<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@include file="../includes/header.jsp"%>

</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Books INDEX</h1>
            <p>views/books/index.jsp</p>
        </div>
          <div class="row">
            <c:forEach var="book" items="${books}" varStatus="status">
                <div class="col-md-4">
                    <div class="thumbnail">
                <img src="${ book.image }" />
                <div class="caption">
                    <h3>${ book.title } <small>${ book.author }</small></h3>
                    <a href="<c:url value='/books/edit/${ book.bno }' />" class="btn btn-lg btn-default">수정</a>
                	<a href="<c:url value='/books/delete/${ book.bno }' />" class="btn btn-lg btn-danger">삭제</a>
                </div>
            </div>
                </div>
            </c:forEach>
        </div>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
       		 <a href="<c:url value="/books/new" />"class="btn btn-lg btn-primary">도서 등록</a>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
			 <h5><a href="/customLogin"><i class="fa fa-sign-out fa-fw"></i>운영자로 로그인</a></h5>
		</sec:authorize>
    </div>
</body>
</html>
<%@include file="../includes/footer.jsp"%>
