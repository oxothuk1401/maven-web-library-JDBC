<fmt:message bundle="${loc}" key="local.search" var="search" />
<fmt:message bundle="${loc}" key="local.authors" var="authors" />
<fmt:message bundle="${loc}" key="local.titles" var="titles" />
<fmt:message bundle="${loc}" key="local.dates" var="dates" />
<fmt:message bundle="${loc}" key="local.sorting" var="sorted" />
<fmt:message bundle="${loc}" key="local.searching" var="searching" />
<fmt:message bundle="${loc}" key="local.query" var="query" />



<div class="navbar-fixed-top col-md-offset-4 col-lg-6">
    <form action="Controller" method="post" class="navbar-form">
        <input type="hidden" name="command" value="search-book"/>
        <div class="form-group">
            <input class="form-control" name="searching"  placeholder="${query}" value="">
            <select class="form-control" name="sorted">
                <option selected value="author">${sorted}
                <option value="author">${authors}
                <option value="title">${titles}
                <option value="date">${dates}
            </select>
        </div>
        <button class="btn btn-default">${search}</button>
    </form>
</div>
