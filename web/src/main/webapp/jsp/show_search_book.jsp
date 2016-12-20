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
    <fmt:message var="userListTitle" bundle="${loc}" key="locale.title.user.list"/>
    <fmt:message var="id" bundle="${loc}" key="local.id"/>
    <fmt:message var="access" bundle="${loc}" key="local.access"/>
    <fmt:message var="author" bundle="${loc}" key="local.author"/>
    <fmt:message var="title" bundle="${loc}" key="local.title"/>
    <fmt:message var="date" bundle="${loc}" key="local.date"/>
    <fmt:message var="amount" bundle="${loc}" key="local.amount"/>

    <fmt:message var="edit" bundle="${loc}" key="local.edit"/>
    <fmt:message var="closeAccess" bundle="${loc}" key="local.closeAccess"/>
    <fmt:message var="apply" bundle="${loc}" key="local.apply"/>

    <fmt:message var="authorInfo" bundle="${loc}" key="locale.message.author"/>
    <fmt:message var="titleInfo" bundle="${loc}" key="locale.message.title"/>
    <fmt:message var="dateInfo" bundle="${loc}" key="locale.message.date"/>
    <fmt:message var="amountInfo" bundle="${loc}" key="locale.message.amount"/>
    <fmt:message var="enterId" bundle="${loc}" key="local.enterId"/>
    <title>${userListTitle }</title>
</head>

<body class="content">
<%@ include file="include/header.jsp" %>
<div>
    <div class="row container">
        <div>
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="close-access"/>
                <input class="btn btn-info" id="" type="button" value="${closeAccess}"/>
                <input type="text" class="span2 search-query" name="close-access" placeholder="${enterId}" value="">
            </form>
        </div>
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
                            <input type="text" name="amountEdit" placeholder="${amount}"
                                   value=""/> ${amountInfo}
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
    <div class="container">
        <article class="row">
            <p style="color: #006dcc" class="span1" align="center"><strong>${id }</strong></p>
            <p style="color: #006dcc" class="span2"><strong>${access }</strong></p>
            <p style="color: #006dcc" class="span2"><strong>${author }</strong></p>
            <p style="color: #006dcc" class="span3"><strong>${title }</strong></p>
            <p style="color: #006dcc" class="span2"><strong>${date }</strong></p>
            <p style="color: #006dcc" class="span1"><strong>${amount }</strong></p>
        </article>
        <c:forEach var="book" items="${bookList}">
            <article class="row ">
                <p style="color: #1a1a1a" class="span1 " align="center"><c:out value="${ book.idbooks }"/></p>
                <p style="color: #1a1a1a" class="span2 "><c:out value="${ book.access }"/></p>
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