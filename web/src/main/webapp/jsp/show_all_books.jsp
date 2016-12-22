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
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle var="loc" basename="localization.locale"/>
    <fmt:message var="bookList" bundle="${loc}" key="locale.title.book"/>
    <fmt:message var="access" bundle="${loc}" key="local.access"/>
    <fmt:message var="author" bundle="${loc}" key="local.author"/>
    <fmt:message var="title" bundle="${loc}" key="local.title"/>
    <fmt:message var="date" bundle="${loc}" key="local.date"/>
    <fmt:message var="edit" bundle="${loc}" key="local.edit"/>
    <fmt:message var="closeAccess" bundle="${loc}" key="local.closeAccess"/>
    <fmt:message var="apply" bundle="${loc}" key="local.apply"/>

    <fmt:message var="authorInfo" bundle="${loc}" key="locale.message.author"/>
    <fmt:message var="titleInfo" bundle="${loc}" key="locale.message.title"/>
    <fmt:message var="dateInfo" bundle="${loc}" key="locale.message.date"/>
    <fmt:message var="enterId" bundle="${loc}" key="local.enterId"/>
    <title>${bookList }</title>
</head>
<body class="body2">
<%@ include file="include/header.jsp" %>
<div class="row container">
    <div>
        <input class="btn btn-info" id="show_div" type="button" value="${edit}"/>
        <div>
            <c:choose>
            <c:when test="${not empty requestScope.invalidRegistrData}">
            <div id="hidden_div" style="display:block">
                </c:when>
                <c:otherwise>
                <div id="hidden_div" style="display:none">
                    </c:otherwise>
                    </c:choose>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="sign-up-user"/>
                        <input type="hidden" name="pageUnique" value="${sessionScope.pageUnique }"/>
                        <c:if test="${not empty requestScope.invalidRegistrData}">
                            <span class="error">${requestScope.invalidRegistrData }</span>
                            <br>
                        </c:if>
                        <input type="text" name="authorEdit" placeholder="${author}"
                               value=""/> ${authorInfo}
                        <br>
                        <input type="text" name="titleEdit" placeholder="${title}"
                               value=""/> ${titleInfo}
                        <br>
                        <input type="text" name="dateEdit" placeholder="${date}"
                               value=""/> ${dateInfo}
                        <br>
                        <input class="btn btn-primary"
                               type="submit" value="${apply }"/>
                    </form>
                </div>
            </div>
            <script src="js/show_hidden_div.js"></script>
        </div>
    </div>
    <br>
</div>
<div class="content">
    <table align="center"></table>
    <ctg:show-all-books bookList="${sessionScope.bookList }"/>
</div>
<%@ include file="include/footer.jsp" %>

</body>
</html>