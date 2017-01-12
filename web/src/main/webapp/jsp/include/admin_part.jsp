<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<fmt:message var="showUsers" bundle="${loc}" key="locale.show.users"/>
<fmt:message var="viewcatalog" bundle="${loc}" key="local.viewcatalog"/>
<fmt:message var="addbook" bundle="${loc}" key="local.addbook"/>
<fmt:message var="add" bundle="${loc}" key="local.add"/>
<fmt:message var="authorAdd" bundle="${loc}" key="local.authorAdd"/>
<fmt:message var="titleAdd" bundle="${loc}" key="local.titleAdd"/>
<fmt:message var="dateAdd" bundle="${loc}" key="local.dateAdd"/>
<fmt:message var="amountAdd" bundle="${loc}" key="local.amountAdd"/>
<fmt:message var="requiredFieldMessage" bundle="${loc}" key="locale.message.required.field"/>
<fmt:message var="successOperation" bundle="${loc}" key="locale.message.success.operation"/>
<fmt:message var="requiredFieldChar" bundle="${loc}" key="locale.message.required.character"/>
<fmt:message var="authorInfo" bundle="${loc}" key="locale.message.author"/>
<fmt:message var="titleInfo" bundle="${loc}" key="locale.message.title"/>
<fmt:message var="dateInfo" bundle="${loc}" key="locale.message.date"/>
<fmt:message var="amountInfo" bundle="${loc}" key="locale.message.amount"/>


<div>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="show-all-users"/>
        <input class="btn btn-primary" type="submit" value="${showUsers }"/>
    </form>
</div>
<div>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="show-all-books"/>
        <input class="btn btn-primary" type="submit" value="${viewcatalog }"/>
    </form>
</div>
<div>
    <input class="btn btn-info" id="hide" style="display:none" onclick="return false" type="button" value="${addbook}"/>
    <input class="btn btn-info" id="show" onclick="return false" type="button" value="${addbook}"/>
    <c:if test="${requestScope.successOperation}">
        <h1>${successOperation }</h1>
    </c:if>
    <c:choose>
    <c:when test="${not empty requestScope.wrongData}">
    <div id="panel" style="display: block;">
        </c:when>
        <c:otherwise>
        <div id="panel" style="display: none;">
            </c:otherwise>
            </c:choose>
        <form action="Controller" method="post" class="navbar-form">
            <input type="hidden" name="command" value="add-book"/>
            <c:if test="${not empty requestScope.wrongData}">
                <span class="error">${requestScope.wrongData }</span>
                <br>
            </c:if>
            <input class="form-control" type="text" name="authorAdd"
                   placeholder="${authorAdd }${requiredFieldChar }"
                   required pattern="[\s,.\wа-яА-Я]{2,50}"
                   value=""/> ${authorInfo}
            <br>
            <input class="form-control" type="text" name="titleAdd"
                   placeholder="${titleAdd }${requiredFieldChar }"
                   required pattern="[\s,.\wа-яА-Я]{4,50}"
                   value=""/> ${titleInfo}
            <br>
            <input class="form-control" type="number" name="dateAdd"
                   placeholder="${dateAdd }${requiredFieldChar }"
                   required pattern="[\d]{4,6}"
                   value=""/> ${dateInfo}
            <br>
            ${requiredFieldMessage }
            <br>
            <input class="btn btn-primary"
                   type="submit" value="${add }"/>
        </form>
    </div>
</div>
</div>