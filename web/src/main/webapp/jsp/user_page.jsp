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
    <fmt:message var="hello1" bundle="${loc}" key="locale.hello.client1"/>
    <fmt:message var="userTitle" bundle="${loc}" key="locale.title.user"/>
    <fmt:message var="orderCompleted" bundle="${loc}" key="locale.message.completed.order"/>
    <title>${userTitle }</title>
</head>
<body class="body">
<%@ include file="include/header.jsp" %>
<c:if test="${sessionScope.blacklist eq 'unblock'}">
    <%@ include file="include/search.jsp" %>
</c:if>


<div class="content">
    ${hello1 }
    <c:out value="${sessionScope.userName}"/>
    <c:if test="${not empty requestScope.errorMessage}">
        <br/>
        <span class="error">${errorMessage }</span>
    </c:if>
    <br/>
    <c:if test="${sessionScope.userRole eq 'admin'}">
        <%@ include file="include/admin_part.jsp" %>
    </c:if>
    <c:if test="${sessionScope.userRole eq 'user'}">
        <%@ include file="include/user_part.jsp" %>
    </c:if>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>