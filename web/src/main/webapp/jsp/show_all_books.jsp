<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="style/style.css"/>
    <link rel="stylesheet" type="text/css" href="style/bootstrap.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle var="loc" basename="localization.locale"/>
    <fmt:message var="bookList" bundle="${loc}" key="locale.title.book"/>
    <fmt:message var="access" bundle="${loc}" key="local.access"/>
    <fmt:message var="author" bundle="${loc}" key="local.author"/>
    <fmt:message var="title" bundle="${loc}" key="local.title"/>
    <fmt:message var="date" bundle="${loc}" key="local.date"/>
    <fmt:message var="show" bundle="${loc}" key="local.edit.panel.show"/>
    <fmt:message var="hide" bundle="${loc}" key="local.edit.panel.hide"/>
    <fmt:message var="closeAccess" bundle="${loc}" key="local.closeAccess"/>
    <fmt:message var="apply" bundle="${loc}" key="local.apply"/>

    <fmt:message var="authorInfo" bundle="${loc}" key="locale.message.author"/>
    <fmt:message var="titleInfo" bundle="${loc}" key="locale.message.title"/>
    <fmt:message var="dateInfo" bundle="${loc}" key="locale.message.date"/>
    <fmt:message var="enterId" bundle="${loc}" key="local.enterId"/>
    <script src="/js/show_panel.js"></script>
    <title>${bookList }</title>
</head>
<body class="body2">
<%@ include file="include/header.jsp" %>
<div class="content">
    <table align="center"></table>
    <ctg:show-all-books bookList="${sessionScope.bookList }"/>
</div>
<%@ include file="include/footer.jsp" %>

</body>
</html>