<fmt:message var="choseLanguage" bundle="${loc}" key="locale.settings.choselanguage"/>
<fmt:message var="logout" bundle="${loc}" key="locale.button.logout"/>
<fmt:message var="ruButton" bundle="${loc}" key="locale.locbutton.name.ru"/>
<fmt:message var="enButton" bundle="${loc}" key="locale.locbutton.name.en"/>
<fmt:message var="toMainPage" bundle="${loc}" key="locale.button.to.main.page"/>
<fmt:message bundle="${loc}" key="local.search" var="search" />
<fmt:message bundle="${loc}" key="local.authors" var="authors" />
<fmt:message bundle="${loc}" key="local.titles" var="titles" />
<fmt:message bundle="${loc}" key="local.dates" var="dates" />
<fmt:message bundle="${loc}" key="local.sorting" var="sorted" />
<fmt:message bundle="${loc}" key="local.searching" var="searching" />
<fmt:message bundle="${loc}" key="local.query" var="query" />

<div class="top">
    <div class="logo navbar-fixed-top">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="to-main-page"/>
            <input type="image" src="img/logo1.png">
        </form>
    </div>
    <div class="navbar-fixed-top">
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
</div>
<hr>