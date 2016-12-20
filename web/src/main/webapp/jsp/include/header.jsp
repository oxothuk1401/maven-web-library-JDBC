<fmt:message var="choseLanguage" bundle="${loc}"
             key="locale.settings.choselanguage"/>
<fmt:message var="logout" bundle="${loc}" key="locale.button.logout"/>
<fmt:message var="ruButton" bundle="${loc}"
             key="locale.locbutton.name.ru"/>
<fmt:message var="enButton" bundle="${loc}"
             key="locale.locbutton.name.en"/>
<fmt:message var="toMainPage" bundle="${loc}"
             key="locale.button.to.main.page"/>
<div class="top">
    <div class="logo">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="to-main-page"/>
            <input type="image" src="img/logo1.png">
        </form>
    </div>
    <div class="personal">
        <c:if test="${sessionScope.userId != null}">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="logout"/>
                <input class="btn" type="submit" value="${logout }"/>
            </form>
        </c:if>
    </div>
    <div class="personal">${choseLanguage }</div>
    <div class="personal">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="localization"/>
            <input type="hidden" name="locale" value="ru"/>
            <input type="image" src="img/rus1.jpg">
        </form>
    </div>
    <div class="personal">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="localization"/>
            <input type="hidden" name="locale" value="en"/>
            <input type="image" src="img/en.jpg">
        </form>
    </div>
</div>
<hr>