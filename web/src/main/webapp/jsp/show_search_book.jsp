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
    <fmt:message var="resultSearch" bundle="${loc}" key="locale.title.user.list"/>
    <fmt:message var="id" bundle="${loc}" key="local.id"/>
    <fmt:message var="access" bundle="${loc}" key="local.access"/>
    <fmt:message var="author" bundle="${loc}" key="local.author"/>
    <fmt:message var="title" bundle="${loc}" key="local.title"/>
    <fmt:message var="date" bundle="${loc}" key="local.date"/>
    <fmt:message var="amount" bundle="${loc}" key="local.amount"/>

    <title>${resultSearch }</title>
</head>

<body class="content">
<%@ include file="include/header.jsp" %>
<div>

    <div class="container">
        <article class="row">
            <p style="color: #006dcc" class="span2"><strong>${author }</strong></p>
            <p style="color: #006dcc" class="span3"><strong>${title }</strong></p>
            <p style="color: #006dcc" class="span2"><strong>${date }</strong></p>
            <p style="color: #006dcc" class="span1"><strong>${amount }</strong></p>
        </article>
        <c:forEach var="book" items="${bookList}">
            <article class="row ">
                <p style="color: #1a1a1a" class="span2 "><c:out value="${ book.author }"/></p>
                <p style="color: #1a1a1a" class="span3 "><c:out value="${ book.title }"/></p>
                <p style="color: #1a1a1a" class="span2 "><c:out value="${ book.date }"/></p>
                <p style="color: #1a1a1a" class="span1 "><c:out value="${ book.amount }"/></p>
            </article>
        </c:forEach>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>