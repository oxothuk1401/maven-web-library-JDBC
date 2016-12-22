<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="style/style.css"/>
    <link rel="stylesheet" type="text/css" href="style/bootstrap.css"/>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle var="loc" basename="localization.locale"/>
    <fmt:message var="resultSearch" bundle="${loc}" key="locale.title.book.search"/>
    <fmt:message var="id" bundle="${loc}" key="local.id"/>
    <fmt:message var="access" bundle="${loc}" key="local.access"/>
    <fmt:message var="author" bundle="${loc}" key="local.author"/>
    <fmt:message var="title" bundle="${loc}" key="local.title"/>
    <fmt:message var="date" bundle="${loc}" key="local.date"/>

    <title>${resultSearch }</title>
</head>

<body class="content">
<%@ include file="include/header.jsp" %>
<div class="container panel">
    <h1 class="panel-heading" align="center">${resultSearch }</h1>
    <table class="table table-striped ">
        <thead>
        <tr>
            <th style="color: #1b6d85"><strong>${author }</strong></th>
            <th style="color: #1b6d85"><strong>${title }</strong></th>
            <th style="color: #1b6d85"><strong>${date }</strong></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td><c:out value="${ book.author }"/></td>
                <td><c:out value="${ book.title }"/></td>
                <td><c:out value="${ book.date }"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>