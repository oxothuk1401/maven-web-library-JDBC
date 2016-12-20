<fmt:message bundle="${loc}" key="local.search" var="search" />
<fmt:message bundle="${loc}" key="local.authors" var="authors" />
<fmt:message bundle="${loc}" key="local.titles" var="titles" />
<fmt:message bundle="${loc}" key="local.dates" var="dates" />
<fmt:message bundle="${loc}" key="local.sorting" var="sorted" />
<fmt:message bundle="${loc}" key="local.searching" var="searching" />
<fmt:message bundle="${loc}" key="local.query" var="query" />


<div class="search">
    <form action="Controller" method="post" class="navbar-form pull-left">
        <input type="hidden" name="command" value="search-book"/>
        <input type="submit" class="btn btn-info" value="${search}"/>
        <select name="sorted">
            <option selected value="author">${sorted}
            <option value="author">${authors}
            <option value="title">${titles}
            <option value="date">${dates}
        </select>
        <input type="search" class="span2 search-query " name="searching"  placeholder="${query}" value=""><br>
    </form>
    <c:out value="${requestScope.errorMessage}" />
 </div>

<hr>