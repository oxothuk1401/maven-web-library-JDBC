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
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.11/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle var="loc" basename="localization.locale"/>
    <fmt:message var="loginTitle" bundle="${loc}" key="locale.title.login"/>
    <fmt:message var="greeting" bundle="${loc}" key="locale.greeting"/>
    <fmt:message var="invalidData" bundle="${loc}" key="locale.invalid.data"/>
    <fmt:message var="userField" bundle="${loc}" key="locale.user.field"/>
    <fmt:message var="passField" bundle="${loc}" key="locale.pass.field"/>
    <fmt:message var="loginButton" bundle="${loc}" key="locale.login.button"/>
    <fmt:message var="signUp" bundle="${loc}" key="locale.button.sign.up"/>
    <fmt:message var="requiredFieldChar" bundle="${loc}" key="locale.message.required.character"/>
    <fmt:message var="enterName" bundle="${loc}" key="locale.entername"/>
    <fmt:message var="nameInfo" bundle="${loc}" key="locale.message.name.info"/>
    <fmt:message var="enterLogin" bundle="${loc}" key="locale.user.field"/>
    <fmt:message var="loginInfo" bundle="${loc}" key="locale.message.login.info"/>
    <fmt:message var="enterPassword" bundle="${loc}" key="locale.pass.field"/>
    <fmt:message var="passInfo" bundle="${loc}" key="locale.message.pass.info"/>
    <fmt:message var="repeatPassword" bundle="${loc}" key="locale.repeatpassword"/>
    <fmt:message var="enterEmail" bundle="${loc}" key="locale.enteremail"/>
    <fmt:message var="emailInfo" bundle="${loc}" key="locale.message.email.info"/>
    <fmt:message var="send" bundle="${loc}" key="locale.button.send"/>
    <fmt:message var="requiredFieldMessage" bundle="${loc}" key="locale.message.required.field"/>
    <script src="/js/show_panel.js"></script>

    <title>${loginTitle }</title>
</head>
<body class="body">
<%@ include file="include/header.jsp" %>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="well col-lg-3 col-md-offset-4">
                <p class="text-center"> ${greeting }</p>
                <c:if test="${requestScope.invalidData}">
                    <span class="error text-center">${invalidData }</span>
                </c:if>
                <c:if test="${not empty requestScope.successOperation}">
                    <span style="color: green;">${successOperation }</span>
                    <br>
                </c:if>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="authorization"/>
                    <input class="form-control" type="text" name="login" value="" placeholder="${userField }"
                           required pattern="[a-zA-Z0-9]{4,20}"/>

                    <input class="form-control" type="password" name="password" value="" placeholder="${passField }"
                           required pattern="[a-zA-Z0-9]{4,20}"/> <br/>
                    <button class="btn btn-primary btn-block" type="submit" value="">
                        ${loginButton}
                    </button>
                </form>
                <input class="btn btn-info btn-block" id="hide" style="display:none" onclick="return false" type="button" value="${signUp}"/>
                <input class="btn btn-info  btn-block" id="show" onclick="return false" type="button" value="${signUp}"/>

            </div>
        </div>
    </div>
    <c:choose>
    <c:when test="${not empty requestScope.invalidRegistrData}">
    <div id="panel" style="display:block">
        </c:when>
        <c:otherwise>
        <div id="panel" style="display: none;">
            </c:otherwise>
            </c:choose>
            <form action="Controller" method="post" class="navbar-form">
                <input type="hidden" name="command" value="sign-up-user"/>
                <c:if test="${not empty requestScope.invalidRegistrData}">
                    <span class="error">${requestScope.invalidRegistrData }</span>
                    <br>
                </c:if>
                <input class="form-control" type="text" name="registrName" required
                       pattern="[a-zA-Zа-яёА-ЯЁ\s]{2,20}"
                       placeholder="${enterName }${requiredFieldChar }" value=""/> ${nameInfo }
                <br>
                <input class="form-control" type="text" name="registrLogin" required pattern="[a-zA-Z0-9]{4,20}"
                       placeholder="${enterLogin }${requiredFieldChar }" value=""/> ${loginInfo }
                <br>
                <input class="form-control" type="password" name="registrPass" required pattern="[a-zA-Z0-9]{4,20}"
                       placeholder="${enterPassword }${requiredFieldChar }" value=""/> ${passInfo }
                <br>
                <input class="form-control" type="password" name="registrRepeatPass" required
                       pattern="[a-zA-Z0-9]{4,20}"
                       placeholder="${repeatPassword }${requiredFieldChar }" value=""/>
                <br>
                <input class="form-control" type="text" name="registrEmail" required pattern=".+@.+"
                       placeholder="${enterEmail }${requiredFieldChar }" value=""/> ${emailInfo }
                <br>
                ${requiredFieldMessage }
                <br>
                <button class="btn btn-info" type="submit" value="">
                    ${send }
                </button>
            </form>
        </div>
        <%@ include file="include/footer.jsp" %>
    </div>
</body>
</html>